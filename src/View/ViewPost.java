/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Admin;
import Model.Comment;
import Model.Liker;
import Model.Post;
import Model.User;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author NealsonW
 */
public class ViewPost extends JFrame{
    
    ArrayList<Post> listPost = Controller.getAllPost();
    JFrame frame;
    JButton button_Next,button_Prev,button_Upload,button_DeletePost,button_DeleteUser,
            button_LogOut,button_SeeComment, button_Like, button_Profile;
    JLabel label_NicknameUser,label_NicknamePoster,label_KumulatifLike, label_Caption, postImage;
    JPanel panel_Gambar;
    String test = "Hans Patrick Eko Prasetyo Hans Patrick Eko Prasetyo Hans Patrick Eko Prasetyo Hans Patrick";
    
    public ViewPost(User user) {
        Action action = new Action();
        frame = new JFrame();
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        label_NicknameUser = new JLabel(user.getNickname());
        label_NicknameUser.setBounds(75, 20, 100, 30);
        label_NicknameUser.setFont(new Font("Serif",0,15));        

        button_Prev = new JButton("Prev Post");
        button_Prev.setBounds(20, 610, 100, 30);
        button_Prev.addActionListener(action);
        
        button_Next = new JButton("Next Post");
        button_Next.setBounds(460, 610, 100, 30);
        button_Next.addActionListener(action);
        
        button_SeeComment = new JButton("Comment");
        button_SeeComment.setBounds(460, 545, 100, 30);
        button_SeeComment.addActionListener(action);
        
        label_NicknamePoster = new JLabel("Nickname Poster");
        label_NicknamePoster.setBounds(65,540,100,20);
        
        label_KumulatifLike = new JLabel("99999+");
        label_KumulatifLike.setBounds(65,555,50,30);
        
        label_Caption = new JLabel(test);
        label_Caption.setBounds(25, 505, 550, 30);
            
        frame.add(label_NicknameUser);
        frame.add(button_Prev);
        frame.add(button_Next);
        frame.add(button_SeeComment);
        frame.add(label_NicknamePoster);
        frame.add(label_KumulatifLike);
        frame.add(label_Caption);
        
        frame.setLayout(null);
        frame.setVisible(true);
    }

    class Action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch(command){
                case"Prev Post":
                    break;
                case"Next Post":
                    break;
                case"Comment":
                    frame.setVisible(false);
                    //new FrameComment();
                    break;
                default:
                    break;
            }
        }
    }
}
