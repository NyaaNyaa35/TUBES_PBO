/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Person;
import Model.Teman;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author HansNotFound
 */
public class ControllerUser {
    static DatabaseHandler conn = new DatabaseHandler();
    //Get Semua user 
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
    
    //get semua person
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
    
    //get user by Username
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
    
    //menginsert person
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
    
    //menginsert user baru
    public static boolean insertUser(User user) {
        conn.connect();
        String query_InsertToUser = "INSERT INTO user VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt_2 = conn.con.prepareStatement(query_InsertToUser);
            stmt_2.setInt(1, User.countUser());
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
    
    //memanggil 2 void insert user dan memastikan agar dapat terinput dengan benar ke kedua tabel(person dan user)
    public static boolean insertNewUser(User user){
        boolean valid_1,valid_2;
        valid_1 = insertPerson(user);
        valid_2 = insertUser(user);
        return(valid_1 && valid_2);
    }
    
    //fungsi delete user by admin
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
    
    //fungsi recover password 
    public static boolean recoverPassword(String Username, String Password){
        conn.connect();
        String query = "Update person set Password = '" + Password +"' where Username = '" + Username+ "'";
        try{
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return(true);
        } catch (SQLException ex){
            ex.printStackTrace();
            return(false);
        }
    }
    
    //mendapatkan tanggal sekarang
    public static String getTanggal() {  
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
        Date date = new Date();  
        return dateFormat.format(date);  
    }
    
    //mendapatkan waktu sekarang
    public static String getWaktu() {  
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        Date date = new Date();  
        return dateFormat.format(date);  
    } 
    
    //mengecek apakah email yang diinput itu valid
    public static boolean isValidEmail(String email) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
 
        if (email.matches(emailPattern)) {
            validate = true;
        } else {validate = email.matches(emailPattern2);}
        return validate;
    }
    
    //get seluruh Nickname teman by Username
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
    
    //menambahkan Nickname teman kedalam tabel list_teman
    public static boolean addTeman(User user,User user_teman){
        conn.connect();
        ArrayList<Teman> listTeman = getAllTeman(user.getUsername());
            String query = "INSERT INTO list_teman VALUES (?,?,?)";
            int idTeman;
            if(listTeman == null){
                idTeman = 0;
            } else {
                idTeman = listTeman.size();
            }
            try{
                PreparedStatement stmt = conn.con.prepareStatement(query);
                stmt.setInt(1, idTeman);
                stmt.setString(2, user.getUsername());
                stmt.setString(3, user_teman.getNickname());
                stmt.executeUpdate();
                return true;
            } catch (Exception ex){
                ex.printStackTrace();
            }
        return false;
    }
    
    //menambahkan jumlah teman pada tabel user
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
    
    //menambahkan dan mencari teman by nick
    public static boolean addFriend(User user, String nick){
        ArrayList<User> listUser = getAllUsers();
        User user_teman = null;
        boolean valid = false;
        for(int i = 0; i < listUser.size();i++){
            if(nick.equals(listUser.get(i).getNickname())){
                user_teman = listUser.get(i);
                valid = true;
                break;
            }
        }
        if(valid){
            if(addTeman(user,user_teman)){
                System.out.println("AddTeman oleh = " + user.getNickname() + "berhasil");
                tambahJumlahTeman(user);
                if(addTeman(user_teman,user)){
                    System.out.println("AddTeman oleh = " + user_teman.getNickname() + "berhasil");
                    tambahJumlahTeman(user_teman);
                }
            }
        }
        return false;
    }
    
    //mengupdate nickname baru
    public static boolean updateNickname(User user, String newNick){
        conn.connect();
        String query = "UPDATE user SET Nickname = '" + newNick +
                "' WHERE Username = '" + user.getUsername() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //mengupdate Nickname pada tabel Postingan
    public static boolean updateNicknameList_in_Postingan(User user, String newNick){
        conn.connect();
        String query = "UPDATE postingan SET PostNickname = '" + newNick +
                "' WHERE Username = '" + user.getUsername() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //mengupdate Nickname pada tabel list_teman
    public static boolean updateNicknameList_in_ListTeman(User user, String newNick){
        conn.connect();
        String query = "UPDATE list_teman SET Nickname_teman = '" + newNick +
                "' WHERE Nickname_teman = '" + user.getNickname() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //mengupdate ProfilePict user
    public static boolean updateProfilePict(User user){
        conn.connect();
        String query = "UPDATE user SET ProfilePict = '" + user.getProfilePict() +
                "' WHERE Username = '" + user.getUsername() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //mengecek username didatabase (mencegah duplikat entry)
    public static int cekDuplikatUsername(String Username){
        conn.connect();
        int total = 0;
        String query = "SELECT Username FROM person WHERE Username = '" + Username + "'";
        ArrayList<User> listUser = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("Username"));
                listUser.add(user);
            }
            total = listUser.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    
    //mengecek nickname didatabase (mencegah duplikat entry)
    public static int cekDuplikatNickname(String Nickname){
        conn.connect();
        int total = 0;
        String query = "SELECT Nickname FROM user WHERE Nickname = '" + Nickname + "'";
        ArrayList<User> listUser = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setNickname(rs.getString("Nickname"));
                listUser.add(user);
            }
            total = listUser.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    
    //mengecek Email didatabase (mencegah duplikat entry)
    public static int cekDuplikatEmail(String Email){
        conn.connect();
        int total = 0;
        String query = "SELECT Email FROM user WHERE Email = '" + Email + "'";
        ArrayList<User> listUser = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("Email"));
                listUser.add(user);
            }
            total = listUser.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
