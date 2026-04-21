package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;
import javax.swing.JOptionPane;

public class Aplicacion {
    public static Empresa empresa = new Empresa();

    public static void main(String[] args) {
        int opcion;
        do {
            String menu = "-----SISTEMA DE NÓMINA-----\n" +
                    "1. Agregar empleado de planta\n" +
                    "2. Agregar empleado de ventas\n" +
                    "3. Agregar empleado temporal\n" +
                    "4. Mostrar todos los empleados\n" +
                    "5. Buscar empleado por documento\n" +
                    "6. Empleado con mayor salario neto\n" +
                    "7. Mostrar nómina total\n" +
                    "8. Mostrar resumen de pagos\n" +
                    "9. Salir\n" +
                    "Que accion desea realizar?: ";

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                opcion = 9;
            } else {
                opcion = Integer.parseInt(input);
            }

            switch (opcion) {
                case 1 -> agregarPlanta();
                case 2 -> agregarVentas();
                case 3 -> agregarTemporal();
                case 4 -> empresa.mostrarTodos();
                case 5 -> {
                    String docBusqueda = JOptionPane.showInputDialog("Documento:");
                    empresa.buscarPorDocumento(docBusqueda);
                }
                case 6 -> empresa.mostrarEmpleadoConMayorSalario();
                case 7 -> empresa.calcularNominaTotal();
                case 8 -> empresa.mostrarResumenesPago();
                case 9 -> JOptionPane.showMessageDialog(null, "Gracias");
                default -> JOptionPane.showMessageDialog(null, "Opción no valida.");
            }
        } while (opcion != 9);
    }

    static void agregarPlanta() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String doc = JOptionPane.showInputDialog("Documento:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
            float salario = Float.parseFloat(JOptionPane.showInputDialog("Salario base:"));

            CategoriaEmpleado cat = CategoriaEmpleado.valueOf(
                    JOptionPane.showInputDialog("Categoría (JUNIOR/SEMI_SENIOR/SENIOR):").toUpperCase()
            );

            float salud = Float.parseFloat(JOptionPane.showInputDialog("Descuento salud (%):"));
            float pension = Float.parseFloat(JOptionPane.showInputDialog("Descuento pensión (%):"));
            String cargo = JOptionPane.showInputDialog("Cargo:");
            int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas extra:"));
            float vHora = Float.parseFloat(JOptionPane.showInputDialog("Valor hora extra:"));
            float auxilio = Float.parseFloat(JOptionPane.showInputDialog("Auxilio transporte:"));

            empresa.agregarEmpleado(new EmpleadoPlanta(
                    nombre, doc, edad, salario, cat,
                    salud, pension, cargo, horas, vHora, auxilio
            ));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    static void agregarVentas() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String doc = JOptionPane.showInputDialog("Documento:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
            float salario = Float.parseFloat(JOptionPane.showInputDialog("Salario base:"));

            CategoriaEmpleado cat = CategoriaEmpleado.valueOf(
                    JOptionPane.showInputDialog("Categoría (JUNIOR/SEMI_SENIOR/SENIOR):").toUpperCase()
            );

            float salud = Float.parseFloat(JOptionPane.showInputDialog("Descuento salud (%):"));
            float pension = Float.parseFloat(JOptionPane.showInputDialog("Descuento pensión (%):"));
            float ventas = Float.parseFloat(JOptionPane.showInputDialog("Total ventas:"));
            float comision = Float.parseFloat(JOptionPane.showInputDialog("% comisión:"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    static void agregarTemporal() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String doc = JOptionPane.showInputDialog("Documento:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
            float salario = Float.parseFloat(JOptionPane.showInputDialog("Salario base:"));

            CategoriaEmpleado cat = CategoriaEmpleado.valueOf(
                    JOptionPane.showInputDialog("Categoría (JUNIOR/SEMI_SENIOR/SENIOR):").toUpperCase()
            );

            float salud = Float.parseFloat(JOptionPane.showInputDialog("Descuento salud (%):"));
            float pension = Float.parseFloat(JOptionPane.showInputDialog("Descuento pensión (%):"));
            int dias = Integer.parseInt(JOptionPane.showInputDialog("Días trabajados:"));
            float vDia = Float.parseFloat(JOptionPane.showInputDialog("Valor por día:"));

            empresa.agregarEmpleado(new EmpleadoTemporal(
                    nombre, doc, edad, salario, cat,
                    salud, pension, dias, vDia
            ));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}