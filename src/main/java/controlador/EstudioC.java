package controlador;

import dao.CentroImpl;
import dao.EmpleadoImpl;
import dao.EstudioImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.EstudioM;

@Data
@Named(value = "estudioC")
@SessionScoped
public class EstudioC implements Serializable {

    private EstudioM estudio = new EstudioM();
    private EstudioM editExperiencia;
    private List<EstudioM> filter;
    private List<EstudioM> lstExperiencia;
    private List<EstudioM> lstExterno;
    private EstudioImpl daoEst;
    private EmpleadoImpl daoEmp;
    private CentroImpl daoCen;

    @PostConstruct
    public void inicio() {
        try {

            listarExperiencia();
            listarExterno();
        } catch (Exception e) {
        }
    }

    public void instancia() {
        daoEst = new EstudioImpl();
        daoEmp = new EmpleadoImpl();
        daoCen = new CentroImpl();
    }

    public void limpiar() {
        try {
            estudio = new EstudioM();
        } catch (Exception e) {
        }
    }

    /*Agregar Funcional*/
    public void agregarExperiencia() throws Exception {
        EstudioImpl estu;
        EmpleadoImpl emple;
        CentroImpl centr;
        try {
            estu = new EstudioImpl();
            emple = new EmpleadoImpl();
            centr = new CentroImpl();
            estudio.setCODEMP(emple.obtenerCodigoEmpleado(estudio.getCODEMP()));
            estudio.setCODCEN(centr.obtenerCodigoCentro(estudio.getCODCEN()));
            estu.agregarexperiencia(estudio);
            limpiar();
            listarExperiencia();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", null));
            throw e;
        }
    }

    /*Listar Funcional*/
    public void listarExperiencia() throws Exception {
        try {
            daoEst = new EstudioImpl();
            lstExperiencia = daoEst.listarExperiencia();
        } catch (Exception e) {
            throw e;
        }
    }

    public void agregarExterno() throws Exception {
        EstudioImpl estu;
        EmpleadoImpl emple;
        CentroImpl centr;
        try {
            estu = new EstudioImpl();
            emple = new EmpleadoImpl();
            centr = new CentroImpl();
            estudio.setCODEMP(emple.obtenerCodigoEmpleado(estudio.getCODEMP()));
            estudio.setCODCEN(centr.obtenerCodigoCentro(estudio.getCODCEN()));
            estu.agregarExterno(estudio);
            limpiar();
            listarExterno();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", null));
            throw e;
        }
    }

    public void listarExterno() throws Exception {
        try {
            daoEst = new EstudioImpl();
            lstExterno = daoEst.listarExterno();

        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarExperiencia() throws Exception {
        try {
            daoEst.eliminarExperiencia(estudio);
            listarExperiencia();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarExperiencia() throws Exception {
        EstudioImpl estu;
        EmpleadoImpl emple;
        CentroImpl centr;
        try {
            estu = new EstudioImpl();
            emple = new EmpleadoImpl();
            centr = new CentroImpl();
            estudio.setCODEMP(emple.obtenerCodigoEmpleado(estudio.getCODEMP()));
            estudio.setCODCEN(centr.obtenerCodigoCentro(estudio.getCODCEN()));
            estu.modificarExperiencia(estudio);
            listarExperiencia();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarExterno() throws Exception {
        try {
            daoEst.eliminarExterno(estudio);
            listarExterno();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarExterno() throws Exception {
        EstudioImpl estu;
        EmpleadoImpl emple;
        CentroImpl centr;
        try {
            estu = new EstudioImpl();
            emple = new EmpleadoImpl();
            centr = new CentroImpl();
            estudio.setNOMEMP(emple.obtenerCodigoEmpleado(estudio.getCODEMP()));
            estudio.setCODCEN(centr.obtenerCodigoCentro(estudio.getCODCEN()));
            estu.modificarExterno(estudio);
            listarExterno();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

}
