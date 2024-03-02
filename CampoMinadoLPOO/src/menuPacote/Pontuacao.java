package menuPacote;

public class Pontuacao implements Comparable<Pontuacao> {
    private String nomeJogador;
    private int pontos;

    public Pontuacao(String nomeJogador, int pontos) {
        this.nomeJogador = nomeJogador;
        this.pontos = pontos;
    }


    @Override
    public int compareTo(Pontuacao outra) {
        return Integer.compare(outra.pontos, this.pontos); 
    }
    
    @Override
    public String toString() {
        return nomeJogador + ": " + pontos;
    }
}
