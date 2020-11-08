/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Font;
import Controller.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
/**
 *
 * @author HansNotFound
 */
public class MainMenuScreen extends JFrame implements Interface {
    
    public MainMenuScreen() {
        showMainMenu();     
    }
    JFrame frame_Main;
    JLabel label_namaApp;
    JButton button_Exit,button_Begin;
    private void showMainMenu() {
        frame_Main = new JFrame(Interface.namaApp);
        frame_Main.setLocationRelativeTo(null);
        frame_Main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_Main.setSize(300, 300);
        
        label_namaApp = new JLabel("Welcome to "+Interface.namaApp);
        label_namaApp.setBounds(50,30,200,30);
        label_namaApp.setFont(new Font("Calibri",Font.BOLD,15));
        
        Action action = new Action();
        
        button_Exit = new JButton("EXIT");
        button_Exit.setBounds(100, 150, 100, 30);
        button_Exit.addActionListener(action);
        
        button_Begin = new JButton("Begin Your Journey!");
        button_Begin.setBounds(50,100, 200, 30);
        button_Begin.addActionListener(action);
        
        frame_Main.add(button_Exit);
        frame_Main.add(button_Begin);
        frame_Main.add(label_namaApp);
        frame_Main.setLayout(null);
        frame_Main.setVisible(true);
    }
    
    class Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==button_Exit){
                System.exit(0);
            } else if(ae.getSource()==button_Begin){
                frame_Main.setVisible(false);
                new RegisterScreen();
            }
        }
        
    }
}
