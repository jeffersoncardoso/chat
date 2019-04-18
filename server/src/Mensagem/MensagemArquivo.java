package Mensagem;

import java.io.File;

public class MensagemArquivo {
    private final Origem origem;
    private final Destino destino;
    private final File arquivo;

    public MensagemArquivo(Origem origem, Destino destino, File arquivo) {
        this.origem = origem;
        this.destino = destino;
        this.arquivo = arquivo;
    }
}
