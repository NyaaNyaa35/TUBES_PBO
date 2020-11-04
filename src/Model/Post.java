/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HansNotFound
 */
public class Post {
    private int idPost;
    private String imagepath;
    private String postNickname;
    private ArrayList<Comment> listComment = new ArrayList<>();
    private ArrayList<Liker> listLiker = new ArrayList<>();
    private String waktuPost;
    private String caption;
    private int jumlahLike;

    public Post() {
    }

    public Post(int idPost, String imagepath, String postNickname, String waktuPost, String caption, int jumlahLike) {
        this.idPost = idPost;
        this.imagepath = imagepath;
        this.postNickname = postNickname;
        this.waktuPost = waktuPost;
        this.caption = caption;
        this.jumlahLike = jumlahLike;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPostNickname() {
        return postNickname;
    }

    public void setPostNickname(String postNickname) {
        this.postNickname = postNickname;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public ArrayList<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(ArrayList<Comment> listComment) {
        this.listComment = listComment;
    }

    public ArrayList<Liker> getListLiker() {
        return listLiker;
    }

    public void setListLiker(ArrayList<Liker> listLiker) {
        this.listLiker = listLiker;
    }

    public String getWaktuPost() {
        return waktuPost;
    }

    public void setWaktuPost(String waktuPost) {
        this.waktuPost = waktuPost;
    }
    
    @Override
    public String toString() {
        return "Post{" + "idPost=" + idPost + ", imagepath=" + imagepath + ", postUsername=" + postNickname + ", listComment=" + listComment + ", listLiker=" + listLiker + '}';
    }

    public int getJumlahLike() {
        return jumlahLike;
    }

    public void setJumlahLike(int jumlahLike) {
        this.jumlahLike = jumlahLike;
    }


    
}
