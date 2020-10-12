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
public class Post {
    private int idPost;
    private String imagepath;
    private String postUsername;
    private ArrayList<Comment> listComment = new ArrayList<>();
    private ArrayList<Liker> listLiker = new ArrayList<>();

    public Post(int idPost, String imagepath, String postUsername) {
        this.idPost = idPost;
        this.imagepath = imagepath;
        this.postUsername = postUsername;
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

    @Override
    public String toString() {
        return "Post{" + "idPost=" + idPost + ", imagepath=" + imagepath + ", postUsername=" + postUsername + ", listComment=" + listComment + ", listLiker=" + listLiker + '}';
    }
    
}
