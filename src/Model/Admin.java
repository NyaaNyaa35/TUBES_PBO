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
    boolean admin;

    public Admin() {
    }

    public Admin(boolean admin, String username, String password) {
        super(username, password);
        this.admin = admin;
    }
}
