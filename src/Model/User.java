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
public class User extends Person{
    String nickname;
    String email;

    public User() {
    }

    public User(String nickname, String email, String username, String password) {
        super(username, password);
        this.nickname = nickname;
        this.email = email;
    }
    
}
