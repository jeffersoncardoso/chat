package Mensagem;

public interface Mensagem {
    public Origem origem();
    public Destino destino();
    public Object conteudo();
    public void enviar();
}
