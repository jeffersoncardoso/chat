package client;

import java.util.ArrayList;

public class Servidor {
    
    public void conectar()
    {
        
    }
    
    public ArrayList<Cliente> buscarClientesConectados()
    {
        ArrayList<Cliente> clientes = new ArrayList();
        clientes.add(new Cliente("Ana"));
        clientes.add(new Cliente("João"));
        clientes.add(new Cliente("Pedro"));
        clientes.add(new Cliente("Maria"));
        
        return clientes;
    }
    
    public void enviarMensagem(String mensagem, String destino)
    {
        System.out.printf("Enviando mensagem para %s : %s \n", destino, mensagem);
    }
}
