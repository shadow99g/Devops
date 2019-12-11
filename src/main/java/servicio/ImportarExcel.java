/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class ImportarExcel {

    StreamedContent file;

    public ImportarExcel() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/ExportarExcel.jar");
        file = new DefaultStreamedContent(stream, "resources/jar", "ExportarExcel.jar");
    }

    public StreamedContent getFile() {
        return file;
    }
}
