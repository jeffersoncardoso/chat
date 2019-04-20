package Servidor;

import javax.swing.JLabel;
import javax.swing.JList;

public class Dashboard {

    private JLabel lblTotalUsuarios;
    private JList listaUsuariosConectados;

    public Dashboard(JLabel lblTotalUsuarios, JList listaUsuariosConectados) {
        this.lblTotalUsuarios = lblTotalUsuarios;
        this.listaUsuariosConectados = listaUsuariosConectados;
    }
    
    public void atualizar(int totalUsuarios, String[] usuarios) {
        if(totalUsuarios > 0)
            this.lblTotalUsuarios.setText(totalUsuarios + " usuários conectados");
        else
            this.lblTotalUsuarios.setText("Nenhum usuário conectado");
        
        this.listaUsuariosConectados.setListData(usuarios);
    }
}
