/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
 
public class Koneksi {
    public static Connection con;
    public static Statement stm;
    public Koneksi(){
        try {
            String url ="jdbc:mysql://localhost/tubes_pbo";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
        } catch (Exception e) {
            System.err.println("koneksi gagal" +e.getMessage());
        }
    }
        
}