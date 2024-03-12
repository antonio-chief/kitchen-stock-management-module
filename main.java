import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class KitchenManagementSystem {
    private Map<String, Integer> stock;

    public KitchenManagementSystem() {
        stock = new HashMap<>();
    }

    public void addStock(String item, int quantity) {
        if (stock.containsKey(item)) {
            int currentQuantity = stock.get(item);
            stock.put(item, currentQuantity + quantity);
        } else {
            stock.put(item, quantity);
        }
    }

    public void removeStock(String item, int quantity) {
        if (stock.containsKey(item)) {
            int currentQuantity = stock.get(item);
            int updatedQuantity = currentQuantity - quantity;
            if (updatedQuantity <= 0) {
                stock.remove(item);
            } else {
                stock.put(item, updatedQuantity);
            }
        }
    }

    public int getStockQuantity(String item) {
        return stock.getOrDefault(item, 0);
    }

    public void evaluateStock() {
        System.out.println("Stock Evaluation:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            if (quantity == 0) {
                System.out.println(item + ": Out of stock");
            } else if (quantity < 10) {
                System.out.println(item + ": Low stock");
            } else {
                System.out.println(item + ": Sufficient stock");
            }
        }
    }
}

public class Main extends JFrame {
    private KitchenManagementSystem kitchenSystem;
    private JTextField itemField, quantityField;
    private JTextArea outputArea;

    public Main() {
        kitchenSystem = new KitchenManagementSystem();
        setTitle("Kitchen Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.setVisible(true);
            }
        });
    }
}
