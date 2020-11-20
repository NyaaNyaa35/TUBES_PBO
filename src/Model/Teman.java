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
public class Teman {
    private int idTeman;
    private String Username_user;
    private String Nickname_teman;

    @Override
    public String toString() {
        return "Teman{" + "idTeman=" + idTeman + ", Username_user=" + Username_user + ", Nickname_teman=" + Nickname_teman + '}';
    }

    public Teman() {
    }

    public Teman(int idTeman, String Username_user, String Nickname_teman) {
        this.idTeman = idTeman;
        this.Username_user = Username_user;
        this.Nickname_teman = Nickname_teman;
    }

    public String getUsername_user() {
        return Username_user;
    }

    public void setUsername_user(String Username_user) {
        this.Username_user = Username_user;
    }

    public String getNickname_teman() {
        return Nickname_teman;
    }

    public void setNickname_teman(String Nickname_teman) {
        this.Nickname_teman = Nickname_teman;
    }

    public int getIdTeman() {
        return idTeman;
    }

    public void setIdTeman(int idTeman) {
        this.idTeman = idTeman;
    }
}
