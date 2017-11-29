
package chatclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatController {
    
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private String msg = "";
    Boolean threadWorking = true;
    
    public ChatController(String ip, int port){
        try {
            client = new Socket(ip,port);
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendText(String msg) throws IOException{
        out.writeUTF(User.getUsername()+": "+msg);
    }
    
    public class chatThread extends Thread{
        @Override
        public void run(){
            try {
                String username;
                //tell connected to server
                out.writeUTF(User.getUsername()+" Connected");
                while(threadWorking){
                    msg = in.readUTF();
                    if(msg.length()>0){
                        //if its a user array which is sended by server
                        if(msg.contains("@") && !msg.contains(":")){
                            String[] users = msg.split("@");
                            ChatGui.userList.setListData(users);
                        //else its a message
                        }else{
                            ChatGui.chatArea.append(msg+"\n");
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void close() throws IOException{
        out.writeUTF(User.getUsername()+" Disconnected");
        threadWorking=false;
        client.close();
        in.close();
        out.close();
    }
    
}
