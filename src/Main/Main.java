/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.Controller;
import Model.*;
import View.MainMenuScreen;
import java.util.ArrayList;
/**
 *
 * @author HansNotFound
 */
public class Main {
    public static void main(String[] args) {
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        ArrayList<User> listUser = Controller.getAllUsers();
        for(int i = 0; i < listUser.size(); i++){
            System.out.println(listUser.get(i).toString());
        }
        Admin admin = new Admin();
        System.out.println(admin.toString());
    }
}
