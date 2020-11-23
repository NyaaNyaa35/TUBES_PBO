/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerComment;
import Controller.ControllerPost;
import Controller.ControllerUser;
import Controller.Interface;
import Model.Admin;
import Model.Comment;
import Model.Liker;
import Model.Post;
import Model.Teman;
import Model.User;
import Model.UserManager;
import static View.TimeLine.loadImage;
import static View.TimeLine.resize;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author NealsonW
 */
public class ViewPost extends JFrame {

    JFrame frame;
    JButton button_Next, button_Prev, button_Upload, button_DeletePost, button_DeleteUser,
            button_LogOut, button_SeeComment, button_Like, button_Profile, button_mainmenu, submit,delete;
    JLabel label_NicknameUser, label_NicknamePoster, label_KumulatifLike, label_Caption, panel_Gambar,
            tempat_gambar;
    String Nicknamepost = "";
    String strCaption = "";
    String isiComment;
    int counter,idPost,likeCount;
    User user_global;
    JList list;
    JTextField comments;
    Comment comment = new Comment();
    JFrame frame_Comment = new JFrame("Comment");
    public ViewPost(User user, int counter_post) {
        UserManager.getInstance().setUser(user);
        ArrayList<Post> listPost = ControllerPost.getListPostByUser(UserManager.getInstance().getUser().getUsername());
        Action action = new Action();
        counter = counter_post;
        user_global = user;

        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label_NicknameUser = new JLabel(UserManager.getInstance().getUser().getNickname());
        label_NicknameUser.setBounds(75, 20, 200, 30);
        label_NicknameUser.setFont(new Font("Serif", 0, 15));

        button_Profile = new JButton();
        button_Profile.setBounds(20, 10, 50, 50);
        BufferedImage loadImgProfile = loadImage(UserManager.getInstance().getUser().getProfilePict());
        ImageIcon imageIconProfile = new ImageIcon(resize(loadImgProfile, 50, 50));
        button_Profile.setIcon(imageIconProfile);
        button_Profile.setFocusPainted(false);
        button_Profile.setBorderPainted(false);
        button_Profile.setContentAreaFilled(false);
        button_Profile.setOpaque(false);
        button_Profile.setActionCommand("ViewProfile");
        button_Profile.addActionListener(action);

        button_Prev = new JButton("Prev Post");
        button_Prev.setBounds(20, 610, 100, 30);
        button_Prev.addActionListener(action);
        if ((counter_post - 1) <= 0) {
            button_Prev.setEnabled(false);
        }

        button_Next = new JButton("Next Post");
        button_Next.setBounds(460, 610, 100, 30);
        int pembatas = listPost.size();
        button_Next.addActionListener(action);
        if ((counter_post + 1) > (pembatas)) {
            button_Next.setEnabled(false);
        }

        button_SeeComment = new JButton("Comment");
        button_SeeComment.setBounds(460, 545, 100, 30);
        button_SeeComment.addActionListener(action);

        button_DeletePost = new JButton("DeletePost");
        button_DeletePost.setBounds(240, 610, 100, 30);
        button_DeletePost.addActionListener(action);
        
        button_mainmenu = new JButton("MainMenu");
        button_mainmenu.setBounds(460, 20, 100, 30);
        button_mainmenu.addActionListener(action);

        panel_Gambar = new JLabel();
        panel_Gambar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel_Gambar.setBounds(20, 75, 550, 430);

        tempat_gambar = new JLabel();
        tempat_gambar.setBounds(25, 80, 540, 420);

        tempat_gambar.setBounds(panel_Gambar.getLocation().x + 5, panel_Gambar.getLocation().y + 5, 545, 415);
        tempat_gambar.setAlignmentY(CENTER_ALIGNMENT);

        int jumlahLike = 0;
        if (counter_post != 0) {
            likeCount = listPost.get(counter_post - 1).getJumlahLike();
            Nicknamepost = listPost.get(counter_post - 1).getPostNickname();
            strCaption = listPost.get(counter_post - 1).getCaption();
            idPost = listPost.get(counter_post - 1).getIdPost();
            BufferedImage loadImg = loadImage(listPost.get(counter_post - 1).getImagepath());
            ImageIcon imageIcon = new ImageIcon(resize(loadImg, tempat_gambar.getWidth() - 5, tempat_gambar.getHeight() - 5));
            tempat_gambar.setIcon(imageIcon);
        }

        label_NicknamePoster = new JLabel(Nicknamepost);
        label_NicknamePoster.setBounds(30, 540, 200, 20);

        label_KumulatifLike = new JLabel("" + jumlahLike);
        label_KumulatifLike.setBounds(30, 555, 50, 30);

        label_Caption = new JLabel(strCaption);
        label_Caption.setBounds(25, 505, 550, 30);

        frame.add(tempat_gambar);
        frame.add(button_Profile);
        frame.add(label_Caption);
        frame.add(label_KumulatifLike);
        frame.add(label_NicknamePoster);
        frame.add(panel_Gambar);
        frame.add(button_SeeComment);
        frame.add(button_Next);
        frame.add(button_Prev);
        frame.add(button_mainmenu);
        frame.add(button_DeletePost);
        frame.add(label_NicknameUser);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

    class Action implements ActionListener,ListSelectionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            ArrayList<Post> listPost = ControllerPost.getAllPost();
            switch (command) {
                case "Prev Post":
                    frame.setVisible(false);
                    new ViewPost(UserManager.getInstance().getUser(), counter - 1);
                    break;
                case "Next Post":
                    frame.setVisible(false);
                    new ViewPost(UserManager.getInstance().getUser(), counter + 1);
                    break;
                case"Comment":
                    ArrayList<Comment> listComment = ControllerComment.getListCommentByIDPost(idPost);
                    DefaultListModel<String> l1 = new DefaultListModel<>();
                    Post user_yang_ngepost = ControllerPost.getPost(idPost);

                    frame_Comment.setLocationRelativeTo(null);
                    frame_Comment.setSize(380, 350);

                    comments = new JTextField();
                    comments.setBounds(30, 50, 300, 30);

                    submit = new JButton("PostComment");
                    submit.setBounds(180, 80, 150, 30);
                    submit.addActionListener(this);

                    delete = new JButton("Delete");
                    delete.setEnabled(false);
                    delete.setBounds(30, 80, 100, 30);
                    delete.addActionListener(this);

                    if (!user_yang_ngepost.getUsername_user().equals(UserManager.getInstance().getUser().getUsername())) {
                        delete.setVisible(false);
                    }

                    for (int i = 0; i < listComment.size(); i++) {
                        l1.addElement(listComment.get(i).getNicknameComment() + "   " + listComment.get(i).getIsiComment());
                    }

                    list = new JList<>(l1);
                    list.setBounds(30, 130, 300, 160);
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
                    list.addListSelectionListener(this);
        
                    frame_Comment.add(delete);
                    frame_Comment.add(comments);
                    frame_Comment.add(submit);
                    frame_Comment.add(list);

                    frame_Comment.setLayout(null);
                    frame_Comment.setVisible(true);
                    break;
                case "DeletePost":
                    int status = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mendelete post ini?");
                    if(status == JOptionPane.YES_OPTION){
                        String pass = JOptionPane.showInputDialog(null, "Masukkan password = ");
                        if(pass.equals(ControllerUser.getPassword(user_global.getUsername()))){
                            if(ControllerPost.deletePost(listPost.get(counter-1).getIdPost())){
                                JOptionPane.showMessageDialog(null, "Delete Berhasil!!");
                                frame.setVisible(false);
                                new TimeLine(user_global,ControllerPost.getListPostByUser(user_global.getUsername()).size());
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Delete Gagal!!","Error",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        } else {
                            frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "Password Salah!! Anda akan dialihkan ke Login Screen","Error",JOptionPane.ERROR_MESSAGE);
                            new LoginScreen();
                            break;
                        }
                    }
                    break;
                case "MainMenu":
                    frame.setVisible(false);
                    new TimeLine(user_global,1);
                    break;
                case "PostComment":
                    isiComment = comments.getText();
                    if (isiComment.equals("")) {
                        JOptionPane.showMessageDialog(null, "Comment masih kosong!!", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    } else {
                        comment.setIsiComment(isiComment);
                        comment.setNicknameComment(UserManager.getInstance().getUser().getNickname());
                        comment.setIdComment(Comment.countComment());                    
                        boolean insertComment = ControllerComment.insertNewComments(comment, idPost);
                        if (insertComment) {
                            JOptionPane.showMessageDialog(null, "Comment berhasil di post");
                            frame_Comment.setVisible(false);
                            frame.setVisible(false);
                            new ViewPost(UserManager.getInstance().getUser(),counter);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Comment gagal di post");
                            break;
                        }
                    }
                case "Delete":
                    String isiComment = (String) list.getSelectedValue();
                    boolean deleteComment = ControllerComment.deleteComment(isiComment);
                    if (deleteComment) {
                        JOptionPane.showMessageDialog(null, "Comment berhasil di hilangkan!");
                        frame_Comment.setVisible(false);
                        frame.setVisible(false);
                        new ViewPost(UserManager.getInstance().getUser(),counter);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Comment gagal di hilangkan");
                        break;
                    }                      
                default:
                    break;
            }
        }

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (list.getSelectedIndex() > -1) {
                delete.setEnabled(true);
            }  
        }
    }
}
