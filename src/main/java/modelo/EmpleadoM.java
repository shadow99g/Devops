package modelo;

import java.util.Date;
import lombok.Data;
import servicio.ConverterValue;

@Data
public class EmpleadoM {

    private String CODEMP, DNIEMP, NOMEMP, APAEMP, AMAEMP, RUCEMP, EMAEMP, TELEMP, CELEMP, GRSEMP, ESCEMP, COLEMP,
                   CAREMP, REFEMP, LEYEMP, DIREMP, ESTEMP, LIMEMP, REPEMP, NOAEMP, AESEMP,
                   SEXEMP, PANEMP, NUHEMP, PASEMP, CUSEMP, BREEMP, RLABEMP, CATREMEMP, UNIORGEMP;
    private Date FECEMP, FEIEMP, FENEMP, FEAEMP;
    private String CODUBI, CODREN;
    
    public String getFECEMPFORMAT(){
        return ConverterValue.fechaToStringFormatView(this.FECEMP);
    }
}
