package interfaces;

import java.util.List;

public interface DialectoI {

    List<String> autocompleteDialecto(String Consulta) throws Exception;

    String obtenerCodigoDialecto(String a) throws Exception;
}
