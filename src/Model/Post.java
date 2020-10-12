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
    private String postUsername;
    private ArrayList<Comment> listComment = new ArrayList<>();
    private ArrayList<Liker> listLiker = new ArrayList<>();
    private Date waktuPost;
    
    public Post(int idPost, String imagepath, String postUsername, Date waktuPost) {
        this.idPost = idPost;
        this.imagepath = imagepath;
        this.postUsername = postUsername;
        this.waktuPost = waktuPost;
    }

    public String getPostUsername() {
        return postUsername;
    }

    public void setPostUsername(String postUsername) {
        this.postUsername = postUsername;
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

    public Date getWaktuPost() {
        return waktuPost;
    }

    public void setWaktuPost(Date waktuPost) {
        this.waktuPost = waktuPost;
    }
    
    @Override
    public String toString() {
        return "Post{" + "idPost=" + idPost + ", imagepath=" + imagepath + ", postUsername=" + postUsername + ", listComment=" + listComment + ", listLiker=" + listLiker + '}';
    }


    
}
