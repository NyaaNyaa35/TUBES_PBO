/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author HansNotFound
 */
public class LoginScreen {
    public LoginScreen(){
        Logins();
    }
    private void Logins(){
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestLoginJFrame().setVisible(true);
            }
        });
    new TestLoginJFrame();
    }
}
