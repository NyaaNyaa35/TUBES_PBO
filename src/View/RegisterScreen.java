/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Controller.ControllerUser;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HansNotFound
 */
public class RegisterScreen extends JFrame implements ActionListener {
    public RegisterScreen(String uname, String Nickname, String Email){
        register(uname, Nickname, Email);
    }
    JFrame frame;
    JLabel label_datadiri,label_Username,label_Nickname,label_email,label_Password;
    JButton button_Register,button_Login;
    JTextField TF_Username,TF_Nickname,TF_Email;
    JPasswordField passwordfield;
    Boolean Username_filled = false, Nickname_filled = false, Email_filled = false,Pass_filled = false;
    private void register(String uname, String Nickname, String Email){
        frame = new JFrame("Register Form");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        label_datadiri = new JLabel("Silahkan isi data diri anda : ");
        label_datadiri.setForeground(Color.blue);
        label_datadiri.setFont(new Font("Serif", Font.BOLD, 20));
        label_datadiri.setBounds(100, 30, 400, 30);
        
        label_Username = new JLabel("Username ");
        label_Username.setBounds(80, 70, 200, 30);
        
        TF_Username = new JTextField(uname);
        TF_Username.setBounds(300, 70, 200, 30);
        TF_Username.setColumns(20);
        
        label_Nickname = new JLabel("Nickname ");
        label_Nickname.setBounds(80, 110, 200, 30);
        
        TF_Nickname = new JTextField(Nickname);
        TF_Nickname.setBounds(300, 110, 200, 30);
        TF_Nickname.setColumns(20);
        
        label_email = new JLabel("Email ");
        label_email.setBounds(80, 150, 200, 30);
        
        TF_Email = new JTextField(Email);
        TF_Email.setBounds(300, 150, 200, 30);
        
        label_Password = new JLabel("Password ");
        label_Password.setBounds(80, 190, 200, 30);
        
        passwordfield = new JPasswordField(20);
        passwordfield.setBounds(300, 190, 200, 30);
        passwordfield.setColumns(20);
        
        button_Register = new JButton("Register");
        button_Register.setBounds(240,250,100,30);
        button_Register.addActionListener(this);
        
        button_Login = new JButton("Sudah Punya Akun? Login Sekarang! Klik di sini");
        button_Login.setBounds(125,300,350,30);
        button_Login.addActionListener(this);
        button_Login.setFocusPainted(false);
        button_Login.setBorderPainted(false);
        button_Login.setContentAreaFilled(false);
        button_Login.setOpaque(false);
        button_Login.setActionCommand("To Login Screen");
        
        frame.add(button_Register);
        frame.add(button_Login);
        
        frame.add(label_datadiri);
        frame.add(label_Username);
        frame.add(label_Nickname);
        frame.add(label_email);
        frame.add(label_Password);
        
        frame.add(TF_Username);
        frame.add(TF_Nickname);
        frame.add(TF_Email);
        frame.add(passwordfield);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch(command){
            case "Register":
                boolean valid = false;
                String pathDefaultProfilePict = "src/Image/default_profile_pict.png";
                String Nickname = TF_Nickname.getText();
                String Username = TF_Username.getText();
                String Password = passwordfield.getText();
            if(Nickname.equals("") && Username.equals("") && Password.equals("")){
                JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong!!");
            } else if(Nickname.length() > 20 || Username.length() > 20 || Password.length() > 20){
                JOptionPane.showMessageDialog(null, "Input data maks = 20 karakter");
            } else {
                ArrayList<User> listUser = ControllerUser.getAllUsers();
                String Email = TF_Email.getText();
                if(ControllerUser.isValidEmail(Email)){
                    if(!listUser.isEmpty()){
                        int cekUsername = ControllerUser.cekDuplikatUsername(Username);
                        int cekNickname = ControllerUser.cekDuplikatUsername(Nickname);
                        int cekEmail = ControllerUser.cekDuplikatEmail(Email);
                        if(cekUsername == 0 && cekNickname == 0 && cekEmail == 0){
                            valid = true;
                        } else if(cekUsername > 0){
                            JOptionPane.showMessageDialog(null, "Username sudah terpakai! Silahkan Isi Ulang data anda","Error",JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            new RegisterScreen(Username, Nickname, Email);
                            break;
                        } else if(cekNickname > 0){
                            JOptionPane.showMessageDialog(null, "Nickname sudah terpakai! Silahkan Isi Ulang data anda","Error",JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            new RegisterScreen(Username, Nickname, Email);
                            break;
                        } else if(cekEmail > 0){
                            JOptionPane.showMessageDialog(null, "Email sudah terpakai! Silahkan Isi Ulang data anda","Error",JOptionPane.ERROR_MESSAGE);
                            frame.setVisible(false);
                            new RegisterScreen(Username, Nickname, Email);
                            break;
                        } 
                    } 
                    boolean insert_berhasil;
                    if(valid){
                        User user = new User();
                        user.setNickname(Nickname);
                        user.setUsername(Username);
                        user.setPassword(Password);
                        user.setJumlahTeman(0);
                        user.setProfilePict(pathDefaultProfilePict);
                        user.setEmail(Email);
                        insert_berhasil = ControllerUser.insertNewUser(user);
                        if(insert_berhasil){
                            JOptionPane.showMessageDialog(null,"Register Berhasil, Anda akan dialihkan ke Login Screen!");
                            frame.setVisible(false);
                            new LoginScreen();
                        } else {
                            JOptionPane.showMessageDialog(null,"Register Gagal!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Register tidak valid!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email tidak valid!");
                }
            }
                break;
            case "To Login Screen":
                frame.setVisible(false);
                new LoginScreen();
                break;
            default:
                break;
        }
    }
}
