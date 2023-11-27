package teste;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Campo tabuleiro = new Campo();
        tabuleiro.imprimeMatriz();
        
        Scanner input = new Scanner(System.in);

        System.out.println("Digite a Linha: ");
        int escolhaLinha = input.nextInt();
        System.out.println("Digite a Coluna: ");
        int escolhaColuna = input.nextInt();
        
        char valorEscolhido = tabuleiro.escolha(escolhaLinha, escolhaColuna);
        System.out.println("O valor na posição escolhida é: " + valorEscolhido);
        
        input.close();
    }
}