package dao;

import interfaces.DesplazamientoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.DesplazamientoM;

public class DesplazamientoImpl extends Conexion implements DesplazamientoI {

    Format formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void agregarDesplazamiento(DesplazamientoM desplazamiento) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO DESPLAZAMIENTO (TIPDES,FECDES,RESDES,CARDES,AREADES,LPRDES,LDRDES,FECINIDES,FECFINDES,OTRODES,CODEMP,ESTDES) "
                    + "VALUES (?,TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, desplazamiento.getTIPDES());
            ps.setString(2, desplazamiento.getFECDES());
            ps.setString(3, desplazamiento.getRESDES());
            ps.setString(4, desplazamiento.getCARDES());
            ps.setString(5, desplazamiento.getAREADES());
            ps.setString(6, desplazamiento.getLPRDES());
            ps.setString(7, desplazamiento.getLDRDES());
            ps.setString(8, desplazamiento.getFECINIDES());
            ps.setString(9, desplazamiento.getFECFINDES());
            ps.setString(10, desplazamiento.getOTRODES());
            ps.setString(11, desplazamiento.getCODEMP());
            ps.setString(12, "A"); //ESTADO 
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarDesplazamiento(DesplazamientoM desplazamiento) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE DESPLAZAMIENTO SET TIPDES=?,FECDES=TO_DATE(?,'DD/MM/YYYY'),CARDES=? WHERE CODDES=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, desplazamiento.getTIPDES());
            ps.setString(2, desplazamiento.getFECDES());
            ps.setString(3, desplazamiento.getCARDES());
            ps.setString(4, desplazamiento.getCODDES());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }

    }

    @Override
    public void eliminarDesplazamiento(DesplazamientoM desplazamiento) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE DESPLAZAMIENTO SET ESTDES = 'I' WHERE CODDES LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, desplazamiento.getCODDES());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<DesplazamientoM> listarDesplazamiento() throws Exception {
        List<DesplazamientoM> listaDesplazamiento;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_DESPLAZAMIENTO";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listaDesplazamiento = new ArrayList<>();
            DesplazamientoM desplazamiento;
            while (rs.next()) {
                desplazamiento = new DesplazamientoM();
                desplazamiento.setCODDES(rs.getString("CODDES"));
                desplazamiento.setTIPDES(rs.getString("TIPDES"));
                desplazamiento.setNOMEMP(rs.getString("NOMEMP"));
                desplazamiento.setAPEEMP(rs.getString("APAEMP"));
                desplazamiento.setRESDES(rs.getString("RESDES"));
                desplazamiento.setFECDES(rs.getString("FECDES"));
                desplazamiento.setFECINIDES(rs.getString("FECINIDES"));
                desplazamiento.setFECFINDES(rs.getString("FECFINDES"));
                listaDesplazamiento.add(desplazamiento);
            }
            return listaDesplazamiento;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

}
