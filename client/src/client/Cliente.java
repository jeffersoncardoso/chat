package client;

import Erros.EventoException;
import Erros.EventoInvalidoException;
import Eventos.Evento;
import Eventos.EventoFabrica;
import Eventos.EventoTipo;
import Eventos.ListaUsuarios;
import Eventos.Login;
import Eventos.Mensagem;
import Eventos.MensagemArquivo;
import Eventos.MensagemPrivada;
import Eventos.MensagemPublica;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JList;

public class Cliente extends Thread{
    
    private String login;
    private final Socket socket;
    private final ObjectOutputStream enviar;
    private final ObjectInputStream receber;
    private String[] usuarios;
    private JList<String> listaChat;
    private boolean estaAtivo = false;

    public Cliente(String endereco) throws IOException
    {
        socket = new Socket(endereco, 8084);
        enviar = new ObjectOutputStream(socket.getOutputStream());
        receber = new ObjectInputStream(socket.getInputStream());
    }
    
    public boolean fazerLogin(String usuario) throws IOException, EventoException, EventoInvalidoException
    {
        login = usuario;
        enviar.writeObject(new Login(usuario));
        
        Evento evento;
        try {
            evento = (Evento)receber.readObject();
            if(evento.getTipo() == EventoTipo.FALHA_LOGIN)
                throw new EventoException(evento.getDescricao());
            
            return true;
            
        } catch (ClassNotFoundException ex) {
            throw new EventoInvalidoException("Erro na comunicação com o servidor");
        }
    }
    
    public String getUsuario()
    {
        return this.login;
    }
    
    public String[] getUsuariosConectados()
    {
        return this.usuarios;
    }
    
    public void enviarMensagem(String texto, String destino, File arquivo) throws IOException 
    {
        byte[] conteudo = Files.readAllBytes(arquivo.toPath());
        
        int index = arquivo.getName().lastIndexOf('.');
        String nomeArquivo = arquivo.getName().substring(0, index);
        String extensao = arquivo.getName().substring(index + 1);
        
        MensagemArquivo mensagem = new MensagemArquivo(new MensagemPrivada(texto, login, destino), conteudo, nomeArquivo, extensao);
        
        enviar.writeObject(mensagem);
        enviar.flush();
        
        Saida.escrever(mensagem, false, arquivo.toPath().toString());
    }
    
    public void enviarMensagem(String texto, String destino) throws IOException 
    {
        Mensagem mensagem;
        
        if(destino == null)
            mensagem = new MensagemPublica(texto, login);
        else
            mensagem = new MensagemPrivada(texto, login, destino);
        
        enviar.writeObject(mensagem);
        enviar.flush();
        
        if(destino == null)
            Saida.escrever((MensagemPublica)mensagem, false);
        else
            Saida.escrever((MensagemPrivada)mensagem, false);
    }
    
    public void receberMensagem(MensagemPrivada mensagem) {
        Saida.escrever(mensagem, true);
    }    
    public void receberMensagem(MensagemPublica mensagem) {
        Saida.escrever(mensagem, true);
    }   
    public void receberMensagem(MensagemArquivo mensagem) throws EventoException{
        try {
            File temp = File.createTempFile(mensagem.getNomeArquivo(), "." + mensagem.getExtensao());
            temp.deleteOnExit();

            FileOutputStream stream = new FileOutputStream(temp);
            stream.write(mensagem.getConteudo());
            stream.close();
            
            Saida.escrever(mensagem, true, temp.getAbsolutePath());
        } catch (IOException ex) {
            throw new EventoException("Erro ao receber a mensagem: " + ex.getMessage());
        }
    }
    
    public void iniciar() {
        estaAtivo = true;
        start();
    }
    
    @Override
    public void run() {
        while (estaAtivo) {
            try {
                Evento evento = EventoFabrica.escolher(this.receber.readObject());
                
                if(evento.getTipo() == EventoTipo.LISTA_USUARIOS) {
                    
                    ListaUsuarios lista;
                    lista = (ListaUsuarios)evento;
                    
                    this.usuarios = lista.getUsuarios();
                    this.atualizarSelectUsuarios();
                }
                
                if(evento.getTipo() == EventoTipo.MENSAGEM_PRIVADA) {
                    receberMensagem((MensagemPrivada)evento);
                }
                
                if(evento.getTipo() == EventoTipo.MENSAGEM_PUBLICA) {
                    receberMensagem((MensagemPublica)evento);
                }
                
                if(evento.getTipo() == EventoTipo.MENSAGEM_ARQUIVO) {
                    receberMensagem((MensagemArquivo)evento);
                }
                    
            } catch (ClassNotFoundException | EventoException ex ) {
                System.out.println(ex.getMessage());
            } catch (IOException ex){
                System.out.println(ex.getMessage());
                throw new RuntimeException("O servidor caiu");
            }
        }
    }
    
    public void setListaChat(JList<String> listaChat) {
        this.listaChat = listaChat;
    }

    void atualizarSelectUsuarios() {
        String selecionado = listaChat.getSelectedValue();
        
        ArrayList<String> outrosUsuarios = new ArrayList<>();
        
        for (String usuario : this.getUsuariosConectados()) {
            if(!usuario.equals(this.login))
                outrosUsuarios.add(usuario);
        }
        
        listaChat.setListData(outrosUsuarios.toArray(new String[0]));
        listaChat.setSelectedValue(selecionado, true);
    }
}
