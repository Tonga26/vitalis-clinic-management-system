package main;

import model.HistoriaClinica;
import model.Paciente;
import service.HistoriaClinicaService;
import service.PacienteService;
import model.HistoriaClinica.GrupoSanguineo;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private PacienteService pacienteService;
    private HistoriaClinicaService hcService;

    public MenuHandler(Scanner scanner, PacienteService pacienteService, HistoriaClinicaService hcService) {
        this.scanner = scanner;
        this.pacienteService = pacienteService;
        this.hcService = hcService;
    }

    public void crearPaciente(){
        try {
            System.out.println("== ALTA DE NUEVO PACIENTE ==");
            String nombre = leerTexto("Nombre: ");
            String apellido = leerTexto("Apellido: ");
            String dni = leerTexto("DNI: ");
            String fechaTexto = leerTexto("Fecha Nacimiento (formato YYYY-MM-DD): ");
            LocalDate fechaNacimiento = LocalDate.parse(fechaTexto);

            System.out.println("== HISTORIA CLÍNICA (Obligatoria) ==");
            String nroHc = leerTexto("Número de historia clínica: ");
            String grupoTexto = leerTexto("Grupo sanguíneo (A+,A-,B+,B-,AB+,AB-,O+,O- o vacío): ");
            GrupoSanguineo grupo = (grupoTexto.isBlank()) ? null : GrupoSanguineo.fromDb(grupoTexto);
            String ant = leerTexto("Antecedentes (opcional): ");
            String med = leerTexto("Medicación actual (opcional): ");
            String obs = leerTexto("Observaciones (opcional): ");

            Paciente p = new Paciente();
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setDni(dni);
            p.setFechaNacimiento(fechaNacimiento);

            HistoriaClinica hc = new HistoriaClinica();
            hc.setNroHistoria(nroHc);
            hc.setGrupoSanguineo(grupo);
            hc.setAntecedentes(ant);
            hc.setMedicacionActual(med);
            hc.setObservaciones(obs);
            hc.setFechaApertura(LocalDate.now());

            p.setHistoriaClinica(hc);

            pacienteService.create(p);

            MenuDisplay.printSuccess("Paciente " + p.getNombre() + p.getApellido() + " " + " guardado con ID: " + p.getId());
        } catch (java.time.format.DateTimeParseException e) {
            MenuDisplay.printError("El formato de la fecha es incorrecto. Debe ser YYYY-MM-DD (ej: 1990-12-31)");
        } catch (IllegalArgumentException e) {
            MenuDisplay.printError("Dato inválido: " + e.getMessage());
        } catch (Exception e) {
            MenuDisplay.printError("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        String texto = scanner.nextLine();
        return texto.trim();
    }
}