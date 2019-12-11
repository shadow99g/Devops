package dao;

import interfaces.FamiliaI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.FamiliaM;

public class FamiliaImpl extends Conexion implements FamiliaI {

    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public void agregarFamilia(FamiliaM familia) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO FAMILIA(NOMFAM,APEFAM,PARFAM,OCUFAM,FENFAM,TELFAM,CELFAM,ETCFAM,VIVFAM,ESTFAM,TIPFAM,SEXFAM,BONFAM,INCFAM,CODEMP) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, familia.getNOMFAM());
            ps.setString(2, familia.getAPEFAM());
            ps.setString(3, familia.getPARFAM());
            ps.setString(4, familia.getOCUFAM());
            ps.setString(5, formatter.format(familia.getFENFAM()));
            ps.setString(6, familia.getTELFAM());
            ps.setString(7, familia.getCELFAM());
            ps.setString(8, familia.getETCFAM());
            ps.setString(9, familia.getVIVFAM());
            ps.setString(10, "A");
            ps.setString(11, familia.getTIPFAM());
            ps.setString(12, familia.getSEXFAM());
            ps.setString(13, familia.getBONFAM());
            ps.setString(14, familia.getINCFAM());            
            ps.setString(15, familia.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarFamilia(FamiliaM familia) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE FAMILIA SET NOMFAM=?,APEFAM=?,PARFAM=?,OCUFAM=?,FENFAM=?,TELFAM=?,CELFAM=?,ETCFAM=?,VIVFAM=?,ESTFAM=?,TIPFAM=?,SEXFAM=?,BONFAM=?,INCFAM=? WHERE CODFAM LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, familia.getNOMFAM());
            ps.setString(2, familia.getAPEFAM());
            ps.setString(3, familia.getPARFAM());
            ps.setString(4, familia.getOCUFAM());
            ps.setString(5, formatter.format(familia.getFENFAM()));
            ps.setString(6, familia.getTELFAM());
            ps.setString(7, familia.getCELFAM());
            ps.setString(8, familia.getETCFAM());
            ps.setString(9, familia.getVIVFAM());
            ps.setString(10, familia.getESTFAM());
            ps.setString(11, familia.getTIPFAM());
            ps.setString(12, familia.getSEXFAM());
            ps.setString(13, familia.getBONFAM());
            ps.setString(14, familia.getINCFAM());
            ps.setString(15, familia.getCODFAM());            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarFamilia(FamiliaM familia) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE FAMILIA SET ESTFAM = 'I' WHERE CODFAM LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, familia.getCODFAM());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<FamiliaM> listarFamilia() throws Exception {
        List<FamiliaM> listaFamilia;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT CODFAM,NOMFAM,APEFAM,PARFAM,OCUFAM,FENFAM,TELFAM,CELFAM,ETCFAM,VIVFAM,ESTFAM,TIPFAM,SEXFAM,BONFAM,INCFAM,(EMPLEADO.NOMEMP||' '||EMPLEADO.APAEMP||' '||EMPLEADO.AMAEMP) AS CODEMP \n" +
                         "FROM FAMILIA INNER JOIN EMPLEADO ON FAMILIA.CODEMP = EMPLEADO.CODEMP WHERE ESTFAM LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            listaFamilia = new ArrayList<>();
            FamiliaM lstFamilia;
            while (rs.next()) {
                lstFamilia = new FamiliaM();                
                lstFamilia.setCODFAM(rs.getString("CODFAM"));
                lstFamilia.setNOMFAM(rs.getString("NOMFAM"));
                lstFamilia.setAPEFAM(rs.getString("APEFAM"));
                lstFamilia.setPARFAM(rs.getString("PARFAM"));
                lstFamilia.setOCUFAM(rs.getString("OCUFAM"));                
                lstFamilia.setFENFAM(rs.getDate("FENFAM"));
                lstFamilia.setTELFAM(rs.getString("TELFAM"));
                lstFamilia.setCELFAM(rs.getString("CELFAM"));
                lstFamilia.setETCFAM(rs.getString("ETCFAM"));
                lstFamilia.setVIVFAM(rs.getString("VIVFAM"));
                lstFamilia.setESTFAM(rs.getString("ESTFAM"));
                lstFamilia.setTIPFAM(rs.getString("TIPFAM"));
                lstFamilia.setSEXFAM(rs.getString("SEXFAM"));
                lstFamilia.setBONFAM(rs.getString("BONFAM"));
                lstFamilia.setINCFAM(rs.getString("INCFAM"));                
                lstFamilia.setCODEMP(rs.getString("CODEMP"));
                listaFamilia.add(lstFamilia);
            }
            return listaFamilia;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }        
    
}