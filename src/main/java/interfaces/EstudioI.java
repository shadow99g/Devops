package interfaces;

import java.util.List;
import modelo.EstudioM;

public interface EstudioI {


    void agregarexperiencia(EstudioM estudio) throws Exception;

    List<EstudioM> listarExperiencia() throws Exception;

    void agregarExterno(EstudioM estudio) throws Exception;

    List<EstudioM> listarExterno() throws Exception;

    void modificarExperiencia(EstudioM estudio) throws Exception;

    void eliminarExperiencia(EstudioM estudio) throws Exception;

    void modificarExterno(EstudioM estudio) throws Exception;

    void eliminarExterno(EstudioM estudio) throws Exception;

}
