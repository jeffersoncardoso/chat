package Util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.swing.JTextArea;


public class Saida {
    private static JTextArea textArea;
    
    public static void setSaida(JTextArea textArea) {
        Saida.textArea = textArea;
    }
    
    public static void escrever(String texto) {
        Saida.textArea.append(String.format("%s \n", texto));
        Saida.textArea.setCaretPosition(Saida.textArea.getDocument().getLength());
    }
    
    public static void escrever(String formato, Object... args) {
        Saida.escrever(String.format(formato, args));
    }
    
    
}
