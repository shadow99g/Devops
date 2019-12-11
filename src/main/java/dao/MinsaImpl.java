package dao;

import interfaces.MinsaI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.MinsaM;

public class MinsaImpl extends Conexion implements MinsaI {

    @Override
    public void agregarMinsa(MinsaM minsa) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO MINSA(FEIMIN,RESMIN,CODEMP) VALUES(TO_DATE(?,'DD/MM/YYYY'),?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, minsa.getFEIMIN());
            ps.setString(2, minsa.getRESMIN());
            ps.setString(3, minsa.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
    
    @Override
    public void modificarMinsa(MinsaM minsa) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE MINSA SET FEIMIN=TO_DATE(?,'DD/MM/YYYY'),RESMIN=?,CODEMP=? WHERE CODMIN LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, minsa.getFEIMIN());
            ps.setString(2, minsa.getRESMIN());
            ps.setString(3, minsa.getCODEMP());
            ps.setString(4, minsa.getCODMIN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }
    
    @Override
    public void eliminarMinsa(MinsaM minsa) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE MINSA SET ESTMIN = 'I' WHERE CODMIN LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, minsa.getCODMIN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public List<MinsaM> listarMinsa() throws Exception {
        List<MinsaM> listaMinsa;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_MINSA WHERE ESTMIN LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            listaMinsa = new ArrayList<>();
            MinsaM minsa;
            while (rs.next()) {
                minsa = new MinsaM();
                minsa.setCODMIN(rs.getString("CODMIN"));
                minsa.setFEIMIN(rs.getString("FEIMIN"));
                minsa.setRESMIN(rs.getString("RESMIN"));
                minsa.setCODEMP(rs.getString("CODEMP"));
                minsa.setEMPLEADO(rs.getString("EMPLEADO"));
                listaMinsa.add(minsa);
            }
            return listaMinsa;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
