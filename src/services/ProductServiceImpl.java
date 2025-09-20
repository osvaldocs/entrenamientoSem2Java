package services;

import helper.InputValidator;
import interfaces.ProductService;
import model.Appliance;
import model.Food;
import model.Product;
import helper.Inventory;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductServiceImpl implements ProductService {

    private Inventory inventory = new Inventory();
    

    private Product searchByName(String productName) {
        for (Product product : inventory.getProducts()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;  // Lo encontró
            }
        }
        return null;  // No lo encontró
    }
    
    @Override
    public void addProduct(Product product, int quantity) {
        // 1. Validar producto
        if (!InputValidator.productValidator(product)) {
            JOptionPane.showMessageDialog(null, "Invalid input");
            return;
        }

        // 2. Validar cantidad
        if (quantity < 0) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be negative");
            return;
        }

        // 3. Verificar duplicado usando searchByName
        Product existingProduct = searchByName(product.getName());
        if (existingProduct != null) {
            int currentStock = inventory.getStock(product.getName());
            int option = JOptionPane.showConfirmDialog(
                null,
                "Product '" + product.getName() + "' already exists with " + currentStock + " units. Add more stock?",
                "Product exists",
                JOptionPane.YES_NO_OPTION
            );
            
            if (option == JOptionPane.YES_OPTION) {
                int newStock = currentStock + quantity;
                inventory.updateStock(product.getName(), newStock);
                JOptionPane.showMessageDialog(null, "Stock updated! New stock: " + newStock);
            }
            return;
        }

        // 4. Agregar al inventario (solo si no existe)
        inventory.addProduct(product, quantity);

        // 5. Confirmación
        JOptionPane.showMessageDialog(null, "Product correctly added");
    }



    @Override
    public void listProducts() {
        if (inventory.getProducts().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products in inventory.");
            return;
        }
        
        StringBuilder allProducts = new StringBuilder("INVENTORY:\n\n");
        for (Product product : inventory.getProducts()) {
            int stock = inventory.getStock(product.getName());
            allProducts.append(product.getDescription())
                      .append("\nStock: ")
                      .append(stock)
                      .append("\n\n");
        }
        
        JOptionPane.showMessageDialog(null, allProducts.toString());
    }

    @Override
    public boolean productExists(String productName) {
        return searchByName(productName) != null;
    }

    @Override
    public int getStock(String productName) {
        return inventory.getStock(productName);
    }

    @Override
    public String[] getAvailableProducts() {
        ArrayList<Product> products = inventory.getProducts();
        if (products.isEmpty()) {
            return new String[0];  // array vacío
        }
        
        String[] disponibles = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int stock = inventory.getStock(product.getName());
            // Formato: "Apple - $3500.0 (Stock: 25)"
            disponibles[i] = product.getName() + " - $" + product.getPrice() + " (Stock: " + stock + ")";
        }
        return disponibles;
    }

    @Override
    public double getProductPrice(String productName) {
        Product product = searchByName(productName);
        if (product != null) {
            return product.getPrice();
        }
        return 0.0;
    }

    @Override
    public void reduceStock(String productName, int quantity) {
        int currentStock = inventory.getStock(productName);
        if (currentStock >= quantity) {
            int newStock = currentStock - quantity;
            inventory.updateStock(productName, newStock);
        }
    }

    @Override
    public void orderProductsByPrice() {
        ArrayList<Product> products = inventory.getProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products in inventory.");
            return;
        }

        // Ordenar productos por precio (menor a mayor)
        products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));

        StringBuilder mensaje = new StringBuilder("PRODUCTS ORDERED BY PRICE:\n\n");
        for (Product product : products) {
            int stock = inventory.getStock(product.getName());
            mensaje.append(product.getDescription())
                  .append("\nStock: ")
                  .append(stock)
                  .append("\n\n");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    @Override
    public void searchProductByName(String productName) {
        Product product = searchByName(productName);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "Product '" + productName + "' not found.");
            return;
        }

        int stock = inventory.getStock(product.getName());
        String mensaje = "PRODUCT FOUND:\n\n" +
                        product.getDescription() + 
                        "\nStock: " + stock;
        
        JOptionPane.showMessageDialog(null, mensaje);
    }



}
