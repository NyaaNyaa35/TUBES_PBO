/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerUser;
import Model.Liker;
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
public class FrameLiker extends JFrame{
    JList<String> listUser;
    JScrollPane sc;
    public FrameLiker(ArrayList<Liker> listLiker){
        setTitle("List Liker");
        setLocationRelativeTo(null);
        setSize(250,150);
        seeLiker(listLiker);
        setVisible(true);
    }
    
    private void seeLiker(ArrayList<Liker> listLiker){
        DefaultListModel<String> lm = new DefaultListModel<>();
        for(int i = 0; i < listLiker.size(); i++){
            lm.addElement(listLiker.get(i).getNicknameLike());
        }
        listUser = new JList<>(lm);
        listUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        sc = new JScrollPane(listUser);
        getContentPane().add(sc);
    }
}
