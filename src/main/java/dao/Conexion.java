package dao;

import java.sql.Connection;
import java.sql.SQLException;
import lombok.Data;
import servicio.FabricaConexion;

@Data
public class Conexion {

    private Connection cn = null;

    public void conectar() throws SQLException {
        FabricaConexion fcn;
        try {
            if (cn == null) {
                fcn = new FabricaConexion();
                Class.forName("oracle.jdbc.OracleDriver");
                cn = fcn.getConexion(fcn.ConexionExterna);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error -> " + e.getMessage());
        }
    }

    public void cerrar() throws SQLException {
        try {
            if (cn != null) {
                if (cn.isClosed() == false) {
                    cn.close();
                }
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Conexion dao = new Conexion();
        dao.conectar();
        if (dao.getCn() != null) {
            System.out.println("Conectado");
        } else {
            System.err.println("Coneccion es null Error");
        }
    }

}
