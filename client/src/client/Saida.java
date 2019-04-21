package client;

import Eventos.MensagemArquivo;
import Eventos.MensagemPrivada;
import Eventos.MensagemPublica;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


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
    
    private static String escapar(String texto) {
        try {
            
            HTMLDocument doc = new HTMLDocument();
            new HTMLEditorKit().read( new StringReader( "<html><body>" + texto ), doc, 0 );
            return doc.getText( 1, doc.getLength() );
            
        } catch( IOException | BadLocationException ex ) {
            return texto;
        }
    }
    
    public static void escrever(String formato, Object... args) {
        Saida.escrever(String.format(formato, args));
    }
    
    public static void escrever(MensagemPrivada mensagem, boolean recebido) {
        String origem = (recebido) ? "&larr; &nbsp; " + mensagem.getOrigem() : "Eu &rarr; " + mensagem.getDestino();
        String saida = String.format("<b>%s</b><br> &nbsp; %s", origem, Saida.escapar(mensagem.getTexto()));
        Saida.escrever(saida);
    }
    
    public static void escrever(MensagemPublica mensagem, boolean recebido) {
        String origem = (recebido) ? "&larr; &nbsp; " + mensagem.getOrigem() : "Eu &rarr; TODOS";
        String saida = String.format("<b>%s</b><br> &nbsp; %s", origem , Saida.escapar(mensagem.getTexto()));
        Saida.escrever(saida);
    }
    
    public static void escrever(MensagemArquivo mensagem, boolean recebido, String path) {
        String origem = (recebido) ? "&larr; &nbsp; " + mensagem.getOrigem() : "Eu &rarr; " + mensagem.getDestino();
        String saida = String.format("<b>%s</b><br> &nbsp; %s <br>", origem, Saida.escapar(mensagem.getTexto()));
        saida = saida.concat(String.format("<b>&nbsp;<a href='%s'>%s.%s</a></b>", path, mensagem.getNomeArquivo(), mensagem.getExtensao()));
        Saida.escrever(saida);
    }
    
    
}
