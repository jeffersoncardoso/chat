package Eventos;

public class MensagemPublica implements Mensagem{
    private final String mensagem;
    private final String origem;

    public MensagemPublica(String mensagem, String origem) {
        this.mensagem   = mensagem;
        this.origem     = origem;
    }
    
    @Override
    public String getOrigem() {
        return origem;
    }
    
    @Override
    public Enum getTipo() {
        return EventoTipo.MENSAGEM_PUBLICA;
    }

    @Override
    public String getDescricao() {
        return origem + " > Todos : " + this.mensagem;
    }
    
    @Override
    public String getTexto() {
        return this.mensagem;
    }
}
