package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import Controller.ControllerUser;
import javax.swing.WindowConstants;
/**
 *
 * @author Davjd
 */
public class RecoverPassword extends JFrame implements ActionListener{
    JLabel label_RecoverPassword, label_Password, label_RePassword;
    JButton button_change, button_back;
    JPasswordField password_1,password_2;
    User user_get;
    JFrame frame;
    public RecoverPassword(User user) {
        user_get = user;
        frame = new JFrame("Recover Password");
        frame.setSize(700, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        label_RecoverPassword = new JLabel("Recover Password");
        label_RecoverPassword.setForeground(Color.blue);
        label_RecoverPassword.setFont(new Font("Serif", Font.BOLD, 20));

        password_1 = new JPasswordField();
        label_Password = new JLabel("Password");
        label_RePassword = new JLabel("Re-enter Password");
        password_2 = new JPasswordField();
        button_change = new JButton("Change Password");
        button_change.addActionListener(this);
        button_back = new JButton("Back to MainMenu");
        button_back.addActionListener(this);

        label_RecoverPassword.setBounds(100, 30, 400, 30);
        label_Password.setBounds(80, 70, 200, 30);
        label_RePassword.setBounds(80, 110, 200, 30);
        password_1.setBounds(300, 70, 200, 30);
        password_2.setBounds(300, 110, 200, 30);
        button_change.setBounds(80, 160, 200, 30);
        button_back.setBounds(300, 160, 200, 30);

        frame.add(button_back);
        frame.add(label_RecoverPassword);
        frame.add(label_Password);
        frame.add(label_RePassword);
        frame.add(password_1);
        frame.add(password_2);
        frame.add(button_change);

        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch(command){
            case "Change Password" :
             String pass1 = password_1.getText();
             String pass2 = password_2.getText();
            if (pass1 == null ? pass2 == null : pass1.equals(pass2) ) {
                if(ControllerUser.recoverPassword(user_get.getUsername(),pass1)){
                    JOptionPane.showMessageDialog(null, "Berhasil, Anda Akan dialihkan ke LoginScreen!");
                    frame.setVisible(false);
                    new LoginScreen();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Passwprd doesnt same hehe",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            break;
            case "Back to MainMenu":
                frame.setVisible(false);
                new MainMenuScreen();
                break;
        }
    }
}
