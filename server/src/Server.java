
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 0101765
 */
public class Server {    
    ServerSocket ss;
    Socket s;
    public static List<ConnectedClient> clients = new ArrayList<>();    
    
    public void waitClients(){
        ConnectedClient client;
        try {
            ss = new ServerSocket(8084);
            
            while(true){
                client = new ConnectedClient(ss.accept());
                client.setup();
                clients.add(client);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server chatServer = new Server();
        chatServer.waitClients();
    }
    
}
