package controller;

import helper.CartHelper;
import helper.InputValidator;
import interfaces.ProductService;
import model.Appliance;
import model.Food;
import model.Product;
import services.ProductServiceImpl;

import javax.swing.*;
import java.time.LocalDate;

public class MenuController     {

    private static ProductService service = new ProductServiceImpl();
    public static void showMenu() {
        String[] opciones = {"Add new product", "List inventory", "Buy product", "Order by price", "Search product by name", "Check Out", "Exit program"};
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Select an option:",
                    "Store app",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0: // Add product
                    addNewProduct();
                    break;

                case 1: // List inventory
                    service.listProducts();
                    break;

                case 2: // Buy product
                    buyProduct();
                    break;

                case 3: // Order by price
                    orderByPrice();
                    break;

                case 4: // Search product by name
                    searchProduct();
                    break;

                case 5: // Check Out
                    CartHelper.checkout(service);
                    break;

                case 6: // Salir
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "See you!.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
                    break;
            }

        } while (opcion != 6 && opcion != JOptionPane.CLOSED_OPTION);
    }

    // Método para cargar datos de prueba
    public static void loadTestData() {
        Product apple = new Food(3500, "Apple", LocalDate.of(2025, 10, 15));
        Product milk = new Food(4200, "Milk", LocalDate.of(2025, 9, 30));
        Product television = new Appliance(1850000, "Television");
        
        service.addProduct(apple, 25);
        service.addProduct(milk, 18);
        service.addProduct(television, 8);
    }

    private static void addNewProduct() {
        // 1. Pedir tipo de producto
        String[] options = {"Food", "Appliance"};
        String type = (String) JOptionPane.showInputDialog(
                null,
                "Select product type:",
                "Product Type",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (type == null) return; // cancelado

        // 2. Pedir nombre
        String name = JOptionPane.showInputDialog("Enter product name:");
        if (!InputValidator.stringValidator(name)) {
            JOptionPane.showMessageDialog(null, "Invalid name");
            return;
        }

        // 3. Pedir precio
        String priceStr = JOptionPane.showInputDialog("Enter price:");
        if (!InputValidator.doubleValidator(priceStr)) {
            JOptionPane.showMessageDialog(null, "Invalid price");
            return;
        }
        double price = Double.parseDouble(priceStr);

        // 4. Pedir cantidad
        String quantityStr = JOptionPane.showInputDialog("Enter quantity:");
        if (!InputValidator.intValidator(quantityStr)) {
            JOptionPane.showMessageDialog(null, "Invalid quantity");
            return;
        }
        int quantity = Integer.parseInt(quantityStr);


        // 5. Crear producto
        Product product;
        if (type.equals("Food")) {
            String dateStr = JOptionPane.showInputDialog("Enter expiration date (yyyy-MM-dd):");
            if (dateStr == null) {
                return; // cancela la operación y vuelve al menú
            }
            if (!InputValidator.dateValidator(dateStr)) {
                JOptionPane.showMessageDialog(null, "Invalid date format");
                return;
            }
            LocalDate expirationDate = LocalDate.parse(dateStr);
            product = new Food(price, name, expirationDate);

        } else {
            product = new Appliance(price, name);
        }

        // 6. Llamar al Service
        service.addProduct(product, quantity);
    }

    private static void buyProduct() {
        // 1. Obtener productos disponibles
        String[] disponibles = service.getAvailableProducts();
        if (disponibles.length == 0) {
            JOptionPane.showMessageDialog(null, "No products available.");
            return;
        }

        // 2. Mostrar dropdown selector
        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "Select a product:",
            "Buy product",
            JOptionPane.PLAIN_MESSAGE,
            null,
            disponibles,
            disponibles[0]
        );
        if (seleccion == null) return; // cancelado
        
        // 3. Extraer solo el nombre del producto
        String productName = seleccion.split(" - \\$")[0];  // extrae "Apple" de "Apple - $3500.0 (Stock: 25)"
        
        // 4. Pedir cantidad
        String qtyStr = JOptionPane.showInputDialog("Enter quantity:");
        if (!InputValidator.intValidator(qtyStr)) {
            JOptionPane.showMessageDialog(null, "Invalid quantity.");
            return;
        }
        int quantity = Integer.parseInt(qtyStr);
        
        // 5. Verificar stock
        int currentStock = service.getStock(productName);
        if (quantity > currentStock) {
            JOptionPane.showMessageDialog(null, "Not enough stock. Available: " + currentStock);
            return;
        }
        
        // 6. Agregar al carrito
        CartHelper.addToCart(productName, quantity);
        JOptionPane.showMessageDialog(null, "Added to cart: " + quantity + " x " + productName);
    }

    private static void orderByPrice() {
        service.orderProductsByPrice();
    }

    private static void searchProduct() {
        String productName = JOptionPane.showInputDialog("Enter product name to search:");
        if (!InputValidator.stringValidator(productName)) {
            JOptionPane.showMessageDialog(null, "Invalid product name.");
            return;
        }
        service.searchProductByName(productName);
    }



}
