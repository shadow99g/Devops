package modelo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CapacitacionM implements Serializable {

    private String CODCAP;      //Codigo de Cpacitacion
    private String TIPPARCAP;   //Asistente (A), Doctor (D), Enfermera (E)
    private String CURCAP;      //Curso en que se basó la capacitación
    private String FECEJECAP;   //Se registra la fecha en que se desarrolló el curso
    private String INSCAP;      //Institucion en la que realizo su capacitacion
    private Date FECINICAP;     //fecha en la que inicio la capacitacion
    private Date FECFINCAP;     //fecha en la que culmino la capacitación
    private String FINCAP;      //Quien Financió el curso
    private String HORCAP;      //Numero de Horas que tomó la capacitación
    private String TIPCAP="X";      //Cuando el empleado obtiene una costancia interna (i) / externa (E)
    private String NLIBCAP;     //Numero de Libros
    private String FOLCAP;      //Folio
    private String NREGCAP;     //Numero de Registros, se reinicia cada año, paginado de 20
    private String NNDICAP;     //Nombre del Director
    private String CNDICAP;     //Cargo de Nivel Director(Nombre de Cargo)
    private String NNOFCAP;     //Nombre de Director(a) de la Red
    private String CNOFCAP;     //Nombre de cargo
    private String NNCOCAP;     //Nombre de la coordinadora de la Red
    private String CNCOCAP;     //Cargo de Nivel Coordinadora(Nombre de cargo)
    private String FECCAP;        //Fecha cunado se emite la constancia
    private String CODCEN;      //Fecha cunado se emite la constancia
    private String CODEMP;      //Codigo de Empleado
    private String ESTCAP;      //Estado de Capacitacion como Activo (A) o inactivo (I)

    private String EMPLEADO;      //Nombre de Empleado
    private String CENTRO;      //Nombre de Centro

    
//    public String getTIPPARCAP() {
//        String TIPPARCAP = "";
//        if (TIPPARCAP != null) {
//            switch (TIPPARCAP) {
//                case "A":
//                    TIPPARCAP="Asistente";
//                    break;
//                case "D":
//                    TIPPARCAP= "Doctor";
//                    break;
//                case "E":
//                    TIPPARCAP= "Enfermera";
//                    break;
//                default:
//                    return null;
//            }
//        }
//        return null;
//    }
//    public String getDescTIPPARCAP(String tipo) {
//        switch (tipo) {
//            case "A":
//                return "Asistente";
//            case "D":
//                return "Director";
//            case "E":
//                return "Enfermera";
//            default:
//                return null;
//        }
//    }
}
