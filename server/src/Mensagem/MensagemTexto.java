package Mensagem;

public class MensagemTexto implements Mensagem{
    private final Origem origem;
    private final Destino destino;
    private final String texto;

    public MensagemTexto(Origem origem, Destino destino, String texto) {
        this.origem = origem;
        this.destino = destino;
        this.texto = texto;
    }
    
    @Override
    public Origem origem() {
        return origem;
    }
    
    @Override
    public Destino destino() {
        return destino;
    }
    
    @Override
    public Object conteudo() {
        return texto;
    }

    @Override
    public void enviar() {
        
    }
}
