package mainPacote;

import celulaPacote.*;
import excessaoPacote.AtributoException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Primeiro Jogador: ");
        String nomeJogador1 = scanner.next();
        System.out.println("Segundo Jogador: ");
        String nomeJogador2 = scanner.next();
        System.out.println("Digite {1} para campo minado normal e {2} para campo minado maluco ");
        int modoDeJogo = scanner.nextInt();
        
        // Criação de Jogadores
        Jogador[] jogadores = new Jogador[2];
        jogadores[0] = new Jogador(nomeJogador1, 0);
        jogadores[1] = new Jogador(nomeJogador2, 0);
        
        // Seleciona o modo de jogo
        iCampo c;
        if (modoDeJogo == 1) {
            c = new Campo(i.linha, i.coluna, i.bombas);
        } else if (modoDeJogo == 2) {
            c = new CampoMaluco(i.linha, i.coluna, i.bombas, 2);
        } else {
            System.out.println("Modo de jogo inválido. Encerrando o jogo.");
            scanner.close();
            return; // Termina o programa
        }
        
        c.iniciarJogo(); // Inicia o jogo
        
        System.out.println("Campo Inicial:");
        System.out.println(c);

        int jogadorVez = 0;
        boolean jogoEmAndamento = true;

        while (jogoEmAndamento) {
            System.out.println(jogadores[jogadorVez].getNome() + ", sua vez");
            System.out.println();
            System.out.println("Digite a linha:");
            int linha = scanner.nextInt();
            System.out.println("Digite a coluna:");
            int coluna = scanner.nextInt();
            System.out.println("Para Revelar a Casa, (DIGITE 0) Para adicionar bandeira, (DIGITE 1)");
            int escolha = scanner.nextInt();
            System.out.println();

            try {
                c.selecaoUsuario(linha, coluna, escolha);
            } catch (AtributoException e) {
                e.printStackTrace();
            }
            System.out.println(c);

            jogadorVez = (jogadorVez + 1) % 2;
        }

        scanner.close();
    }
}

