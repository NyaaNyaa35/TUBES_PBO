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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author HansNotFound
 */
public class FrameComment extends JFrame implements ActionListener {

    JFrame frame_Comment;
    JTextField comments;
    JButton submit;
    User containUser;
    String nicknameUser, isiComment;
    int IdPost, counter;
    Comment comment = new Comment();

    public FrameComment(User users, int idPost, int counter_post) {

        containUser = users;
        nicknameUser = users.getNickname();
        IdPost = idPost;
        counter = counter_post;
        ArrayList<Comment> listComment = ControllerComment.getListCommentByIDPost(IdPost);
        DefaultListModel<String> l1 = new DefaultListModel<>();

        frame_Comment = new JFrame("Comment");
        frame_Comment.setLocationRelativeTo(null);
        frame_Comment.setSize(300, 500);

        comments = new JTextField();
        comments.setBounds(30, 50, 100, 30);

        submit = new JButton("PostComment");
        submit.setBounds(100, 80, 100, 30);
        submit.addActionListener(this);

        //int width = 20;
        //String strLabel = "label_";
        JLabel label = new JLabel();
        for (int i = 0; i < listComment.size(); i++) {
            l1.addElement(listComment.get(i).getIsiComment());
        }

        JList<String> list = new JList<>(l1);
        list.setBounds(100, 100, 90, 90);
        list.setEnabled(false);

        UIManager.put("Label.disabledForeground", Color.black);

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
        }
    }
}
