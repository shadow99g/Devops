package controlador;

import dao.AdministracionPublicaImpl;
import dao.EmpleadoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.AdministracionPublicaM;

@Data
@Named(value = "administracionPublicaC")
@SessionScoped
public class AdministracionPublicaC implements Serializable {

    AdministracionPublicaM adminPublica = new AdministracionPublicaM();
    private List<AdministracionPublicaM> lstAdminPublica;
    private AdministracionPublicaM selectedAdminPublica;

    @PostConstruct
    public void iniciarAdminPublica() {
        try {
            listarAdminPublica();
        } catch (Exception e) {
        }
    }

    public void agregarAdminPublica() throws Exception {
        AdministracionPublicaImpl dao;
        EmpleadoImpl empleado;
        try {
            dao = new AdministracionPublicaImpl();
            empleado = new EmpleadoImpl();
            adminPublica.setCODEMP(empleado.obtenerCodigoEmpleado(adminPublica.getCODEMP()));
            dao.agregarAdminPublica(adminPublica);
            listarAdminPublica();
            limpiarAdminPublica();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al agregar", null));
        }
    }

    public void modificarAdminPublica() {
        AdministracionPublicaImpl dao;
        EmpleadoImpl empleado;
        try {
            dao = new AdministracionPublicaImpl();
            empleado = new EmpleadoImpl();
            selectedAdminPublica.setCODEMP(empleado.obtenerCodigoEmpleado(selectedAdminPublica.getEMPLEADO()));
            dao.modificarAdminPublica(selectedAdminPublica);
            listarAdminPublica();
            limpiarAdminPublica();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al modificar", null));
        }
    }

    public void eliminarAdminPublica() {
        AdministracionPublicaImpl dao;
        try {
            dao = new AdministracionPublicaImpl();
            dao.eliminarAdminPublica(selectedAdminPublica);
            listarAdminPublica();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado satisfactoriamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al eliminar", null));
        }
    }

    public void listarAdminPublica() {
        AdministracionPublicaImpl dao;
        try {
            dao = new AdministracionPublicaImpl();
            lstAdminPublica = dao.listarAdminpublica();
        } catch (Exception e) {
        }
    }

    public void limpiarAdminPublica() {
        adminPublica = new AdministracionPublicaM();
    }

}
