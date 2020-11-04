/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author HansNotFound
 */
public class Admin extends Person{

    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean Login(String Username, String Password) {
        return Username.equals("Admin12345") && Password.equals("12345");
    }
}
