package dao;

import interfaces.AdministracionPublicaI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.AdministracionPublicaM;

public class AdministracionPublicaImpl extends Conexion implements AdministracionPublicaI{

    @Override
    public void agregarAdminPublica(AdministracionPublicaM adminPublica) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO ADMINISTRACION_PUBLICA(FECADMPUB,RESADMPUB,CODEMP) VALUES(TO_DATE(?,'DD/MM/YYYY'),?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, adminPublica.getFECADMPUB());
            ps.setString(2, adminPublica.getRESADMPUB());
            ps.setString(3, adminPublica.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
    
    @Override
    public void modificarAdminPublica(AdministracionPublicaM adminPublica) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ADMINISTRACION_PUBLICA SET FECADMPUB=TO_DATE(?,'DD/MM/YYYY'),RESADMPUB=?,CODEMP=? WHERE CODADMPUB LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, adminPublica.getFECADMPUB());
            ps.setString(2, adminPublica.getRESADMPUB());
            ps.setString(3, adminPublica.getCODEMP());
            ps.setString(4, adminPublica.getCODADMPUB());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
    
    @Override
    public void eliminarAdminPublica(AdministracionPublicaM adminPublica) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ADMINISTRACION_PUBLICA SET ESTADMPUB = 'I' WHERE CODADMPUB LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, adminPublica.getCODADMPUB());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public List<AdministracionPublicaM> listarAdminpublica() throws Exception {
        List<AdministracionPublicaM> listaAdminPublica;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_ADMINISTRACION_PUBLICA WHERE ESTADMPUB LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            listaAdminPublica = new ArrayList<>();
            AdministracionPublicaM adminPublica;
            while(rs.next()){
                adminPublica = new AdministracionPublicaM();
                adminPublica.setCODADMPUB(rs.getString("CODADMPUB"));
                adminPublica.setFECADMPUB(rs.getString("FECADMPUB"));
                adminPublica.setRESADMPUB(rs.getString("RESADMPUB"));
                adminPublica.setCODEMP(rs.getString("CODEMP"));
                adminPublica.setEMPLEADO(rs.getString("EMPLEADO"));
                listaAdminPublica.add(adminPublica);
            }
            return listaAdminPublica;
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
}
