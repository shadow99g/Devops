package dao;

import interfaces.EspecialidadI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.EspecialidadM;

public class EspecialidadImpl extends Conexion implements EspecialidadI {

    @Override
    public void agregarEspecialidad(EspecialidadM especialidad) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO ESPECIALIDAD (NOMESP) VALUES (?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, especialidad.getNOMESP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarEspecialidad(EspecialidadM especialidad) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ESPECIALIDAD SET NOMESP = ? WHERE CODESP = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, especialidad.getNOMESP());
            ps.setString(2, especialidad.getCODESP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarEspecialidad(EspecialidadM especialidad) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ESPECIALIDAD SET ESTESP = 'I' where CODESP = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, especialidad.getCODESP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<EspecialidadM> listarEspecialidad() throws Exception {
        this.conectar();
        List<EspecialidadM> listar;
        ResultSet rs;
        try {
            String sql = "SELECT CODESP, NOMESP FROM ESPECIALIDAD WHERE ESTESP = 'A' ORDER BY CODESP ASC ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listar = new ArrayList<>();
            EspecialidadM especialidad;
            while (rs.next()) {
                especialidad = new EspecialidadM();
                especialidad.setCODESP(rs.getString("CODESP"));
                especialidad.setNOMESP(rs.getString("NOMESP"));
                listar.add(especialidad);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<String> autoCompletarEspecialidad(String consEsp) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT UPPER(NOMESP) AS NOMESP FROM ESPECIALIDAD WHERE UPPER(NOMESP) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + consEsp + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("NOMESP"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public String obtenerCodigoEspecialidad(String apliCen) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODESP FROM ESPECIALIDAD WHERE UPPER(NOMESP) = UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, apliCen);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("NOMESP");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        } 
        
    }
}
