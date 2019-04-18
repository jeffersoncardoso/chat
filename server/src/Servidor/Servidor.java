package Servidor; 

import Servidor.Barramentos.AguardarConexoes;
import Cliente.Usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {    
    ServerSocket socketServidor;
    Socket s;
    public List<Usuario> usuarios = new ArrayList<>();

    public Servidor(int porta) {
        try {
            this.socketServidor = new ServerSocket(porta);
            System.out.printf("Servidor iniciado na porta %s \n", porta);
        } catch (IOException ex) {
            throw new RuntimeException("Ocorreu um error: " + ex.getMessage());
        }
    }
    
    public Socket esperarCliente() throws IOException
    {
        return socketServidor.accept();
    }
    
    public void adicionar(Usuario usuario) {
        this.usuarios.add(usuario);
    }
    
    public boolean estaLigado() {
        return true;
    }
    
    public void iniciarBarramentos(){
        try {
            new AguardarConexoes(this).start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
