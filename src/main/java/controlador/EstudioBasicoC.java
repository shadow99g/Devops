package controlador;

import dao.CentroImpl;
import dao.EmpleadoImpl;
import dao.EspecialidadImpl;
import dao.EstudioBasicoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.EstudioBasicoM;

@Data
@Named(value = "estudioBasicoC")
@SessionScoped
public class EstudioBasicoC implements Serializable {

    private List<EstudioBasicoM> lstEstudioBasico;
    private List<EstudioBasicoM> lstEstudioBasicoFilter;
    private EstudioBasicoM estudioBasico = new EstudioBasicoM();
    private EstudioBasicoM estudioSuperior = new EstudioBasicoM();
    private EstudioBasicoM selectedEstudio;

    @PostConstruct
    public void init() {
        try {
            listarEstudioBasico();
        } catch (Exception e) {
        }
    }

    public void agregarEstudioB() throws Exception {
        EstudioBasicoImpl estudioB;
        EmpleadoImpl empleado;
        CentroImpl centro;
        try {
            estudioB = new EstudioBasicoImpl();
            empleado = new EmpleadoImpl();
            centro = new CentroImpl();
            estudioBasico.setCODEMP(empleado.obtenerCodigoEmpleado(estudioBasico.getCODEMP()));
            estudioBasico.setCODCEN(centro.obtenerCodigoCentro(estudioBasico.getCODCEN()));
            estudioB.agregarEstudioBasico(estudioBasico);
            listarEstudioBasico();
            limpiarEstudioBasico();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", null));
            throw e;
        }
    }

    public void modificarEstudioB() throws Exception {
        EstudioBasicoImpl estudioB;
        try {
            estudioB = new EstudioBasicoImpl();
            estudioB.modificarEstudioBasico(estudioBasico);
            listarEstudioBasico();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ACTUALIZADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", null));
            throw e;
        }
    }

    public void listarEstudioBasico() throws Exception {
        EstudioBasicoImpl estudioB;
        try {
            estudioB = new EstudioBasicoImpl();
            lstEstudioBasico = estudioB.listarEstudioBasico();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarEstudioBasico() throws Exception {
        EstudioBasicoImpl estudioBS;
        try {
            estudioBS = new EstudioBasicoImpl();
            estudioBS.eliminarEstudioBasico(estudioBasico);
            listarEstudioBasico();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void limpiarEstudioBasico() throws Exception {
        try {
            estudioBasico.setEDUESTBAS(null);
            estudioBasico.setCULESTBAS(null);
            estudioBasico.setCODCEN(null);
            estudioBasico.setDESESTBAS(null);
            estudioBasico.setHASESTBAS(null);
            estudioBasico.setGRAESTBAS(null);
        } catch (Exception e) {
            throw e;
        }
    }

    /* METODOS PARA EL AMEL DE ESTUDIOS SUPERIORES */
    public void agregarEstudioSuperior() throws Exception {
        EstudioBasicoImpl estudioS;
        EmpleadoImpl empleado;
        EspecialidadImpl especialidad;
        try {
            estudioS = new EstudioBasicoImpl();
            empleado = new EmpleadoImpl();
            especialidad = new EspecialidadImpl();
            estudioSuperior.setCODEMP(empleado.obtenerCodigoEmpleado(estudioSuperior.getCODEMP()));
            estudioSuperior.setCODESP(especialidad.obtenerCodigoEspecialidad(estudioSuperior.getCODESP()));
            estudioS.agregarEstudioSuperior(estudioSuperior);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            throw e;
        }
    }

}
