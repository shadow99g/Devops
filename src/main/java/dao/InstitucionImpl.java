package dao;

import interfaces.InstitucionI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.InstitucionM;

public class InstitucionImpl extends Conexion implements InstitucionI {

    @Override
    public void agregarInstitucion(InstitucionM institucion) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO INSTITUCION(NOMINS,CARINS,SECINS,FEIINS,FETINS,SITLABINS,CODEMP) "
                    + "VALUES(?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, institucion.getNOMINS());
            ps.setString(2, institucion.getCARINS());
            ps.setString(3, institucion.getSECINS());
            ps.setString(4, institucion.getFEIINS());
            ps.setString(5, institucion.getFETINS());
            ps.setString(6, institucion.getSITLABINS());
            ps.setString(7, institucion.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarInstitucion(InstitucionM institucion) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE INSTITUCION SET NOMINS=?,CARINS=?,SECINS=?,FEIINS=TO_DATE(?,'DD/MM/YYYY'),"
                    + "FETINS=TO_DATE(?,'DD/MM/YYYY'),CODEMP=? WHERE CODINS LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, institucion.getNOMINS());
            ps.setString(2, institucion.getCARINS());
            ps.setString(3, institucion.getSECINS());
            ps.setString(4, institucion.getFEIINS());
            ps.setString(5, institucion.getFETINS());
            ps.setString(6, institucion.getCODEMP());
            ps.setString(7, institucion.getCODINS());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void eliminarInstitucion(InstitucionM institucion) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE INSTITUCION SET ESTINS = 'I' WHERE CODINS LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, institucion.getCODINS());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public List<InstitucionM> listarInstitucion() throws Exception {
        List<InstitucionM> listaInstitucion;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_INSTITUCION";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listaInstitucion = new ArrayList<>();
            InstitucionM institucion;
            while (rs.next()) {
                institucion = new InstitucionM();
                institucion.setCODINS(rs.getString("CODINS"));
                institucion.setNOMINS(rs.getString("NOMINS"));
                institucion.setSECINS(rs.getString("SECINS"));
                institucion.setDEPINS(rs.getString("DEPINS"));
                institucion.setTELINS(rs.getString("TELINS"));
                institucion.setFEIINS(rs.getString("FEIINS"));
                institucion.setFETINS(rs.getString("FETINS"));
                institucion.setCARINS(rs.getString("CARINS"));
                institucion.setNPLAINS(rs.getString("NPLAINS"));
                institucion.setSITLABINS(rs.getString("SITLABINS"));
                institucion.setCODREN(rs.getString("CODREN"));
                institucion.setCODEMP(rs.getString("CODEMP"));
                institucion.setEMPLEADO(rs.getString("EMPLEADO"));
                institucion.setRENAES(rs.getString("RENAES"));
                listaInstitucion.add(institucion);
            }
            return listaInstitucion;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }

    }

    @Override
    public List<String> autocompleteRenaes(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT UPPER(NESREN||'-'||CLAREN) AS RENAES FROM RENAES WHERE UPPER(NESREN||'-'||CLAREN) LIKE UPPER(?) AND rownum <= 10";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("RENAES"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public String obtenerCodigoRenaes(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODREN FROM RENAES WHERE UPPER(NESREN||'-'||CLAREN) = ? AND rownum <= 10";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODREN");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
