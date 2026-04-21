package co.edu.uniquindio.poo.model;

import javax.xml.transform.stream.StreamSource;

public class EmpleadoPlanta extends Empleado{
    private String cargo;
    private int horasExtra;
    private float valorHoraExtra;
    private float auxilioTransporte;

    public EmpleadoPlanta(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoriaEmpleado, float descuentoSalud, float descuentoPension, String cargo, int horasExtra, float valorHoraExtra, float auxilioTransporte) {
        super(nombre, documento, edad, salarioBase, categoriaEmpleado, descuentoSalud, descuentoPension);
        this.cargo = cargo;
        this.horasExtra = horasExtra;
        this.valorHoraExtra = valorHoraExtra;
        this.auxilioTransporte = auxilioTransporte;
        if(horasExtra<0){
            throw new IllegalArgumentException("las horas extras no pueden ser negativas");
        }
        if (valorHoraExtra<0){
            throw new IllegalArgumentException("el valor de la hora extra no puede ser negativo");
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

    public float getValorHoraExtra() {
        return valorHoraExtra;
    }

    public void setValorHoraExtra(float valorHoraExtra) {
        this.valorHoraExtra = valorHoraExtra;
    }

    public float getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(float auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    @Override
    public String toString() {
        return "EmpleadoPlanta{" +
                "cargo='" + cargo + '\'' +
                ", horasExtra=" + horasExtra +
                ", valorHoraExtra=" + valorHoraExtra +
                ", auxilioTransporte=" + auxilioTransporte +
                '}';
    }

    @Override
    public float calcularSalarioBruto() {
    return salarioBase+calcularBonificacionCategoria()+(horasExtra*valorHoraExtra)+auxilioTransporte;

    }
    @Override
    public void mostrarInformacion() {
        String mensajePlanta = "-----Empleado de Planta:-----\n" +
                "cargo del empleado de planta: " + cargo + "\n" +
                "horas extra: " + horasExtra;

        javax.swing.JOptionPane.showMessageDialog(null, mensajePlanta);
        super.mostrarInformacion();
    }
}
