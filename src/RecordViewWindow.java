import javax.swing.*;
import java.awt.*;

public class RecordViewWindow extends JFrame {

    public RecordViewWindow() {
        super("Record View");

        // Sample data for demonstration
        String[] columnNames = {"ID", "Name", "Batch", "Phone", "Email"};
        Object[][] data = {
                {1, "John Doe", "2022", "1234567890", "john@example.com"},
                {2, "Jane Smith", "2023", "9876543210", "jane@example.com"}
        };

        // Create a table with sample data
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the table to the content pane
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Set window size and behavior
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the RecordViewWindow
        SwingUtilities.invokeLater(() -> new RecordViewWindow());
    }
}
