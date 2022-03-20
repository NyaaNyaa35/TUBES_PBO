/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ControllerPost.conn;
import Model.Comment;
import Model.Post;
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
public class ControllerComment {
    
    private Connection dbCon;
    static DatabaseHandler conn = new DatabaseHandler();
    
    public ControllerComment(Connection c)
    {
        dbCon = c;
    }
    
    //mengambil data comment base on idPost
    public static ArrayList<Comment> getListCommentByIDPost(int idPost){
        conn.connect();
        ArrayList<Comment> listComment = new ArrayList<>();
        String query = "SELECT * FROM comment WHERE IdPostingan='" + idPost + "'";
        try{
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setIsiComment(rs.getString("isiComment"));
                comment.setNicknameComment(rs.getString("nicknameCommentar"));
                listComment.add(comment);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listComment;
    }
    
    //mengambil semua data comment dari tabel 
    public static ArrayList<Comment> getAllComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM comment";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setIdComment(rs.getInt("idComment"));
                comment.setIsiComment(rs.getString("isiComment"));
                comment.setNicknameComment(rs.getString("nicknameCommentar"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (comments);
    }
    
    //memasukkan data comment baru ke database tabel comment
    public static boolean insertNewComments(Comment comment, int idPost){
        conn.connect();
        String query_InsertToComment = "INSERT INTO comment VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = conn.con.prepareStatement(query_InsertToComment);
            stmt.setInt(1, comment.getIdComment());
            stmt.setString(2, comment.getIsiComment());
            stmt.setString(3, comment.getNicknameComment());
            stmt.setInt(4, idPost);
            stmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //delete data comment dari tabel comment
    public static boolean deleteComment(String comment) {
        conn.connect();
        String[] kata = comment.split("   ");
        String query = "DELETE FROM comment WHERE isiComment='" + kata[1] + "'";
        System.out.println(kata[1]);
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
