  package Eventos;

import java.io.Serializable;

public interface Evento extends Serializable{
    public Enum getTipo();
    public String getDescricao();
}
