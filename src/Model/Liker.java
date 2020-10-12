/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author NealsonW
 */
public class Liker {
    private int jumlahLike;
    private String nicknameLike;

    public Liker() {
    }

    public Liker(int jumlahLike, String nicknameLike) {
        this.jumlahLike = jumlahLike;
        this.nicknameLike = nicknameLike;
    }

    public String getNicknameLike() {
        return nicknameLike;
    }

    public void setNicknameLike(String nicknameLike) {
        this.nicknameLike = nicknameLike;
    }

    public int getJumlahLike() {
        return jumlahLike;
    }

    public void setJumlahLike(int jumlahLike) {
        this.jumlahLike = jumlahLike;
    }

    
    
}
