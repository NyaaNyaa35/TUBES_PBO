/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Comment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author HansNotFound
 */
public class FrameComment extends JFrame implements ActionListener{
    JFrame frame_Comment;
    public FrameComment(ArrayList<Comment> listComm){
        frame_Comment = new JFrame("Comment");
        frame_Comment.setSize(300, 500);
        frame_Comment.setLocationRelativeTo(null);
        
        int width = 20;
        JLabel label = new JLabel();
        String strLabel = "";
        for(int i = 0; i < /*listComm.size()*/ 3 ; i++){
            
        }
        
        frame_Comment.setLayout(null);
        frame_Comment.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
