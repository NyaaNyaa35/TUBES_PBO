/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Comment;
import Model.Person;
import Model.Post;
import Model.Teman;
import Model.User;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HansNotFound
 */
public class Controller {
    static DatabaseHandler conn = new DatabaseHandler();

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
    public static ArrayList<Post> getAllPost() {
        ArrayList<Post> posts = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM postingan";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Post post = new Post();
                post.setIdPost(rs.getInt("IdPostingan"));
                post.setCaption(rs.getString("Caption"));
                post.setPostNickname(rs.getString("PostNickname"));
                post.setJumlahLike(rs.getInt("Likes"));
                post.setWaktuPost(rs.getString("WaktuPost"));
                post.setImagepath(rs.getString("GambarPost"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (posts);
    }
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
    public static boolean insertPerson(User user){
        conn.connect();
        String query_InsertToPerson = "INSERT INTO person VALUES (?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertToPerson);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            return(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(false);
    }
    public static boolean insertUser(User user) {
        conn.connect();
        String query_InsertToUser = "INSERT INTO user VALUES(?,?,?,?,?,?)";
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
            return(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(false);
    }
    public static boolean insertNewUser(User user){
        boolean valid_1,valid_2;
        valid_1 = insertPerson(user);
        valid_2 = insertUser(user);
        return(valid_1 && valid_2);
    }
    public static boolean insertNewPost(Post post){
        conn.connect();
        String query_InsertNewPost = "INSERT INTO postingan VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertNewPost);
            stmt.setInt(1, post.getIdPost());
            stmt.setString(2, post.getCaption());
            stmt.setString(3, post.getImagepath());
            stmt.setInt(4, post.getJumlahLike());
            stmt.setString(5, post.getWaktuPost());
            stmt.setString(6, post.getPostNickname());
            stmt.executeUpdate();
            return(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        return false;
    }
    public static boolean insertNewComments(Comment comment){
        conn.connect();;
        String query_InsertToComment = "INSERT INTO comment VALUES(?,?,?)";
        try{
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertToComment);
            stmt.setString(1, comment.getIsiComment());
            stmt.setString(2, comment.getNicknameComment());
            stmt.setString(3, comment.getWaktuComment());
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
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
        } else {validate = email.matches(emailPattern2);}
        return validate;
    }
    public static ArrayList<Teman> getAllTeman(String username){
        conn.connect();
        ArrayList<Teman> listTeman = new ArrayList<>();
        String query = "SELECT * FROM list_teman WHERE Username = '" + username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Teman teman = new Teman();
                teman.setNickname_teman(rs.getString("Nickname_teman"));
                teman.setUsername_user(rs.getString("Username"));
                listTeman.add(teman);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTeman;
    }
    public static boolean searchUser(User user, String nick){
        conn.connect();
        User user_teman;
        boolean valid = false;
        String Username_teman = null;
        ArrayList<User> listUser = getAllUsers();
        for(int i = 0; i < listUser.size();i++){
            if(nick.equals(listUser.get(i).getNickname())){
                Username_teman = listUser.get(i).getUsername();
                valid = true;
                break;
            }
        }
        if(valid){
            user_teman = getUser(Username_teman);
            ArrayList<Teman> listTeman = getAllTeman(user.getUsername());
            String query = "INSERT INTO list_teman VALUES (?,?,?)";
            String idTeman = "" + user.getUsername() + "_"+listTeman.size();
            try{
                PreparedStatement stmt = conn.con.prepareStatement(query);
                stmt.setString(1, idTeman);
                stmt.setString(2, user.getUsername());
                stmt.setString(3, user_teman.getNickname());
                stmt.executeUpdate();
                return true;
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }
    public static boolean tambahJumlahTeman(User user){
        conn.connect();
        ArrayList<Teman> listTeman = getAllTeman(user.getUsername());
        int totalTeman = listTeman.size();
        String query = "UPDATE user SET JumlahTeman = " + totalTeman +
                " WHERE Username = '" + user.getUsername() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
