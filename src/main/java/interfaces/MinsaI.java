package interfaces;

import java.util.List;
import modelo.MinsaM;

public interface MinsaI {
    
    void agregarMinsa(MinsaM minsa) throws Exception;
    
    void modificarMinsa(MinsaM minsa) throws Exception;
    
    void eliminarMinsa(MinsaM minsa) throws Exception;
    
    List<MinsaM> listarMinsa() throws Exception;
    
}
