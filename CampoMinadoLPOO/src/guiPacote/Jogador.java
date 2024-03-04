package guiPacote;

public class Jogador {
    private String nomesJogador;
    private int pontos;

    public Jogador(String nomeJogador, int pontos) {
        this.nomesJogador = nomeJogador;
        this.pontos = pontos;
    }

    public String getNomeJogador() {
        return nomesJogador;
    }

    public int getPontos() {
        return pontos;
    }
}