package controlador;

import dao.EmpleadoImpl;
import dao.InstitucionImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.InstitucionM;

@Data
@Named(value = "institucionC")
@SessionScoped
public class InstitucionC implements Serializable {

    InstitucionM institucion = new InstitucionM();
    private List<InstitucionM> lstInstitucion;
    private InstitucionM selectedInstitucion;

    @PostConstruct
    public void iniciarInstitucion() {
        try {
            listarInstitucion();
        } catch (Exception e) {
        }
    }

    public void agregarInstitucion() {
        InstitucionImpl dao;
        EmpleadoImpl empleado;
        try {
            dao = new InstitucionImpl();
            empleado = new EmpleadoImpl();
            institucion.setCODEMP(empleado.obtenerCodigoEmpleado(institucion.getCODEMP()));
            dao.agregarInstitucion(institucion);
            listarInstitucion();
            limpiarInstitucion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al modificar", null));
        }
    }

    public void modificarInstitucion() {
        InstitucionImpl dao;
        EmpleadoImpl empleado;
        try {
            dao = new InstitucionImpl();
            empleado = new EmpleadoImpl();
            selectedInstitucion.setCODEMP(empleado.obtenerCodigoEmpleado(selectedInstitucion.getEMPLEADO()));
            dao.modificarInstitucion(selectedInstitucion);
            listarInstitucion();
            limpiarInstitucion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al modificar", null));
        }
    }
    
    public void eliminarInstitucion() {
        InstitucionImpl dao;
        try {
            dao = new InstitucionImpl();
            dao.eliminarInstitucion(selectedInstitucion);
            listarInstitucion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al eliminar", null));
        }
    }

    public void listarInstitucion() {
        InstitucionImpl dao;
        try {
            dao = new InstitucionImpl();
            lstInstitucion = dao.listarInstitucion();
        } catch (Exception e) {
        }
    }

    public void limpiarInstitucion() {
        institucion = new InstitucionM();
    }

}
