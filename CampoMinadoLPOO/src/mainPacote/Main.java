package mainPacote;

import celulaPacote.*;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Primeiro Jogador: ");
		String nomeJogador1 = scanner.next();
		System.out.println("Segundo Jogador: ");
		String nomeJogador2 = scanner.next();

		Jogador[] jogadores = new Jogador[2];
		jogadores[0] = new Jogador(nomeJogador1, 0);
		jogadores[1] = new Jogador(nomeJogador2, 0);
		int jogadorVez = 0;

		Campo c = new Campo(7, 7, 10);
		c.iniciarJogo();

		System.out.println("Campo Inicial:");
		System.out.println(c);

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

			c.selecaoUsuario(linha, coluna, escolha);
			System.out.println(c);
			

			jogadorVez = (jogadorVez + 1) % 2;

		}

		scanner.close();
	}
}
