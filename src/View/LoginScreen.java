/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
import Controller.Controller;
import Model.Admin;
import Model.User;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *
 * @author HansNotFound
 */
public class LoginScreen extends JFrame implements ActionListener {
    public LoginScreen(){
        Logins();
    }
    JFrame frame;
    JLabel label_username, label_Login, label_Password,label_Register;
    JTextField textfield_Username;
    JButton button_Login,button_RecoverPass,button_Register;
    JPasswordField passwordfield;
    private void Logins(){
    
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
        button_Register = new JButton("Belum punya akun? Ayo daftar Sekarang");
        button_Register.addActionListener(this);

        label_Login.setBounds(100, 30, 400, 30);
        label_username.setBounds(80, 70, 200, 30);
        label_Password.setBounds(80, 110, 200, 30);
        textfield_Username.setBounds(300, 70, 200, 30);
        passwordfield.setBounds(300, 110, 200, 30);
        button_Login.setBounds(100, 160, 100, 30);
        button_RecoverPass.setBounds(250, 160, 200, 30);
        button_Register.setBounds(130,200,300,30);

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
        switch(command){
            case "Login" :
                String uname = textfield_Username.getText();
                String pass = passwordfield.getText();
                Admin admin = new Admin();
                User user = new User();
                if (admin.Login(uname, pass)) {
                    frame.setVisible(false);
                    TimeLine timeLine = new TimeLine(admin);
                } else if(user.Login(uname, pass)){
                    frame.setVisible(false);
                    TimeLine timeLine = new TimeLine(Controller.getUser(uname));
                } else{
                    JOptionPane.showMessageDialog(null, "Incorrect login or password",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Belum punya akun? Ayo daftar Sekarang":
                frame.setVisible(false);
        RegisterScreen registerScreen = new RegisterScreen();
                break;
            case "Recover Password":
                frame.setVisible(false);
        RecoverPasswordScreen recoverPasswordScreen = new RecoverPasswordScreen();
                break;
            default :
                break;
        }
    }
    //INI PERCOBAAN HANS PATRICK JANGAN D GANGGU!!!! NGERTI GK KAU??!!
    /*class KeyLis implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                actionPerformed("Login");
            }
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }*/
}

