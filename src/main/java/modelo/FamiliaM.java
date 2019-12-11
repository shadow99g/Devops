package modelo;

import java.util.Date;
import lombok.Data;

@Data
public class FamiliaM {
    
    private String CODFAM, NOMFAM, APEFAM,
                   PARFAM, OCUFAM, TELFAM,
                   CELFAM, ETCFAM, VIVFAM,
                   ESTFAM, TIPFAM, SEXFAM,
                   BONFAM, INCFAM, CODEMP;
    private Date FENFAM;
}