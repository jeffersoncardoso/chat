package Servidor.Barramentos;

import Servidor.Usuario;
import Servidor.Servidor;

public class UsuariosConectados extends Thread{
    
    private Servidor servidor;

    public UsuariosConectados(Servidor servidor) {
        this.servidor = servidor;
    }
    
    @Override
    public void run() {
        while(this.servidor.estaLigado()){
            String[] lista = new String[servidor.getUsuarios().size()];
            this.servidor.getDashboard().atualizar(servidor.getUsuarios().size(), lista);
            
            if(servidor.getUsuarios().isEmpty()){
                aguardar();
                continue;
            }
            
            servidor.getUsuarios().keySet().toArray(lista);
            
            for (Usuario usuario : servidor.getUsuarios().values()) {
                usuario.atualizarListaUsuarios(lista);
            }
            
            aguardar();
        }
    }
    
    private void aguardar() {
        try { Thread.sleep(1000); } catch (InterruptedException ex) { }
    }
}

