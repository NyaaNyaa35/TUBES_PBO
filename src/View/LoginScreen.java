/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerPost;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Controller.ControllerUser;
import Model.Admin;
import Model.Post;
import Model.User;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author HansNotFound
 */
public class LoginScreen extends JFrame implements ActionListener {

    public LoginScreen() {
        Logins();
    }
    JFrame frame;
    JLabel label_username, label_Login, label_Password, label_Register;
    JTextField textfield_Username;
    JButton button_Login, button_RecoverPass, button_Register;
    JPasswordField passwordfield;

    private void Logins() {

        frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        label_Login = new JLabel("Login Form");
        label_Login.setForeground(Color.blue);
        label_Login.setFont(new Font("Serif", Font.BOLD, 20));

        label_username = new JLabel("Username");
        label_Password = new JLabel("Password");
        textfield_Username = new JTextField();
        passwordfield = new JPasswordField();
        button_Login = new JButton("Login");
        button_Login.addActionListener(this);
        button_RecoverPass = new JButton("Recover Password");
        button_RecoverPass.addActionListener(this);
        button_Register = new JButton("Belum punya akun? Ayo daftar Sekarang Klik di sini");
        button_Register.addActionListener(this);
        button_Register.setFocusPainted(false);
        button_Register.setBorderPainted(false);
        button_Register.setContentAreaFilled(false);
        button_Register.setOpaque(false);
        button_Register.setActionCommand("To Regis Screen");

        label_Login.setBounds(100, 30, 400, 30);
        label_username.setBounds(80, 70, 200, 30);
        label_Password.setBounds(80, 110, 200, 30);
        textfield_Username.setBounds(300, 70, 200, 30);
        passwordfield.setBounds(300, 110, 200, 30);
        button_Login.setBounds(100, 160, 100, 30);
        button_RecoverPass.setBounds(250, 160, 150, 30);
        button_Register.setBounds(110, 200, 350, 30);

        frame.add(label_Login);
        frame.add(label_username);
        frame.add(textfield_Username);
        frame.add(label_Password);
        frame.add(passwordfield);
        frame.add(button_Login);
        frame.add(button_RecoverPass);
        frame.add(button_Register);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch (command) {
            case "Login":
                String uname = textfield_Username.getText();
                String pass = passwordfield.getText();
                Admin admin = new Admin();
                User user = new User();
                if (!uname.equals("") && !pass.equals("")) {
                    if (admin.Login(uname, pass)) {
                        frame.setVisible(false);
                        TimeLine timeLine = new TimeLine(admin,1);
                    } else if (user.Login(uname, pass)) {
                        frame.setVisible(false);
                        ArrayList<User> listUser = ControllerUser.getAllUsers();
                        for(int i = 0; i < listUser.size();i++){
                            if(listUser.get(i).getUsername().equals(uname)){
                                user = listUser.get(i);
                                break;
                            }
                        }
                        ArrayList<Post> listPost = ControllerPost.getListPostByUser(uname);
                        if(!listPost.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Maaf mengganti ProfilePict akan di ditiadakan untuk periode tertentu!!","Alert",JOptionPane.ERROR_MESSAGE);
                            TimeLine timeLine = new TimeLine(user,1);
                        } else {
                            JOptionPane.showMessageDialog(null,"Maaf mengganti ProfilePict akan di ditiadakan untuk periode tertentu!!","Alert",JOptionPane.ERROR_MESSAGE);
                            TimeLine timeLine = new TimeLine(user,0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect login or password",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong!!");
                }
                break;
            case "To Regis Screen":
                frame.setVisible(false);
                RegisterScreen registerScreen = new RegisterScreen("","","");
                break;
            case "Recover Password":
                frame.setVisible(false);
        FormRecover formRecover = new FormRecover();
                break;
            default:
                break;
        }
    }
}
