package pontuacaoPacote;

import java.io.*;
import java.util.*;

public class GerenciadorDePontuacoes {
    private List<String> pontuacoes;
    private static final String ARQUIVO_DE_PONTUACOES = "pontuacoes.txt";

    public GerenciadorDePontuacoes() {
        this.pontuacoes = new ArrayList<>();
        carregarPontuacoes();
    }

    private void carregarPontuacoes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DE_PONTUACOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                pontuacoes.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar as pontuações.");
            e.printStackTrace();
        }
    }

    public void adicionarPontuacao(String nomeJogador, int pontuacaoAtual) {
        String entradaPontuacao = nomeJogador + " - " + pontuacaoAtual;
        pontuacoes.add(entradaPontuacao);
        salvarPontuacoes(); 
    }

    private void salvarPontuacoes() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_DE_PONTUACOES))) {
            for (String pontuacao : pontuacoes) {
                writer.println(pontuacao); // Aqui, pontuação já inclui o nome do jogador e sua pontuação
            }
        } catch (IOException e) {
            System.out.println("Não foi possível salvar as pontuações.");
            e.printStackTrace();
        }
    }
    

    public List<String> getPontuacoes() {
        return new ArrayList<>(pontuacoes);
    }
}
