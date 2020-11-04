/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Post;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author NealsonW
 */
public class CreatePost extends JFrame implements ActionListener{
    
    JFrame frame;
    JButton chooseFile,upload;
    JTextField caption;
    JLabel lCaption,previewPhotos;
    JFileChooser choosePhotos;
    File photos;
    String pathPhotos;
    int idPost;
    
    public CreatePost() {
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        chooseFile = new JButton("Choose File...");
        chooseFile.addActionListener(this);
        
        lCaption = new JLabel("Caption");
        caption = new JTextField();
        
        upload = new JButton("Upload");
        upload.addActionListener(this);
        
        frame.add(chooseFile);
        frame.add(lCaption);
        frame.add(caption);
        frame.add(upload);
        
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Post post = new Post();
        String input = ae.getActionCommand();        
        switch(input){
            case "Upload":
                idPost = post.getIdPost()+1;
                post.setCaption(caption.getText());
                post.setIdPost(idPost);
                previewPhotos = new JLabel(new ImageIcon(pathPhotos));
                
                frame = new JFrame();
                frame.setSize(500, 500);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
                
                frame.add(previewPhotos); 
                
                frame.setVisible(true);
                frame.setLayout(null);                
                break;
            case "Choose File...":
                choosePhotos = new JFileChooser();
                choosePhotos.showOpenDialog(null);
                photos = choosePhotos.getSelectedFile();
                pathPhotos = photos.getAbsolutePath();
                post.setImagepath(pathPhotos);
                break;
        }
    }
    
}
