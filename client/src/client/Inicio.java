/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author 0190690
 */
public class Inicio extends javax.swing.JFrame {

    private Servidor servidor;
    private Socket socket;
    private ObjectOutputStream enviar;
    private ObjectInputStream receber;
    
    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        this.setTitle("Bem vindo ao chat");
        
        try {
            socket = new Socket("localhost", 8084);
            enviar = new ObjectOutputStream(socket.getOutputStream());
            receber = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
        
//        this.servidor = new Servidor();
        
        //this.atualizarListaChat();
        
    }
    
    
    private void atualizarListaChat() {
        DefaultListModel model = new DefaultListModel();
        ArrayList<Cliente> clientes = this.servidor.buscarClientesConectados();
        for (Cliente cliente : clientes) {
            model.addElement(cliente.toString());
        }
        this.listaChat.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtEnviarMensagem = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensagens = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaChat = new javax.swing.JList<>();
        btnTodos = new javax.swing.JButton();
        btnEnviarMensagem = new javax.swing.JButton();
        btnEnviarArquivo = new javax.swing.JButton();
        labelUsuario = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("janelaPrincipal"); // NOI18N
        setResizable(false);

        txtEnviarMensagem.setColumns(20);
        txtEnviarMensagem.setRows(5);
        jScrollPane1.setViewportView(txtEnviarMensagem);

        jScrollPane2.setViewportView(txtMensagens);

        listaChat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        listaChat.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Ana", "João", "Pedro", "Maria", "José", "Luiz" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaChatMouseClicked(evt);
            }
        });
        listaChat.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaChatValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listaChat);

        btnTodos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTodos.setText("Todos");
        btnTodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTodosMouseClicked(evt);
            }
        });
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        btnEnviarMensagem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnEnviarMensagem.setText("Enviar");
        btnEnviarMensagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnviarMensagemMouseClicked(evt);
            }
        });
        btnEnviarMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMensagemActionPerformed(evt);
            }
        });

        btnEnviarArquivo.setText("Enviar Arquivo");
        btnEnviarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarArquivoActionPerformed(evt);
            }
        });

        labelUsuario.setAlignment(java.awt.Label.CENTER);
        labelUsuario.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelUsuario.setText("Todos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(btnTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(224, 224, 224))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEnviarMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEnviarArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnviarArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviarMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        try {
            enviar.writeObject("jefferson");
            //this.txtMensagens.setText(this.txtEnviarMensagem.getText() + '\n' + receber.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnEnviarMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMensagemActionPerformed
        
        try {
            enviar.writeObject(this.txtEnviarMensagem.getText());
            enviar.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btnEnviarMensagemActionPerformed

    private void btnEnviarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarArquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarArquivoActionPerformed

    private void listaChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaChatMouseClicked
        this.listaChat.getSelectedValue();
    }//GEN-LAST:event_listaChatMouseClicked

    private void btnTodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTodosMouseClicked
        this.listaChat.clearSelection();
    }//GEN-LAST:event_btnTodosMouseClicked

    private void listaChatValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaChatValueChanged
        if(this.listaChat.isSelectionEmpty())
            this.labelUsuario.setText("Todos");
        else
            this.labelUsuario.setText(this.listaChat.getSelectedValue());
    }//GEN-LAST:event_listaChatValueChanged

    private void btnEnviarMensagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnviarMensagemMouseClicked
        this.servidor.enviarMensagem(this.txtEnviarMensagem.getText(), this.listaChat.getSelectedValue());
        this.txtEnviarMensagem.setText("");
    }//GEN-LAST:event_btnEnviarMensagemMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarArquivo;
    private javax.swing.JButton btnEnviarMensagem;
    private javax.swing.JButton btnTodos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label labelUsuario;
    private javax.swing.JList<String> listaChat;
    private javax.swing.JTextArea txtEnviarMensagem;
    private javax.swing.JTextPane txtMensagens;
    // End of variables declaration//GEN-END:variables
}
