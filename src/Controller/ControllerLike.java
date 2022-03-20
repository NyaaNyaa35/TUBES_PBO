/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ControllerPost.conn;
import Model.Comment;
import Model.Liker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ControllerLike {
    private Connection dbCon;
    static DatabaseHandler conn = new DatabaseHandler();
    
    public ControllerLike(Connection c)
    {
        dbCon = c;
    }
    //menambahkan list like di database jika user me-like post
    public static boolean insertNewLiker(Liker liker){
        conn.connect();
        String query_InsertToComment = "INSERT INTO liker VALUES(?,?)";
        try{
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertToComment);
            stmt.setString(1, liker.getNicknameLike());
            stmt.setInt(2, liker.getIdPost());
            stmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //mengambil semua jumlah like
    public static ArrayList<Liker> getAllLiker() {
        ArrayList<Liker> likers = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM liker";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Liker liker = new Liker();
                liker.setNicknameLike(rs.getString("nicknameLike"));
                liker.setIdPost(rs.getInt("idPostingan"));
                likers.add(liker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (likers);
    }
    
    //mengecek isLiked or not
    public static int checkLike(String nick, int idPost){
        conn.connect();
        String query = "SELECT * FROM liker WHERE nicknameLike = '" + nick + "' and idPostingan = '" + idPost + "'";
        ArrayList<Liker> listLike = new ArrayList<>();
        int total = 0;
        try{
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Liker liker = new Liker();
                liker.setNicknameLike(rs.getString("nicknameLike"));
                liker.setIdPost(rs.getInt("idPostingan"));
                listLike.add(liker);
            }
            total = listLike.size();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return total;
    }
    
    //menambah likes pada post
    public static boolean updateLike(int idPost){
        conn.connect();
        int jumlahLikesekarang = ControllerPost.getPost(idPost).getJumlahLike();
        String query = "UPDATE postingan SET Likes='" + (jumlahLikesekarang+1) + "' WHERE IdPostingan='"+idPost+"'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //mengambil semua liker by idPost
    public static ArrayList<Liker> getLiker(int idPost) {
        ArrayList<Liker> likers = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM liker WHERE idPostingan = " + idPost;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Liker liker = new Liker();
                liker.setNicknameLike(rs.getString("nicknameLike"));
                liker.setIdPost(rs.getInt("idPostingan"));
                likers.add(liker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (likers);
    }
}
