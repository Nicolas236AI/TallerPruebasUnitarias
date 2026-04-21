package co.edu.uniquindio.poo.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Empresa {
    private String nombre;
    private ArrayList<Empleado> listaEmpleados;

    public Empresa() {
        this.listaEmpleados = new ArrayList<>();
    }

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.listaEmpleados = new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }


    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", listaEmpleados=" + listaEmpleados +
                '}';
    }

    public String registrarEmpleado(Empleado empleado){
        String respuesta="";
        Optional<Empleado>optionalEmpleado = buscarEmpleado(empleado.getDocumento());
        if(optionalEmpleado.isPresent()){
            Empleado empleadoEncontrado=optionalEmpleado.get();
            respuesta="el empleado ya existe";
        }else{
            listaEmpleados.add(empleado);
        }
        return respuesta;
    }

    private Optional<Empleado>buscarEmpleado(String documento){
        return listaEmpleados.stream().filter(empleado -> empleado.getDocumento().equals(documento)).findAny();
    }

    private String actualizarEmpleado(String documento, String nombre, int edad, float salarioBase, CategoriaEmpleado categoriaEmpleado, float descuentoSalud, float descuentoPension){
        String respuesta;
        Optional<Empleado> optionalEmpleado = buscarEmpleado(documento);
        if(optionalEmpleado.isPresent()){
            Empleado empleadoEncontrado=optionalEmpleado.get();
            empleadoEncontrado.setNombre(nombre);
            empleadoEncontrado.setEdad(edad);
            empleadoEncontrado.setDocumento(documento);
            empleadoEncontrado.setCategoriaEmpleado(categoriaEmpleado);
            empleadoEncontrado.setSalarioBase(salarioBase);
            empleadoEncontrado.setDescuentoSalud(descuentoSalud);
            empleadoEncontrado.setDescuentoPension(descuentoPension);

            respuesta ="Empleado actualizado exitosamente";

        }else{
            respuesta = "El empleado no existe";
        }
        return respuesta;
    }

    private String borrarEmpleado(String documento) {
        String respuesta="";
        Optional<Empleado> optionalEmpleado = buscarEmpleado(documento);
        if(optionalEmpleado.isPresent()){
            Empleado empleadoEncontrado=optionalEmpleado.get();
            listaEmpleados.remove(empleadoEncontrado);
            respuesta ="Empleado borrado exitosamente";

        }else{
            respuesta = "El empleado no existe";
        }
        return respuesta;
    }

    private List<Empleado>buscarEmpleadosPlanta(){
        return listaEmpleados.stream().filter(empleado -> empleado instanceof EmpleadoPlanta).collect(Collectors.toList());
    }

    public void mostrarTodos() {
        if (listaEmpleados.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay empleados registrados");
            return;
        }
        for (Empleado empleado : listaEmpleados) {
            empleado.mostrarInformacion();
        }
    }

    public Empleado buscarPorDocumento(String documento) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getDocumento().equals(documento)) {
                return empleado;
            }
        }
        return null;
    }
    public void calcularNominaTotal() {
        float total = 0;
        for (Empleado empleado : listaEmpleados) {
            total += empleado.calcularSalarioNeto();
        }
        javax.swing.JOptionPane.showMessageDialog(null, "Nomina total: " + total);
    }

    public Empleado obtenerMayorSalario() {
        if (listaEmpleados.isEmpty()) return null;

        Empleado mayor = listaEmpleados.get(0);
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getSalarioNeto() > mayor.getSalarioNeto()) {
                mayor = empleado;
            }
        }
        return mayor;
    }

    public void mostrarResumenesPago() {
        if (listaEmpleados.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
            return;
        }


        String reporte = "---- RESÚMENES DE PAGO: ----\n";

        for (Empleado empleado : listaEmpleados) {
            ResumenPago resumen = empleado.generarResumenPago();

            reporte = reporte + "Documento: " + resumen.documento() + "\n";
            reporte = reporte + "Nombre: " + resumen.nombre() + "\n";
            reporte = reporte + "Tipo de empleado: " + resumen.tipoEmpleado() + "\n";
            reporte = reporte + "Salario bruto: " + resumen.salarioBruto() + "\n";
            reporte = reporte + "Descuentos por salud y pension: " + resumen.descuentos() + "\n";
            reporte = reporte + "Salario neto: " + resumen.salarioNeto() + "\n";
            reporte = reporte + "----------------------------\n";
        }
        javax.swing.JOptionPane.showMessageDialog(null, reporte);
    }

    public void agregarEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
        javax.swing.JOptionPane.showMessageDialog(null, "Empleado registrado: " + empleado.nombre);
    }

    public List<Empleado> empleadosMayorSalario(float valor) {
        List<Empleado> Filtro = new ArrayList<>();
        for (Empleado emp : listaEmpleados) {
            if (emp.calcularSalarioNeto() > valor) {
                Filtro.add(emp);
            }
        }
        return Filtro;
    }
    public List<EmpleadoTemporal> obtenerTemporalesMasDe100Horas() {
        return listaEmpleados.stream()
                .filter(e -> e instanceof EmpleadoTemporal)
                .map(e -> (EmpleadoTemporal) e)
                .filter(t -> (t.getDiasTrabajados() * 8) > 100)
                .collect(Collectors.toList());
    }

    public void mostrarEmpleadoConMayorSalario() {
        if (listaEmpleados.isEmpty()) {
           javax.swing.JOptionPane.showMessageDialog(null,"no hay empleados en la lista");
            return;
        }

        Empleado mayor = listaEmpleados.get(0);

        for (Empleado emp : listaEmpleados) {
            if (emp.calcularSalarioNeto() > mayor.calcularSalarioNeto()) {
                mayor = emp;
            }
        }
        String mensaje = "El empleado con mayor salario NETO es:\n" +
                "Nombre:" + mayor.getNombre() + "\n" +
                "Salario Bruto:" + mayor.calcularSalarioBruto() + "\n" +
                "Salario Neto:" + mayor.calcularSalarioNeto();

        JOptionPane.showMessageDialog(null, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);

    }
}
