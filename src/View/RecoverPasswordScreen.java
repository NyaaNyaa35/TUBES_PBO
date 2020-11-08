/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author HansNotFound
 */
public class RecoverPasswordScreen extends JFrame implements ActionListener{
    
    JFrame frame_Recover;
    public RecoverPasswordScreen(){
        frame_Recover = new JFrame();
        frame_Recover.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_Recover.setLocationRelativeTo(null);
        frame_Recover.setSize(600, 400);
        
        frame_Recover.setLayout(null);
        frame_Recover.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch(command){
            default:
                break;
        }
    }
}
