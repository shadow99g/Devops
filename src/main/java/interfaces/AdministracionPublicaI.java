package interfaces;

import java.util.List;
import modelo.AdministracionPublicaM;

public interface AdministracionPublicaI {
    
    void agregarAdminPublica(AdministracionPublicaM adminPublica) throws Exception;
    
    void modificarAdminPublica(AdministracionPublicaM adminPublica) throws Exception;
    
    void eliminarAdminPublica(AdministracionPublicaM adminPublica) throws Exception;
    
    List<AdministracionPublicaM> listarAdminpublica() throws Exception;
    
}
