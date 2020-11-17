/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.User;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Controller.ControllerUser;

/**
 *
 * @author HansNotFound
 */
public class ViewProfile extends JFrame implements ActionListener{
    JFrame frame_Profile;
    JLabel  label_Nickname, label_Email, label_ProfilePict, label_KeteranganPict;
    JFileChooser file_ProfilePict;
    JButton button_back, button_RecoverPassword, button_Save, button_seeTeman, 
            button_seeAddTeman, button_seeRequestTeman,button_PickFile, button_viewpost;
    JTextField TF_Nickname,TF_Email;
    String pathFriendImage = "src/Image/friend_Image_35_x35.PNG";
    User user;
    int counter;
    public ViewProfile(User users,int counter_post){
        VP(users, counter_post);
    }
    private void VP(User users,int counter_post){
        user = users;
        counter = counter_post;
        frame_Profile = new JFrame("Your Profile");
        frame_Profile.setSize(400, 450);
        frame_Profile.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_Profile.setLocationRelativeTo(null);
        
        Icon icon = new ImageIcon(user.getProfilePict());
        label_ProfilePict = new JLabel();
        label_ProfilePict.setIcon(icon);
        label_ProfilePict.setBounds(20, 20, 100, 100);
        
        button_PickFile = new JButton("Choose File");
        button_PickFile.setBounds(130, 60, 100, 30);
        button_PickFile.addActionListener(this);
        
        label_KeteranganPict = new JLabel();
        label_KeteranganPict.setBounds(20, 120, 380, 20);
        label_KeteranganPict.setText("ps : Pict View Max = 100 x 100; Pict on Timeline Max = 50 x 50");
        label_KeteranganPict.setFont(new Font("Serif",Font.BOLD,10));
        
        label_Nickname = new JLabel("Nickname");
        label_Nickname.setBounds(20, 150, 100, 30);
        
        TF_Nickname = new JTextField(user.getNickname());
        TF_Nickname.setBounds(130, 150, 200, 25);
        
        label_Email = new JLabel("Email");
        label_Email.setBounds(20, 180, 100, 30);
        
        String strEmail = user.getEmail();
        String sub_email_1 = strEmail.substring(0, 1);
        String sub_email_2 = strEmail.substring(strEmail.length()-10,strEmail.length());
        String str_Email = "" +sub_email_1 + "***" + sub_email_2;
        TF_Email = new JTextField(str_Email);
        TF_Email.setBounds(130, 180, 200, 25);
        TF_Email.setEnabled(false);
        
        String jumlahTeman = " " + user.getJumlahTeman();
        Icon icon_2 = new ImageIcon(pathFriendImage);
        button_seeTeman = new JButton(jumlahTeman);
        button_seeTeman.setIcon(icon_2);
        button_seeTeman.setBounds(270, 15, 85, 30);
        button_seeTeman.setActionCommand("SeeFriend");
        button_seeTeman.addActionListener(this);
        
        button_viewpost = new JButton("View Post");
        button_viewpost.setBounds(263, 60, 100, 30);
        button_viewpost.addActionListener(this);
        
        button_seeAddTeman = new JButton("Add Friend");
        button_seeAddTeman.setBounds(50,240,100,30);
        button_seeAddTeman.addActionListener(this);
        
        button_seeRequestTeman = new JButton("Friend Request");
        button_seeRequestTeman.setBounds(220,240,120,30);
        button_seeRequestTeman.addActionListener(this);
        
        button_back = new JButton("Back");
        button_back.setBounds(50, 300, 100, 30);
        button_back.addActionListener(this);
        
        button_Save = new JButton("Save");
        button_Save.setBounds(230, 300, 100, 30);
        button_Save.addActionListener(this);
        
        button_RecoverPassword = new JButton("Recover Password");
        button_RecoverPassword.setBounds(20, 360, 350, 30);
        button_RecoverPassword.addActionListener(this);
        
        frame_Profile.add(button_viewpost);
        frame_Profile.add(button_PickFile);
        frame_Profile.add(button_back);
        frame_Profile.add(button_RecoverPassword);
        frame_Profile.add(button_Save);
        frame_Profile.add(button_seeTeman);
        frame_Profile.add(button_seeAddTeman);
        frame_Profile.add(button_seeRequestTeman);
        frame_Profile.add(TF_Nickname);
        frame_Profile.add(TF_Email);
        frame_Profile.add(label_KeteranganPict);
        frame_Profile.add(label_Nickname);
        frame_Profile.add(label_Email);
        frame_Profile.add(label_ProfilePict);
        frame_Profile.setLayout(null);
        frame_Profile.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch(command){
            case"Back":
                frame_Profile.setVisible(false);
        TimeLine timeLine = new TimeLine(user, counter);
                break;
            case"Save":
                break;
            case"Recover Password":
                frame_Profile.setVisible(false);
        FormRecover formRecover = new FormRecover();
                break;
            case"Add Friend":
                String nick = JOptionPane.showInputDialog("Silahkan masukkan Nickname user = ");
                boolean berhasil = ControllerUser.searchUser(user,nick);
                if(berhasil){
                    if(ControllerUser.tambahJumlahTeman(user)){
                        JOptionPane.showMessageDialog(null, "Sekarang " +nick+ " sudah menjadi teman anda!");
                        frame_Profile.setVisible(false);
                        ViewProfile viewProfile = new ViewProfile(user, counter);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan cek kembali nickname yang anda masukkan",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case"Friend Request":
                break;
            case"SeeFriend":
                JOptionPane.showMessageDialog(null,"See Friend Button");
                break;
            case"View Post":
                new ViewPost(user,counter);
                break;
            default:
                break;
        }
    }
}
