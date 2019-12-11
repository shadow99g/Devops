package interfaces;

import java.util.List;
import modelo.EstudioBasicoM;

public interface EstudioBasicoI {

    /* METODOS PARA EL AMEL DE ESTUDIOS BASICOS (PRIMARIA Y SECUNDARIA) */
    void agregarEstudioBasico(EstudioBasicoM estudio) throws Exception;

    void modificarEstudioBasico(EstudioBasicoM estudio) throws Exception;

    void eliminarEstudioBasico(EstudioBasicoM estudio) throws Exception;

    List<EstudioBasicoM> listarEstudioBasico() throws Exception;


    /* METODOS PARA EL AMEL DE ESTUDIOS SUPERIORES */
    void agregarEstudioSuperior(EstudioBasicoM estudioSuperior) throws Exception;

    void modificarEstudioSuperior(EstudioBasicoM estudioSuperior) throws Exception;

    void eliminarEstudioSuperior(EstudioBasicoM estudioSuperior) throws Exception;

    List<EstudioBasicoM> listarEstudioSuperior() throws Exception;

//    List<EstudioBasicoM> listarEstudioSecundario() throws Exception;
}
