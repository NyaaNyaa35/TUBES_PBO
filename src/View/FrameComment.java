/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerComment;
import Model.Comment;
import Model.User;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author HansNotFound
 */
public class FrameComment implements ActionListener,ListSelectionListener{

    JFrame frame_Comment;
    JTextField comments;
    JButton submit,delete;
    User containUser;
    String nicknameUser, isiComment;
    int IdPost, counter;
    Comment comment = new Comment();
    JList<String> list;
    JScrollPane scrollBar;
    public FrameComment(User users, int idPost, int counter_post) {

        containUser = users;
        nicknameUser = users.getNickname();
        IdPost = idPost;
        counter = counter_post;
        ArrayList<Comment> listComment = ControllerComment.getListCommentByIDPost(IdPost);
        DefaultListModel<String> l1 = new DefaultListModel<>();

        frame_Comment = new JFrame("Comment");
        frame_Comment.setLocationRelativeTo(null);
        frame_Comment.setSize(380, 350);

        comments = new JTextField();
        comments.setBounds(30, 50, 300, 30);

        submit = new JButton("PostComment");
        submit.setBounds(180, 80, 150, 30);
        submit.addActionListener(this);

        delete = new JButton("Delete");
        delete.setEnabled(false);
        delete.setBounds(30, 80, 100, 30);
        delete.addActionListener(this);
        
        for (int i = 0; i < listComment.size(); i++) {
            l1.addElement(listComment.get(i).getNicknameComment()+"   "+listComment.get(i).getIsiComment());
        }

        list = new JList<>(l1);
        list.setBounds(30, 130, 300, 160);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);      
        list.addListSelectionListener(this);
        
        scrollBar = new JScrollPane(list);
        frame_Comment.getContentPane().add(scrollBar);
        
        frame_Comment.add(delete);
        frame_Comment.add(comments);
        frame_Comment.add(submit);
        frame_Comment.add(list);
        
        frame_Comment.setLayout(null);
        frame_Comment.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch (command) {
            case "PostComment":
                isiComment = comments.getText();
                if (isiComment.equals("")) {
                    JOptionPane.showMessageDialog(null, "Comment masih kosong!!", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                } else {
                    comment.setIsiComment(isiComment);
                    comment.setNicknameComment(nicknameUser);
                    comment.setIdComment(Comment.countComment()); 
                    boolean insertComment = ControllerComment.insertNewComments(comment, IdPost);
                    if (insertComment) {
                        JOptionPane.showMessageDialog(null, "Comment berhasil di post");
                        frame_Comment.setVisible(false);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Comment gagal di post");
                        break;
                    }
                }
            case "Delete":
                String isiComment = (String) list.getSelectedValue();
                boolean deleteComment = ControllerComment.deleteComment(isiComment);
                if(deleteComment){
                    JOptionPane.showMessageDialog(null, "Comment berhasil di hilangkan!");
                    break;
                }else{
                    JOptionPane.showMessageDialog(null, "Comment gagal di hilangkan");
                    break;
                }                
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(list.getSelectedIndex() > -1){
             delete.setEnabled(true);
        }
    }
}
