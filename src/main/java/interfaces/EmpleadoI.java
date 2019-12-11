package interfaces;

import java.util.List;
import modelo.EmpleadoM;

public interface EmpleadoI {
    
    void agregarEmpleado(EmpleadoM empleado) throws Exception;
    
    void modificarEmpleado(EmpleadoM empleado) throws Exception;
    
    void eliminarEmpleado(EmpleadoM empleado) throws Exception;
    
    List<EmpleadoM> listarEmpleado() throws Exception;
    
    List<String> autocompleteEmpleado(String Consulta) throws Exception;
    
    String obtenerCodigoEmpleado(String Empleado) throws Exception;
    
}
