package dao;

import interfaces.DialectoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DialectoImpl extends Conexion implements DialectoI {

    @Override
    public List<String> autocompleteDialecto(String Consulta) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT UPPER(NOMDIA) AS NOMDIAL FROM DIALECTO WHERE UPPER(NOMDIA) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("NOMDIAL"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public String obtenerCodigoDialecto(String codigo) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODDIA FROM DIALECTO WHERE UPPER(NOMDIA) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, codigo);
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

}
