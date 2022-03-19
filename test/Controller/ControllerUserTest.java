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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author rafae
 */
public class ControllerUserTest {
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
    
    /**
     * Test of insertPerson method, of class ControllerUser.
     */
    @Test
    public void testInsertPerson() {
        System.out.println("insertPerson");
        
        String username = "rafaelag";
        String password = "abc123";
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        ControllerUser cUser = new ControllerUser(dbCon);
        try{
            boolean result = cUser.insertPerson(user);
            assertTrue(result);
        }catch(Exception e){
            fail("result gagal");
        }
    }

    /**
     * Test of insertUser method, of class ControllerUser.
     */
    @Test
    public void testInsertUser() {
        System.out.println("insertUser");
        
        String username = "rafaelag";
        String Nickname = "rap";
        String email = "abc112";
        int jumlahTeman = 0;
        String pp = "src/Image/default_profile_pict.png";
        
        User user = new User();
        user.setUsername(username);
        user.setNickname(Nickname);
        user.setEmail(email);
        user.setJumlahTeman(jumlahTeman);
        user.setProfilePict(pp);
        
        ControllerUser cUser = new ControllerUser(dbCon);
        try{
            boolean result = cUser.insertUser(user);
            assertTrue(result);
        }catch(Exception e){
            fail("result gagal");
        }
    }

}
