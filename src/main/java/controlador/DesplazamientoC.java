package controlador;

import dao.DesplazamientoImpl;
import dao.EmpleadoImpl;
import dao.InstitucionImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.DesplazamientoM;
import lombok.Data;

@Data
@Named(value = "desplazamientoC")
@SessionScoped
public class DesplazamientoC implements Serializable {

    DesplazamientoM desplazamiento = new DesplazamientoM();
    private List<DesplazamientoM> lstDesplazamiento;
    private DesplazamientoM selectedDesplazamiento;

    @PostConstruct
    public void iniciar() {
        try {
            listarDesplazamiento();
        } catch (Exception e) {
        }

    }

    public void agregarDesplazamiento() throws Exception {
        DesplazamientoImpl dao;
        EmpleadoImpl daoEmp;
        InstitucionImpl daoInst;
        try {
            daoInst = new InstitucionImpl();
            dao = new DesplazamientoImpl();
            daoEmp = new EmpleadoImpl();
            desplazamiento.setCODEMP(daoEmp.obtenerCodigoEmpleado(desplazamiento.getCODEMP()));
            desplazamiento.setLPRDES(daoInst.obtenerCodigoRenaes(desplazamiento.getLPRDES()));
            dao.agregarDesplazamiento(desplazamiento);
            listarDesplazamiento();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al agregar", null));
            throw e;
        }
    }

    public void listarDesplazamiento() {
        DesplazamientoImpl dao;
        try {
            dao = new DesplazamientoImpl();
            lstDesplazamiento = dao.listarDesplazamiento();
        } catch (Exception e) {
        }
    }

    public void modificarDesplazamiento() throws Exception {
        DesplazamientoImpl dao;
        try {
            dao = new DesplazamientoImpl();
            dao.modificarDesplazamiento(selectedDesplazamiento);
            listarDesplazamiento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO CORRECTAMENTE", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERRO AL MODIFICAR", null));
            throw e;
        }

    }

    public void eliminarDesplazamiento() throws Exception {
        DesplazamientoImpl dao;
        try {
            dao = new DesplazamientoImpl();
            dao.eliminarDesplazamiento(desplazamiento);
            listarDesplazamiento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL ELIMINAR", null));
            throw e;
        }
    }

    public void limpiar() {
        desplazamiento = new DesplazamientoM();
    }
}
