public class WalgreensInventory {
    private Map<String, Integer> medicationStock = new HashMap<>();

    public synchronized void deductStock(String medicationName, int quantity) {
        // Ensure only one thread can update the inventory at a time
        if (medicationStock.containsKey(medicationName)) {
            int currentStock = medicationStock.get(medicationName);
            if (currentStock >= quantity) {
                medicationStock.put(medicationName, currentStock - quantity);
            } else {
                throw new IllegalArgumentException("Insufficient stock for " + medicationName);
            }
        } else {
            throw new IllegalArgumentException("Medication " + medicationName + " not found");
        }
    }

    public synchronized void addStock(String medicationName, int quantity) {
        // Ensure only one thread can update the inventory at a time
        int currentStock = medicationStock.getOrDefault(medicationName, 0);
        medicationStock.put(medicationName, currentStock + quantity);
    }

    public synchronized int getStock(String medicationName) {
        // Ensure only one thread can access the inventory at a time
        return medicationStock.getOrDefault(medicationName, 0);
    }
}
