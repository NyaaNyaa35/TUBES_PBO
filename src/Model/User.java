/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import Controller.Controller;
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
    private ArrayList<Post> listPost = new ArrayList<>();
    
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
        return "User{" +"password="+super.getPassword()+", Username="+super.getUsername() +", nickname=" + nickname + ", email=" + email + ", jumlahTeman=" + jumlahTeman + ", profilePict=" + profilePict + ", listTeman=" + listTeman + ", listPost=" + listPost + '}';
    }

    public ArrayList<Post> getListPost() {
        return listPost;
    }

    public void setListPost(ArrayList<Post> listPost) {
        this.listPost = listPost;
    }

    @Override
    public boolean Login(String Username, String Password) {
        ArrayList<User> listUser = Controller.getAllUsers();
        for(int i = 0; i < listUser.size(); i++){
            if(Username.equals(listUser.get(i).getUsername()) && Password.equals(listUser.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }

    
    
}
