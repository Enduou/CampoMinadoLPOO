package mainPacote;
import java.util.Scanner;

import celulaPacote.Campo;
import celulaPacote.Jogador;

public class Main {

    public static void main(String[] args) {

    	//Gera Tabuleiro e Adiciona Bombas aleatórias nas células
    	Campo c = new Campo();
    	c.adicionarBomba();
    	
    	boolean jogo = true;
    	
    	//Input dos jogadores e Criação de objeto para cara jogador
        Scanner scanner = new Scanner(System.in);
        System.out.println("Primeiro Jogador: ");
        String nomeJogador1 = scanner.next();
        System.out.println("Segundo Jogador: ");
        String nomeJogador2 = scanner.next();

        Jogador[] jogadores = new Jogador[2];
        jogadores[0] = new Jogador(nomeJogador1, 0);
        jogadores[1] = new Jogador(nomeJogador2, 0);
        
        int jogadorVez = 0;
        
        // Print do Tabuleiro inicial
        
        System.out.println("Campo Inicial:");
        System.out.println(c);
        
        
        // Loop de execução do jogo
        while(jogo) {
	   
        
        	
        // Opções de jogo, com condicionais para executar a ação solicitada
        System.out.println("----------------digite [1] para revelar casa e [2] para colocar bandeira------------");
        System.out.println(jogadores[jogadorVez].getNome() + ", sua vez");
        
        int escolhaUsuario = scanner.nextInt();
	    if(escolhaUsuario==1) {
	        System.out.print("Digite o numero da linha: ");
	        int linha = scanner.nextInt();
	
	        System.out.print("Digite o numero da coluna: ");
	        int coluna = scanner.nextInt();
	
	        // Aciona o método que verifica o conteúdo da célula na posição especificada
	        c.descobrirCelula(linha, coluna);
	        c.explodir(linha, coluna);
	    }else if(escolhaUsuario==2) {
	    	System.out.print("Digite o numero da linha: ");
	        int linha = scanner.nextInt();
	
	        System.out.print("Digite o numero da coluna: ");
	        int coluna = scanner.nextInt();
	        
	        // Aciona o método que coloca uma flag na célula requisitada
	        c.colocarFlag(linha, coluna);
	        
	    }
	    jogadorVez = (jogadorVez + 1) % 2;
	    System.out.println("Campo Atualizado:");
	    System.out.println(c);
	
	   
	}
        scanner.close();
	}
} 