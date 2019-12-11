package interfaces;

import java.util.List;
import modelo.EspecialidadM;

public interface EspecialidadI {
    
    void agregarEspecialidad(EspecialidadM especialidad) throws Exception ;

    void modificarEspecialidad(EspecialidadM estudio) throws Exception;
    
    void eliminarEspecialidad(EspecialidadM especialidad) throws Exception;
    
    List<EspecialidadM> listarEspecialidad() throws Exception;
    
    List<String> autoCompletarEspecialidad(String consEsp) throws Exception;

    String obtenerCodigoEspecialidad(String apliCen) throws Exception;
}
