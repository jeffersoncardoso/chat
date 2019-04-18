package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Conexao extends Thread{
    private final Socket socket;
    private final ObjectInputStream receber;
    private final ObjectOutputStream enviar;

    public Conexao(Socket socket) throws IOException {
        this.socket = socket;
        receber = new ObjectInputStream(socket.getInputStream());
        enviar = new ObjectOutputStream(socket.getOutputStream());
    }
    
    public void enviar(Object object) {
        try {
            this.enviar.writeObject(object);
            this.enviar.flush();
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro");
        }
    }
    
    public Object receber() {
        try {
            Object object = (Object)this.receber.readObject();
            return object;
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Erro fatal");
        }
    }
}
