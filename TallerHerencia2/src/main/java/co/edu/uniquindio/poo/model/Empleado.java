package co.edu.uniquindio.poo.model;

import javax.swing.*;

import static co.edu.uniquindio.poo.model.CategoriaEmpleado.*;

public abstract class Empleado {
    protected String nombre;
    protected String documento;
    protected int edad;
    protected float salarioBase;
protected CategoriaEmpleado categoriaEmpleado;
protected float descuentoSalud;
protected float descuentoPension;
protected Empresa ownedByEmpresa;


    public Empleado(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoriaEmpleado, float descuentoSalud, float descuentoPension) {
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.salarioBase = salarioBase;
        this.categoriaEmpleado = categoriaEmpleado;
        this.descuentoSalud = descuentoSalud;
        this.descuentoPension = descuentoPension;
        if(salarioBase<0){
            throw new IllegalArgumentException("el salario base no puede ser menor a cero");

        }
        if(descuentoPension<0|| descuentoPension>100){
            throw new IllegalArgumentException("el descuento de pension debe estar entre cero y 100");

        }
        if(descuentoSalud<0|| descuentoSalud>100){
            throw new IllegalArgumentException("el descuento de salud debe estar entre cero y 100");

        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public CategoriaEmpleado getCategoriaEmpleado() {
        return categoriaEmpleado;
    }

    public void setCategoriaEmpleado(CategoriaEmpleado categoriaEmpleado) {
        this.categoriaEmpleado = categoriaEmpleado;
    }

    public float getDescuentoSalud() {
        return descuentoSalud;
    }

    public void setDescuentoSalud(float descuentoSalud) {
        this.descuentoSalud = descuentoSalud;
    }

    public float getDescuentoPension() {
        return descuentoPension;
    }

    public void setDescuentoPension(float descuentoPension) {
        this.descuentoPension = descuentoPension;
    }

    public float getSalarioNeto() {
        return calcularSalarioNeto();
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", edad=" + edad +
                ", salarioBase=" + salarioBase +
                ", categoriaEmpleado=" + categoriaEmpleado +
                ", descuentoSalud=" + descuentoSalud +
                ", descuentoPension=" + descuentoPension +
                '}';
    }
    public abstract float calcularSalarioBruto();
    public float calcularBonificacionCategoria(){
        return switch(categoriaEmpleado){
            case JUNIOR -> salarioBase *0.05f;
            case SEMI_SENIOR  -> salarioBase * 0.10f;
            case SENIOR -> salarioBase * 0.15f;
        };
    }
    public float calcularDescuento(){
        float bruto=calcularSalarioBruto();
        return bruto * (descuentoPension/100)+ bruto* (descuentoSalud/100);
    }
    public float calcularSalarioNeto(){
        return calcularSalarioBruto()-calcularDescuento();
    }
    public void mostrarInformacion() {
        String mensaje = "nombre: " + nombre + "\n" +
                "documento: " + documento + "\n" +
                "edad: " + edad + "\n" +
                "categoria del empleado: " + categoriaEmpleado + "\n" +
                "salario base: " + salarioBase + "\n" +
                "descuentos: " + calcularDescuento() + "\n" +
                "salarioBruto: " + calcularSalarioBruto() + "\n" +
                "salario neto: " + calcularSalarioNeto();

        javax.swing.JOptionPane.showMessageDialog(null, mensaje);
    }
    public ResumenPago generarResumenPago(){
        return new ResumenPago(documento,nombre, categoriaEmpleado.toString(),calcularSalarioBruto(),calcularDescuento(),calcularSalarioNeto());

    }

}
