
package interfaces;

import java.util.List;
import modelo.DesplazamientoM;


public interface DesplazamientoI {
    
     void agregarDesplazamiento(DesplazamientoM desplazamiento) throws Exception;
     void modificarDesplazamiento(DesplazamientoM desplazamiento) throws Exception;
     void eliminarDesplazamiento(DesplazamientoM desplazamiento) throws Exception;
     List<DesplazamientoM> listarDesplazamiento() throws Exception;
}
