/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author HansNotFound
 */
public class FriendRequest {
    private String Username_user;
    private String Nickname_request;
    private Date Tanggal_request;

    public String getUsername_user() {
        return Username_user;
    }

    public void setUsername_user(String Username_user) {
        this.Username_user = Username_user;
    }

    public String getNickname_request() {
        return Nickname_request;
    }

    public void setNickname_request(String Nickname_request) {
        this.Nickname_request = Nickname_request;
    }

    public Date getTanggal_request() {
        return Tanggal_request;
    }

    public void setTanggal_request(Date Tanggal_request) {
        this.Tanggal_request = Tanggal_request;
    }
    
    
}
