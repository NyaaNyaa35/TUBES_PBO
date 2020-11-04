/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Post;
import Model.User;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sun.security.util.Password;

/**
 *
 * @author HansNotFound
 */
public class Controller {
    static DatabaseHandler conn = new DatabaseHandler();

    // SELECT ALL from table users
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM datauser";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setNickname(rs.getString("Nickname"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setJumlahTeman(rs.getInt("JumlahTeman"));
                user.setProfilePict(rs.getString("ProfilePict"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }
    
    //SELECT ALL FROM TABLE POST
    public static ArrayList<Post> getAllPost() {
        ArrayList<Post> posts = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM postingan";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Post post = new Post();
                post.setIdPost(rs.getInt("IdPost"));
                post.setPostNickname(rs.getString("PostNickname"));
                post.setWaktuPost(rs.getString("WaktuPost"));
                post.setImagepath(rs.getString("Imagepath"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (posts);
    }

    // SELECT WHERE
    public static User getUser(String Username) {
        conn.connect();
        String query = "SELECT * FROM datauser WHERE Username='" + Username + "'";
        User user = new User();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setNickname(rs.getString("Nickname"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setJumlahTeman(rs.getInt("JumlahTeman"));
                user.setProfilePict(rs.getString("ProfilePict"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user);
    }
    
    // INSERT
    public static boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO datauser VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, user.getNickname());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getJumlahTeman());
            stmt.setString(5, user.getProfilePict());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // UPDATE
    /*public static boolean updateUser(User user, Boolean filechooserFoto, Boolean filechooserTT) {
        conn.connect();
        String query = null;
        if(filechooserFoto == true && filechooserTT == true){
            query = "UPDATE datauser SET Nama='" + user.getNama() + "', "
                + "TempatLahir='" + user.getTempatLahir() + "', "
                + "Tanggallahir='" + user.getTanggallahir() + "', "
                + "JenisKelamin='" + user.getJenisKelamin() + "', "
                + "GolonganDarah='" + user.getGolonganDarah() + "', "
                + "alamat='" + user.getAlamat() + "', "
                + "RTRW='" + user.getRTRW() + "', "
                + "Kecamatan='" + user.getKecamatan() + "', "
                + "KelDesa='" + user.getKelDesa() + "', "
                + "Agama='" + user.getAgama() + "', "
                + "StatusPerkawinan='" + user.getStatusPerkawinan() + "', "
                + "Pekerjaan='" + user.getPekerjaan() + "', "
                + "Kewarganegaraan='" + user.getKewarganegaraan() + "', "
                + "Foto='" + user.getFoto() + "', "
                + "TandaTangan='" + user.getTandaTangan() + "', "
                + "TempatPembuatKTP='" + user.getTempatPembuatKTP() + "', "
                + "BerlakuHingga='" + user.getBerlakuHingga() + "', "
                + "TanggalPembuatKTP='" + user.getTanggalPembuatKTP() + "' "
                + " WHERE NIK='" + user.getNIK() + "'";
        }
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }*/

    // DELETE
    public static boolean deleteUser(String Username) {
        conn.connect();

        String query = "DELETE FROM person WHERE Username='" + Username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    public static boolean insertNewPost(Post post){
        return false;
    }
    public static boolean deletePost(int idPost){
        conn.connect();

        String query = "DELETE FROM postingan WHERE idPost='" + idPost + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    public void TimeLine(){ //nanti
        
    }
    public boolean recoverPassword(String Username, String Password){
        conn.connect();
        
        String query = "Update user set Password = '" + Password +"' where Username = '" + Username+ "';";
        
        try{
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return(true);
        } catch (SQLException ex){
            ex.printStackTrace();
            return(false);
        }
    }
    public void LoginSuccess(String username, String Password){
        JOptionPane.showMessageDialog(null, "Username = " + username + " password = " + Password);
    }
    public static String getTanggal() {  
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
        Date date = new Date();  
        return dateFormat.format(date);  
    }  
     
    public static String getWaktu() {  
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        Date date = new Date();  
        return dateFormat.format(date);  
    } 
}
