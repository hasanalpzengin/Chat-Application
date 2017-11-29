
package chatserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatController {
    
    private ServerSocket server;
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private String msg = "";
    Boolean threadWorking = true;
    
    public ChatController(int port){
        try {
            server = new ServerSocket(port);
            client = server.accept();
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            //out.writeUTF(User.getUsername()+" Connected");
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
                out.writeUTF(User.getUsername()+" Connected");
                while(threadWorking){
                    msg = in.readUTF();
                    System.out.println(User.getUsers().toString());
                    if(msg.length()>0){
                        //if someone connected to server
                        if(msg.contains("Connected") && !msg.contains(":")){
                            String username = msg.split(" ")[0];
                            User.addUser(username);
                            writeUsers();
                            ChatGui.chatArea.append(msg+"\n");
                            ChatGui.userList.setListData(User.getUsers().toArray(new String[0]));
                        //if someone disconnected from server
                        }else if(msg.contains("Disconnected") && !msg.contains(":")){
                            String username = msg.split(" ")[0];
                            User.removeUser(username);
                            writeUsers();
                            ChatGui.userList.setListData(User.getUsers().toArray(new String[0]));
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
    
    public void writeUsers(){
        StringBuilder sb = new StringBuilder();
        for(String user: User.getUsers()){
            try {
                sb.append(user+"@");
                out.writeUTF(sb.toString());
            } catch (IOException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
