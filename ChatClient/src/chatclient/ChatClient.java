package chatclient;

import javax.swing.JOptionPane;

/**
 *
 * @author hasalp
 */
public class ChatClient {

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter Username");
        User.setUsername(username);
        String ip = JOptionPane.showInputDialog("Enter IP address that is you want to connect without port");
        ChatController controller = new ChatController(ip,1313);
        
        Thread t = controller.new chatThread();
                
        ChatGui gui = new ChatGui(controller);
        gui.setVisible(true);
        
        t.start();
        
    }
    
}
