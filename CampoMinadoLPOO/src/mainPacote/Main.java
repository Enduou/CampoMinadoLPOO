package mainPacote;
import celulaPacote.*;

import java.util.Scanner;

import celulaPacote.Campo;

public class Main {

    public static void main(String[] args) {

    	
    	Campo c = new Campo();
    	c.adicionarBomba();
    	
    	boolean jogo = true;
    	
    	
        Scanner scanner = new Scanner(System.in);
        System.out.println("Primeiro Jogador: ");
        String nomeJogador1 = scanner.next();
        System.out.println("Segundo Jogador: ");
        String nomeJogador2 = scanner.next();

        Jogador[] jogadores = new Jogador[2];
        jogadores[0] = new Jogador(nomeJogador1, 0);
        jogadores[1] = new Jogador(nomeJogador2, 0);
        
        int jogadorVez = 0;
        
        
        System.out.println("Campo Inicial:");
        System.out.println(c);
        
        while(jogo) {
	   
        
        	
        
        System.out.println("----------------digite [1] para revelar casa e [2] para colocar bandeira------------");
        System.out.println(jogadores[jogadorVez].getNome() + ", sua vez");
        
        int escolhaUsuario = scanner.nextInt();
	    if(escolhaUsuario==1) {
	        System.out.print("Digite o numero da linha: ");
	        int linha = scanner.nextInt();
	
	        System.out.print("Digite o numero da coluna: ");
	        int coluna = scanner.nextInt();
	
	      
	        c.descobrirCelula(linha, coluna);
	    }else if(escolhaUsuario==2) {
	    	System.out.print("Digite o numero da linha: ");
	        int linha = scanner.nextInt();
	
	        System.out.print("Digite o numero da coluna: ");
	        int coluna = scanner.nextInt();
	        
	        c.colocarFlag(linha, coluna);
	        
	    }
	    jogadorVez = (jogadorVez + 1) % 2;
	    System.out.println("Campo Atualizado:");
	    System.out.println(c);
	
	   
	}
        scanner.close();
	}
} 