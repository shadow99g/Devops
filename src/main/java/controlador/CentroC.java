
package controlador;

import dao.CentroImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Data;
import modelo.CentroM;

@Data
@Named(value = "centroC")
@SessionScoped
public class CentroC implements Serializable {

    CentroM centro = new CentroM();
    private List<CentroM> lstCentro;
    private CentroM editCentro;
    CentroImpl daoCen;
    
    
    @PostConstruct
    public void inicio(){
        try {
            listarCentro();
        } catch (Exception e) {
        }
    }
    
    public void instancia(){
        daoCen = new CentroImpl();
    }
    
    public void limpiar(){
        try {
            centro = new CentroM();
        } catch (Exception e) {
        }
    }
    
    public void agregarCentro() throws Exception{
        try {
            daoCen.agregarCentro(centro);
            listarCentro();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void listarCentro() throws Exception{
        try {
            daoCen = new CentroImpl();
            lstCentro = daoCen.listarCentro();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarCentro() throws Exception{
        try {
            daoCen.modificarCentro(editCentro);
            listarCentro();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void eliminarCentro(){
        try {
            daoCen.eliminarCentro(centro);
            listarCentro();
        } catch (Exception e) {
        }
    }
    
}
