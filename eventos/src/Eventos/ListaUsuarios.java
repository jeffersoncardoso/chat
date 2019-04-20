package Eventos;

public class ListaUsuarios implements Evento{

    private String[] usuarios;

    public ListaUsuarios(String[] usuarios) {
        this.usuarios = usuarios;
    }

    public String[] getUsuarios() {
        return usuarios;
    }
    
    @Override
    public Enum getTipo() {
        return EventoTipo.LISTA_USUARIOS;
    }

    @Override
    public String getDescricao() {
        return "";
    }
    
}
