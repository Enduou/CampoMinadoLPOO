package teste;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean jogo = true;
        Scanner input = new Scanner(System.in);

        System.out.println("Hello World");
        while (jogo) {
            Campo tabuleiro = new Campo();
            tabuleiro.imprimeMatriz();

            System.out.println("-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("Digite [1] para selecionar a celula/" 
                    + " Digite [2] Para selecionar a flag/"
                    + " Digite [3] Para Sair");

            int escolhaUsuario = input.nextInt();

            // Condicionais de Escolha
            if (escolhaUsuario == 1) {
                System.out.println("Digite a Linha: ");
                int escolhaLinha = input.nextInt();
                System.out.println("Digite a Coluna: ");
                int escolhaColuna = input.nextInt();
                char valorEscolhido = tabuleiro.escolha(escolhaLinha, escolhaColuna);
                System.out.println("O valor na posicao escolhida e: " + valorEscolhido);
            } else if (escolhaUsuario == 2) {
                System.out.println("Digite a Linha: ");
                int escolhaLinha = input.nextInt();
                System.out.println("Digite a Coluna: ");
                int escolhaColuna = input.nextInt();
                char valorEscolhido = tabuleiro.escolhaFlag(escolhaLinha, escolhaColuna);
                System.out.println("O valor na posicao escolhida e: " + valorEscolhido);
            } else if (escolhaUsuario == 3) {
                System.out.println("Sair");
                jogo = false;
            } else {
                System.out.println("Escolha inválida. Tente novamente.");
            }

            // Limpar o buffer do Scanner
            input.nextLine();
        }

        // Fechar o Scanner quando não for mais necessário
        input.close();
        System.out.println("Obrigado por jogar!");
    }
}

