/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerUser;
import Model.Teman;
import Model.User;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author HansNotFound
 */
public class SeeFriend extends JFrame{
    JList<String> listUser;
    JScrollPane sc;
    public SeeFriend(User user){
        setTitle("Friend List");
        setLocationRelativeTo(null);
        setSize(250,150);
        friendList(user);
        setVisible(true);
    }
    
    private void friendList(User user){
        ArrayList<Teman> listTeman = ControllerUser.getTeman(user.getUsername());
        DefaultListModel<String> lm = new DefaultListModel<>();
        for(int i = 0; i < listTeman.size(); i++){
            lm.addElement(listTeman.get(i).getNickname_teman());
        }
        listUser = new JList<>(lm);
        listUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        sc = new JScrollPane(listUser);
        getContentPane().add(sc);
    }
}
