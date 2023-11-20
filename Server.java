import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    ServerSocket serverSocket;
    ArrayList<ServerConnectionThread> usersConnection = new ArrayList<ServerConnectionThread>();
   
    
    public Server(){
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Begining Chat Server");
            System.out.println("*********************LOGS***********************");
            while(true){
                Socket clientSocket = serverSocket.accept();
                ServerConnectionThread conn = new ServerConnectionThread(clientSocket,this);
                conn.start();
                usersConnection.add(conn);
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        new Server();
        
    }
    
}
