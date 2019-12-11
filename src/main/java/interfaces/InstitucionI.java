package interfaces;

import java.util.List;
import modelo.InstitucionM;

public interface InstitucionI {
    
    void agregarInstitucion(InstitucionM institucion) throws Exception;
    
    void modificarInstitucion(InstitucionM institucion) throws Exception;
    
    void eliminarInstitucion(InstitucionM institucion) throws Exception;
    
    List<InstitucionM> listarInstitucion() throws Exception;
    
    List<String> autocompleteRenaes(String a) throws Exception;
    
    String obtenerCodigoRenaes(String a) throws Exception;
    
}
