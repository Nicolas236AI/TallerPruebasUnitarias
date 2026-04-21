package co.edu.uniquindio.poo.model;

public class EmpleadoTemporal extends Empleado {
    private int diasTrabajados;
    private float valorDia;

    public EmpleadoTemporal(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoriaEmpleado, float descuentoSalud, float descuentoPension, int diasTrabajados, float valorDia) {
        super(nombre, documento, edad, salarioBase, categoriaEmpleado, descuentoSalud, descuentoPension);
        this.diasTrabajados = diasTrabajados;
        this.valorDia = valorDia;
        if(diasTrabajados<0){
            throw new IllegalArgumentException("los dias trbajados no pueden ser menores a cero");
        }
        if(valorDia<0){
            throw new IllegalArgumentException("el valor por dia no puede ser negativo");
        }


    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public float getValorDia() {
        return valorDia;
    }

    public void setValorDia(float valorDia) {
        this.valorDia = valorDia;
    }

    @Override
    public String toString() {
        return "EmpleadoTemporal{" +
                "diasTrabajados=" + diasTrabajados +
                ", valorDia=" + valorDia +
                '}';
    }

    @Override
    public float calcularSalarioBruto() {
 return getSalarioBase()+(diasTrabajados*valorDia)+ calcularBonificacionCategoria();

    }
    @Override
    public void mostrarInformacion() {
        String mensajeVentas = "---Empleado de Ventas ---\n" +
                "Dias trabajados por el empleado: " + diasTrabajados + "\n" +
                "Valor por día: " + valorDia;

        javax.swing.JOptionPane.showMessageDialog(null, mensajeVentas);
        super.mostrarInformacion();
    }
}
