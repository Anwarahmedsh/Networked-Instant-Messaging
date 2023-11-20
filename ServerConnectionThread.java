import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnectionThread extends Thread{
    Socket clientSocket;
    Server server;
    DataInputStream  input;
    DataOutputStream output;
    String username;
    
    public ServerConnectionThread(Socket clientSocket,Server server){
        super("user Thread");
        this.clientSocket = clientSocket;
        this.server = server;
    }
    public void run(){
        try {
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());
            while(true){
                String recievedData = input.readUTF();
                if(recievedData.equals("end")){
                    System.out.println(this.username + " is offline");
                    input.close();
                    output.close();
                    clientSocket.close();
                    server.usersConnection.remove(this);
                    getAllClients();
                }
                String[] recievedDataSpliter = recievedData.split("#");
                if ("reg".equals(recievedDataSpliter[0])) {
                    
                    this.username = recievedDataSpliter[1];
                    Date date = new Date();
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
                    System.out.println("username:" + username + " joined in " + dateStr + ".");
                    getAllClients();
                }
                 else if("public".equals(recievedDataSpliter[0])){
                    String message = recievedDataSpliter[1];
                    sendForAllClients(message);
                }
                else if("private".equals(recievedDataSpliter[0])){
                    String reciverName = recievedDataSpliter[1];
                    String message = recievedDataSpliter[2];
                    sendForAClient(message,reciverName);
                   }
               
                
                
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void sendForAllClients(String message) {
        for(int i=0;i<server.usersConnection.size();i++){
            ServerConnectionThread serverConnTemp =  server.usersConnection.get(i);
            serverConnTemp.sendMsg("pubmsg#"+username+"#"+message);
            System.out.println("public chat From " + this.username + " To " + serverConnTemp.username + " message:" + message);
        }     
    }
    private void sendForAClient(String message, String reciverName) {
        for(int i=0;i<server.usersConnection.size();i++){
            ServerConnectionThread serverConnTemp = server.usersConnection.get(i);
            if(serverConnTemp.username.equals(reciverName)){
                serverConnTemp.sendMsg("primsg#"+username + "#" + message);
                System.out.println("private chat From " + this.username + " To " + serverConnTemp.username + " message:" + message);
                break;
            }
        }
    }

   

    private void sendMsg(String message) {
        try {
            output.writeUTF(message);
            output.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

  

    private void getAllClients() {
        String clients = "clients#";
        for(int i=0;i<server.usersConnection.size();i++){
            
            ServerConnectionThread serverConnTemp =  server.usersConnection.get(i);
            clients += serverConnTemp.username;
            if(i != server.usersConnection.size()-1){
                clients += "#";
            }
        }
        
        for(int i=0;i<server.usersConnection.size();i++){
            
            ServerConnectionThread serverConnTemp =  server.usersConnection.get(i);
           serverConnTemp.sendMsg(clients);
        }
      
        
        
    }
}
