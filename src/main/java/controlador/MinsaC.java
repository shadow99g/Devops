package controlador;

import dao.EmpleadoImpl;
import dao.MinsaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.MinsaM;

@Data
@Named(value = "minsaC")
@SessionScoped
public class MinsaC implements Serializable {

    MinsaM minsa = new MinsaM();
    private List<MinsaM> lstMinsa;
    private MinsaM selectedMinsa;

    @PostConstruct
    public void iniciarMinsa() {
        try {
            listarMinsa();
        } catch (Exception e) {
        }
    }

    public void agregarMinsa() {
        MinsaImpl dao;
        EmpleadoImpl empleado;
        try {
            dao = new MinsaImpl();
            empleado = new EmpleadoImpl();
            minsa.setCODEMP(empleado.obtenerCodigoEmpleado(minsa.getCODEMP()));
            dao.agregarMinsa(minsa);
            listarMinsa();
            limpiarMinsa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al agregar", null));

        }
    }

    public void modificarMinsa() throws Exception {
        MinsaImpl dao;
        EmpleadoImpl empleado;
        try {
            dao = new MinsaImpl();
            empleado = new EmpleadoImpl();
            selectedMinsa.setCODEMP(empleado.obtenerCodigoEmpleado(selectedMinsa.getEMPLEADO()));
            dao.modificarMinsa(selectedMinsa);
            listarMinsa();
            limpiarMinsa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al modificar", null));
        }
    }

    public void eliminarMinsa() {
        MinsaImpl dao;
        try {
            dao = new MinsaImpl();
            dao.eliminarMinsa(selectedMinsa);
            listarMinsa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al eliminar", null));
        }
    }

    public void listarMinsa() {
        MinsaImpl dao;
        try {
            dao = new MinsaImpl();
            lstMinsa = dao.listarMinsa();
        } catch (Exception e) {
        }
    }

    public void limpiarMinsa() {
        minsa = new MinsaM();
    }

}
