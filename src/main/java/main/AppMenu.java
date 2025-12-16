package main;

import service.HistoriaClinicaService;
import service.PacienteService;
import service.impl.HistoriaClinicaServiceImpl;
import service.impl.PacienteServiceImpl;

import java.util.Scanner;

public class AppMenu {
    private Scanner scanner;
    private MenuHandler menuHandler;

    public AppMenu() {
        this.scanner = new Scanner(System.in);
        PacienteService pService = new PacienteServiceImpl();
        HistoriaClinicaService hService = new HistoriaClinicaServiceImpl();
        this.menuHandler = new MenuHandler(scanner, pService, hService);
    }

    public void start() {
        boolean running = true;
        while (running) {
            try {
                MenuDisplay.mostrarMenuPrincipal();
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        menuHandler.crearPaciente();
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        running = false;
                        break;
                    default:
                        System.out.println("⚠ Opción inválida, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                MenuDisplay.printError("Debe ingresar un número válido.");
            } catch (Exception e) {
                MenuDisplay.printError("Error inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }
}