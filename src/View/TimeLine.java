/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerComment;
import Controller.ControllerLike;
import Controller.ControllerPost;
import Controller.ControllerUser;
import Controller.Interface;
import Model.Admin;
import Model.Comment;
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
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author HansNotFound
 */
public class TimeLine extends JFrame implements Interface {

    JFrame frame_TimeLine, frameLiker = new JFrame(), frame_ListUser = new JFrame();
    JButton button_Next, button_Prev, button_Upload, button_DeletePost, button_DeleteUser,
            button_LogOut, button_SeeComment, button_Like, button_Profile, button_Delete, button_KumulatifLike,
            submit, delete, button_delete;
    JLabel label_NicknameUser, label_NicknamePoster, label_Caption, panel_Gambar, label_KumulatifLike,
            tempat_gambar;
    String test = "Hans Patrick Eko Prasetyo Hans Patrick Eko Prasetyo Hans Patrick Eko Prasetyo Hans Patrick";
    String Nicknamepost = "", strCaption = "", imagePath, nicknameUser, isiComment;
    Admin admin;
    int counter, counter_comment, idPost, jumlahLike, totalLike, cekLike = 0;
    JFrame frame_Comment = new JFrame("Comment");
    JTextField comments, TF_NicknameTerpilih;
    User containUser, users;
    Comment comment = new Comment();
    JList<String> list, listUser;
    JScrollPane scrollBar, sc, sc_deleteUser;
    JList listDeleteUser;
    JPanel panel;

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

            label_KumulatifLike = new JLabel("" + jumlahLike);
            label_KumulatifLike.setBounds(75, 555, 50, 30);

            frame_TimeLine.add(label_KumulatifLike);
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

    void FrameComment(Person person, int idPost, int counter_post) {
        Action action = new Action();
        ListEvent le = new ListEvent();
        if (person instanceof User) {
            frame_Comment.setLocationRelativeTo(null);
            frame_Comment.setSize(380, 350);
            users = (User) person;
            containUser = users;
            nicknameUser = users.getNickname();
            counter_comment = counter_post;
            ArrayList<Comment> listComment = ControllerComment.getListCommentByIDPost(idPost);
            DefaultListModel<String> l1 = new DefaultListModel<>();
            Post user_yang_ngepost = ControllerPost.getPost(idPost);

            comments = new JTextField();
            comments.setBounds(30, 50, 300, 30);

            submit = new JButton("PostComment");
            submit.setBounds(180, 80, 150, 30);
            submit.addActionListener(action);

            delete = new JButton("Delete");
            delete.setEnabled(false);
            delete.setBounds(30, 80, 100, 30);
            delete.addActionListener(action);

            if (!user_yang_ngepost.getUsername_user().equals(users.getUsername())) {
                delete.setVisible(false);
            }

            for (int i = 0; i < listComment.size(); i++) {
                l1.addElement(listComment.get(i).getNicknameComment() + "   " + listComment.get(i).getIsiComment());
            }

            list = new JList<>(l1);
            list.setBounds(30, 130, 300, 160);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.addListSelectionListener(le);

            scrollBar = new JScrollPane(list);
            frame_Comment.add(scrollBar);

            frame_Comment.add(delete);
            frame_Comment.add(comments);
            frame_Comment.add(submit);
            frame_Comment.add(list);

            frame_Comment.setLayout(null);
            frame_Comment.setVisible(true);
        } else if (person instanceof Admin) {
            frame_Comment.setLocationRelativeTo(null);
            frame_Comment.setSize(380, 350);
            admin = (Admin) person;
            counter_comment = counter_post;
            ArrayList<Comment> listComment = ControllerComment.getListCommentByIDPost(idPost);
            DefaultListModel<String> l1 = new DefaultListModel<>();

            for (int i = 0; i < listComment.size(); i++) {
                l1.addElement(listComment.get(i).getNicknameComment() + "   " + listComment.get(i).getIsiComment());
            }
            delete = new JButton("Delete");
            delete.setBounds(30, 10, 100, 30);
            delete.setActionCommand("DeleteAdmin");
            delete.addActionListener(action);
            delete.setEnabled(false);

            list = new JList<>(l1);
            list.setBounds(30, 50, 300, 230);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.addListSelectionListener(le);

            scrollBar = new JScrollPane(list);
            frame_Comment.add(scrollBar);
            frame_Comment.add(delete);
            frame_Comment.add(list);

            frame_Comment.setLayout(null);
            frame_Comment.setVisible(true);
        }

    }

