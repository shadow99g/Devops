package interfaces;

import java.util.List;
import modelo.CentroM;

public interface CentroI {
    
    void agregarCentro(CentroM centro) throws Exception;
    
    void modificarCentro(CentroM centro) throws Exception;
    
    void eliminarCentro(CentroM centro) throws Exception ;
    
    List<String> autoCompletarCentro(String consCen) throws Exception;

    String obtenerCodigoCentro(String apliCen) throws Exception;
    
    List<CentroM> listarCentro() throws Exception;
}
