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

public class Main {
    public static void main(String[] args) {
        KitchenManagementSystem kitchenSystem = new KitchenManagementSystem();

        // Adding stock
        kitchenSystem.addStock("Flour", 20);
        kitchenSystem.addStock("Cooking oil", 5);
        kitchenSystem.addStock("Wheat", 15);
        kitchenSystem.addStock("Potatoes", 30);

        // Evaluating stock
        kitchenSystem.evaluateStock();

        // Removing stock
        kitchenSystem.removeStock("Flour", 10);
        kitchenSystem.removeStock("Cooking oil", 5);

        // Evaluating stock after removal
        kitchenSystem.evaluateStock();

        // Getting stock quantity
        int orangesQuantity = kitchenSystem.getStockQuantity("Wheat");
        System.out.println("Quantity of Wheat: " + orangesQuantity);
    }
}
