package Servidor; 

import Servidor.Barramentos.AguardarConexoes;
import Servidor.Barramentos.UsuariosConectados;
import Eventos.MensagemArquivo;
import Eventos.MensagemPrivada;
import Eventos.MensagemPublica;
import Util.Saida;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {    
    ServerSocket socketServidor;
    Socket s;
    public Map<String, Usuario> usuarios = new HashMap<>();
    public Dashboard dashboard;

    public Servidor(int porta) {
        try {
            this.socketServidor = new ServerSocket(porta);
            Saida.escrever("Servidor iniciado na porta %s", porta);
        } catch (IOException ex) {
            throw new RuntimeException("Ocorreu um erro: " + ex.getMessage());
        }
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }
    
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
    
    public Socket esperarCliente() throws IOException
    {
        return socketServidor.accept();
    }
    
    public void adicionar(Usuario usuario) {
        this.usuarios.put(usuario.getLogin(), usuario);
    }
    
    public boolean estaLigado() {
        return true;
    }
    
    public void iniciarBarramentos() {
        try {
            new AguardarConexoes(this).start();
            new UsuariosConectados(this).start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public boolean verificarUsuario(Usuario usuario) {
        return this.usuarios.containsKey(usuario.getLogin());
    }
    
    public boolean possuiUsuario(String login) {
        return this.usuarios.containsKey(login);
    }

    public void removerUsuario(Usuario usuario) {
        this.usuarios.remove(usuario.getLogin());
    }
    
    public void enviarMensagem(MensagemPublica mensagem) {
        for (String login : this.usuarios.keySet()) {
            if(!mensagem.getOrigem().equals(login))
                this.usuarios.get(login).encaminhar(mensagem);
        }
        
        Saida.escrever("Mensagem enviada de %s para TODOS", mensagem.getOrigem());
    }
    
    public void enviarMensagem(MensagemPrivada mensagem) {
        if(this.usuarios.containsKey(mensagem.getDestino())) {
            Usuario usuario = this.usuarios.get(mensagem.getDestino());
            usuario.encaminhar(mensagem);
            
            Saida.escrever("Mensagem enviada de %s para %s", mensagem.getOrigem(), mensagem.getDestino());
        }
    }
    
    public void enviarMensagem(MensagemArquivo mensagem) {
        if(this.usuarios.containsKey(mensagem.getDestino())) {
            Usuario usuario = this.usuarios.get(mensagem.getDestino());
            usuario.encaminhar(mensagem);
            
            Saida.escrever("Arquivo enviado de %s para %s", mensagem.getOrigem(), mensagem.getDestino());
        }
    }
}
