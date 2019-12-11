package interfaces;

import java.util.List;
import modelo.CapacitacionM;

public interface CapacitacionI {

    void agregarCapacitacion(CapacitacionM capacitacion) throws Exception;

    void modificarCapacitacion(CapacitacionM capacitacion) throws Exception;

    void eliminarCapacitacion(CapacitacionM capacitacion) throws Exception;

    List<CapacitacionM> listarCapacitacion() throws Exception;
}
