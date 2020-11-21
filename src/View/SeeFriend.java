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

/**
 *
 * @author HansNotFound
 */
public class SeeFriend extends JFrame{
    JList<String> listUser;
    public SeeFriend(User user){
        setTitle("Friend List");
        setLocationRelativeTo(null);
        setSize(400,250);
        friendList(user);
        setVisible(true);
    }
    
    private void friendList(User user){
        listUser = new JList<>();
        ArrayList<Teman> listTeman = ControllerUser.getTeman(user.getUsername());
        DefaultListModel<String> lm = new DefaultListModel<>();
        for(int i = 0; i < listTeman.size(); i++){
            
        }
    }
}
