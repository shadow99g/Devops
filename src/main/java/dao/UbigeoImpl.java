package dao;

import interfaces.UbigeoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbigeoImpl extends Conexion implements UbigeoI {

    @Override
    public List<String> autocompleteUbigeo(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT UPPER(DEPUBI ||', '|| PROUBI ||', '|| DISUBI) AS UBI FROM UBIGEO WHERE UPPER(DEPUBI ||', '|| PROUBI ||', '|| DISUBI) LIKE UPPER(?) AND rownum <= 10";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("UBI"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public String obtenerCodigoUbigeo(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODUBI FROM UBIGEO WHERE UPPER(DEPUBI ||', '|| PROUBI ||', '|| DISUBI) = ? AND rownum <= 10";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODUBI");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
