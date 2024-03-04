package jogadorPacote;

class a {
    private String nomeJogador;
    private int pontos;

    public void Jogador(String nomeJogador, int pontos) {
        this.nomeJogador = nomeJogador;
        this.pontos = pontos;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public int getPontos() {
        return pontos;
    }
}