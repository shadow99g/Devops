package interfaces;

import java.util.List;
import modelo.DialectoDetalleM;

public interface DialectoDetalleI {

    void agregarDialectoDetalle(DialectoDetalleM detalle) throws Exception;

    void modificarDialectoDetalle(DialectoDetalleM detalle) throws Exception;

    void eliminarDialectoDetalle(DialectoDetalleM detalle) throws Exception;

    List<DialectoDetalleM> listarDialectoDetalle() throws Exception;

    public String obtenerCodigoDialecto(String Dialecto) throws Exception;
    
    public List<String> autocompleteDialecto(String Consulta) throws Exception;

}
