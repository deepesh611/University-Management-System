import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentDB extends JDialog {
    private JTextField tfMIS;
    private JTextField tfName;
    private JTextField tfBatch;
    private JTextField tfPhone;
    private JTextField tfEmail;
    private JButton editRecordButton;
    private JButton addRecordButton;
    private JButton viewRecordsButton;
    private JButton quitButton;
    private JPanel DBPlane;

    private User user;

    public StudentDB(User user) {
        this.user = user;

        addRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int MIS = Integer.parseInt(tfMIS.getText());
                String name = tfName.getText();
                String batch = tfBatch.getText();
                String phone = tfPhone.getText();
                String email = tfEmail.getText();

                try{
                    addRecord(MIS, name, batch, phone, email);
                }

                catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentDB.this,
                            "Record Already Exists...",
                            "", JOptionPane.ERROR_MESSAGE
                    );
                }

                tfBatch.setText("");
                tfEmail.setText("");
                tfMIS.setText("");
                tfName.setText("");
                tfPhone.setText("");
            }
        });

        editRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int MIS = Integer.parseInt(tfMIS.getText());
                String name = tfName.getText();
                String batch = tfBatch.getText();
                String phone = tfPhone.getText();
                String email = tfEmail.getText();

                try {
                    editRecord(MIS, name, batch, phone, email);
                }

                catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentDB.this,
                            "Record Does Not Exist...",
                            "", JOptionPane.ERROR_MESSAGE
                    );
                }
//                editRecord(MIS, name, batch, phone, email);

                tfBatch.setText("");
                tfEmail.setText("");
                tfMIS.setText("");
                tfName.setText("");
                tfPhone.setText("");
            }
        });

        viewRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the StudentDB dialog
                Records records = new Records(user); // Create an instance of the Records dialog
                records.setVisible(true); // Display the Records dialog

            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setTitle("Student Database");
        setContentPane(DBPlane);
        setMinimumSize(new Dimension(1000, 600));
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addRecord(int MIS, String name, String batch, String phone, String email) throws SQLException {
        final String DB_URL = "jdbc:mysql://localhost/JavaDB?";
        final String USER = "root";
        final String PASSWORD = "";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to the Database");

            String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, MIS);
                ps.setString(2, name);
                ps.setString(3, batch);
                ps.setString(4, phone);
                ps.setString(5, email);

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new record was inserted successfully.");
                    JOptionPane.showMessageDialog(StudentDB.this,
                            "Record Added Successfully...",
                            "Added", JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    throw new SQLException("Record Already Exists");
                }
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    private void editRecord(int MIS, String name, String batch, String phone, String email) {
        final String DB_URL = "jdbc:mysql://localhost/JavaDB?";
        final String USER = "root";
        final String PASSWORD = "";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to the Database");

            String sql = "UPDATE students SET name=?, batch=?, phone=?, email=? WHERE MIS=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, batch);
                ps.setString(3, phone);
                ps.setString(4, email);
                ps.setInt(5, MIS);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Record Updated Successfully!");
                    JOptionPane.showMessageDialog(StudentDB.this,
                            "Record Updated Successfully...",
                            "Updated", JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    System.out.println("Record Does Not Exist!");
                    JOptionPane.showMessageDialog(StudentDB.this,
                            "Record Does Not Exist...",
                            "Error", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // StudentDB studentDB = new StudentDB();
    }
}