    void FrameDeleteUser() {
        frame_ListUser.setTitle("List User");
        frame_ListUser.setLocationRelativeTo(null);
        frame_ListUser.setSize(400, 250);
        Action action = new Action();
        ListEvent_2 le = new ListEvent_2();
        ArrayList<User> listUsers = ControllerUser.getAllUsers();
        DefaultListModel<String> lm = new DefaultListModel<>();
        for (int i = 0; i < listUsers.size(); i++) {
            lm.addElement(listUsers.get(i).getNickname());
        }
        listDeleteUser = new JList<>(lm);
        listDeleteUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listDeleteUser.addListSelectionListener(le);

        sc_deleteUser = new JScrollPane(listDeleteUser);
        frame_ListUser.add(sc_deleteUser);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        TF_NicknameTerpilih = new JTextField();
        TF_NicknameTerpilih.setEnabled(false);
        TF_NicknameTerpilih.setPreferredSize(new Dimension(getWidth() - 150, 30));
        panel.add(TF_NicknameTerpilih);

        button_delete = new JButton("Delete");
        button_delete.setPreferredSize(new Dimension(getWidth() - (getWidth() - 100), 30));
        button_delete.setActionCommand("DeleteUser");
        button_delete.addActionListener(action);
        panel.add(button_delete);

        frame_ListUser.add(panel, "South");
        setVisible(true);
    }

    void FrameLiker(ArrayList<Liker> listLiker) {
        frameLiker.setTitle("List Liker");
        frameLiker.setLocationRelativeTo(null);
        frameLiker.setSize(250, 150);
        DefaultListModel<String> lm = new DefaultListModel<>();
        for (int i = 0; i < listLiker.size(); i++) {
            lm.addElement(listLiker.get(i).getNicknameLike());
        }
        listUser = new JList<>(lm);
        listUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sc = new JScrollPane(listUser);
        frameLiker.add(sc);
        frameLiker.setVisible(true);
    }

