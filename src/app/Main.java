package app;

import static controller.MenuController.showMenu;
import controller.MenuController;

public class Main {
    public static void main(String[] args) {
        MenuController.loadTestData();  // Cargar datos de prueba
        showMenu();                     // Mostrar menú
    }
}
