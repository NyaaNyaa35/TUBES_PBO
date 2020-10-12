/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author HansNotFound
 */
public class User extends Person{
    private String nickname;
    private String email;
    private int jumlahTeman;
    private String profilePict;
    private ArrayList<User> listTeman = new ArrayList<>();
    
    public User() {
    }

    public User(String nickname, String email,int jumlahTeman,String profilePict,String username, String password) {
        super(username, password);
        this.nickname = nickname;
        this.email = email;
        this.jumlahTeman = jumlahTeman;
        this.profilePict = profilePict;
    }

    public String getProfilePict() {
        return profilePict;
    }

    public void setProfilePict(String profilePict) {
        this.profilePict = profilePict;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJumlahTeman() {
        return jumlahTeman;
    }

    public void setJumlahTeman(int jumlahTeman) {
        this.jumlahTeman = jumlahTeman;
    }

    public ArrayList<User> getListTeman() {
        return listTeman;
    }

    public void setListTeman(ArrayList<User> listTeman) {
        this.listTeman = listTeman;
    }

    @Override
    public String toString() {
        return "User{" + "nickname=" + nickname + ", email=" + email + ", jumlahTeman=" + jumlahTeman + ", profilePict=" + profilePict + ", listTeman=" + listTeman + '}';
    }

    
    
}
