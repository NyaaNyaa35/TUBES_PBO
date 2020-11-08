/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Comment;
import Model.Liker;
import Model.Post;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author NealsonW
 */
public class ViewPost extends JFrame implements ActionListener{
    
    Post post = new Post();
    ArrayList<Comment> listComment = new ArrayList<>();
    ArrayList<Liker> listLiker = new ArrayList<>();
    JFrame frame;
    JLabel postImage,userPost,caption,comment,likes;
    JButton postComment,likePost;
    JTextField fComment;
    int jumlahLike;
    String suka;
    
    public ViewPost() {
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        userPost = new JLabel(post.getPostNickname());
        
        caption = new JLabel(post.getCaption());
        
        postImage = new JLabel();
        postImage.setIcon(new ImageIcon(post.getImagepath()));
        
        suka = Integer.toString(post.getJumlahLike());
        likes = new JLabel(suka);
        
        likePost = new JButton("<3");
        likePost.addActionListener(this);

        postComment = new JButton();
        postComment.addActionListener(this);
        postComment.setVisible(false);
        
        fComment = new JTextField("Make Your Comment!");
        fComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                postComment.setVisible(true);
            }
        });

        
        for(int i=0; i<listComment.size();i++){
            comment = new JLabel();
        }
        
        frame.add(userPost);
        frame.add(caption);
        frame.add(postImage);
        
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String input = ae.getActionCommand();
        
        switch(input){
            case "<3":
                Liker userLike = new Liker();
                userLike.setNicknameLike("");
                listLiker.add(userLike);
                post.setListLiker(listLiker);
                jumlahLike = post.getJumlahLike()+1;
                post.setJumlahLike(jumlahLike);
                break;
            case "Make Your Comment!": 
                Comment comment = new Comment();
                comment.setIsiComment(fComment.getText());
                listComment.add(comment);
                post.setListComment(listComment);
                break;
        }
    }
}
