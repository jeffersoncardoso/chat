package Eventos;

public class Login implements Evento{
    
    private String usuario;

    public Login(String usuario) {
        this.usuario = usuario.trim();
    }
    
    public String getUsuario() {
        return this.usuario;
    }
    
    @Override
    public Enum getTipo() {
        return EventoTipo.LOGIN;
    }

    @Override
    public String getDescricao() {
        return "Login";
    }
    
}
