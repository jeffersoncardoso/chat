package Eventos;

public class FalhaLogin implements Evento{

    public String mensagem;

    public FalhaLogin(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public Enum getTipo() {
        return EventoTipo.FALHA_LOGIN;
    }

    @Override
    public String getDescricao() {
        return this.mensagem;
    }
    
}
