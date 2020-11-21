/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ControllerComment;
import Controller.ControllerUser;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author NealsonW
 */
public class Comment {
    private String nicknameComment;
    private String isiComment;
    private String waktuComment;
    private int idComment;
    public Comment() {
    }

    public Comment(String nicknameComment, String isiComment, int idComment) {
        this.nicknameComment = nicknameComment;
        this.isiComment = isiComment;
        this.idComment = idComment;
    }

    public String getIsiComment() {
        return isiComment;
    }

    public void setIsiComment(String isiComment) {
        this.isiComment = isiComment;
    }

    public String getNicknameComment() {
        return nicknameComment;
    }

    public void setNicknameComment(String nicknameComment) {
        this.nicknameComment = nicknameComment;
    }

    public String getWaktuComment() {
        return waktuComment;
    }

    public void setWaktuComment(String waktuComment) {
        this.waktuComment = waktuComment;
    }
    

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public static int countComment(){
        int count;
        ArrayList<Comment> listComment = ControllerComment.getAllComments();
        if(listComment.isEmpty()){
            return 0;
        }else{
            count = listComment.get(listComment.size()-1).getIdComment()+listComment.size();
        }
        return count;
    }
}
