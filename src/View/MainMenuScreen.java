/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JOptionPane;
import Controller.Interface;
/**
 *
 * @author HansNotFound
 */
public class MainMenuScreen {
    public MainMenuScreen() {
        showMainMenu();     
    }
    
    private void showMainMenu() {
        JOptionPane.showMessageDialog(null,"Selamat Datang di-"+Interface.namaApp);
        int menuChoise = Integer.parseInt(JOptionPane.showInputDialog("Silahkan Pilih Menu\n1. Login\n2.Register"));
        
    }
}
