package Eventos;

public class EventoFabrica {
    public static Evento escolher(Object objeto)
    {
        Evento evento = (Evento)objeto;
        
        if(evento.getTipo().equals(EventoTipo.MENSAGEM_PRIVADA))
            return (MensagemPrivada)objeto;
        
        if(evento.getTipo().equals(EventoTipo.MENSAGEM_PUBLICA))
            return (MensagemPublica)objeto;
        
        if(evento.getTipo().equals(EventoTipo.LOGIN))
            return (Login)objeto;
        
        if(evento.getTipo().equals(EventoTipo.LOGOUT))
            return (Logout)objeto;
        
        return evento;
    }
}
