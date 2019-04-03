
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
 * @author gabriel
 */
public class ConnectedClient implements Runnable{
    Socket s;
    BufferedReader in;
    PrintWriter out;
    Thread t;

    public ConnectedClient(Socket s) {
        this.s = s;
    }

    public void setup(){
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());
            t = new Thread(this);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String readMessage(){
        String msg = null;
        try {
            msg = in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return msg;
    }
    
    public void sendMsg(String msg){
        out.println(msg);
        out.flush();
    }
    
    @Override
    public void run() {
        String msg = null;
        while((msg = readMessage()) != null){
            System.out.println(msg);
            for(ConnectedClient client:Server.clients){
                client.sendMsg(msg);
            }
        }
    }
}
