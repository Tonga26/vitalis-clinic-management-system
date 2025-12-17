package main;

/**
 * Clase de utilidad responsable de la capa de presentación visual (Vista).
 * <p>
 * Su única responsabilidad es formatear y mostrar información en la consola.
 * Mantiene la interfaz de usuario desacoplada de la lógica de control y negocio.
 * </p>
 */
public class MenuDisplay {

    /**
     * Muestra el menú principal del sistema con un diseño estilizado.
     * Lista todas las operaciones disponibles para el usuario.
     */
    public static void mostrarMenuPrincipal() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║           SISTEMA DE GESTIÓN DE CLÍNICA            ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Crear Paciente (con Historia Clínica)           ║");
        System.out.println("║ 2. Listar todos los Pacientes                      ║");
        System.out.println("║ 3. Buscar Paciente por DNI                         ║");
        System.out.println("║ 4. Actualizar Paciente                             ║");
        System.out.println("║ 5. Actualizar Historia Clínica de un Paciente      ║");
        System.out.println("║ 6. Listar todas las Historias Clínicas             ║");
        System.out.println("║ 7. Eliminar Paciente (Baja Lógica)                 ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ 0. Salir                                           ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print(" ➤ Ingrese una opción: ");
    }

    /**
     * Muestra un mensaje de error formateado estándar.
     * @param msg El mensaje de error a mostrar.
     */
    public static void printError(String msg) {
        System.err.println(" ❌ ERROR: " + msg);
        System.out.println("-".repeat(60));
    }

    /**
     * Muestra un mensaje de éxito formateado estándar.
     * @param msg El mensaje de éxito a mostrar.
     */
    public static void printSuccess(String msg) {
        System.out.println(" ✅ " + msg);
        System.out.println("-".repeat(60));
    }
}