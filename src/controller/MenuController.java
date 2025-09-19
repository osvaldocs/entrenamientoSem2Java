package controller;

import javax.swing.*;

public class MenuController     {

    public static void showMenu() {
        String[] opciones = {"Add new product", "List inventory", "Buy product", "Order by price", "Search product by name", "Check Out", "Exit program"};
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Select an option:",
                    "Menú app",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0: // Add product
                    Product.producInput();
                    break;
                case 1: // List inventory
                    Product.showProducts();
                    break;
                case 2: // Buy product
                    String[] disponibles = Product.getAvailableProducts();
                    if (disponibles.length == 0) {
                        JOptionPane.showMessageDialog(null, "No products available.");
                        break;
                    }

                    String seleccion = (String) JOptionPane.showInputDialog(
                            null,
                            "Select a product:",
                            "Buy product",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            disponibles,
                            disponibles[0]
                    );
                    if (seleccion == null) break;
                    String productName = seleccion.split(" \\(")[0];

                    String qtyStr = JOptionPane.showInputDialog("Quantity:");
                    int qty;
                    try {
                        qty = Integer.parseInt(qtyStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity.");
                        break;
                    }

                    if (CalculatorService.buyProduct(productName, qty)) {
                        JOptionPane.showMessageDialog(null, "Added to cart!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough stock.");
                    }
                    break;

                case 3: // Order by price
                    String resultado = Product.getCheapestAndMostExpensive();
                    JOptionPane.showMessageDialog(null, resultado);
                    break;

                case 4: // Search product by name
                    String query = JOptionPane.showInputDialog("Enter product name (or part of it):");
                    if (query == null) break; // cancel
                    String results = Product.searchProductsByName(query);
                    JOptionPane.showMessageDialog(null, results);
                    break;

                case 5: // Check Out
                    String ticket = CalculatorService.generateTicket();
                    JOptionPane.showMessageDialog(null, ticket);
                    CalculatorService.clearCart();
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
}
