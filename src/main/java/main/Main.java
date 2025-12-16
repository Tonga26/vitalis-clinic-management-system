package main;

/**
 * Punto de entrada de la aplicación (Entry Point).
 * <p>
 * Solo se encarga de instanciar la clase principal de la aplicación {@link AppMenu}
 * y dar la orden de inicio.
 * </p>
 */
public class Main {
    public static void main(String[] args) {
        new AppMenu().start();
    }
}