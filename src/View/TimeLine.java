/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Interface;
import Model.Admin;
import Model.Person;
import Model.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author HansNotFound
 */
public class TimeLine extends JFrame implements Interface{
    JFrame frame_TimeLine;
    JButton button_Next,button_Prev,button_Upload,button_DeletePost,button_DeleteUser,
            button_LogOut,button_SeeComment, button_Like, button_Profile;
    JLabel label_NicknameUser,label_NicknamePoster,label_KumulatifLike, label_Caption;
    JPanel panel_Gambar;
    String test = "Hans Patrick Eko Prasetyo Hans Patrick Eko Prasetyo Hans Patrick Eko Prasetyo Hans Patrick";
    User user;
    Admin admin;
    public TimeLine(Person person){
        if(person instanceof User){
            user = (User) person;
            Action action = new Action();
            frame_TimeLine = new JFrame(Interface.namaApp);
            frame_TimeLine.setSize(600, 700);
            frame_TimeLine.setLocationRelativeTo(null);
            frame_TimeLine.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
            label_NicknameUser = new JLabel(user.getNickname());
            label_NicknameUser.setBounds(75, 20, 100, 30);
            label_NicknameUser.setFont(new Font("Serif",0,15));
        
            button_Profile = new JButton();
            button_Profile.setBounds(23, 20, 35, 25);
            button_Profile.setIcon(new ImageIcon(user.getProfilePict()));
            button_Profile.setFocusPainted(false);
            button_Profile.setBorderPainted(false);
            //button_Profile.setContentAreaFilled(false);
            button_Profile.setActionCommand("ViewProfile");
        
            button_Prev = new JButton("Prev Post");
            button_Prev.setBounds(20, 610, 100, 30);
            button_Prev.addActionListener(action);
        
            button_Next = new JButton("Next Post");
            button_Next.setBounds(460, 610, 100, 30);
            button_Next.addActionListener(action);
        
            button_SeeComment = new JButton("Comment");
            button_SeeComment.setBounds(460, 545, 100, 30);
            button_SeeComment.addActionListener(action);
        
            button_Upload = new JButton("UploadPost");
            button_Upload.setBounds(240, 610, 100, 30);
            button_Upload.addActionListener(action);
        
            button_LogOut = new JButton("LogOut");
            button_LogOut.setBounds(460, 20, 100, 30);
            button_LogOut.addActionListener(action);
        
            panel_Gambar = new JPanel();
            panel_Gambar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel_Gambar.setBounds(20, 75, 550, 430);
        
            Icon iconLike = new ImageIcon("src/Image/Like_Image.png");
            button_Like = new JButton(iconLike);
            button_Like.setBounds(20, 540, 40, 40);
        
            label_NicknamePoster = new JLabel("Nickname Poster");
            label_NicknamePoster.setBounds(65,540,100,20);
        
            label_KumulatifLike = new JLabel("99999+");
            label_KumulatifLike.setBounds(65,555,50,30);
        
            String panjang = "" + test.length();
        
            label_Caption = new JLabel(test);
            label_Caption.setBounds(25, 505, 550, 30);
        
            frame_TimeLine.add(button_Profile);
            frame_TimeLine.add(label_Caption);
            frame_TimeLine.add(label_KumulatifLike);
            frame_TimeLine.add(label_NicknamePoster);
            frame_TimeLine.add(button_Like);
            frame_TimeLine.add(panel_Gambar);
            frame_TimeLine.add(button_SeeComment);
            frame_TimeLine.add(button_Next);
            frame_TimeLine.add(button_Prev);
            frame_TimeLine.add(button_LogOut);
            frame_TimeLine.add(button_Upload);
            frame_TimeLine.add(label_NicknameUser);
            frame_TimeLine.setLayout(null);
            frame_TimeLine.setVisible(true);
        }
    }

    class Action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch(command){
                case"LogOut":
                    int tutup = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin LogOut?");
                    if(tutup == 0){
                        JOptionPane.showMessageDialog(null, "Anda berhasil LogOut");
                        frame_TimeLine.setVisible(false);
                        LoginScreen loginScreen = new LoginScreen();
                    }
                    break;
                case"Prev Post":
                    break;
                case"Next Post":
                    break;
                case"UploadPost":
                    frame_TimeLine.setVisible(false);
                    //new CreatePost();
                    break;
                case"Comment":
                    frame_TimeLine.setVisible(false);
                    //new FrameComment();
                    break;
                case "ViewProfile":
                    new ViewProfile(user);
                    break;
                default:
                    break;
            }
        }
    }
}
