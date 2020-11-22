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
import Controller.ControllerPost;
import Controller.ControllerUser;
import static View.TimeLine.loadImage;
import static View.TimeLine.resize;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javafx.animation.Timeline;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author NealsonW
 */
public class CreatePost extends JFrame implements ActionListener{
    
    JFrame frame,framePreview;
    JButton chooseFile,upload,confirm_yes,confirm_no;
    JTextField caption;
    JLabel lCaption,previewPhotos,panel_Gambar,tempat_gambar;
    JFileChooser choosePhotos;
    File photos;
    String pathPhotos;
    int idPost;
    String postNickname,tanggalPost="";
    User user_cadangan;
    Post post = new Post();
    int counter_post;
    public CreatePost(User user, int counter) {
        counter_post = counter;
        user_cadangan = user;
        frame = new JFrame("Create Post");
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        postNickname = user.getNickname();
        
        tanggalPost += ControllerUser.getTanggal() + " " + ControllerUser.getWaktu();

        chooseFile = new JButton("Choose File...");
        chooseFile.setBounds(40, 40, 120, 30);
        chooseFile.addActionListener(this);
        
        lCaption = new JLabel("Caption");
        caption = new JTextField();
        lCaption.setBounds(40, 100, 50, 30);
        caption.setBounds(40, 125, 500, 100);
        
        upload = new JButton("Upload");
        upload.setBounds(445,300,80,30);
        upload.addActionListener(this);
        
        
        frame.add(chooseFile);
        frame.add(lCaption);
        frame.add(caption);
        frame.add(upload);
        

        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String input = ae.getActionCommand();   
        switch(input){
            case "Upload":
                post.setCaption(caption.getText());
                post.setPostNickname(postNickname);
                post.setWaktuPost(tanggalPost);
                post.setIdPost(Post.countPost());
                post.setUsername_user(user_cadangan.getUsername());
                
                framePreview = new JFrame();
                framePreview.setSize(500, 500);
                framePreview.setLocationRelativeTo(null);
                
                panel_Gambar = new JLabel();
                panel_Gambar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel_Gambar.setBounds(20, 10, 440, 320);

                tempat_gambar = new JLabel();
                tempat_gambar.setBounds(25, 15, 450, 310);

                tempat_gambar.setBounds(panel_Gambar.getLocation().x + 5, panel_Gambar.getLocation().y + 5, 435, 310);
                tempat_gambar.setAlignmentY(CENTER_ALIGNMENT);
                BufferedImage loadImg = loadImage(pathPhotos);
                ImageIcon imageIcon = new ImageIcon(resize(loadImg, tempat_gambar.getWidth() - 5, tempat_gambar.getHeight() - 5));
                tempat_gambar.setIcon(imageIcon);                
                               
                confirm_yes = new JButton("Confirm");
                confirm_yes.setBounds(20, 350, 100, 50);
                confirm_yes.addActionListener(this);
                
                confirm_no = new JButton("Re-Upload");
                confirm_no.setBounds(362, 350, 100, 50);
                confirm_no.addActionListener(this);
                
                framePreview.add(panel_Gambar); 
                framePreview.add(tempat_gambar);
                framePreview.add(confirm_yes);
                framePreview.add(confirm_no);
                
                framePreview.setLayout(null);                 
                framePreview.setVisible(true);             
                
                break;
            case "Choose File...":
                choosePhotos = new JFileChooser();
                choosePhotos.showOpenDialog(null);
                photos = choosePhotos.getSelectedFile();
                pathPhotos = photos.getAbsolutePath();
                post.setImagepath(pathPhotos);
                break;
            case "Confirm":
                boolean insertPost = ControllerPost.insertNewPost(post);
                if(insertPost){
                    JOptionPane.showMessageDialog(null, "Upload Post Berhasil");
                    counter_post = ControllerPost.getListPostByUser(user_cadangan.getUsername()).size();
                    new TimeLine(user_cadangan, counter_post);
                    frame.setVisible(false);
                    framePreview.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Upload Post Gagal");
                    new CreatePost(user_cadangan, counter_post);
                }
                break;
            case "Re-Upload":
                framePreview.setVisible(false);
                break;        
            default:
                break;
        }
    }
    public static BufferedImage loadImage(String ref) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(ref));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }

    //Method untuk Resize Image
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
    
}
