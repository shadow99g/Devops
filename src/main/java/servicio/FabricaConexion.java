package servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.Data;

@Data
public class FabricaConexion {

//    public int DesarrolloInterno = 1;
//    public int DesarrolloExterno = 2;
    public int ConexionExterna = 3;

    public Connection getConexion(int Servidor) throws SQLException {
        switch (Servidor) {
//            case 1: /*Conexion con la IP Interna de VG*/
//
//                return DriverManager.getConnection("jdbc:oracle:thin:@192.168.168.3:1521:XE", "LEGAJOS", "Vallencito_2019");
//            case 2: /*Conexion con la IP Externa de VG*/

//                return DriverManager.getConnection("jdbc:oracle:thin:@200.37.85.115:1521:XE", "LEGAJOS", "Vallencito_2019");
            case 3: /*Conexion reciente*/

                return DriverManager.getConnection("jdbc:oracle:thin:@35.229.77.157:1521:XE", "Legajo", "vallegrande2018");
            default:
                return null;
        }
    }
}
