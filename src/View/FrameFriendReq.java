/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JList list_request;
    private JButton button_acc,button_back;
    private JTextField TF_NicknameTerpilih;
    private JScrollPane scrollpane;
    private JPanel panel;
    public FrameFriendReq(){
        setTitle("Friend Request");
        setLocationRelativeTo(null);
        setSize(400, 255);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        showList();
        setVisible(true);
    }
    private void showList(){
        list_request = new JList();
        list_request.setListData(new Object[]{
            "Hans", "Patrick", 
            "Eko", "Prasetyo", 
            "Hans", "Patrick", 
            "Eko", "Prasetyo", 
            "Hans", "Patrick", 
            "Eko", "Prasetyo", 
            "Hans", "Patrick", 
            "Eko", "Prasetyo","Hans Patrick Eko Prasetyo","Hans", "Patrick", 
            "Eko", "Prasetyo", 
            "Hans", "Patrick", 
            "Eko", "Prasetyo", 
            "Hans", "Patrick", 
            "Eko", "Prasetyo", 
            "Hans", "Patrick", 
            "Eko", "Prasetyo","Hans Patrick Eko Prasetyo"
        });
        list_request.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list_request.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list_request.addListSelectionListener(this);
        
        scrollpane = new JScrollPane(list_request);
        getContentPane().add(scrollpane);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        button_back = new JButton("Back");
        button_back.setLocation(getWidth(), getHeight()-235);
        button_back.setPreferredSize(new Dimension(getWidth()-(getWidth()-70),30));
        panel.add(button_back);
        
        TF_NicknameTerpilih = new JTextField();
        TF_NicknameTerpilih.setEnabled(false);
        TF_NicknameTerpilih.setPreferredSize(new Dimension(getWidth()-230, 30));
        panel.add(TF_NicknameTerpilih);
        
        button_acc = new JButton("Accept");
        button_acc.setPreferredSize(new Dimension(getWidth()-(getWidth()-90),30));
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
                    } else {
                        JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menambahkan");
                    }
                    break;
                default:
                    break;
            }
        }
        
    }
    
}
