package dao;

import interfaces.EmpleadoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.EmpleadoM;
import servicio.ConverterValue;

public class EmpleadoImpl extends Conexion implements EmpleadoI {

    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public void agregarEmpleado(EmpleadoM empleado) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO EMPLEADO(DNIEMP,NOMEMP,APAEMP,AMAEMP,RUCEMP,EMAEMP,TELEMP,CELEMP,FECEMP,GRSEMP,ESCEMP"
                    + ",COLEMP,CAREMP,REFEMP,LEYEMP,FEIEMP,FENEMP,DIREMP,ESTEMP,LIMEMP,REPEMP,NOAEMP,FEAEMP,AESEMP,SEXEMP,PANEMP,NUHEMP"
                    + ",PASEMP,CUSEMP,BREEMP,RLABEMP,CATREMEMP,UNIORGEMP,CODUBI,CODREN"
                    + ") VALUES(?,?,?,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY')"
                    + ",?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, empleado.getDNIEMP());
            ps.setString(2, empleado.getNOMEMP());
            ps.setString(3, empleado.getAPAEMP());
            ps.setString(4, empleado.getAMAEMP());
            ps.setString(5, empleado.getRUCEMP());
            ps.setString(6, empleado.getEMAEMP());
            ps.setString(7, empleado.getTELEMP());
            ps.setString(8, empleado.getCELEMP());
            ps.setString(9, formatter.format(empleado.getFECEMP()));
            ps.setString(10, empleado.getGRSEMP());
            ps.setString(11, empleado.getESCEMP());
            ps.setString(12, empleado.getCOLEMP());
            ps.setString(13, empleado.getCAREMP());
            ps.setString(14, empleado.getREFEMP());
            ps.setString(15, empleado.getLEYEMP());
            ps.setString(16, formatter.format(empleado.getFEIEMP()));
            ps.setString(17, formatter.format(empleado.getFENEMP()));
            ps.setString(18, empleado.getDIREMP());
            ps.setString(19, "A");
            ps.setString(20, empleado.getLIMEMP());
            ps.setString(21, empleado.getREPEMP());
            ps.setString(22, empleado.getNOAEMP());
            ps.setString(23, formatter.format(empleado.getFEAEMP()));
            ps.setString(24, empleado.getAESEMP());
            ps.setString(25, empleado.getSEXEMP());
            ps.setString(26, empleado.getPANEMP());
            ps.setString(27, empleado.getNUHEMP());
            ps.setString(28, empleado.getPASEMP());
            ps.setString(29, empleado.getCUSEMP());
            ps.setString(30, empleado.getBREEMP());
            ps.setString(31, empleado.getRLABEMP());
            ps.setString(32, empleado.getCATREMEMP());
            ps.setString(33, empleado.getUNIORGEMP());
            ps.setString(34, empleado.getCODUBI());
            ps.setString(35, empleado.getCODREN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarEmpleado(EmpleadoM empleado) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE EMPLEADO SET DNIEMP=?,NOMEMP=?,APAEMP=?,AMAEMP=?,RUCEMP=?,EMAEMP=?,TELEMP=?,CELEMP=?"
                    + ",FECEMP=TO_DATE(?,'DD/MM/YYYY'),GRSEMP=?,ESCEMP=?,COLEMP=?,CAREMP=?,REFEMP=?,LEYEMP=?"
                    + ",FEIEMP=TO_DATE(?,'DD/MM/YYYY'),FENEMP=TO_DATE(?,'DD/MM/YYYY'),DIREMP=?,ESTEMP=?,LIMEMP=?,REPEMP=?,NOAEMP=?"
                    + ",FEAEMP=TO_DATE(?,'DD/MM/YYYY'),AESEMP=?,SEXEMP=?,PANEMP=?,NUHEMP=?,PASEMP=?,CUSEMP=?,BREEMP=?"
                    + ",RLABEMP=?,CATREMEMP=?,UNIORGEMP=?,CODUBI=?,CODREN=? WHERE CODEMP LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, empleado.getDNIEMP());
            ps.setString(2, empleado.getNOMEMP());
            ps.setString(3, empleado.getAPAEMP());
            ps.setString(4, empleado.getAMAEMP());
            ps.setString(5, empleado.getRUCEMP());
            ps.setString(6, empleado.getEMAEMP());
            ps.setString(7, empleado.getTELEMP());
            ps.setString(8, empleado.getCELEMP());
            ps.setString(9, formatter.format(empleado.getFECEMP()));
            ps.setString(10, empleado.getGRSEMP());
            ps.setString(11, empleado.getESCEMP());
            ps.setString(12, empleado.getCOLEMP());
            ps.setString(13, empleado.getCAREMP());
            ps.setString(14, empleado.getREFEMP());
            ps.setString(15, empleado.getLEYEMP());
            ps.setString(16, formatter.format(empleado.getFEIEMP()));
            ps.setString(17, formatter.format(empleado.getFENEMP()));
            ps.setString(18, empleado.getDIREMP());
            ps.setString(19, empleado.getESTEMP());
            ps.setString(20, empleado.getLIMEMP());
            ps.setString(21, empleado.getREPEMP());
            ps.setString(22, empleado.getNOAEMP());
            ps.setString(23, formatter.format(empleado.getFEAEMP()));
            ps.setString(24, empleado.getAESEMP());
            ps.setString(25, empleado.getSEXEMP());
            ps.setString(26, empleado.getPANEMP());
            ps.setString(27, empleado.getNUHEMP());
            ps.setString(28, empleado.getPASEMP());
            ps.setString(29, empleado.getCUSEMP());
            ps.setString(30, empleado.getBREEMP());
            ps.setString(31, empleado.getRLABEMP());
            ps.setString(32, empleado.getCATREMEMP());
            ps.setString(33, empleado.getUNIORGEMP());
            ps.setString(34, empleado.getCODUBI());
            ps.setString(35, empleado.getCODREN());
            ps.setString(36, empleado.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarEmpleado(EmpleadoM empleado) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE EMPLEADO SET ESTEMP = 'I' WHERE CODEMP LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, empleado.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<EmpleadoM> listarEmpleado() throws Exception {
        List<EmpleadoM> listaEmpleado;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT CODEMP,DNIEMP,NOMEMP,APAEMP,AMAEMP,RUCEMP,EMAEMP,TELEMP,CELEMP,TO_CHAR(FECEMP,'DD/MM/YYYY') FECEMP"
                    + ",GRSEMP,ESCEMP,COLEMP,CAREMP,REFEMP,LEYEMP,TO_CHAR(FEIEMP,'DD/MM/YYYY') FEIEMP,TO_CHAR(FENEMP,'DD/MM/YYYY') FENEMP"
                    + ",DIREMP,ESTEMP,LIMEMP,REPEMP,NOAEMP,TO_CHAR(FEAEMP,'DD/MM/YYYY') FEAEMP,AESEMP,SEXEMP,PANEMP,NUHEMP,PASEMP,CUSEMP"
                    + ",BREEMP,RLABEMP,CATREMEMP,UNIORGEMP,CODUBI,CODREN FROM EMPLEADO WHERE ESTEMP LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listaEmpleado = new ArrayList<>();
            EmpleadoM empleado;
            while (rs.next()) {
                empleado = new EmpleadoM();
                empleado.setCODEMP(rs.getString("CODEMP"));
                empleado.setDNIEMP(rs.getString("DNIEMP"));
                empleado.setNOMEMP(rs.getString("NOMEMP"));
                empleado.setAPAEMP(rs.getString("APAEMP"));
                empleado.setAMAEMP(rs.getString("AMAEMP"));
                empleado.setRUCEMP(rs.getString("RUCEMP"));
                empleado.setEMAEMP(rs.getString("EMAEMP"));
                empleado.setTELEMP(rs.getString("TELEMP"));
                empleado.setCELEMP(rs.getString("CELEMP"));
                empleado.setFECEMP(ConverterValue.stringToFecha(rs.getString("FECEMP")));
                empleado.setGRSEMP(rs.getString("GRSEMP"));
                empleado.setESCEMP(rs.getString("ESCEMP"));
                empleado.setCOLEMP(rs.getString("COLEMP"));
                empleado.setCAREMP(rs.getString("CAREMP"));
                empleado.setREFEMP(rs.getString("REFEMP"));
                empleado.setLEYEMP(rs.getString("LEYEMP"));
                empleado.setFEIEMP(ConverterValue.stringToFecha(rs.getString("FEIEMP")));
                empleado.setFENEMP(ConverterValue.stringToFecha(rs.getString("FENEMP")));
                empleado.setDIREMP(rs.getString("DIREMP"));
                empleado.setESTEMP(rs.getString("ESTEMP"));
                empleado.setLIMEMP(rs.getString("LIMEMP"));
                empleado.setREPEMP(rs.getString("REPEMP"));
                empleado.setNOAEMP(rs.getString("NOAEMP"));
                empleado.setFEAEMP(ConverterValue.stringToFecha(rs.getString("FEAEMP")));
                empleado.setAESEMP(rs.getString("AESEMP"));
                empleado.setSEXEMP(rs.getString("SEXEMP"));
                empleado.setPANEMP(rs.getString("PANEMP"));
                empleado.setNUHEMP(rs.getString("NUHEMP"));
                empleado.setPASEMP(rs.getString("PASEMP"));
                empleado.setCUSEMP(rs.getString("CUSEMP"));
                empleado.setBREEMP(rs.getString("BREEMP"));
                empleado.setRLABEMP(rs.getString("RLABEMP"));
                empleado.setCATREMEMP(rs.getString("CATREMEMP"));
                empleado.setUNIORGEMP(rs.getString("UNIORGEMP"));
                empleado.setCODUBI(rs.getString("CODUBI"));     
                empleado.setCODREN(rs.getString("CODREN")); 
                listaEmpleado.add(empleado);
            }
            return listaEmpleado;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<String> autocompleteEmpleado(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT UPPER(APAEMP||' '||AMAEMP||' '||NOMEMP) AS EMPLEADO FROM EMPLEADO WHERE UPPER(APAEMP||' '||AMAEMP||' '||NOMEMP) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("EMPLEADO"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public String obtenerCodigoEmpleado(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODEMP FROM EMPLEADO WHERE UPPER(APAEMP||' '||AMAEMP||' '||NOMEMP) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODEMP");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

}
