/**
 * Clase para probar el coreecto funcionamiento del programa
 * @author Nicolas lopez Garcia
 * @since 2026-04
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 *
 *
 *
 */
import static co.edu.uniquindio.poo.model.CategoriaEmpleado.JUNIOR;
import static co.edu.uniquindio.poo.model.CategoriaEmpleado.SENIOR;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import co.edu.uniquindio.poo.model.*;
import org.junit.jupiter.api.Test;
public class Pruebas {
    private static final Logger LOG = Logger.getLogger(Pruebas.class.getName());
    @Test
public void verificarSalarioBrutoVentas(){
      LOG.info("inicio de la prueba para verificar el salario bruto del empleado de ventas:");
 EmpleadoVentas ventas=new EmpleadoVentas("nicolas","nit",34,1000000f,CategoriaEmpleado.JUNIOR,4f,4f,20f,20f,null);

        float actual = ventas.calcularSalarioBruto();
        float expected = 1050004f;

        assertEquals(expected, actual,
                "El salario bruto del empleado de ventas se calcula correctamente");


        LOG.info("fin de la prueba para verificar el salario bruto del empleado de ventas:");
    }
    @Test
    public void verificarSalarionetoNocero() {
        LOG.info("Inicio de la prueba para verificar que el salario neto del empleado temporal no sea cero:");
        EmpleadoTemporal temporal = new EmpleadoTemporal("Ana", "nat", 20, 1000000f, JUNIOR, 4f, 4f, 10, 50000f);
        assertNotEquals(0, temporal.calcularSalarioNeto(), "El salario neto no puede ser 0:");
        LOG.info("Fin de la prueba:");
    }
    @Test
    public void verificarEmpleadosEmpresa(){
        LOG.info("Inicio de la prueba para verificar que los empledados almacenados sean correctos:");
        Empresa empresa=new Empresa("Finanz");
        EmpleadoVentas ventas=new EmpleadoVentas("Nicolas","nit",33,1000000f,CategoriaEmpleado.SENIOR,10f,5f,40,15f,null);
        EmpleadoTemporal temporal=new EmpleadoTemporal("martin","nut",23,1200000f,CategoriaEmpleado.SENIOR,10f,5f,7,60000f);
        empresa.registrarEmpleado(ventas);
        empresa.registrarEmpleado(temporal);
        assertEquals("nit",empresa.getListaEmpleados().get(0).getDocumento(),"el empleado deberia ser Nicolas o nit");
        assertEquals("nut",empresa.getListaEmpleados().get(1).getDocumento(),"el empleado deberia ser Martin o nut");
        LOG.info("los empleados estan registrados correctamente");

        LOG.info("Fin de la prueba");

    }
    @Test
    public void verificarBonificacionMayorCero(){
        LOG.info("Inicio de la prueba:");
        EmpleadoTemporal temporal=new EmpleadoTemporal("martin","nut",23,1200000f,CategoriaEmpleado.JUNIOR,10f,5f,7,60000f);
        assertTrue(temporal.calcularBonificacionCategoria()>0);
        LOG.info("Fin de la prueba");

    }
    @Test
    public void verificarSalarioNetoNoNegativo(){
        LOG.info("Inicio de la prueba:");
        EmpleadoTemporal temporal=new EmpleadoTemporal("martin","nut",23,1200000f,CategoriaEmpleado.JUNIOR,10f,5f,7,60000f);
        assertTrue(temporal.getSalarioNeto()>=0);
        LOG.info("Fin de la prueba,salario neto verificado");

    }
    @Test
    public void verificarBusquedaEmpleado(){
        LOG.info("Inicio de la prueba:");
        Empresa empresa = new Empresa("Finanz");
        assertFalse(empresa.getListaEmpleados().stream().anyMatch(e -> e.getDocumento().equals("999")));
        LOG.info("Fin de la prueba:");


    }
    @Test
    public void verficarExcepcionSalarioBaseNegativo(){
        LOG.info("Inicio de la prueba:");
        assertThrows(IllegalArgumentException.class,()->new EmpleadoVentas("Nicolas","nit",33,-1000000f,CategoriaEmpleado.SENIOR,10f,5f,40,15f,null));

        LOG.info("fin de la prueba:");

    }
    @Test
    public void verificarMetodoEmpleadosMayorSalario(){
        LOG.info("Inicio de la prueba:");
        Empresa empresa=new Empresa();
        EmpleadoVentas ventas=new EmpleadoVentas("nicolas","nit",34,1000000f,CategoriaEmpleado.JUNIOR,4f,4f,20f,20f,null);
        EmpleadoTemporal temporal=new EmpleadoTemporal("martin","nut",23,1500000f,CategoriaEmpleado.JUNIOR,4f,3f,7,60000f);
        empresa.registrarEmpleado(temporal);
        empresa.registrarEmpleado(ventas);
        LOG.info("Salario calculado para Martín: " + temporal.calcularSalarioNeto());
        List<Empleado> resultado = empresa.empleadosMayorSalario(1100000f);
        boolean estaMartin = resultado.stream().anyMatch(e -> e.getDocumento().equals("nut"));
        assertTrue(estaMartin, "El empleado Martín (nut) debería estar en la lista de salarios altos");
        LOG.info("fin de la prueba:");
    }
    @Test
    public void verificarBusquedDocumento(){
        LOG.info("Inicio de la prueba:");
        Empresa miEmpresa = new Empresa("Finanz");
        EmpleadoPlanta planta = new EmpleadoPlanta("Andres", "not", 20, 1000f, CategoriaEmpleado.JUNIOR, 4, 4, "Soporte", 0, 0, 0);
        miEmpresa.registrarEmpleado(planta);
        String docInexistente = "999";
        Empleado resultado = miEmpresa.buscarPorDocumento(docInexistente);
        assertNull(resultado, "El método debe retornar un valor null cuando el empleado no existe");
        LOG.info("efetivamente, el resultado de busqueda es null");

        LOG.info("fin de la prueba:");

    }
    @Test
    public void verificarNoempleadosDuplicados(){
        LOG.info("inicio prueba:");
        Empresa empresa = new Empresa("Finanz");
        EmpleadoPlanta original = new EmpleadoPlanta("Andres", "nat", 20, 1000f, CategoriaEmpleado.JUNIOR, 4, 4, "Soporte", 0, 0, 0);
        empresa.registrarEmpleado(original);

            EmpleadoPlanta duplicado = new EmpleadoPlanta("Andres ", "nat", 20, 1000f, JUNIOR, 4, 4, "Soporte", 0, 0, 0);

        String respuesta = empresa.registrarEmpleado(duplicado);
        assertEquals("el empleado ya existe", respuesta, "el empleado ya existe");
        LOG.info("el sistema elimino al usuario duplicado");
        LOG.info("fin de la prueba");
    }
    @Test
    public void verificarMetodoSalarioMayor(){
        LOG.info("inicio prueba:");
        Empresa empresa = new Empresa("Finanz");

        EmpleadoPlanta p1 = new EmpleadoPlanta("nicolas", "mat", 20, 1000f, CategoriaEmpleado.JUNIOR, 4, 4, "gerente", 0, 0, 0);
        EmpleadoPlanta p2 = new EmpleadoPlanta("carlos", "met", 30, 5000f, CategoriaEmpleado.SENIOR, 4, 4, "encargado", 0, 0, 0);
        EmpleadoPlanta p3 = new EmpleadoPlanta("esteban", "mit", 25, 3000f, CategoriaEmpleado.SEMI_SENIOR, 4, 4, "oficinista", 0, 0, 0);

        empresa.registrarEmpleado(p1);
        empresa.registrarEmpleado(p2);
        empresa.registrarEmpleado(p3);

        Empleado resultado = empresa.obtenerMayorSalario();
        assertEquals("met", resultado.getDocumento(), "El empleado con mayor salario debe ser el de documento met");
        assertEquals("carlos", resultado.getNombre(), "El nombre del ganador debe ser 'carlos'");
        LOG.info("el metodo que obtiene al empleado con mayor salario es correcto");
        LOG.info("fin de la prueba");

    }
    @Test
    public void verificarMetodo100Horas() {
        LOG.info("inicio prueba:");
        Empresa empresa = new Empresa("Finanz");
        EmpleadoTemporal t1 = new EmpleadoTemporal("Juan", "sit", 20, 1000f, CategoriaEmpleado.JUNIOR, 4, 4, 15, 50f);
        EmpleadoTemporal t2 = new EmpleadoTemporal("Ana", "set", 22, 1000f, CategoriaEmpleado.JUNIOR, 4, 4, 5, 50f);

        empresa.registrarEmpleado(t1);
        empresa.registrarEmpleado(t2);

        List<EmpleadoTemporal> resultado = empresa.obtenerTemporalesMasDe100Horas();
        assertEquals("sit", resultado.get(0).getDocumento(), "El empleado debe ser Juan (sit)");

        LOG.info("prueba exitosa");
        LOG.info("fin de la prueba");
    }
    @Test
    public void verificarSalarioNetoPlantaMayorSalarioBase() {
        LOG.info("inicio prueba:");
        EmpleadoPlanta planta = new EmpleadoPlanta("Carlos", "sat", 30, 1000000f, CategoriaEmpleado.SENIOR, 4, 4, "Ingeniero", 20, 25000f, 140000f);

        float neto = planta.getSalarioNeto();
        float base = planta.getSalarioBase();

        assertTrue(neto > base, "El salario neto debe ser mayor al salario base");

        LOG.info("el salario neto es mayor al salario base");
        LOG.info("fin de la prueba");
    }
    @Test
    public void verificarSalarioNetoTemporalMayorCero(){
        LOG.info("inicio prueba:");
        EmpleadoTemporal temporal = new EmpleadoTemporal("Marta", "rot", 25, 1000f, CategoriaEmpleado.JUNIOR, 4, 4, 1, 50000f);

        float neto = temporal.getSalarioNeto();

        assertTrue(neto > 0, "El salario neto debe ser mayor a cero si los días y el valor por dia son positivos");
        LOG.info("prueba exitosa");
        LOG.info("fin de la prueba");
    }

    }






