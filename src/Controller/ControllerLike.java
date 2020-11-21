/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ControllerPost.conn;
import Model.Comment;
import Model.Liker;
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
    public static boolean insertNewLiker(Liker liker, int idPost){
        conn.connect();
        String query_InsertToComment = "INSERT INTO liker VALUES(?,?)";
        try{
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertToComment);
            stmt.setString(1, liker.getNicknameLike());
            stmt.setInt(2, idPost);
            stmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
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
                likers.add(liker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (likers);
    }
}