    class ListEvent implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (list.getSelectedIndex() > -1) {
                delete.setEnabled(true);
            }
        }
    }

    class ListEvent_2 implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (listDeleteUser.getSelectedIndex() > -1) {
                TF_NicknameTerpilih.setText(listDeleteUser.getSelectedValue().toString());
            }
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
                        frame_Comment.setVisible(false);
                        frameLiker.setVisible(false);
                        frame_ListUser.setVisible(false);
                    }
                    break;
                case "Prev Post":
                    frame_TimeLine.setVisible(false);
                    new TimeLine(UserManager.getInstance().getUser(), counter - 1);
                    frame_Comment.setVisible(false);
                    frameLiker.setVisible(false);
                    break;
                case "Next Post":
                    frame_TimeLine.setVisible(false);
                    new TimeLine(UserManager.getInstance().getUser(), counter + 1);
                    frame_Comment.setVisible(false);
                    frameLiker.setVisible(false);
                    break;
                case "UploadPost":
                    frame_TimeLine.setVisible(false);
                    new CreatePost(UserManager.getInstance().getUser(), counter);
                    frame_Comment.setVisible(false);
                    frameLiker.setVisible(false);
                    break;
                case "Comment":
                    frame_Comment.setVisible(false);
                    FrameComment(UserManager.getInstance().getUser(), idPost, (counter - 1));
                    break;
                case "CommentAdmin":
                    frame_Comment.setVisible(false);
                    FrameComment(admin, idPost, (counter - 1));
                    break;
                case "ViewProfile":
                    frame_TimeLine.setVisible(false);
                    new ViewProfile(UserManager.getInstance().getUser(), counter);
                    frame_Comment.setVisible(false);
                    frameLiker.setVisible(false);
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
                                frame_Comment.setVisible(false);
                                frameLiker.setVisible(false);
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
                    frame_Comment.setVisible(false);
                    frameLiker.setVisible(false);
                    frame_ListUser.setVisible(false);
                    break;
                case "Prev_admin":
                    frame_TimeLine.setVisible(false);
                    new TimeLine(admin, counter - 1);
                    frame_Comment.setVisible(false);
                    frameLiker.setVisible(false);
                    frame_ListUser.setVisible(false);
                    break;
                case "Like":
                    Liker liker = new Liker();
                    liker.setNicknameLike(UserManager.getInstance().getUser().getNickname());
                    liker.setIdPost(idPost);
                    if (ControllerLike.insertNewLiker(liker)) {
                        if (ControllerLike.updateLike(idPost)) {
                            frame_TimeLine.setVisible(false);
                            new TimeLine(UserManager.getInstance().getUser(), counter);
                            frame_Comment.setVisible(false);
                            frameLiker.setVisible(false);
                        }
                    }
                    break;
                case "List User":
                    FrameDeleteUser();
                    break;
                case "seeLiker":
                    ArrayList<Liker> listLiker = ControllerLike.getLiker(idPost);
                    FrameLiker(listLiker);
                    break;
                case "PostComment":
                    isiComment = comments.getText();
                    if (isiComment.equals("")) {
                        JOptionPane.showMessageDialog(null, "Comment masih kosong!!", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    } else {
                        comment.setIsiComment(isiComment);
                        comment.setNicknameComment(nicknameUser);
                        comment.setIdComment(Comment.countComment());
                        boolean insertComment = ControllerComment.insertNewComments(comment, idPost);
                        if (insertComment) {
                            JOptionPane.showMessageDialog(null, "Comment berhasil di post");
                            frame_Comment.setVisible(false);
                            frame_TimeLine.setVisible(false);
                            new TimeLine(UserManager.getInstance().getUser(), counter);
                            frameLiker.setVisible(false);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Comment gagal di post");
                            break;
                        }
                    }
                case "Delete":
                    String isiComment = (String) list.getSelectedValue();
                    if (ControllerComment.deleteComment(isiComment)) {
                        JOptionPane.showMessageDialog(null, "Comment berhasil di hilangkan!");
                        frame_Comment.setVisible(false);
                        frame_TimeLine.setVisible(false);
                        new TimeLine(UserManager.getInstance().getUser(), counter);
                        frameLiker.setVisible(false);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Comment gagal di hilangkan");
                        frame_Comment.setVisible(false);
                        frame_TimeLine.setVisible(false);
                        new TimeLine(UserManager.getInstance().getUser(), counter);
                        frameLiker.setVisible(false);
                        break;
                    }
                case "DeleteAdmin":
                    String isianComment = (String) list.getSelectedValue();
                    if (ControllerComment.deleteComment(isianComment)) {
                        JOptionPane.showMessageDialog(null, "Comment berhasil di hilangkan!");
                        frame_TimeLine.setVisible(false);
                        new TimeLine(admin, counter);
                        frame_Comment.setVisible(false);
                        frameLiker.setVisible(false);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Comment gagal di hilangkan");
                        frame_Comment.setVisible(false);
                        frame_TimeLine.setVisible(false);
                        new TimeLine(admin, counter);
                        frameLiker.setVisible(false);
                        break;
                    }
                case "DeleteUser":
                    if (!TF_NicknameTerpilih.getText().equals("")) {
                        int stat = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mendelete user ini?");
                        if (stat == JOptionPane.YES_OPTION) {
                            String pass = JOptionPane.showInputDialog(null, "Masukkan password Admin = ");
                            if (pass.equals(Interface.passAdmin)) {
                                ArrayList<User> listAllUser = ControllerUser.getAllUsers();
                                for (int i = 0; i < listAllUser.size(); i++) {
                                    if (TF_NicknameTerpilih.getText().equals(listAllUser.get(i).getNickname())) {
                                        ArrayList<Post> listPostUser = ControllerPost.getListPostByUser(listAllUser.get(i).getUsername());
                                        for (int j = 0; j < listPostUser.size(); j++) {
                                            ControllerPost.deletePost(listPostUser.get(j).getIdPost());
                                        }
                                        ArrayList<Teman> listTeman = ControllerUser.getTeman(listAllUser.get(i).getUsername());
                                        for (int k = 0; k < listTeman.size(); k++) {
                                            ControllerUser.updateJumlahTeman(listTeman.get(k).getNickname_teman());
                                        }
                                        ControllerUser.deletePerson(listAllUser.get(i).getUsername(), TF_NicknameTerpilih.getText());
                                        JOptionPane.showMessageDialog(null, "Delete User tersebut berhasil!!");
                                        setVisible(false);
                                        frame_TimeLine.setVisible(false);
                                        new TimeLine(admin, counter);
                                        frame_Comment.setVisible(false);
                                        frameLiker.setVisible(false);
                                        break;
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Password yang anda masukkan salah!! Anda dikeluarkan dari Program", "Error", JOptionPane.ERROR_MESSAGE);
                                System.exit(0);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
