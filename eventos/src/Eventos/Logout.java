package Eventos;

public class Logout implements Evento{
    
    @Override
    public Enum getTipo() {
        return EventoTipo.LOGIN;
    }

    @Override
    public String getDescricao() {
        return "Logout";
    }
}
