package Eventos;

public interface Mensagem extends Evento{
    
    public String getOrigem();
    public String getTexto();
}
