package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();
    private HashMap<String, Integer> stock = new HashMap<>();
    private ArrayList<Double> prices = new ArrayList<>(); // lista de precios

    // Agregar producto
    public void addProduct(Product product, int quantity) {
        products.add(product);
        stock.put(product.getName(), quantity);

        // Agregar precio directamente a la lista
        prices.add(product.getPrice());
    }

    // Obtener productos
    public ArrayList<Product> getProducts() {
        return products;
    }

    // Obtener stock por nombre
    public int getStock(String productName) {
        return stock.getOrDefault(productName, 0);
    }

    // Modificar stock
    public void updateStock(String productName, int quantity) {
        if (stock.containsKey(productName)) {
            stock.put(productName, quantity);
        }
    }

    // Obtener lista de precios
    public ArrayList<Double> getPrices() {
        return prices;
    }
}
