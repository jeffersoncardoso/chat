package Cliente;

import java.io.IOException;
import java.net.Socket;

public class Disco {
    private String nome;
    private Conexao conexao;

    public Disco(String endereco, Integer porta) throws IOException {
        Socket socket = new Socket(endereco, porta);
        conexao = new Conexao(socket);
    }
    
    public void enviar(Arquivo arquivo) {
        conexao.enviar(arquivo);
    }
    
    public void fazerDownload(String chave) {
        
    }
}
