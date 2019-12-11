package servicio;

import dao.CentroImpl;
import dao.DialectoImpl;
import dao.EmpleadoImpl;
import dao.EspecialidadImpl;
import dao.InstitucionImpl;
import dao.UbigeoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "autoCompletar")
@SessionScoped
public class AutoCompletar implements Serializable {
    
    public List<String> completeEmpleado(String query) throws Exception {
        EmpleadoImpl empleado = new EmpleadoImpl();
        return empleado.autocompleteEmpleado(query);
    }
    
    public List<String> completeDialecto(String query) throws Exception {
        DialectoImpl dialecto = new DialectoImpl();
        return dialecto.autocompleteDialecto(query);
    }
    
    public List<String> completeUbigeo(String query) throws Exception {
        UbigeoImpl ubigeo = new UbigeoImpl();
        return ubigeo.autocompleteUbigeo(query);
    }
    
    public List<String> completeCentro(String query) throws Exception {
        CentroImpl centro = new CentroImpl();
        return centro.autoCompletarCentro(query);
    }
    
    public List<String> completeEspecialidad(String query) throws Exception {
        EspecialidadImpl especialidad = new EspecialidadImpl();
        return especialidad.autoCompletarEspecialidad(query);
    }
    
    public List<String> completeRenaes(String query) throws Exception {
        InstitucionImpl ubigeo = new InstitucionImpl();
        return ubigeo.autocompleteRenaes(query);
    }
}
