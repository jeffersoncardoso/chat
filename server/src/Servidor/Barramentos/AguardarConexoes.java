package Servidor.Barramentos;

import Cliente.Usuario;
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
    
    @Override
    public void run() {
        while(this.servidor.estaLigado()){
            try {
                Conexao conexao = new Conexao(this.servidor.esperarCliente());
                Login login = (Login)conexao.receber();
                Usuario novoUsuario = new Usuario(login.getUsuario(), conexao, this.servidor);
                this.servidor.adicionar(novoUsuario);
                novoUsuario.start();
            } catch (IOException ex) {
                Saida.escrever(ex.getMessage());
            }
        }
    }
}
