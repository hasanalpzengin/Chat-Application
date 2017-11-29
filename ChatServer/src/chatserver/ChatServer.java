/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author hasalp
 */
public class ChatServer {

    
    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter Username");
        User.setUsername(username);
        User.addUser(username);
        ChatController controller = new ChatController(1313);
        
        Thread t = controller.new chatThread();
        
        ChatGui gui = new ChatGui(controller);
        gui.setVisible(true);
        
        t.start();
        
        JOptionPane.getRootFrame().dispose();
    }
    
}
