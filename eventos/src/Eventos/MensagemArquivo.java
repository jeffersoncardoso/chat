package Eventos;

public class MensagemArquivo implements Mensagem{

    private MensagemPrivada mensagem;
    private final String nome;
    private final String extensao;
    private byte[] conteudo;

    public MensagemArquivo(MensagemPrivada mensagem, byte[] conteudo, String nome, String extensao) {
        this.mensagem   = mensagem;
        this.conteudo = conteudo;
        this.nome = nome;
        this.extensao = extensao;
    }

    public String getNomeArquivo() {
        return nome;
    }

    public String getExtensao() {
        return extensao;
    }

    public byte[] getConteudo() {
        return conteudo;
    }
    
    @Override
    public String getOrigem() {
        return mensagem.getOrigem();
    }
    
    public String getDestino() {
        return mensagem.getDestino();
    }
            
    @Override
    public Enum getTipo() {
        return EventoTipo.MENSAGEM_ARQUIVO;
    }

    @Override
    public String getDescricao() {
        return this.mensagem.getDescricao() + " - " + nome + "." + extensao + " Recebido";
    }
    
}
