/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Post;
import Model.User;
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
import Controller.Controller;
import javax.swing.JOptionPane;

/**
 *
 * @author NealsonW
 */
public class CreatePost extends JFrame implements ActionListener{
    
    JFrame frame;
    JButton chooseFile,upload,confirm_yes,confirm_no;
    JTextField caption;
    JLabel lCaption,previewPhotos;
    JFileChooser choosePhotos;
    File photos;
    String pathPhotos;
    int idPost;
    String postNickname,tanggalPost="";
    User user_cadangan;
    
    public CreatePost(User user) {
        user_cadangan = user;
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        postNickname = user.getNickname();
        
        tanggalPost += Controller.getTanggal() + " " + Controller.getWaktu();
        
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
                post.setPostNickname(postNickname);
                post.setWaktuPost(tanggalPost);
                frame = new JFrame();
                frame.setSize(500, 500);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
                previewPhotos.setBounds(10, 10, 200, 300);
                
                confirm_yes = new JButton("Confirm");
                confirm_yes.setBounds(10, 350, 100, 50);
                confirm_yes.addActionListener(this);
                
                confirm_no = new JButton("Re-Upload");
                confirm_no.setBounds(300, 350, 100, 50);
                confirm_no.addActionListener(this);
                
                frame.add(previewPhotos); 
                frame.add(confirm_yes);
                frame.add(confirm_no);
                
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
            case "Confirm":
                boolean insertPost = Controller.insertNewPost(post);
                if(insertPost){
                    JOptionPane.showMessageDialog(null, "Upload Post Berhasi");
                } else {
                    JOptionPane.showMessageDialog(null, "Upload Post Gagal");
                }
                break;
            case "Re-Upload":
                new CreatePost(user_cadangan);
                break;    
            default:
                break;
        }
    }
    
}
