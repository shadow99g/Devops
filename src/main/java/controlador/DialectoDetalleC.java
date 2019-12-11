package controlador;

import dao.DialectoDetalleImpl;
import dao.DialectoImpl;
import dao.EmpleadoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.DialectoDetalleM;

@Named(value = "dialectoDetalleC")
@SessionScoped
public class DialectoDetalleC implements Serializable {

    private List<DialectoDetalleM> lstDialectoDetalle;
    private List<DialectoDetalleM> lstDialectoDetalleFilter;
    private DialectoDetalleM dialectoDetalle = new DialectoDetalleM();

    @PostConstruct
    public void inicio() {
        try {
            listarDialectoDetalle();
        } catch (Exception e) {
        }
    }

    /* METODO PARA REGISTRAR UN DIALECTO DEL EMPLEADO */
    public void agregarDialectoDetalle() throws Exception {
        DialectoDetalleImpl detalleDialec;
        EmpleadoImpl empleado;
        DialectoImpl dialecto;

        try {
            detalleDialec = new DialectoDetalleImpl();
            empleado = new EmpleadoImpl();
            dialecto = new DialectoImpl();
            dialectoDetalle.setCODEMP(empleado.obtenerCodigoEmpleado(dialectoDetalle.getCODEMP()));
            dialectoDetalle.setCODDIA(dialecto.obtenerCodigoDialecto(dialectoDetalle.getCODDIA()));
            detalleDialec.agregarDialectoDetalle(dialectoDetalle);
            listarDialectoDetalle();
            limpiarDialectoDetalle();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", null));
            throw e;
        }
    }

    /* METODO PARA MODIFICAR UN DIALECTO DEL EMPLEADO */
    public void modificarDialectoDetalle() throws Exception {
        DialectoDetalleImpl detalleDialec;
        EmpleadoImpl empleado;
        DialectoImpl dialecto;
        try {
            detalleDialec = new DialectoDetalleImpl();
            empleado = new EmpleadoImpl();
            dialecto = new DialectoImpl();
            dialectoDetalle.setCODEMP(empleado.obtenerCodigoEmpleado(dialectoDetalle.getCODEMP()));
            dialectoDetalle.setCODDIA(dialecto.obtenerCodigoDialecto(dialectoDetalle.getCODDIA()));
            detalleDialec.modificarDialectoDetalle(dialectoDetalle);
            listarDialectoDetalle();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    /* METODO PARA ELIMINAR UN DIALECTO DEL EMPLEADO */
    public void eliminarDialectoDetalle() throws Exception {
        DialectoDetalleImpl detalleDialec;
        try {
            detalleDialec = new DialectoDetalleImpl();
            detalleDialec.eliminarDialectoDetalle(dialectoDetalle);
            listarDialectoDetalle();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    /* METODO PARA LISTAR EL DIALECTO DEL EMPLEADO */
    public void listarDialectoDetalle() throws Exception {
        DialectoDetalleImpl detalleDialec;
        try {
            detalleDialec = new DialectoDetalleImpl();
            lstDialectoDetalle = detalleDialec.listarDialectoDetalle();
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }


    /* METODO PARA LIMPIAR EL DIALECTO AL MOMENTO DE REGISTRAR */
    public void limpiarDialectoDetalle() {
        dialectoDetalle = new DialectoDetalleM();
    }

    /* GETTER AND SETTER */
    public List<DialectoDetalleM> getLstDialectoDetalle() {
        return lstDialectoDetalle;
    }

    public void setLstDialectoDetalle(List<DialectoDetalleM> lstDialectoDetalle) {
        this.lstDialectoDetalle = lstDialectoDetalle;
    }

    public DialectoDetalleM getDialectoDetalle() {
        return dialectoDetalle;
    }

    public void setDialectoDetalle(DialectoDetalleM dialectoDetalle) {
        this.dialectoDetalle = dialectoDetalle;
    }

    public List<DialectoDetalleM> getLstDialectoDetalleFilter() {
        return lstDialectoDetalleFilter;
    }

    public void setLstDialectoDetalleFilter(List<DialectoDetalleM> lstDialectoDetalleFilter) {
        this.lstDialectoDetalleFilter = lstDialectoDetalleFilter;
    }

}
