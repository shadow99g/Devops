package modelo;

import java.util.Date;
import lombok.Data;

@Data
public class EstudioBasicoM {

    /* VARIABLES PARA EL REGISTRO DE ESTUDIOS BASICOS */
    private String CODESTBAS; //Codigo de la tabla EStudios Basicos
    private String EDUESTBAS; //Educación Primaria (P), Secundaria (S).
    private String CULESTBAS; //Culminación de sus estudios
    private String DESESTBAS;   //Fecha de inicio sus Estudio
    private String HASESTBAS;   //Fecha que finalizo sus estudios
    private String GRAESTBAS;
    private String ESTESTBAS; //Estado de la tabla 

    /* VARIABLES UTILIZADAS EN LOS DOS REGISTROS (SUPERIOR Y BASICO) */
    private String CODEMP;    //Codigo de la tabla Empleado
    private String CODCEN;    //Codigo de la tabla centro 

    /* VARIABLES PARA EL REGISTRO DE ESTUDIOS SUPERIORES */
    private String CODEST;    //Codigo de la Tabla Estudio
    private String EDUEST;    //Por default Superior
    private String DESEST;      //Fecha de inicio de sus estudios superiores
    private String HASEST;      //Fecha que finalizo sus estudios superiores
    private String GRAACAEST; //Grado academico que obtuvo (Bachiller, Maestria, etc)
    private String CODESP;    //Codigo de la tabla Especialidad  
    private String NCOLEST;   //Numero de colegiatura
    private String TIPEST;    //Numero de colegiatura
    private String ESTEST;    //Numero de colegiatura

}
