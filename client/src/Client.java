
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 0101765
 */
public class Client implements Runnable{
    
    Socket s;
    BufferedReader in;
    BufferedReader keyboard;
    PrintWriter out;
    Thread t;
    public void connect(String host, Integer port){
        try {
            s = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());            
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            t = new Thread(this);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String keyboard(){
        String line = null;
        try {
            line = keyboard.readLine();
        } catch (Exception e) {
        }
        return line;
    }
    
    public void sendMsg(String msg){
        out.println(msg);
        out.flush();
    }
    
    public String waitMsg(){
        String line = null;
        try {
            line = in.readLine();
        } catch (Exception e) {
        }
        return line;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client chatClient = new Client();
        chatClient.connect("localhost", 8084);
        
        while(true){
            String msg = chatClient.keyboard();
            chatClient.sendMsg(msg);
        }
    }

    @Override
    public void run() {
        String msg;
        while(true){
            msg = waitMsg();
            System.out.println(msg);
        }
    }
    
}
