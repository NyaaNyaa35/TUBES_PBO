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
    private String nicknameLike;

    public Liker() {
    }

    public String getNicknameLike() {
        return nicknameLike;
    }

    public void setNicknameLike(String nicknameLike) {
        this.nicknameLike = nicknameLike;
    }

    @Override
    public String toString() {
        return "Liker{" + "nicknameLike=" + nicknameLike + '}';
    }

    
}
