import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Records extends JDialog {
    private JButton modifyRecordsButton;
    private JButton viewAllRecordsButton;
    private JButton quitButton;
    private JTextField misInput;
    private JButton searchButton;
    private JPanel RecordPlane;
    private JTextPane ShowRecords;
    private JTable table;
    private JScrollPane scrollPane;

    public Records(User user) {
        // Create components
        modifyRecordsButton = new JButton("Modify Records");
        viewAllRecordsButton = new JButton("View All Records");
        quitButton = new JButton("Quit");
        misInput = new JTextField(10);
        searchButton = new JButton("Search");
        RecordPlane = new JPanel();
        table = new JTable();
        scrollPane = new JScrollPane(table);

        // Set layout for the main panel
        RecordPlane.setLayout(new BorderLayout());

        // Add action listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String misText = misInput.getText();
                if (misText == null || misText.isEmpty()) {
                    populateTable();
                } else {
                    int MIS = Integer.parseInt(misText);
                    searchRecord(MIS);
                }
                misInput.setText("");
            }
        });

        modifyRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement modify records functionality here
                dispose();
                StudentDB studentDB = new StudentDB(user);
                studentDB.setVisible(true);
            }
        });

        viewAllRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable(); // Populate the table with all records
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add components to the main panel
        RecordPlane.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(modifyRecordsButton);
        buttonsPanel.add(viewAllRecordsButton);
        buttonsPanel.add(quitButton);
        RecordPlane.add(buttonsPanel, BorderLayout.SOUTH);
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("MIS:"));
        searchPanel.add(misInput);
        searchPanel.add(searchButton);
        RecordPlane.add(searchPanel, BorderLayout.NORTH);

        setTitle("Records");
        setContentPane(RecordPlane);
        setMinimumSize(new Dimension(1000, 600));
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void populateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("MIS");
        model.addColumn("Name");
        model.addColumn("Batch");
        model.addColumn("Phone");
        model.addColumn("Email");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/JavaDB", "root", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students order by MIS ASC")) {

            while (resultSet.next()) {
                String mis = resultSet.getString("MIS");
                String name = resultSet.getString("name");
                String batch = resultSet.getString("batch");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                model.addRow(new Object[]{mis, name, batch, phone, email});
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        table.setModel(model);
    }

    private void searchRecord(int MIS) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("MIS");
        model.addColumn("Name");
        model.addColumn("Batch");
        model.addColumn("Phone");
        model.addColumn("Email");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/JavaDB", "root", "");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE MIS like ?")) {
            preparedStatement.setString(1, "%" + MIS + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String mis = resultSet.getString("MIS");
                String name = resultSet.getString("name");
                String batch = resultSet.getString("batch");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                model.addRow(new Object[]{mis, name, batch, phone, email});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setModel(model);
    }

    public static void main(String[] args) {
        Records dialog = new Records(new User());
        // By Default, we display all the records when the opens always

    }
}
