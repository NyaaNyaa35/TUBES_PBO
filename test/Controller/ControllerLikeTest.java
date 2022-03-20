/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controller;

import Model.Liker;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HansWasTaken
 */
public class ControllerLikeTest {
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
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertNewLiker method, of class ControllerLike.
     */
    @Test
    public void testInsertNewLiker() {
        System.out.println("Test case insertNewLiker");
        
        // Declare new object
        Liker liker = new Liker();
        
        //fill the object
        String nicknameLiker = "testing";
        liker.setIdPost(0);
        liker.setNicknameLike(nicknameLiker);
        //try the test if failed show the message
        try{
            boolean result = ControllerLike.insertNewLiker(liker);
            assertTrue(result);
        } catch (Exception ex){
            System.out.println("Exception Error = " + ex.getMessage());
            fail("The test failed");
        }
    }
    @Test
    public void testCheckLike() {
        System.out.println("Test case checkLike");
        String nick = "testing kedua";
        int idPost = 2;
        int expResult = 1;
        try{
            int result = ControllerLike.checkLike(nick, idPost);
            assertEquals(expResult, result);
        } catch (Exception ex){
            System.out.println("Exception Error = " + ex.getMessage());
            fail("The test failed");
        }
    }
}
