package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
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
            Logger.getLogger(Saida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void escrever(String formato, Object... args) {
        Saida.escrever(String.format(formato, args));
    }
    
    
}
