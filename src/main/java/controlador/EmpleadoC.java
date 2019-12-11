package controlador;

import dao.EmpleadoImpl;
import dao.InstitucionImpl;
import dao.UbigeoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.EmpleadoM;

@Data
@Named(value = "empleadoC")
@SessionScoped
public class EmpleadoC implements Serializable {

    EmpleadoM empleado = new EmpleadoM();
    private List<EmpleadoM> lstEmpleado;

    @PostConstruct
    public void iniciar() {
        try {
            listarEmpleado();
        } catch (Exception e) {
        }
    }

    public void agregarEmpleado() throws Exception {
        EmpleadoImpl dao;
        UbigeoImpl ubi;
        InstitucionImpl renaes;
        try {
            dao = new EmpleadoImpl();
            ubi = new UbigeoImpl();
            renaes = new InstitucionImpl();
            empleado.setCODUBI(ubi.obtenerCodigoUbigeo(empleado.getCODUBI()));
            empleado.setCODREN(renaes.obtenerCodigoRenaes(empleado.getCODREN()));
            dao.agregarEmpleado(empleado);
            listarEmpleado();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado satisfactoriamente", null));
        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al agregar", null));
            throw e;
        }
    }

    public void modificarEmpleado() {
        EmpleadoImpl dao;
        try {
            dao = new EmpleadoImpl();
            dao.modificarEmpleado(empleado);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al modificar", null));
        }
    }

    public void eliminarEmpleado() {
        EmpleadoImpl dao;
        try {
            dao = new EmpleadoImpl();
            dao.eliminarEmpleado(empleado);
            listarEmpleado();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al eliminar", null));
        }
    }

    public void listarEmpleado() {
        EmpleadoImpl dao;
        try {
            dao = new EmpleadoImpl();
            lstEmpleado = dao.listarEmpleado();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al listar", e.getMessage()));
        }
    }
    
    public void limpiar() {
        empleado = new EmpleadoM();
    }

}
