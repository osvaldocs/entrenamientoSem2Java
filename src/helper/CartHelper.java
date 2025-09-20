package helper;

import interfaces.ProductService;


import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CartHelper {
    private static Map<String, Integer> cart = new HashMap<>();

    // Agregar producto al carrito
    public static void addToCart(String productName, int quantity) {
        if (cart.containsKey(productName)) {
            cart.put(productName, cart.get(productName) + quantity);
        } else {
            cart.put(productName, quantity);
        }
    }

    // Remover producto del carrito
    public static void removeFromCart(String productName) {
        cart.remove(productName);
    }

    // Mostrar contenido del carrito
    public static void showCart(ProductService service) {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("SHOPPING CART:\n");
        mensaje.append("----------------------------------------\n");
        double total = 0;

        for (Map.Entry<String, Integer> item : cart.entrySet()) {
            String productName = item.getKey();
            int quantity = item.getValue();
            double price = service.getProductPrice(productName);
            double itemTotal = price * quantity;
            total += itemTotal;
            
            mensaje.append(String.format("%-15s x%2d  $%10.2f\n", 
                          productName, quantity, itemTotal));
        }

        mensaje.append("----------------------------------------\n");
        mensaje.append(String.format("TOTAL:              $%10.2f\n", total));
        mensaje.append("Total items: ").append(getTotalItems());
        
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    // Obtener total de items en el carrito
    public static int getTotalItems() {
        int total = 0;
        for (int quantity : cart.values()) {
            total += quantity;
        }
        return total;
    }

    // Limpiar carrito
    public static void clearCart() {
        cart.clear();
    }

    // Verificar si el carrito está vacío
    public static boolean isEmpty() {
        return cart.isEmpty();
    }

    // Checkout - procesar compra
    public static void checkout(ProductService service) {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty.");
            return;
        }

        // Mostrar resumen
        showCart(service);

        // Confirmar compra
        int option = JOptionPane.showConfirmDialog(
            null,
            "Do you want to proceed with the purchase?",
            "Checkout",
            JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            // Generar factura
            String factura = generateInvoice(service);
            
            // Reducir stock del inventario
            for (Map.Entry<String, Integer> item : cart.entrySet()) {
                String productName = item.getKey();
                int quantity = item.getValue();
                service.reduceStock(productName, quantity);
            }
            
            // Mostrar factura
            JOptionPane.showMessageDialog(null, factura);
            
            // Limpiar carrito
            clearCart();
        }
    }

    // Generar factura profesional
    private static String generateInvoice(ProductService service) {
        StringBuilder factura = new StringBuilder();
        
        factura.append("========================================\n");
        factura.append("           STORE INVOICE\n");
        factura.append("========================================\n");
        factura.append("Date: ").append(LocalDate.now()).append("\n");
        factura.append("Time: ").append(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))).append("\n\n");
        
        factura.append("ITEMS PURCHASED:\n");
        factura.append("----------------------------------------\n");
        
        double subtotal = 0;
        for (Map.Entry<String, Integer> item : cart.entrySet()) {
            String productName = item.getKey();
            int quantity = item.getValue();
            double price = service.getProductPrice(productName);
            double itemTotal = price * quantity;
            subtotal += itemTotal;
            
            factura.append(String.format("%-20s x%2d  $%10.2f\n", 
                          productName, quantity, itemTotal));
        }
        
        factura.append("----------------------------------------\n");
        factura.append(String.format("SUBTOTAL:                 $%10.2f\n", subtotal));
        factura.append(String.format("TAX (19%%):                $%10.2f\n", subtotal * 0.19));
        factura.append(String.format("TOTAL:                    $%10.2f\n", subtotal * 1.19));
        factura.append("========================================\n");
        factura.append("        THANK YOU FOR YOUR PURCHASE!\n");
        factura.append("========================================");
        
        return factura.toString();
    }
}
