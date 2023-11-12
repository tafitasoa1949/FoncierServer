/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dtoFoncier.Detailtany;
import dtoFoncier.Tany;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class TanyDAO {
    public static Tany getById(Connection con,int id)throws Exception{
        PreparedStatement stmt = null;
        String sql = "select * from tany where id=?";
        Tany tany = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tany = new Tany();
                tany.setId(id);
                tany.setCinproprietaire(rs.getInt("cinproprietaire"));
                tany.setCinvendeur(rs.getInt("cinvendeur"));
                tany.setSurface(rs.getFloat("surface"));
                tany.setPrixunitaire(rs.getFloat("prixunitaire"));
                tany.setTotalprix(rs.getFloat("totalprix"));
                tany.setDate(rs.getDate("date"));
                tany.setColor(rs.getString("color"));
                tany.setFillcolor(rs.getString("fillcolor"));
            }
        } catch (Exception e) {
            throw new Exception("erreur"+e.getMessage());
        }finally{
            if (stmt != null) stmt.close();
        }
        return tany;
    }
    public static Tany getLast(Connection con,int cinProprietaire)throws Exception{
        PreparedStatement stmt = null;
        String sql = "select * from tany where cinproprietaire=? order by id desc limit 1";
        Tany tany = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cinProprietaire);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tany = new Tany();
                tany.setId(rs.getInt("id"));
                tany.setCinproprietaire(cinProprietaire);
                tany.setCinvendeur(rs.getInt("cinvendeur"));
                tany.setSurface(rs.getFloat("surface"));
                tany.setPrixunitaire(rs.getFloat("prixunitaire"));
                tany.setTotalprix(rs.getFloat("totalprix"));
                tany.setDate(rs.getDate("date"));
                tany.setColor(rs.getString("color"));
                tany.setFillcolor(rs.getString("fillcolor"));
            }
        } catch (Exception e) {
            throw new Exception("erreur"+e.getMessage());
        }finally{
            if (stmt != null) stmt.close();
        }
        return tany;
    }
    public static List<Tany> getTanyByPersonne(Connection con,int cin)throws Exception{
        PreparedStatement stmt = null;
        List<Tany> list = new ArrayList<>();
        String sql = "select * from tany where cinproprietaire=?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cin);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Tany tany = new Tany();
                tany.setId(rs.getInt("id"));
                tany.setCinproprietaire(rs.getInt("cinproprietaire"));
                tany.setCinvendeur(rs.getInt("cinvendeur"));
                tany.setSurface(rs.getFloat("surface"));
                tany.setPrixunitaire(rs.getFloat("prixunitaire"));
                tany.setTotalprix(rs.getFloat("totalprix"));
                tany.setDate(rs.getDate("date"));
                tany.setColor(rs.getString("color"));
                tany.setFillcolor(rs.getString("fillcolor"));
                list.add(tany);
            }
        } catch (Exception e) {
            throw new Exception("Erreur fonction getTanyByPersonne :"+e.getMessage());
        }finally{
            if (stmt != null) stmt.close();
        }
        return list;
    }
    public static List<Detailtany> getAllBorne(Connection con,int idtany)throws Exception{
        PreparedStatement stmt = null;
        List<Detailtany> list_detail = new ArrayList<>();
        String sql = "select * from detailtany where idtany=?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idtany);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Detailtany det = new Detailtany();
                det.setIdtany(idtany);
                det.setLatitude(rs.getDouble("latitude"));
                det.setLongitude(rs.getDouble("longitude"));
                list_detail.add(det);
            }
        } catch (Exception e) {
            throw new Exception("Erreur fonction getAllBorne :"+e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
        return list_detail;
    }
    public static void insertTany(Connection con,Tany tany)throws Exception{
        PreparedStatement stmt = null;
        String sql = "insert into tany(cinproprietaire,cinvendeur,surface,prixunitaire,totalprix,date,color,fillcolor) values"+
                "(?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, tany.getCinproprietaire());
            stmt.setInt(2, tany.getCinvendeur());
            stmt.setDouble(3, tany.getSurface());
            stmt.setDouble(4, tany.getPerimetre());
            stmt.setFloat(5, tany.getPrixunitaire());
            stmt.setFloat(6, tany.getTotalprix());
            stmt.setDate(7, tany.getDate());
            stmt.setString(8, tany.getColor());
            stmt.setString(9,tany.getFillcolor());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur fonction insert :"+e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }
    public static void insertDetailTany(Connection con,Detailtany detail)throws Exception{
        PreparedStatement stmt = null;
        String sql = "insert into detailtany values (?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, detail.getIdtany());
            stmt.setDouble(2, detail.getLatitude());
            stmt.setDouble(3, detail.getLongitude());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur fonction insertDetailTany :"+e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }
    public static void insert(Connection con,Tany tany,Detailtany[] list_detailtany)throws Exception{
        try {
            con.setAutoCommit(false);
            insertTany(con, tany);
            Tany new_tany = getLast(con, tany.getCinproprietaire());
            for(int i=0 ; i<list_detailtany.length ; i++){
                list_detailtany[i].setIdtany(new_tany.getId());
                insertDetailTany(con, list_detailtany[i]);
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Erreur fonction insert :"+e.getMessage());
        }
    }
    
    /*public List<Transaction> getListVarotraTany(int cin)throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        List<Transaction> list = new ArrayList<>();
        String sql = "select * from transaction where idvendeur=?";
        try {
            con = Connexion.getconnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cin);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Transaction transaction = new Transaction();
                transaction.setVendeur(rs.getInt("idvendeur"));
                transaction.setAcheteur(rs.getInt("idacheteur"));
                int idTypeTransaction = rs.getInt("idtypetransaction");
                TypeTransaction typeTransaction = new TypeTransactionDAO().getById(idTypeTransaction);
                transaction.setTypeTransaction(typeTransaction);
                String idTany = rs.getString("idtany");
                Tany tany = getById(idTany);
                transaction.setTany(tany);
                transaction.setPrix(rs.getFloat("prix"));
                transaction.setDate(rs.getDate("date"));
                list.add(transaction);
            }
        } catch (Exception e) {
            throw new Exception("Erreur fonction getListVarotraTany :"+e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return list;
    }*/
    /*public List<Transaction> getListVidyTany(int cin)throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        List<Transaction> list = new ArrayList<>();
        String sql = "select * from transaction where idacheteur=?";
        try {
            con = Connexion.getconnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cin);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Transaction transaction = new Transaction();
                transaction.setVendeur(rs.getInt("idvendeur"));
                transaction.setAcheteur(rs.getInt("idacheteur"));
                int idTypeTransaction = rs.getInt("idtypetransaction");
                TypeTransaction typeTransaction = new TypeTransactionDAO().getById(idTypeTransaction);
                transaction.setTypeTransaction(typeTransaction);
                String idTany = rs.getString("idtany");
                Tany tany = getById(idTany);
                transaction.setTany(tany);
                transaction.setPrix(rs.getFloat("prix"));
                transaction.setDate(rs.getDate("date"));
                list.add(transaction);
            }
        } catch (Exception e) {
            throw new Exception("Erreur fonction getListVidyTany :"+e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return list;
    }*/
    /*public void AchatTany(Transaction transaction)throws Exception{
        Connection con = null;
        try {
            con = Connexion.getconnection();
            con.setAutoCommit(false);
            ChangeProprietaire(con, transaction.getAcheteur(), transaction.getTany().getId());
            FaireTransaction(con, transaction);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Erreur fonction AchatTany :"+e.getMessage());
        }finally{
            if (con != null) con.close();
        }
    }*/
    /*public void ChangeProprietaire(Connection con, int idNouveauProp, String idTany) throws Exception {
        String sql = "UPDATE tany SET cin=? WHERE id=?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idNouveauProp);
            stmt.setString(2, idTany);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }*/
    /*public void FaireTransaction(Connection con, Transaction transaction) throws Exception {
        String sql = "INSERT INTO transaction VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, transaction.getVendeur());
            stmt.setInt(2, transaction.getAcheteur());
            stmt.setInt(3, transaction.getTypeTransaction().getId());
            stmt.setString(4, transaction.getTany().getId());
            stmt.setFloat(5, transaction.getPrix());
            stmt.setDate(6, transaction.getDate());
            stmt.executeUpdate(); // Remove sql argument
        } catch (Exception e) {
            throw new Exception("Erreur : " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }*/
}
