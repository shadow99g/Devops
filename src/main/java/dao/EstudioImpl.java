package dao;

import interfaces.EstudioI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.EstudioM;

public class EstudioImpl extends Conexion implements EstudioI {

    Format formatter = new SimpleDateFormat("dd/MM/yyyy");

    /*Agregar Funcional*/
    @Override
    public void agregarexperiencia(EstudioM estudio) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO ESTUDIO (TIPEST,NCOLEST,DESEST,HASEST,CODEMP,CODCEN)VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudio.getTIPEST());
            ps.setString(2, estudio.getNCOLEST());
            ps.setString(3, formatter.format(estudio.getDESEST()));
            ps.setString(4, formatter.format(estudio.getHASEST()));
            ps.setString(5, estudio.getCODEMP());
            ps.setString(6, estudio.getCODCEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    /*Listar Funcional*/
    @Override
    public List<EstudioM> listarExperiencia() throws Exception {
        List<EstudioM> listar;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT CODEST,(EMPLEADO.NOMEMP||' '||EMPLEADO.APAEMP||' '||EMPLEADO.AMAEMP) AS EMPLEADO,ESTUDIO.CODEMP,CENTRO.NOMCEN AS CENTRO,NCOLEST,TO_CHAR(DESEST,'DD-MON-YYYY') as DESEST1,TO_CHAR(HASEST,'DD-MON-YYYY') AS HASEST1,DESEST,HASEST,CASE TIPEST WHEN 'G' THEN 'SECIGRA' WHEN 'R' THEN 'SERUMS' END AS TIPEST1, TIPEST FROM ESTUDIO INNER JOIN EMPLEADO ON ESTUDIO.CODEMP = EMPLEADO.CODEMP INNER JOIN CENTRO ON ESTUDIO.CODCEN = CENTRO.CODCEN WHERE  (TIPEST='R' OR TIPEST='G' ) AND ESTUDIO.ESTEST = 'A'order by EMPLEADO.NOMEMP DESC";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listar = new ArrayList<>();
            EstudioM estudio;
            while (rs.next()) {
                estudio = new EstudioM();
                estudio.setCODEMP(rs.getString("EMPLEADO"));
                estudio.setNOMEMP(rs.getString("CODEMP"));
                estudio.setCODEST(rs.getString("CODEST"));
                estudio.setCODCEN(rs.getString("CENTRO"));
                estudio.setNCOLEST(rs.getString("NCOLEST"));
                estudio.setDESEST(rs.getDate("DESEST"));
                estudio.setHASEST(rs.getDate("HASEST"));
                estudio.setDESEST1(rs.getString("DESEST1"));
                estudio.setHASEST1(rs.getString("HASEST1"));
                estudio.setTIPEST(rs.getString("TIPEST"));
                estudio.setTIPEST1(rs.getString("TIPEST1"));
                listar.add(estudio);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    /*Agregar Funcional*/
    @Override
    public void agregarExterno(EstudioM estudio) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO CAPACITACION (CURCAP,FECINICAP,FECFINCAP,FINCAP,HORCAP,TIPCAP,CODEMP,CODCEN)VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudio.getNOMCUR());
            ps.setString(2, formatter.format(estudio.getDESEST()));
            ps.setString(3, formatter.format(estudio.getHASEST()));
            ps.setString(4, estudio.getFINEST());
            ps.setString(5, estudio.getHORCAP());
            ps.setString(6, "X");
            ps.setString(7, estudio.getCODEMP());
            ps.setString(8, estudio.getCODCEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    /*Listar Funcional*/
    @Override
    public List<EstudioM> listarExterno() throws Exception {
        List<EstudioM> listar;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT CENTRO.CODCEN,CENTRO.NOMCEN,CODCAP, EMPLEADO.CODEMP,(NOMEMP||' ' ||APAEMP||' '||AMAEMP) AS NOMBRE,TO_CHAR(FECINICAP,'DD-MON-YYYY')AS FECINICAP1,TO_CHAR(FECFINCAP,'DD-MON-YYYY')AS FECFINCAP1,FECINICAP,FECFINCAP, FINCAP,HORCAP,CURCAP,ESTCAP FROM EMPLEADO INNER JOIN CAPACITACION ON EMPLEADO.CODEMP = CAPACITACION.CODEMP INNER JOIN CENTRO ON CAPACITACION.CODCEN = CENTRO.CODCEN WHERE ESTCAP ='A' AND TIPCAP='X'";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listar = new ArrayList<>();
            EstudioM estudio;
            while (rs.next()) {
                estudio = new EstudioM();
                estudio.setCODCEN(rs.getString("NOMCEN"));
                estudio.setCODCAP(rs.getString("CODCAP"));
                estudio.setCODEMP(rs.getString("CODEMP"));
                estudio.setNOMEMP(rs.getString("NOMBRE"));
                estudio.setDESEST(rs.getDate("FECINICAP"));
                estudio.setHASEST(rs.getDate("FECFINCAP"));
                estudio.setDESEST1(rs.getString("FECINICAP1"));
                estudio.setHASEST1(rs.getString("FECFINCAP1"));
                estudio.setFINEST(rs.getString("FINCAP"));
                estudio.setHORCAP(rs.getString("HORCAP"));
                estudio.setNOMCUR(rs.getString("CURCAP"));
                listar.add(estudio);
            }
            return listar;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    /*Modificar Funcional*/
    @Override
    public void modificarExperiencia(EstudioM estudio) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE ESTUDIO SET NCOLEST = ?,DESEST = ?,HASEST = ?,CODEMP = ?,CODCEN = ? WHERE CODEST = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudio.getNCOLEST());
            ps.setString(2, formatter.format(estudio.getDESEST()));
            ps.setString(3, formatter.format(estudio.getHASEST()));
            ps.setString(4, estudio.getNOMEMP());
            ps.setString(5, estudio.getCODCEN());
            ps.setString(6, estudio.getCODEST());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    /*Eliminar Funcional*/
    @Override
    public void eliminarExperiencia(EstudioM estudio) throws Exception {
        this.conectar();
        try {
            String sql = "update ESTUDIO set ESTEST = 'I' where CODEST = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudio.getCODEST());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarExterno(EstudioM estudio) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE CAPACITACION SET FECINICAP = ?,FECFINCAP = ?,FINCAP = ?,CURCAP = ?,HORCAP = ?,CODEMP = ?, CODCEN = ? WHERE CODCAP = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, formatter.format(estudio.getDESEST()));
            ps.setString(2, formatter.format(estudio.getHASEST()));
            ps.setString(3, estudio.getFINEST());
            ps.setString(4, estudio.getNOMCUR());
            ps.setString(5, estudio.getHORCAP());
            ps.setString(6, estudio.getCODEMP());
            ps.setString(7, estudio.getCODCEN());
            ps.setString(8, estudio.getCODCAP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarExterno(EstudioM estudio) throws Exception {
        this.conectar();
        try {
            String sql = "update CAPACITACION set ESTCAP = 'I' where CODCAP = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudio.getCODCAP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();

        }

    }
}
