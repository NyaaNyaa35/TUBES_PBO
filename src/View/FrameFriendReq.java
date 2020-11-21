/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerUser;
import Model.FriendRequest;
import Model.User;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author HansNotFound
 */
public class FrameFriendReq extends JFrame implements ListSelectionListener{
    private JList<String> list_request;
    private JButton button_acc;
    private JTextField TF_NicknameTerpilih;
    private JScrollPane scrollpane;
    private JPanel panel;
    private User user_global;
    public FrameFriendReq(User user){
        setTitle("Friend Request");
        setLocationRelativeTo(null);
        setSize(400, 255);
        showList(user);
        setVisible(true);
    }
    private void showList(User user){
        user_global = user;
        Action action = new Action();
        ArrayList<FriendRequest> listReq = ControllerUser.getRequest(user.getUsername());
        DefaultListModel<String> ll = new DefaultListModel<>();
        for(int i = 0; i < listReq.size(); i++){
            ll.addElement(listReq.get(i).getNickname_request());
        }
        list_request = new JList<>(ll);
        list_request.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_request.addListSelectionListener(this);
        
        scrollpane = new JScrollPane(list_request);
        getContentPane().add(scrollpane);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        TF_NicknameTerpilih = new JTextField();
        TF_NicknameTerpilih.setEnabled(false);
        TF_NicknameTerpilih.setPreferredSize(new Dimension(getWidth()-150, 30));
        panel.add(TF_NicknameTerpilih);
        
        button_acc = new JButton("Accept");
        button_acc.setPreferredSize(new Dimension(getWidth()-(getWidth()-100),30));
        button_acc.addActionListener(action);
        panel.add(button_acc);
        
        getContentPane().add(panel, "South");
    }
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(list_request.getSelectedIndex() > -1){
            TF_NicknameTerpilih.setText(list_request.getSelectedValue().toString());
        }
    }
    
    class Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch(command){
                case "Accept":
                    String nickname = TF_NicknameTerpilih.getText();
                    if(nickname.equals("")){
                        JOptionPane.showMessageDialog(null, "Silahkan pilih nickname!","Error",JOptionPane.ERROR_MESSAGE);
                        break;
                    } else {
                        int status = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menambahkan");
                        if(status==JOptionPane.YES_OPTION){
                            if(ControllerUser.addFriend(user_global,nickname)){
                                JOptionPane.showMessageDialog(null, "Sekarang " +nickname+ " sudah menjadi teman anda!");
                                ControllerUser.deleteReq(user_global.getUsername(),nickname);
                                getContentPane().setVisible(false);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Add Teman GAGAL!!","Error",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        }
                        break;
                    }
                default:
                    break;
            }
        }
        
    }
    
}
