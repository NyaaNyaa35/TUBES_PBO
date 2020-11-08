/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Person;
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
        String query = "SELECT user.*,person.Password FROM user JOIN person ON user.Username = person.Username";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setNickname(rs.getString("user.Nickname"));
                user.setUsername(rs.getString("user.Username"));
                user.setEmail(rs.getString("user.Email"));
                user.setJumlahTeman(rs.getInt("user.JumlahTeman"));
                user.setProfilePict(rs.getString("user.ProfilePict"));
                user.setPassword(rs.getString("person.Password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }
    
    public static ArrayList<Person> getAllPerson() {
        ArrayList<Person> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM person";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
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
        String query = "SELECT * FROM user WHERE Username='" + Username + "'";
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
        String query_InsertToUser = "INSERT INTO user VALUES(?,?,?,?,?,?)";
        String query_InsertToPerson = "INSERT INTO person VALUES (?,?)";
        boolean valid_1 = false,valid_2 = false;
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertToPerson);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            valid_1 = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            PreparedStatement stmt_2 = conn.con.prepareStatement(query_InsertToUser);
            ArrayList<User> listUser = getAllUsers();
            stmt_2.setInt(1, listUser.size() + 1);
            stmt_2.setString(2, user.getUsername());
            stmt_2.setString(3, user.getNickname());
            stmt_2.setString(4, user.getEmail());
            stmt_2.setInt(5, user.getJumlahTeman());
            stmt_2.setString(6, user.getProfilePict());
            stmt_2.executeUpdate();
            valid_2 = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(valid_1 && valid_2);
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
    public boolean recoverPassword(String Username, String Password){
        conn.connect();
        
        String query = "Update person set Password = '" + Password +"' where Username = '" + Username+ "';";
        
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
    public static boolean isValidEmail(String email) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
 
        if (email.matches(emailPattern)) {
            validate = true;
        } else validate = email.matches(emailPattern2);
        return validate;
    }
}
