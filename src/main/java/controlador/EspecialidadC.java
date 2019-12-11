
package controlador;

import dao.EspecialidadImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Data;
import modelo.EspecialidadM;

@Data
@Named(value = "especialidadC")
@SessionScoped
public class EspecialidadC implements Serializable {

    EspecialidadM especialidad = new EspecialidadM();
    private List<EspecialidadM> lstEspecialidad;
    private EspecialidadM editEspecialidad;
    EspecialidadImpl daoEsp;
    
    
    @PostConstruct
    public void inicio(){
        try {
            listarEspecialidad();
        } catch (Exception e) {
        }
    }
    
    public void instancia(){
        daoEsp = new EspecialidadImpl();
    }
    
    public void limpiar(){
        try {
            especialidad = new EspecialidadM();
        } catch (Exception e) {
        }
    }
    
    public void agregarEspecialidad() throws Exception{
        try {
            daoEsp.agregarEspecialidad(especialidad);
            listarEspecialidad();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void listarEspecialidad() throws Exception{
        try {
            daoEsp = new EspecialidadImpl();
            lstEspecialidad = daoEsp.listarEspecialidad();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarEspecialidad() throws Exception{
        try {
            daoEsp.modificarEspecialidad(editEspecialidad);
            listarEspecialidad();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void eliminarEspecialidad(){
        try {
            daoEsp.eliminarEspecialidad(especialidad);
            listarEspecialidad();
        } catch (Exception e) {
        }
    }
    
}
