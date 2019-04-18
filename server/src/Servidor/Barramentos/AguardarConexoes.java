package Servidor.Barramentos;

import Cliente.Usuario;
import Servidor.Servidor;
import Servidor.Conexao;
import java.io.IOException;

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
                Usuario novoUsuario = new Usuario((String)conexao.receber(), conexao, this.servidor);
                this.servidor.adicionar(novoUsuario);
                novoUsuario.start();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
