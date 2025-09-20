package helper;

import model.Product;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static boolean stringValidator(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean intValidator(String input) {
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error invalid input");
            return false;
        }
    }

    public static boolean doubleValidator(String input) {
        try {
            Double.parseDouble(input.trim());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error invalid input");
            return false;
        }
    }

    public static boolean productValidator(Product product) {
        if (product == null) return false;
        if (!stringValidator(product.getName())) return false;
        if (product.getPrice() <= 0) return false;
        return true;
    }

    public static boolean dateValidator(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
