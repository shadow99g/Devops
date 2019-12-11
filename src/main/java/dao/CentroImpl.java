package dao;

import interfaces.CentroI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.CentroM;

public class CentroImpl extends Conexion implements CentroI {

    @Override
    public void agregarCentro(CentroM centro) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO CENTRO (NOMCEN) VALUES (?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, centro.getNOMCEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarCentro(CentroM centro) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE CENTRO SET NOMCEN = ? WHERE CODCEN = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, centro.getNOMCEN());
            ps.setString(2, centro.getCODCEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarCentro(CentroM centro) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE CENTRO SET ESTCEN = 'I' WHERE CODCEN = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, centro.getCODCEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<CentroM> listarCentro() throws Exception {
        this.conectar();
        List<CentroM> listar;
        ResultSet rs;
        try {
            String sql = "SELECT CODCEN,NOMCEN FROM CENTRO WHERE ESTCEN = 'A' ORDER BY CODCEN ASC";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listar = new ArrayList<>();
            CentroM centro;
            while (rs.next()) {
                centro = new CentroM();
                centro.setCODCEN(rs.getString("CODCEN"));
                centro.setNOMCEN(rs.getString("NOMCEN"));
                listar.add(centro);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<String> autoCompletarCentro(String consCen) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT UPPER(NOMCEN) AS NOMCEN FROM CENTRO WHERE UPPER(NOMCEN) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + consCen + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("NOMCEN"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public String obtenerCodigoCentro(String apliCen) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODCEN FROM CENTRO WHERE UPPER(NOMCEN) = UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, apliCen);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODCEN");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        
    }
}
