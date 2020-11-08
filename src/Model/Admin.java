/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Interface;

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
    public boolean Login(String Username, String Password){
        return Username.equals(Interface.unameAdmin) && Password.equals(Interface.passAdmin);
    }

    @Override
    public String toString() {
        return "Admin{"+ super.getUsername() + super.getPassword() + '}';
    }
    
}
