package Eventos;

public class MensagemPrivada implements Mensagem{

    private final String mensagem;
    private final String origem;
    private final String destino;

    public MensagemPrivada(String mensagem, String origem, String destino) {
        this.mensagem   = mensagem;
        this.origem     = origem;
        this.destino    = destino;
    }
    
    @Override
    public String getOrigem() {
        return origem;
    }
    
    public String getDestino() {
        return destino;
    }
            
    @Override
    public Enum getTipo() {
        return EventoTipo.MENSAGEM_PRIVADA;
    }

    @Override
    public String getDescricao() {
        return origem + " > " + destino + " : " + this.mensagem;
    }
    
    public String getTexto() {
        return this.mensagem;
    }
    
}
