package modelo;

import java.io.Serializable;
import lombok.Data;

@Data
public class UbigeoM implements Serializable {

    private String CODUBI; //Código de Ubigeo
    private String DISUBI; //Distrito
    private String PROUBI; //Provincia
    private String DEPUBI; //Departamento

}
