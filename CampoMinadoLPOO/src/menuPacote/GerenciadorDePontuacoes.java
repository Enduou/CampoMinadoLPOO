package menuPacote;

import java.io.*;
import java.util.*;

public class GerenciadorDePontuacoes {
    private List<Pontuacao> pontuacoes;
    private static final String ARQUIVO_DE_PONTUACOES = "pontuacoes.dat";

    public GerenciadorDePontuacoes() {
        pontuacoes = carregarPontuacoes();
    }

    @SuppressWarnings("unchecked")
    private List<Pontuacao> carregarPontuacoes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DE_PONTUACOES))) {
            return (List<Pontuacao>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void salvarPontuacoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DE_PONTUACOES))) {
            oos.writeObject(pontuacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarPontuacao(Pontuacao pontuacao) {
        pontuacoes.add(pontuacao);
        Collections.sort(pontuacoes);
        salvarPontuacoes();
    }

    public List<Pontuacao> getPontuacoes() {
        return pontuacoes;
    }
}
