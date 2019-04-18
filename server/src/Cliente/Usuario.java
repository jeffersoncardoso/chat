package Cliente;

import Servidor.Conexao;
import Servidor.Servidor;

public class Usuario extends Thread{
    private final String login;
    private final Conexao conexao;
    private final Servidor servidor;

    public Usuario(String login, Conexao conexao, Servidor servidor) {
        this.login = login;
        this.conexao = conexao;
        this.servidor = servidor;
        
        System.out.printf("%s se conectou \n", login);
    }
    
    @Override
    public void run() {
        while (this.servidor.estaLigado()) {            
            System.out.printf("Mensagem recebida de %s : %s \n", this.login, this.conexao.receber());
        }
    }
    
}
