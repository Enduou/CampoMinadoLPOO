package pontuacaoPacote;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jogadorPacote.Jogador;

public class GerenciadorDePontuacoes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    private static GerenciadorDePontuacoes instance;
    private List<Jogador> jogadores;

    private GerenciadorDePontuacoes() {
        jogadores = new ArrayList<>();
    }
    
   
    public static GerenciadorDePontuacoes getInstance() {
        if (instance == null) {
            instance = new GerenciadorDePontuacoes();
        }
        return instance;
    }
    
    
    public void addJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }
    
    
    public void savePoints(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Jogador jogador : jogadores) {
                writer.println(jogador.getNome() + "," + jogador.getPontos());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void carregaRecorde(String filename) {
        
        jogadores.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String jogadorNome = parts[0];
                    int pontos = Integer.parseInt(parts[1]);
                    jogadores.add(new Jogador(jogadorNome, pontos));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public List<Jogador> getJogadoresLista() {
        List<Jogador> jogadoresLista = new ArrayList<>(jogadores);
        Collections.sort(jogadoresLista, Comparator.comparingInt(Jogador::getPontos).reversed());
        return jogadoresLista;
    }
}