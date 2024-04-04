import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class KitchenManagementApplet extends JApplet {
    private KitchenManagementSystem kitchenSystem;
    private JTextField itemField, quantityField;
    private JTextArea outputArea;

    public void init() {
        kitchenSystem = new KitchenManagementSystem();
        setSize(400, 300);
        setLayout(new GridLayout(3, 1));

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel itemLabel = new JLabel("Item:");
        itemField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);
        inputPanel.add(itemLabel);
        inputPanel.add(itemField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        add(inputPanel);

        JButton addButton = new JButton("Add Stock");
        JButton removeButton = new JButton("Remove Stock");
        JButton evaluateButton = new JButton("Evaluate Stock");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(evaluateButton);
        add(buttonPanel);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = itemField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                kitchenSystem.addStock(item, quantity);
                outputArea.append("Added " + quantity + " of " + item + " to stock.\n");
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = itemField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                kitchenSystem.removeStock(item, quantity);
                outputArea.append("Removed " + quantity + " of " + item + " from stock.\n");
            }
        });

        evaluateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                kitchenSystem.evaluateStock();
            }
        });
    }
}
