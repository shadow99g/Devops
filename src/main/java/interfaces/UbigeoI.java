package interfaces;

import java.util.List;

public interface UbigeoI {

    List<String> autocompleteUbigeo(String Consulta) throws Exception;
    
    String obtenerCodigoUbigeo(String a) throws Exception;
}
