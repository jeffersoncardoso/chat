package Cliente;

import Servidor.Conexao;
import Servidor.Servidor;
import Eventos.Evento;
import Eventos.EventoFabrica;
import Eventos.EventoTipo;
import Eventos.ListaUsuarios;
import Eventos.Mensagem;
import Eventos.MensagemArquivo;
import Eventos.MensagemPrivada;
import Eventos.MensagemPublica;
import Util.Saida;
import java.io.IOException;

public class Usuario extends Thread{
    private final String login;
    private final Conexao conexao;
    private final Servidor servidor;

    public Usuario(String login, Conexao conexao, Servidor servidor) {
        this.login = login;
        this.conexao = conexao;
        this.servidor = servidor;
        
        Saida.escrever("%s se conectou", login);
    }

    public String getLogin() {
        return login;
    }
    
    @Override
    public void run() {
        while (this.servidor.estaLigado() && this.servidor.verificarUsuario(this)) {
            try {
                Evento evento = EventoFabrica.escolher(this.conexao.receber());
                
                if(evento.getTipo() == EventoTipo.MENSAGEM_PRIVADA)
                    this.servidor.enviarMensagem((MensagemPrivada)evento);
                
                if(evento.getTipo() == EventoTipo.MENSAGEM_PUBLICA)
                    this.servidor.enviarMensagem((MensagemPublica)evento);
                
                if(evento.getTipo() == EventoTipo.MENSAGEM_ARQUIVO)
                    this.servidor.enviarMensagem((MensagemArquivo)evento);
                
                if(evento.getTipo() == EventoTipo.LOGOUT)
                    this.sair();
                
            } catch (IOException ex) {
                this.sair();
                this.servidor.removerUsuario(this);
            }
        }
    }
    
    public void sair() {
        Saida.escrever(this.login + " saiu");
        this.conexao.encerrar();
    }

    public void encaminhar(Mensagem mensagem) {
        conexao.enviar(mensagem);
    }
    
    public void atualizarListaUsuarios(String[] usuarios) {
        ListaUsuarios lista = new ListaUsuarios(usuarios);
        conexao.enviar(lista);
    }
    
}
