package co.edu.uniquindio.poo.model;

public class EmpleadoVentas extends Empleado{
    private float totalVentas;
    private float porcentajeComision;
    private CategoriaEmpleado categoriaEmpleado;


    public EmpleadoVentas(String nombre,String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float salud, float pension, float totalVentas, float porcentajeComision,Empresa ownedByEmpresa) {
        super(nombre, documento, edad, salarioBase, categoria, salud, pension);

        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
        this.ownedByEmpresa=ownedByEmpresa;
        this.totalVentas=totalVentas;
        this.porcentajeComision=porcentajeComision;

        if(porcentajeComision<0||porcentajeComision>100){
            throw new IllegalArgumentException("el porcentaje de comision debe ser de cero a cien");
        }
    }

    public float getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(float totalVentas) {
        this.totalVentas = totalVentas;
    }

    public float getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(float porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public String toString() {
        return "EmpleadoVentas{" +
                "totalVentas=" + totalVentas +
                ", porcentajeComision=" + porcentajeComision +
                '}';
    }

    @Override
    public float calcularSalarioBruto() {
     float comisionVentas=totalVentas*porcentajeComision/100;
     return calcularBonificacionCategoria()+salarioBase+comisionVentas;
    }
    @Override
    public void mostrarInformacion() {
        String mensajeVentas = "---Empleado de Ventas---\n" +
                "Total de ventas: " + totalVentas + "\n" +
                "Comisión: " + (totalVentas * porcentajeComision / 100);

        javax.swing.JOptionPane.showMessageDialog(null, mensajeVentas);
        super.mostrarInformacion();
    }
}
