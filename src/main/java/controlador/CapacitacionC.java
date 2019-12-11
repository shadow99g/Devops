package controlador;

import dao.CapacitacionImpl;
import dao.CentroImpl;
import dao.EmpleadoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.CapacitacionM;
import servicio.Reporte;
import java.util.HashMap;
import java.util.Map;

@Named(value = "capacitacionC")
@SessionScoped
@Data
public class CapacitacionC implements Serializable {

    private List<CapacitacionM> lstCapacitacion;
    private List<CapacitacionM> filter;
    private CapacitacionM capacitacion = new CapacitacionM();
    private String accion;

    
    @PostConstruct
    public void inicio() {
        try {
            listarCapacitacion();
        } catch (Exception e) {
            Logger.getLogger(CapacitacionC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

//    public void operar() throws Exception {
//        switch (accion) {
//            case "Registrar":
//                this.agregarCapacitacion();
//                break;
//            case "Modificar":
//                this.modificarCapacitacion();
//                break;
//        }
//    }


    /* METODO PARA AGREGAR CAPACITACION */
    public void agregarCapacitacion() throws Exception {
        CapacitacionImpl dao;
        EmpleadoImpl empleado;
        CentroImpl centro;
        try {
            dao = new CapacitacionImpl();
            empleado = new EmpleadoImpl();
            centro = new CentroImpl();
            capacitacion.setCODCEN(centro.obtenerCodigoCentro(capacitacion.getCODCEN()));
            capacitacion.setCODEMP(empleado.obtenerCodigoEmpleado(capacitacion.getCODEMP()));
            dao.agregarCapacitacion(capacitacion);
            listarCapacitacion();
            limpiarCapacitacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            throw e;
        }
    }

    /* METODO PARA MODIFICAR UN CAPACITACION */
    public void modificarCapacitacion() throws Exception {
        CapacitacionImpl dao;
        EmpleadoImpl empleado;
        CentroImpl centro;
        try {
            dao = new CapacitacionImpl();
            empleado = new EmpleadoImpl();            
            centro = new CentroImpl();
            capacitacion.setCODCEN(centro.obtenerCodigoCentro(capacitacion.getCENTRO()));
            capacitacion.setEMPLEADO(empleado.obtenerCodigoEmpleado(capacitacion.getCODEMP()));
            dao.modificarCapacitacion(capacitacion);
            listarCapacitacion();
            limpiarCapacitacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", "CAPACITACIÓN"));
        } catch (Exception e) {
            throw e;
        }
    }

    /* METODO PARA ELIMINAR UN CAPACITACION */
    public void eliminarCapacitacion() throws Exception {
        CapacitacionImpl capacitacionImpl;
        try {
            capacitacionImpl = new CapacitacionImpl();
            capacitacionImpl.eliminarCapacitacion(capacitacion);
            listarCapacitacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    /* METODO PARA LISTAR CAPACITACION */
    public void listarCapacitacion() throws Exception {
        CapacitacionImpl capacitacionImpl;
        try {
            capacitacionImpl = new CapacitacionImpl();
            lstCapacitacion = capacitacionImpl.listarCapacitacion();
        } catch (Exception e) {
            throw e;
        }
    }

    /* METODO PARA LIMPIAR CAPACITACION AL MOMENTO DE REGISTRAR */
    public void limpiarCapacitacion() throws Exception {
        try {
            capacitacion = new CapacitacionM();
        } catch (Exception e) {
            throw e;
        }
    }

//    Metodo para reportes
    public void reportCapacitacion() throws Exception {
        Reporte report = new Reporte();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "capacitacion.jasper", "Capacitaciones.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void reportConstancia() throws Exception {
        Reporte report = new Reporte();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "constancia.jasper", "Constancia.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void reportConstanciaAnve() throws Exception {
        Reporte report = new Reporte();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "constanciaAnve.jasper", "ConstanciaAnve.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }
}
