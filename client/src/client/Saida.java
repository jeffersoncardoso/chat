package client;

import Eventos.MensagemArquivo;
import Eventos.MensagemPrivada;
import Eventos.MensagemPublica;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;


public class Saida {
    private static JEditorPane textArea;
    
    public static void setSaida(JEditorPane textArea) {
        Saida.textArea = textArea;
    }
    
    public static void escrever(String texto) {
        HTMLDocument doc = (HTMLDocument)textArea.getDocument();
        try {
            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), String.format("%s", texto) + "<br><hr>");
        } catch (IOException ex) {
            
        } catch (BadLocationException ex) {
            
        }
    }
    
    public static void escrever(String formato, Object... args) {
        Saida.escrever(String.format(formato, args));
    }
    
    public static void escrever(MensagemPrivada mensagem, boolean recebido) {
        String origem = (recebido) ? "&larr; &nbsp; " + mensagem.getOrigem() : "Eu &rarr; " + mensagem.getDestino();
        String saida = String.format("<b>%s</b><br> &nbsp; %s", origem, mensagem.getTexto());
        Saida.escrever(saida);
    }
    
    public static void escrever(MensagemPublica mensagem, boolean recebido) {
        String origem = (recebido) ? "&larr; &nbsp; " + mensagem.getOrigem() : "Eu &rarr; TODOS";
        String saida = String.format("<b>%s</b><br> &nbsp; %s", origem , mensagem.getTexto());
        Saida.escrever(saida);
    }
    
    public static void escrever(MensagemArquivo mensagem, boolean recebido) {
        String origem = (recebido) ? "&larr; &nbsp; " + mensagem.getOrigem() : "Eu &rarr; " + mensagem.getDestino();
        String saida = String.format("<b>%s</b><br> &nbsp; %s <br>", origem, mensagem.getTexto());
        saida = saida.concat(String.format("<b>&nbsp;<a href='arquivo'>%s.%s</a></b>", mensagem.getNomeArquivo(), mensagem.getExtensao()));
        Saida.escrever(saida);
    }
    
    public static void escrever(MensagemArquivo mensagem, boolean recebido, String path) {
        String origem = (recebido) ? "&larr; &nbsp; " + mensagem.getOrigem() : "Eu &rarr; " + mensagem.getDestino();
        String saida = String.format("<b>%s</b><br> &nbsp; %s <br>", origem, mensagem.getTexto());
        saida = saida.concat(String.format("<b>&nbsp;<a href='%s'>%s.%s</a></b>", path, mensagem.getNomeArquivo(), mensagem.getExtensao()));
        Saida.escrever(saida);
    }
    
    
}
