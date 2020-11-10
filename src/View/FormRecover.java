package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.Controller;
import Model.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author Davjd
 */
public class FormRecover extends JFrame implements ActionListener {
    JLabel label_IsiForm, label_Username, label_Email, label_Nickname;
    JTextField tf_Username, tf_Email, tf_Nickname;
    JButton btn1;
    JFrame frame;
 
    public FormRecover() {
        frame = new JFrame("Isi Form Recover Password");
        label_IsiForm = new JLabel("Isi Form");
        label_IsiForm.setForeground(Color.blue);
        label_IsiForm.setFont(new Font("Serif", Font.BOLD, 20));

        label_Username = new JLabel("Username");
        label_Email = new JLabel("Email");
        label_Nickname = new JLabel("NIckname");
        tf_Username = new JTextField();
        tf_Email = new JTextField();
        tf_Nickname = new JTextField();
        btn1 = new JButton("Verify");
        btn1.addActionListener(this);

        label_IsiForm.setBounds(100, 30, 400, 30);
        label_Username.setBounds(80, 70, 200, 30);
        label_Email.setBounds(80, 110, 200, 30);
        label_Nickname.setBounds(80, 150, 200, 30);
        tf_Username.setBounds(300, 70, 200, 30);
        tf_Email.setBounds(300, 110, 200, 30);
        tf_Nickname.setBounds(300, 150, 200, 30);
        btn1.setBounds(80, 200, 100, 30);

        frame.add(label_IsiForm);
        frame.add(label_Username);
        frame.add(tf_Email);
        frame.add(label_Email);
        frame.add(tf_Username);
        frame.add(label_Nickname);
        frame.add(tf_Nickname);
        frame.add(btn1);

        frame.setSize(700, 350);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch(command){
            case "Verify" :
             String uname = tf_Username.getText();
             String nname = tf_Nickname.getText();
             String email = tf_Email.getText();
             ArrayList<User> listUser = Controller.getAllUsers();
             for(int i = 0; i < listUser.size() ; i++){
                 if(uname.equals(listUser.get(i).getUsername())){
                     if(nname.equals(listUser.get(i).getNickname())){
                         if(email.equals(listUser.get(i).getEmail())){
                            JOptionPane.showMessageDialog(null, "Verified");
                            frame.setVisible(false);
                            new RecoverPassword(listUser.get(i));
                            break;
                         }
                     }
                 } else {
                     JOptionPane.showMessageDialog(this, "Not Verified",
                     "Error", JOptionPane.ERROR_MESSAGE);
                     break;
                 }
             }
            break;
        }
    }
}