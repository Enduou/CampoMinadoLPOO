package teste;

import java.security.SecureRandom;

public class Campo {

    private int linha = 7;
    private int coluna = 7;
    private char[][] matriz;

    public void imprimeMatriz() {
        matriz = gerarMatriz(linha, coluna);

        for (int l = 0; l < linha; l++) {
            for (int c = 0; c < coluna; c++) {
                System.out.print(matriz[l][c] + " ");
            }
            System.out.println();
        }
    }

    public char[][] gerarMatriz(int linha, int coluna) {
        char[][] matriz = new char[linha][coluna];
        SecureRandom random = new SecureRandom();

        for (int l = 0; l < linha; l++) {
            for (int c = 0; c < coluna; c++) {
                if (random.nextInt(4) == 0) {
                	Celula colocaBomba = new Celula();
                	colocaBomba.checarBomba();
                	colocaBomba.setBomba(true);
                	boolean verifica = colocaBomba.getBomba();
                	matriz[l][c] = '*';
                } else {
                    matriz[l][c] = '-';
                }
            }
        }

        return matriz;
    }

    public char escolha(int linhaEscolhida, int colunaEscolhida) {
        if (linhaEscolhida >= 0 && linhaEscolhida < linha && colunaEscolhida >= 0 && colunaEscolhida < coluna) {
            char valorEscolhido = matriz[linhaEscolhida][colunaEscolhida];
            System.out.println("Voce escolheu a posicao [" + linhaEscolhida + "][" + colunaEscolhida + "] com valor: " + valorEscolhido);
            return valorEscolhido;
        } else {
            System.out.println("Posicao invalida.");
            return '0'; 
        }
    }
    public char escolhaFlag(int linhaEscolhida, int colunaEscolhida) {
        if (linhaEscolhida >= 0 && linhaEscolhida < linha && colunaEscolhida >= 0 && colunaEscolhida < coluna) {
            char valorEscolhido = matriz[linhaEscolhida][colunaEscolhida];
            System.out.println("Voce escolheu a posicao [" + linhaEscolhida + "][" + colunaEscolhida + "] BANDEIRA COLOCADA");
            return valorEscolhido;
        } else {
            System.out.println("Posicao invalida.");
            return '0'; 
        }
    
    
}
}