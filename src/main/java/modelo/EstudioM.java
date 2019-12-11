package modelo;

import java.util.Date;
import lombok.Data;

@Data
public class EstudioM {

    private String CODEST; //Codigo de estudio
    private String EDUESTCASE;
    private String EDUEST; //Educacion del estudiante Basico(Primaria - Secundaria) Superior(Universidad - Instituto)
    private String CENEST; //Centro Educativo
    private Date DESEST; //Desde cuando Comenzo
    private Date HASEST; //Cuando termino
    private String CULEST; //Si culmino o no 
    private String GRAACAEST; //Grado titulo o Secundaria
    private String TIPEST; // Tipo (Estudio Superior o Estudio Basico) 
    private String ESTEST; // Estado
    private String NOMEMP; //Nombre del empleado
    private String CODEMP; //Codigo de empleado 
    private String CODCEN; //Codigo de grado
    private String CODESP; //Especialidad    
    private String NOMCUR; //Especialidad    
    private String INSCAP; //Especialidad    
    private String HORCAP; //Especialidad    
    private String CODCAP; //Especialidad    

    private String NOMCEN;
    private String FINEST;
    private String NCOLEST;
    private String ACTEST;

    private String DESEST1; //Desde cuando Comenzo
    private String HASEST1; //Cuando termino
    private String TIPEST1; //Tipo de estudio.
}
