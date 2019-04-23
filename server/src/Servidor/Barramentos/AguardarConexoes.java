package Servidor.Barramentos;

import Servidor.Usuario;
import Eventos.FalhaLogin;
import Servidor.Servidor;
import Servidor.Conexao;
import java.io.IOException;
import Eventos.Login;
import Util.Saida;

public class AguardarConexoes extends Thread{
    
    private Servidor servidor;

    public AguardarConexoes(Servidor servidor) {
        this.servidor = servidor;
    }
    
    private boolean fazerLogin(Login login, Conexao conexao) throws IOException {
        if(servidor.possuiUsuario(login.getUsuario())) {
            Saida.escrever("Já existe um usuário com o login %s", login.getUsuario());
            conexao.enviar(new FalhaLogin("Já existe um usuário com esse login"));
            return false;
        }
        
        conexao.enviar(login);
        return true;
    }
    
    @Override
    public void run() {
        while(this.servidor.estaLigado()){
            try {
                Conexao conexao = new Conexao(this.servidor.esperarCliente());
                Login login = (Login)conexao.receber();
                
                if(fazerLogin(login, conexao)){
                    Usuario novoUsuario = new Usuario(login.getUsuario(), conexao, this.servidor);
                    servidor.adicionar(novoUsuario);
                    novoUsuario.start();
                }else{
                    conexao.encerrar();
                }
                
            } catch (IOException ex) {
                ex.getStackTrace();
                Saida.escrever("Erro na conexão: " + ex.getMessage());
            }
        }
    }
}
