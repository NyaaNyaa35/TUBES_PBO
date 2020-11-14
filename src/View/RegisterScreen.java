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
    public RegisterScreen(){
        register();
    }
    JFrame frame;
    JLabel label_datadiri,label_Username,label_Nickname,label_email,label_Password;
    JButton button_Register,button_Login;
    JTextField TF_Username,TF_Nickname,TF_Email;
    JPasswordField passwordfield;
    Boolean Username_filled = false, Nickname_filled = false, Email_filled = false,Pass_filled = false;
    private void register(){
        frame = new JFrame("Register Form");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        label_datadiri = new JLabel("Silahkan isi data diri anda : ");
        label_datadiri.setForeground(Color.blue);
        label_datadiri.setFont(new Font("Serif", Font.BOLD, 20));
        label_datadiri.setBounds(100, 30, 400, 30);
        
        label_Username = new JLabel("Username ");
        label_Username.setBounds(80, 70, 200, 30);
        
        TF_Username = new JTextField(20);
        TF_Username.setBounds(300, 70, 200, 30);
        TF_Username.setColumns(20);
        
        label_Nickname = new JLabel("Nickname ");
        label_Nickname.setBounds(80, 110, 200, 30);
        
        TF_Nickname = new JTextField(20);
        TF_Nickname.setBounds(300, 110, 200, 30);
        TF_Nickname.setColumns(20);
        
        label_email = new JLabel("Email ");
        label_email.setBounds(80, 150, 200, 30);
        
        TF_Email = new JTextField();
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
                User user = new User();
                boolean valid = false;
                String pathDefaultProfilePict = "src/Image/default_profile_pict.png";
                String Nickname = TF_Nickname.getText();
                String Username = TF_Username.getText();
                String Password = passwordfield.getText();
            if(!Nickname.equals("") && !Username.equals("") && !Password.equals("")){
                user.setNickname(Nickname);
                user.setUsername(Username);
                user.setPassword(Password);
                user.setJumlahTeman(0);
                user.setProfilePict(pathDefaultProfilePict);
                ArrayList<User> listUser = ControllerUser.getAllUsers();
                if(ControllerUser.isValidEmail(TF_Email.getText())){
                    user.setEmail(TF_Email.getText());
                    if(!listUser.isEmpty()){
                        for(int i = 0; i < listUser.size(); i++){
                            if(TF_Username.getText().equals(listUser.get(i).getUsername())){
                                JOptionPane.showMessageDialog(null, "Username ini sudah terpakai!!");
                                break;
                            } else if(TF_Nickname.getText().equals(listUser.get(i).getNickname())){
                                JOptionPane.showMessageDialog(null, "Nickname ini sudah terpakai!!");
                                break; 
                            } else if(TF_Email.getText().equals(listUser.get(i).getEmail())){
                                JOptionPane.showMessageDialog(null, "Email ini sudah terpakai!!");
                                break;
                            } else {
                                valid = true;
                                break;
                            }
                        }
                    } else {
                        valid = true;
                    }
                    boolean insert_berhasil;
                    if(valid){
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
            } else {
                JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong!!");
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
