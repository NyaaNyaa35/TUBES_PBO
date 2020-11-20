/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerPost;
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
import Model.Post;
import Model.Teman;
import Model.UserManager;
import static View.TimeLine.loadImage;
import static View.TimeLine.resize;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.imageio.ImageIO;

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
    File fileFoto;
    String pathFoto;
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
        BufferedImage loadImgProfile = loadImage(UserManager.getInstance().getUser().getProfilePict());
        ImageIcon imageIconProfile = new ImageIcon(resize(loadImgProfile,100,100)) ;
        label_ProfilePict.setIcon(imageIconProfile);
        label_ProfilePict.setBounds(20, 20, 100, 100);
        
        button_PickFile = new JButton("Choose File");
        button_PickFile.setBounds(130, 60, 100, 30);
        button_PickFile.addActionListener(this);
        button_PickFile.setEnabled(false);
        
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
                String newNickname = TF_Nickname.getText();
                if(newNickname.equals(user.getNickname())){
                    JOptionPane.showMessageDialog(null, "Nothing Changes","Alert",JOptionPane.ERROR_MESSAGE);
                } else{
                    String getPassword =  JOptionPane.showInputDialog(null,"Masukkan Password anda","Password Vertification",JOptionPane.WARNING_MESSAGE);
                    if(getPassword.equals(user.getPassword())){
                        if(ControllerUser.updateNickname(user,newNickname)){
                            if(ControllerUser.updateNicknameList_in_ListTeman(user, newNickname)){
                                if(ControllerUser.updateNicknameList_in_Postingan(user, newNickname)){
                                    JOptionPane.showMessageDialog(null, "Vertified!, Silahkan ReLogin!","Berhasil",JOptionPane.INFORMATION_MESSAGE);
                                    frame_Profile.setVisible(false);
                                    new LoginScreen();
                                }
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Password Salah!!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case"Recover Password":
                frame_Profile.setVisible(false);
        FormRecover formRecover = new FormRecover();
                break;
            case"Add Friend":
                String nick = JOptionPane.showInputDialog("Silahkan masukkan Nickname user = ");
                boolean userterdaftar = false;
                boolean terdaftarsebagaiteman = false;
                ArrayList<Teman> listAllTeman = ControllerUser.getAllTeman();
                ArrayList<User> listAllUser = ControllerUser.getAllUsers();
                for(int i = 0; i < listAllUser.size(); i++){
                    if(listAllUser.get(i).getNickname().equals(nick)){
                        userterdaftar = true;
                        break;
                    }
                }
                if(userterdaftar){
                    for(int i = 0; i < listAllTeman.size(); i++){
                        if(listAllTeman.get(i).getUsername_user().equals(user.getUsername())){
                            if(listAllTeman.get(i).getNickname_teman().equals(nick)){
                                terdaftarsebagaiteman = true;
                                break;
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan cek kembali nickname yang anda masukkan",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                if(!terdaftarsebagaiteman){
                    if(ControllerUser.addFriend(user,nick)){
                        JOptionPane.showMessageDialog(null, "Sekarang " +nick+ " sudah menjadi teman anda!");
                        frame_Profile.setVisible(false);
                        ViewProfile viewProfile = new ViewProfile(user, counter);
                    } else {
                        JOptionPane.showMessageDialog(null, "Add Teman GAGAL!!",
                            "Error",JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nickname Tersebut sudah terdaftar sebagai teman",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                break;
            case"Friend Request":
                break;
            case"SeeFriend":
                //new FrameListTeman(user);
                frame_Profile.setVisible(false);
                break;
            case"View Post":
                frame_Profile.setVisible(false);
                ArrayList<Post> listPostByUser = ControllerPost.getListPostByUser(user.getUsername());
                if(listPostByUser.isEmpty()){
                    new ViewPost(user,0);
                } else {
                    new ViewPost(user,1);
                }
                break;
            case "Choose File":
            file_ProfilePict =  new JFileChooser();
            int status = file_ProfilePict.showOpenDialog(null);
            if(status == JFileChooser.APPROVE_OPTION){
                int option = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mengganti gambar profile anda?");
                if(option == JOptionPane.YES_OPTION){
                    fileFoto = file_ProfilePict.getSelectedFile();
                    pathFoto = fileFoto.getAbsolutePath();
                    user.setProfilePict(pathFoto);
                    if(ControllerUser.updateProfilePict(user)){
                        JOptionPane.showMessageDialog(null, "Update berhasil! Silahkan ReLogin");
                        frame_Profile.setVisible(false);
                        new LoginScreen();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No File Selected!!","Alert",JOptionPane.ERROR_MESSAGE);
            }
                break;
            default:
                break;
        }
    }
    public static BufferedImage loadImage(String ref) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(ref));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }
    //Method untuk Resize Image
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH,img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    } 
}
