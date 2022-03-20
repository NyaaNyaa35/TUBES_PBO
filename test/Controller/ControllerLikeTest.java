package Controller;

import Model.Liker;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
   
    @Test
    public void testGetLiker() {
        String nicknameLiker = "test3";
        String nicknameLiker2 = "test2";
        int idPost = 1;
        
        
        ControllerLike cLike = new ControllerLike(dbCon);
        
        if(cLike.checkLike(nicknameLiker,idPost) == 0 && cLike.checkLike(nicknameLiker2,idPost) == 0){
            Liker likerPost = new Liker();
            likerPost.setNicknameLike(nicknameLiker);
            likerPost.setIdPost(idPost);
            cLike.insertNewLiker(likerPost);
            likerPost.setNicknameLike(nicknameLiker2);
            likerPost.setIdPost(idPost);
            cLike.insertNewLiker(likerPost);

            cLike.updateLike(idPost);
            
            try{
                int expected = 2;
                int result = cLike.getLiker(idPost).size();
                System.out.print(expected+","+result);
                assertEquals(expected,result);
                expected = result;
            }catch(Exception e){
                fail("result gagal");
            }
        } else {
            fail("gagal");
        }
 
    }
    
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
