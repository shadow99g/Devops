package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DialectoDetalleM;
import interfaces.DialectoDetalleI;

public class DialectoDetalleImpl extends Conexion implements DialectoDetalleI {

    @Override
    public void agregarDialectoDetalle(DialectoDetalleM detalle) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO DIALECTO_DETALLE(LEEDIADET,HABDIADET,ESCDIADET,ESTDIADET,CODEMP,CODDIA) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, detalle.getLEEDIADET());
            ps.setString(2, detalle.getHABDIADET());
            ps.setString(3, detalle.getESCDIADET());
            ps.setString(4, "A");
            ps.setString(5, detalle.getCODEMP());
            ps.setString(6, detalle.getCODDIA());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarDialectoDetalle(DialectoDetalleM detalle) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE DIALECTO_DETALLE SET LEEDIADET =?, HABDIADET=?,ESCDIADET=?,CODEMP=?,CODDIA=? WHERE CODDIADET =?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, detalle.getLEEDIADET());
            ps.setString(2, detalle.getHABDIADET());
            ps.setString(3, detalle.getESCDIADET());
//            ps.setString(5, detalle.getESTDIADET());
            ps.setString(4, detalle.getCODEMP());
            ps.setString(5, detalle.getCODDIA());
            ps.setString(6, detalle.getCODDIADET());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarDialectoDetalle(DialectoDetalleM detalle) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE DIALECTO_DETALLE SET ESTDIADET = 'I' WHERE CODDIADET LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, detalle.getCODDIADET());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<DialectoDetalleM> listarDialectoDetalle() throws Exception {
        List<DialectoDetalleM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT CODDIADET,LEEDIADET,HABDIADET,ESCDIADET,ESTDIADET,(EMPLEADO.NOMEMP||' '||EMPLEADO.APAEMP||' '||EMPLEADO.AMAEMP) AS CODEMP,DIALECTO.NOMDIA AS CODDIA\n"
                    + "                  FROM DIALECTO_DETALLE\n"
                    + "                  INNER JOIN EMPLEADO ON DIALECTO_DETALLE.CODEMP = EMPLEADO.CODEMP\n"
                    + "                    INNER JOIN DIALECTO ON DIALECTO_DETALLE.CODDIA = DIALECTO.CODDIA\n"
                    + "                    WHERE ESTDIADET LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            DialectoDetalleM detalle;
            while (rs.next()) {
                detalle = new DialectoDetalleM();
                detalle.setCODDIADET(rs.getString("CODDIADET"));
                detalle.setLEEDIADET(rs.getString("LEEDIADET"));
                detalle.setHABDIADET(rs.getString("HABDIADET"));
                detalle.setESCDIADET(rs.getString("ESCDIADET"));
                detalle.setESTDIADET(rs.getString("ESTDIADET"));
                detalle.setCODEMP(rs.getString("CODEMP"));
                detalle.setCODDIA(rs.getString("CODDIA"));
                lista.add(detalle);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public String obtenerCodigoDialecto(String Dialecto) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODDIA FROM DIALECTO WHERE NOMDIA LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Dialecto);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODDIA");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<String> autocompleteDialecto(String Consulta) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT NOMDIA FROM DIALECTO WHERE UPPER(NOMDIA) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("NOMDIA"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

}
