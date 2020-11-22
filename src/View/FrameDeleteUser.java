/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerPost;
import Controller.ControllerUser;
import Controller.Interface;
import Model.Post;
import Model.Teman;
import Model.User;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author HansNotFound
 */
public class FrameDeleteUser extends JFrame implements ListSelectionListener {

    JFrame frame;
    JList list;
    JScrollPane sc;
    JPanel panel;
    JTextField TF_NicknameTerpilih;
    JButton button_delete;

    public FrameDeleteUser() {
        setTitle("List User");
        setLocationRelativeTo(null);
        setSize(400, 250);
        UIlistUser();
        setVisible(true);
    }
    
    private void UIlistUser() {
        Action action = new Action();
        ArrayList<User> listUser = ControllerUser.getAllUsers();
        DefaultListModel<String> lm = new DefaultListModel<>();
        for (int i = 0; i < listUser.size(); i++) {
            lm.addElement(listUser.get(i).getNickname());
        }
        list = new JList<>(lm);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        
        sc = new JScrollPane(list);
        getContentPane().add(sc);
        
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        TF_NicknameTerpilih = new JTextField();
        TF_NicknameTerpilih.setEnabled(false);
        TF_NicknameTerpilih.setPreferredSize(new Dimension(getWidth() - 150, 30));
        panel.add(TF_NicknameTerpilih);
        
        button_delete = new JButton("Delete");
        button_delete.setPreferredSize(new Dimension(getWidth() - (getWidth() - 100), 30));
        button_delete.addActionListener(action);
        panel.add(button_delete);
        
        getContentPane().add(panel, "South");
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if (list.getSelectedIndex() > -1) {
            TF_NicknameTerpilih.setText(list.getSelectedValue().toString());
        }
    }
    
    class Action implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch (command) {
                case "Delete":
                    if (!TF_NicknameTerpilih.getText().equals("")) {
                        int status = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mendelete user ini?");
                        if (status == JOptionPane.YES_OPTION) {
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
                                        for(int k = 0; k < listTeman.size();k++){
                                            ControllerUser.updateJumlahTeman(listTeman.get(k).getNickname_teman());
                                        }
                                        ControllerUser.deletePerson(listAllUser.get(i).getUsername(), TF_NicknameTerpilih.getText());
                                        JOptionPane.showMessageDialog(null, "Delete User tersebut berhasil!!");
                                        setVisible(false);
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
}
