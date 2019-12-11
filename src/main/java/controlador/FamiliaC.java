package controlador;

import dao.EmpleadoImpl;
import dao.FamiliaImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;
import modelo.FamiliaM;

@Data
@Named(value = "familiaC")
@SessionScoped
public class FamiliaC implements Serializable {

    private List<FamiliaM> lstFamilia;
    private List<FamiliaM> lstFamiliaFilter;
    private FamiliaM familia = new FamiliaM();

    @PostConstruct
    public void inicio() {
        try {
            listarFamilia();
        } catch (Exception e) {
            Logger.getLogger(FamiliaC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /* METODO PARA AGREGAR FAMILIA */
    public void agregarFamilia() throws Exception {
        FamiliaImpl familiaImpl;   
        EmpleadoImpl empleado;
        try {
            familiaImpl = new FamiliaImpl();
            empleado = new EmpleadoImpl();
            familia.setCODEMP(empleado.obtenerCodigoEmpleado(familia.getCODEMP()));
            familiaImpl.agregarFamilia(familia);
            listarFamilia();
            limpiarFamilia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (SQLException e) {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", null));
            throw e;
        }
    }

    /* METODO PARA MODIFICAR UN FAMILIAR */
    public void modificarFamilia() throws Exception {
        FamiliaImpl familiaImpl;
        try {
            familiaImpl = new FamiliaImpl();
            familiaImpl.modificarFamilia(familia);
            listarFamilia();
            limpiarFamilia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    /* METODO PARA ELIMINAR UN FAMILIAR */
    public void eliminarFamilia() throws Exception {
        FamiliaImpl familiaImpl;
        try {
            familiaImpl = new FamiliaImpl();
            familiaImpl.eliminarFamilia(familia);
            listarFamilia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    /* METODO PARA LISTAR FAMILIA */
    public void listarFamilia() throws Exception {
        FamiliaImpl familiaImpl;
        try {
            familiaImpl = new FamiliaImpl();
            lstFamilia = familiaImpl.listarFamilia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "LISTADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    /* METODO PARA LIMPIAR FAMILIA AL MOMENTO DE REGISTRAR */
    public void limpiarFamilia() {
        familia = new FamiliaM();
    }        
    
    public List<String> completeEmpleado(String query) throws Exception {
        EmpleadoImpl empleado = new EmpleadoImpl();
        return empleado.autocompleteEmpleado(query);
    }        

}