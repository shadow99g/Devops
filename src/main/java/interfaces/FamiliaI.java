package interfaces;

import java.util.List;
import modelo.FamiliaM;

public interface FamiliaI {
    
    void agregarFamilia(FamiliaM familia) throws Exception;
    
    void modificarFamilia(FamiliaM familia) throws Exception;
    
    void eliminarFamilia(FamiliaM familia) throws Exception;
    
    List<FamiliaM> listarFamilia() throws Exception;
}
