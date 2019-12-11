package dao;

import interfaces.EstudioBasicoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.EstudioBasicoM;

public class EstudioBasicoImpl extends Conexion implements EstudioBasicoI {

    /* METODOS PARA EL AMEL DE ESTUDIOS BASICOS (PRIMARIA Y SECUNDARIA) */
    @Override
    public void agregarEstudioBasico(EstudioBasicoM estudio) throws Exception {
        try {
            this.conectar();
            if (estudio.getGRAESTBAS() == null) {
                estudio.setGRAESTBAS("SI");
            }
            String sql = "INSERT INTO ESTUDIO_BASICO (EDUESTBAS,CULESTBAS,DESESTBAS,HASESTBAS,GRAESTBAS,CODCEN,ESTESTBAS,CODEMP) VALUES (?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudio.getEDUESTBAS());
            ps.setString(2, estudio.getCULESTBAS());
            ps.setString(3, estudio.getDESESTBAS());
            ps.setString(4, estudio.getHASESTBAS());
            ps.setString(5, estudio.getGRAESTBAS());
            ps.setString(6, estudio.getCODCEN());
            ps.setString(7, "A");
            ps.setString(8, estudio.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarEstudioBasico(EstudioBasicoM estudio) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE ESTUDIO_BASICO SET EDUESTBAS=?,CULESTBAS=?,DESESTBAS=TO_DATE(?,'DD/MM/YYYY'),HASESTBAS=TO_DATE(?,'DD/MM/YYYY'),GRAESTBAS=?,CODCEN=?,ESTESTBAS=?,CODEMP=? WHERE CODESTBAS=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudio.getEDUESTBAS());
            ps.setString(2, estudio.getCULESTBAS());
            ps.setString(3, estudio.getDESESTBAS());
            ps.setString(4, estudio.getHASESTBAS());
            ps.setString(5, estudio.getGRAESTBAS());
            ps.setString(6, estudio.getCODCEN());
            ps.setString(7, estudio.getESTESTBAS());
            ps.setString(8, estudio.getCODEMP());
            ps.setString(9, estudio.getCODESTBAS());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarEstudioBasico(EstudioBasicoM estudio) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE ESTUDIO_BASICO SET ESTESTBAS = 'I' WHERE CODESTBAS LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudio.getCODESTBAS());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<EstudioBasicoM> listarEstudioBasico() throws Exception {
        List<EstudioBasicoM> lstEstudioBasico;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT CODESTBAS, EDUESTBAS, CULESTBAS,TO_CHAR(DESESTBAS, 'dd/MM/yyyy') AS DESESTBAS,TO_CHAR(HASESTBAS, 'dd/MM/yyyy') AS HASESTBAS,GRAESTBAS,CENTRO.NOMCEN AS CODCEN, ESTESTBAS,\n"
                    + "UPPER(EMPLEADO.APAEMP||' '||EMPLEADO.AMAEMP||' '||EMPLEADO.NOMEMP) AS CODEMP\n"
                    + "FROM ESTUDIO_BASICO\n"
                    + "INNER JOIN CENTRO ON ESTUDIO_BASICO.CODCEN = CENTRO.CODCEN\n"
                    + "INNER JOIN EMPLEADO ON ESTUDIO_BASICO.CODEMP = EMPLEADO.CODEMP\n"
                    + "WHERE ESTESTBAS LIKE  'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lstEstudioBasico = new ArrayList<>();
            EstudioBasicoM estudioBasico;
            while (rs.next()) {
                estudioBasico = new EstudioBasicoM();
                estudioBasico.setCODESTBAS(rs.getString("CODESTBAS"));
                estudioBasico.setEDUESTBAS(rs.getString("EDUESTBAS"));
                estudioBasico.setCULESTBAS(rs.getString("CULESTBAS"));
                estudioBasico.setDESESTBAS(rs.getString("DESESTBAS"));
                estudioBasico.setHASESTBAS(rs.getString("HASESTBAS"));
                estudioBasico.setGRAESTBAS(rs.getString("GRAESTBAS"));
                estudioBasico.setCODCEN(rs.getString("CODCEN"));
                estudioBasico.setESTESTBAS(rs.getString("ESTESTBAS"));
                estudioBasico.setCODEMP(rs.getString("CODEMP"));
                lstEstudioBasico.add(estudioBasico);
            }
            return lstEstudioBasico;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    /* METODOS PARA EL AMEL DE ESTUDIOS SUPERIORES */
    @Override
    public void agregarEstudioSuperior(EstudioBasicoM estudioSuperior) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO ESTUDIO (EDUEST,DESEST,HASEST,GRAACAEST,TIPEST,ESTEST,NCOLEST,CODESP,CODEMP) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudioSuperior.getEDUEST());
            ps.setString(2, estudioSuperior.getDESEST());
            ps.setString(3, estudioSuperior.getHASEST());
            ps.setString(4, estudioSuperior.getGRAACAEST());
            ps.setString(5, "S");
            ps.setString(6, "A");
            ps.setString(7, estudioSuperior.getNCOLEST());
            ps.setString(8, estudioSuperior.getCODESP());
            ps.setString(9, estudioSuperior.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarEstudioSuperior(EstudioBasicoM estudioSuperior) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE ESTUDIO_BASICO SET EDUESTBAS = ?, CULESTBAS = ?, DESESTBAS = ?, HASESTBAS = ?, GRAESTBAS = ?, CODCEN = ?, ESTESTBAS = ?, CODEMP = ? WHERE CODESTBAS = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudioSuperior.getEDUEST());
            ps.setString(2, estudioSuperior.getDESEST());
            ps.setString(3, estudioSuperior.getHASEST());
            ps.setString(4, estudioSuperior.getGRAACAEST());
            ps.setString(5, estudioSuperior.getTIPEST());
            ps.setString(6, estudioSuperior.getESTEST());
            ps.setString(7, estudioSuperior.getNCOLEST());
            ps.setString(8, estudioSuperior.getCODESP());
            ps.setString(9, estudioSuperior.getCODEMP());
            ps.setString(10, estudioSuperior.getCODEST());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarEstudioSuperior(EstudioBasicoM estudioSuperior) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE ESTUDIO_BASICO SET ESTESTBAS = 'I' WHERE CODESTBAS LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, estudioSuperior.getCODEST());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<EstudioBasicoM> listarEstudioSuperior() throws Exception {
        List<EstudioBasicoM> lstEstudioSuperior;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lstEstudioSuperior = new ArrayList<>();
            EstudioBasicoM estudioSuperior;
            while (rs.next()) {
                estudioSuperior = new EstudioBasicoM();
                estudioSuperior.setCODEST(rs.getString("CODFAM"));
                estudioSuperior.setEDUEST(rs.getString("NOMFAM"));
                estudioSuperior.setDESEST(rs.getString("OCUFAM"));
                estudioSuperior.setHASEST(rs.getString("FENFAM"));
                estudioSuperior.setGRAACAEST(rs.getString("TELFAM"));
                estudioSuperior.setESTESTBAS(rs.getString("ETCFAM"));
                estudioSuperior.setCODEMP(rs.getString("VIVFAM"));
                lstEstudioSuperior.add(estudioSuperior);
            }
            return lstEstudioSuperior;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
