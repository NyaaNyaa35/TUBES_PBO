/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Comment;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Nealson William
 */
public class ControllerCommentTest {
    
    private java.sql.Connection dbCon;
    @Before
    public void setUp(){
        String url = "jdbc:mysql://localhost/tubespbo";
        System.out.println("setup called");
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found:" + ex.getMessage());
            return;
        }
        
        
        try{
            dbCon = DriverManager.getConnection(url,"root","");
        } catch(SQLException ex) {
            System.out.println("Error connect to DB : " + ex.getMessage());
            new AssertionError("Koneksi ke DB Gagal");
        }
    }

    @Test
    public void testInsertNewComments() {
        Comment komen = new Comment();
        String isi = "ini kesukaan gua";
        String nickKomen = "test3";
        int idPost = 2;
        komen.setIdComment(komen.countComment());
        komen.setIsiComment(isi);
        komen.setNicknameComment(nickKomen);
        
        ControllerComment cComment = new ControllerComment(dbCon);
        
        try{
            boolean result = cComment.insertNewComments(komen,idPost);
            assertTrue(result);
        }catch(Exception e){
            fail("result gagal");
        }
    }

    
}
