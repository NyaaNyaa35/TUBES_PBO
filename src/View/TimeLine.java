/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerLike;
import Controller.ControllerPost;
import Controller.ControllerUser;
import Controller.Interface;
import Model.Admin;
import Model.Liker;
import Model.Person;
import Model.User;
import Model.UserManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import Model.Post;
import Model.Teman;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
/**
 *
 * @author HansNotFound
 */
public class TimeLine extends JFrame implements Interface {

    JFrame frame_TimeLine;
    JButton button_Next, button_Prev, button_Upload, button_DeletePost, button_DeleteUser,
            button_LogOut, button_SeeComment, button_Like, button_Profile, button_Delete, button_KumulatifLike;
    JLabel label_NicknameUser, label_NicknamePoster, label_Caption, panel_Gambar, label_KumulatifLike,
            tempat_gambar;
    String test = "Hans Patrick Eko Prasetyo Hans Patrick Eko Prasetyo Hans Patrick Eko Prasetyo Hans Patrick";
    String Nicknamepost = "";
    String strCaption = "";
    String imagePath;
    Admin admin;
    int counter, idPost, jumlahLike, totalLike, cekLike = 0;

    public TimeLine(Person person, int counter_post) {
        if (person instanceof User) {

            UserManager.getInstance().setUser((User) person);
            Action action = new Action();
            ArrayList<Post> listPost = ControllerPost.getListPostByUser(UserManager.getInstance().getUser().getUsername());
            ArrayList<Teman> listTemanUser = ControllerUser.getTeman(UserManager.getInstance().getUser().getUsername());
            ArrayList<Post> listAllPost = ControllerPost.getAllPost();
            for (int i = 0; i < listTemanUser.size(); i++) {
                for (int j = 0; j < listAllPost.size(); j++) {
                    if (listAllPost.get(j).getPostNickname().equals(listTemanUser.get(i).getNickname_teman())) {
                        listPost.add(listAllPost.get(j));
                    }
                }
            }
            counter = counter_post;

            frame_TimeLine = new JFrame(Interface.namaApp);
            frame_TimeLine.setSize(600, 700);
            frame_TimeLine.setLocationRelativeTo(null);
            frame_TimeLine.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            label_NicknameUser = new JLabel(UserManager.getInstance().getUser().getNickname());
            label_NicknameUser.setBounds(75, 20, frame_TimeLine.getWidth() - (frame_TimeLine.getWidth() - 200), frame_TimeLine.getHeight() - (frame_TimeLine.getHeight() - 30));
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
            button_SeeComment.setEnabled(false);

            button_Upload = new JButton("UploadPost");
            button_Upload.setBounds(240, 610, 100, 30);
            button_Upload.addActionListener(action);

            button_LogOut = new JButton("LogOut");
            button_LogOut.setBounds(460, 20, 100, 30);
            button_LogOut.addActionListener(action);

            panel_Gambar = new JLabel();
            panel_Gambar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel_Gambar.setBounds(20, 75, 550, 430);

            tempat_gambar = new JLabel();
            tempat_gambar.setBounds(25, 80, 540, 420);

            tempat_gambar.setBounds(panel_Gambar.getLocation().x + 5, panel_Gambar.getLocation().y + 5, 545, 415);
            tempat_gambar.setAlignmentY(CENTER_ALIGNMENT);

            if (counter_post != 0) {
                totalLike = listPost.get(counter_post - 1).getJumlahLike();
                Nicknamepost = listPost.get(counter_post - 1).getPostNickname();
                strCaption = listPost.get(counter_post - 1).getCaption();
                idPost = listPost.get(counter_post - 1).getIdPost();
                cekLike = ControllerLike.checkLike(UserManager.getInstance().getUser().getNickname(), idPost);
                BufferedImage loadImg = loadImage(listPost.get(counter_post - 1).getImagepath());
                ImageIcon imageIcon = new ImageIcon(resize(loadImg, tempat_gambar.getWidth() - 5, tempat_gambar.getHeight() - 5));
                tempat_gambar.setIcon(imageIcon);
                button_SeeComment.setEnabled(true);
            }

            Icon iconLove = new ImageIcon("src/Image/loveimage.png");

            button_KumulatifLike = new JButton(iconLove);
            button_KumulatifLike.setBounds(65, 555, 30, 30);
            button_KumulatifLike.setFocusPainted(false);
            button_KumulatifLike.setBorderPainted(false);
            button_KumulatifLike.setContentAreaFilled(false);
            button_KumulatifLike.setOpaque(false);
            button_KumulatifLike.setActionCommand("seeLiker");
            button_KumulatifLike.addActionListener(action);

            label_KumulatifLike = new JLabel("" + totalLike);
            label_KumulatifLike.setBounds(100, 555, 50, 30);

            Icon iconLike = new ImageIcon("src/Image/Like_Image.png");
            button_Like = new JButton(iconLike);
            button_Like.setActionCommand("Like");
            button_Like.addActionListener(action);
            button_Like.setBounds(20, 540, 40, 40);
            if (cekLike > 0 || counter_post == 0) {
                button_Like.setEnabled(false);
            }

            label_NicknamePoster = new JLabel(Nicknamepost);
            label_NicknamePoster.setBounds(65, 540, 200, 20);

            label_Caption = new JLabel(strCaption);
            label_Caption.setBounds(25, 505, 550, 30);

            frame_TimeLine.add(label_KumulatifLike);
            frame_TimeLine.add(tempat_gambar);
            frame_TimeLine.add(button_Profile);
            frame_TimeLine.add(label_Caption);
            frame_TimeLine.add(button_KumulatifLike);
            frame_TimeLine.add(label_NicknamePoster);
            frame_TimeLine.add(button_Like);
            frame_TimeLine.add(panel_Gambar);
            frame_TimeLine.add(button_SeeComment);
            frame_TimeLine.add(button_Next);
            frame_TimeLine.add(button_Prev);
            frame_TimeLine.add(button_LogOut);
            frame_TimeLine.add(button_Upload);
            frame_TimeLine.add(label_NicknameUser);
            frame_TimeLine.setLayout(null);
            frame_TimeLine.setVisible(true);
        } else if (person instanceof Admin) {
            counter = counter_post;
            admin = (Admin) person;
            Action action = new Action();
            ArrayList<Post> allpost = ControllerPost.getAllPost();
            frame_TimeLine = new JFrame(Interface.namaApp);
            frame_TimeLine.setSize(600, 700);
            frame_TimeLine.setLocationRelativeTo(null);
            frame_TimeLine.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            label_NicknameUser = new JLabel("ADMIN");
            label_NicknameUser.setBounds(75, 20, 100, 30);
            label_NicknameUser.setFont(new Font("Serif", 0, 15));

            button_Prev = new JButton("Prev Post");
            button_Prev.setBounds(20, 610, 100, 30);
            button_Prev.addActionListener(action);
            if ((counter_post - 1) <= 0) {
                button_Prev.setEnabled(false);
            }
            button_Prev.setActionCommand("Prev_admin");

            button_Next = new JButton("Next Post");
            button_Next.setBounds(460, 610, 100, 30);
            int pembatas = allpost.size();
            button_Next.addActionListener(action);
            if ((counter_post + 1) > (pembatas)) {
                button_Next.setEnabled(false);
            }
            button_Next.setActionCommand("Next_admin");

            button_SeeComment = new JButton("Comment");
            button_SeeComment.setBounds(460, 545, 100, 30);
            button_SeeComment.addActionListener(action);
            button_SeeComment.setActionCommand("CommentAdmin");
            button_SeeComment.setEnabled(false);

            button_DeleteUser = new JButton("List User");
            button_DeleteUser.setBounds(245, 20, 100, 30);
            button_DeleteUser.addActionListener(action);

            button_LogOut = new JButton("LogOut");
            button_LogOut.setBounds(460, 20, 100, 30);
            button_LogOut.addActionListener(action);

            button_Delete = new JButton("DeletePost");
            button_Delete.setBounds(240, 610, 100, 30);
            button_Delete.addActionListener(action);
            button_Delete.setEnabled(false);

            panel_Gambar = new JLabel();
            panel_Gambar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel_Gambar.setBounds(20, 75, 550, 430);

            tempat_gambar = new JLabel();
            tempat_gambar.setBounds(25, 80, 540, 420);

            tempat_gambar.setBounds(panel_Gambar.getLocation().x + 5, panel_Gambar.getLocation().y + 5, 545, 415);
            tempat_gambar.setAlignmentY(CENTER_ALIGNMENT);

            if (counter_post != 0) {
                jumlahLike = allpost.get(counter_post - 1).getJumlahLike();
                Nicknamepost = allpost.get(counter_post - 1).getPostNickname();
                strCaption = allpost.get(counter_post - 1).getCaption();
                idPost = allpost.get(counter_post - 1).getIdPost();
                BufferedImage loadImg = loadImage(allpost.get(counter_post - 1).getImagepath());
                ImageIcon imageIcon = new ImageIcon(resize(loadImg, tempat_gambar.getWidth() - 5, tempat_gambar.getHeight() - 5));
                tempat_gambar.setIcon(imageIcon);
                button_Delete.setEnabled(true);
                button_SeeComment.setEnabled(true);
            }

            Icon iconLike = new ImageIcon("src/Image/Like_Image.png");
            button_Like = new JButton(iconLike);

            button_Like.addActionListener(action);
            button_Like.setBounds(20, 540, 40, 40);

            label_NicknamePoster = new JLabel(Nicknamepost);
            label_NicknamePoster.setBounds(30, 540, 200, 20);

            label_Caption = new JLabel(strCaption);
            label_Caption.setBounds(25, 505, 550, 30);

            Icon iconLove = new ImageIcon("src/Image/loveimage.png");
            button_KumulatifLike = new JButton(iconLove);
            button_KumulatifLike.setBounds(30, 555, 30, 30);
            button_KumulatifLike.setFocusPainted(false);
            button_KumulatifLike.setBorderPainted(false);
            button_KumulatifLike.setContentAreaFilled(false);
            button_KumulatifLike.setOpaque(false);
            button_KumulatifLike.setActionCommand("seeLiker");
            button_KumulatifLike.addActionListener(action);

            label_KumulatifLike = new JLabel("" + totalLike);
            label_KumulatifLike.setBounds(65, 555, 50, 30);

            frame_TimeLine.add(button_DeleteUser);
            frame_TimeLine.add(tempat_gambar);
            frame_TimeLine.add(button_Delete);
            frame_TimeLine.add(label_Caption);
            frame_TimeLine.add(button_KumulatifLike);
            frame_TimeLine.add(label_NicknamePoster);
            frame_TimeLine.add(panel_Gambar);
            frame_TimeLine.add(button_SeeComment);
            frame_TimeLine.add(button_Next);
            frame_TimeLine.add(button_Prev);
            frame_TimeLine.add(button_LogOut);
            frame_TimeLine.add(label_NicknameUser);
            frame_TimeLine.setLayout(null);
            frame_TimeLine.setVisible(true);
        }
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            ArrayList<Post> listPost = ControllerPost.getAllPost();
            switch (command) {
                case "LogOut":
                    int tutup = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin LogOut?");
                    if (tutup == JOptionPane.YES_OPTION) {
                        UserManager.getInstance().setUser(null);
                        JOptionPane.showMessageDialog(null, "Anda berhasil LogOut");
                        frame_TimeLine.setVisible(false);
                        LoginScreen loginScreen = new LoginScreen();
                    }
                    break;
                case "Prev Post":
                    frame_TimeLine.setVisible(false);
                    new TimeLine(UserManager.getInstance().getUser(), counter - 1);
                    break;
                case "Next Post":
                    frame_TimeLine.setVisible(false);
                    new TimeLine(UserManager.getInstance().getUser(), counter + 1);
                    break;
                case "UploadPost":
                    frame_TimeLine.setVisible(false);
                    new CreatePost(UserManager.getInstance().getUser(), counter);
                    break;
                case "Comment":
                    new FrameComment(UserManager.getInstance().getUser(), idPost, (counter - 1));
                    break;
                case "CommentAdmin":
                    new FrameComment(admin, idPost, (counter - 1));
                    break;
                case "ViewProfile":
                    frame_TimeLine.setVisible(false);
                    new ViewProfile(UserManager.getInstance().getUser(), counter);
                    break;
                case "DeletePost":
                    frame_TimeLine.setVisible(false);
                    int status = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mendelete post ini?");
                    if (status == JOptionPane.YES_OPTION) {
                        String passAdmin = JOptionPane.showInputDialog(null, "Masukkan password = ");
                        if (passAdmin.equals(Interface.passAdmin)) {
                            if (ControllerPost.deletePost(listPost.get(counter - 1).getIdPost())) {
                                JOptionPane.showMessageDialog(null, "Delete Berhasil!!");
                                new TimeLine(admin, 0);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Delete Gagal!!", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Password Salah!! Anda akan dialihkan ke Login Screen", "Error", JOptionPane.ERROR_MESSAGE);
                            new LoginScreen();
                            break;
                        }
                    }
                    break;
                case "Next_admin":
                    frame_TimeLine.setVisible(false);
                    new TimeLine(admin, counter + 1);
                    break;
                case "Prev_admin":
                    frame_TimeLine.setVisible(false);
                    new TimeLine(admin, counter - 1);
                    break;
                case "Like":
                    Liker liker = new Liker();
                    liker.setNicknameLike(UserManager.getInstance().getUser().getNickname());
                    liker.setIdPost(idPost);
                    if (ControllerLike.insertNewLiker(liker)) {
                        if (ControllerLike.updateLike(idPost)) {
                            frame_TimeLine.setVisible(false);
                            new TimeLine(UserManager.getInstance().getUser(), counter);
                        }
                    }
                    break;
                case "List User":
                    new FrameDeleteUser();
                    break;
                case "seeLiker":
                    ArrayList<Liker> listLiker = ControllerLike.getLiker(idPost);
                    new FrameLiker(listLiker);
                    break;
                default:
                    break;
            }
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
