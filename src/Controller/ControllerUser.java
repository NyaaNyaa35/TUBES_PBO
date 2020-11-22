/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FriendRequest;
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
                user.setIdUser(rs.getInt("user.id_User"));
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

    public static String getPassword(String username) {
        conn.connect();
        String pass = "";
        String query = "SELECT Password FROM person WHERE Username = '" + username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            pass = rs.getString("Password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pass;
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

    //get user by Nickname
    public static User getUserNick(String Nickname) {
        conn.connect();
        String query = "SELECT * FROM user WHERE Nickname='" + Nickname + "'";
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
    public static boolean insertPerson(User user) {
        conn.connect();
        String query_InsertToPerson = "INSERT INTO person VALUES (?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertToPerson);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (false);
    }

    //menginsert user baru
    public static boolean insertUser(User user) {
        conn.connect();
        String query_InsertToUser = "INSERT INTO user VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt_2 = conn.con.prepareStatement(query_InsertToUser);
            stmt_2.setInt(1, User.countUser());
            stmt_2.setString(2, user.getUsername());
            stmt_2.setString(3, user.getNickname());
            stmt_2.setString(4, user.getEmail());
            stmt_2.setInt(5, user.getJumlahTeman());
            stmt_2.setString(6, user.getProfilePict());
            stmt_2.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (false);
    }

    //memanggil 2 void insert user dan memastikan agar dapat terinput dengan benar ke kedua tabel(person dan user)
    public static boolean insertNewUser(User user) {
        boolean valid_1, valid_2;
        valid_1 = insertPerson(user);
        valid_2 = insertUser(user);
        return (valid_1 && valid_2);
    }

    //fungsi delete user by admin
    public static boolean deleteUser(String Username) {
        conn.connect();

        String query = "DELETE FROM user WHERE Username='" + Username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    //fungsi delete person by admin
    public static boolean deletePerson(String Username, String Nickname) {
        conn.connect();
        if (deleteKoneksiTeman(Username, Nickname)) {
            if (deleteKoneksiReq(Nickname)) {
                if (deleteUser(Username)) {
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
            }
        }
        return (false);
    }

    //fungsi koneksi teman by Nickname
    public static boolean deleteKoneksiTeman(String Username, String Nickname) {
        conn.connect();
        if (deleteKoneksiTeman2(Username)) {
            String query = "DELETE FROM list_teman WHERE Nickname_teman='" + Nickname + "'";
            try {
                Statement stmt = conn.con.createStatement();
                stmt.executeUpdate(query);
                return (true);
            } catch (SQLException e) {
                e.printStackTrace();
                return (false);
            }
        }
        return (false);
    }

    //fungsi koneksi teman by Username
    public static boolean deleteKoneksiTeman2(String Username) {
        conn.connect();
        String query = "DELETE FROM list_teman WHERE Username='" + Username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    //fungsi delete koneksi dari friend_req by admin
    public static boolean deleteKoneksiReq(String Nickname) {
        conn.connect();
        String query = "DELETE FROM friend_req WHERE Nickname_req='" + Nickname + "'";
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
    public static boolean recoverPassword(String Username, String Password) {
        conn.connect();
        String query = "Update person set Password = '" + Password + "' where Username = '" + Username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return (false);
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
        } else {
            validate = email.matches(emailPattern2);
        }
        return validate;
    }

    //get seluruh teman
    public static ArrayList<Teman> getAllTeman() {
        conn.connect();
        ArrayList<Teman> listTeman = new ArrayList<>();
        String query = "SELECT * FROM list_teman ";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Teman teman = new Teman();
                teman.setIdTeman(rs.getInt("idTeman"));
                teman.setNickname_teman(rs.getString("Nickname_teman"));
                teman.setUsername_user(rs.getString("Username"));
                listTeman.add(teman);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTeman;
    }

    //get seluruh Nickname teman by Username
    public static ArrayList<Teman> getTeman(String Username) {
        conn.connect();
        ArrayList<Teman> listTeman = new ArrayList<>();
        String query = "SELECT * FROM list_teman Where Username = '" + Username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Teman teman = new Teman();
                teman.setIdTeman(rs.getInt("idTeman"));
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
    public static boolean addTeman(User user, User user_teman) {
        conn.connect();
        ArrayList<Teman> listAllTeman = getAllTeman();
        String query = "INSERT INTO list_teman VALUES (?,?,?)";
        int idTeman;
        if (listAllTeman.isEmpty()) {
            idTeman = 0;
        } else {
            idTeman = listAllTeman.get(listAllTeman.size() - 1).getIdTeman();
        }
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, idTeman + 1);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user_teman.getNickname());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //menambahkan jumlah teman pada tabel user
    public static boolean tambahJumlahTeman(User user) {
        conn.connect();
        ArrayList<Teman> listTeman = getTeman(user.getUsername());
        int totalTeman = listTeman.size();
        String query = "UPDATE user SET JumlahTeman = " + totalTeman
                + " WHERE Username = '" + user.getUsername() + "'";
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
    public static boolean updateJumlahTeman(String Nickname) {
        conn.connect();
        User userteman = getUserNick(Nickname);
        String query = "UPDATE user SET JumlahTeman = '" + (userteman.getJumlahTeman()-1)
                + "' WHERE Nickname = '" + Nickname + "'";
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
    public static boolean addFriend(User user, String nick) {
        ArrayList<User> listUser = getAllUsers();
        User user_teman = null;
        boolean valid = false;
        for (int i = 0; i < listUser.size(); i++) {
            if (nick.equals(listUser.get(i).getNickname())) {
                user_teman = listUser.get(i);
                valid = true;
                break;
            }
        }
        if (valid) {
            if (addTeman(user, user_teman)) {
                System.out.println("AddTeman oleh = " + user.getNickname() + "berhasil");
                tambahJumlahTeman(user);
                if (addTeman(user_teman, user)) {
                    System.out.println("AddTeman oleh = " + user_teman.getNickname() + "berhasil");
                    tambahJumlahTeman(user_teman);
                    return true;
                }
            }
        }
        return false;
    }

    //mengupdate nickname baru
    public static boolean updateNickname(User user, String newNick) {
        conn.connect();
        String query = "UPDATE user SET Nickname = '" + newNick
                + "' WHERE Username = '" + user.getUsername() + "'";
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
    public static boolean updateNicknameList_in_Postingan(User user, String newNick) {
        conn.connect();
        String query = "UPDATE postingan SET PostNickname = '" + newNick
                + "' WHERE Username = '" + user.getUsername() + "'";
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
    public static boolean updateNicknameList_in_ListTeman(User user, String newNick) {
        conn.connect();
        String query = "UPDATE list_teman SET Nickname_teman = '" + newNick
                + "' WHERE Nickname_teman = '" + user.getNickname() + "'";
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
    public static boolean updateProfilePict(User user) {
        conn.connect();
        String query = "UPDATE user SET ProfilePict = '" + user.getProfilePict()
                + "' WHERE Username = '" + user.getUsername() + "'";
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
    public static int cekDuplikatUsername(String Username) {
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
    public static int cekDuplikatNickname(String Nickname) {
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
    public static int cekDuplikatEmail(String Email) {
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

    //get seluruh Friend Request
    public static ArrayList<FriendRequest> getAllRequest() {
        conn.connect();
        ArrayList<FriendRequest> listReq = new ArrayList<>();
        String query = "SELECT * FROM friend_req";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                FriendRequest req = new FriendRequest();
                req.setIdReq(rs.getInt("idReq"));
                req.setNickname_request(rs.getString("Nickname_req"));
                req.setTanggal_request(rs.getString("tanggal_req"));
                req.setUsername_user(rs.getString("Username"));
                listReq.add(req);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReq;
    }

    //menambahkan ke friend request
    public static boolean addRequest(String username, String nickname) {
        conn.connect();
        ArrayList<FriendRequest> listReq = getAllRequest();
        String query = "INSERT INTO friend_req VALUES (?,?,?,?)";
        int idReq;
        if (listReq.isEmpty()) {
            idReq = 0;
        } else {
            idReq = (listReq.get(listReq.size() - 1).getIdReq()) + 1;
        }
        String tanggal = getTanggal() + " " + getWaktu();
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, idReq);
            stmt.setString(2, username);
            stmt.setString(3, nickname);
            stmt.setString(4, tanggal);
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //get request per User
    public static ArrayList<FriendRequest> getRequest(String username) {
        conn.connect();
        ArrayList<FriendRequest> listReq = new ArrayList<>();
        String query = "SELECT * FROM friend_req where Username = '" + username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                FriendRequest req = new FriendRequest();
                req.setIdReq(rs.getInt("idReq"));
                req.setNickname_request(rs.getString("Nickname_req"));
                req.setTanggal_request(rs.getString("tanggal_req"));
                req.setUsername_user(rs.getString("Username"));
                listReq.add(req);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReq;
    }

    //delete req 
    public static boolean deleteReq(String username, String nickname) {
        conn.connect();

        String query = "DELETE FROM friend_req WHERE Username='" + username + "' and Nickname_req = '" + nickname + "'";
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
