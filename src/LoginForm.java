import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel LoginPanel;

    public LoginForm(JFrame parent) {
        super(parent);

        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = String.valueOf(tfPassword.getPassword());

                User user = getAuthenticatedUser(username, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Login successful. \nWelcome " + user.name + "!",
                            "Login", JOptionPane.INFORMATION_MESSAGE
                    );
                    dispose();

                    SwingUtilities.invokeLater(() -> {
                        StudentDB studentDB = new StudentDB(user);
                        studentDB.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Invalid Username or Password",
                            "Login", JOptionPane.ERROR_MESSAGE
                    );
                    tfPassword.setText("");
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setTitle("Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(550, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private User getAuthenticatedUser(String username, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/JavaDB?";
        final String USER = "root";
        final String PASSWORD = "";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to the database");

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.name = rs.getString("username");
                    user.password = rs.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        LoginForm dialog = new LoginForm(null);
    }
}
