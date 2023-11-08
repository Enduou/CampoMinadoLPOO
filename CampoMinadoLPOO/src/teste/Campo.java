package teste;

import java.util.Scanner;
import java.security.SecureRandom;

public class Campo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o tamanho da matriz");
        final int tamanhoMatriz = sc.nextInt();
        sc.close();

        int[][] matriz = gerarMatriz(tamanhoMatriz);

        
        for (int l = 0; l < tamanhoMatriz; l++) {
            for (int c = 0; c < tamanhoMatriz; c++) {
                System.out.print(matriz[l][c] + " ");
            }
            System.out.println(); 
        }
    }

    public static int[][] gerarMatriz(int tamanhoMatriz) {
        int[][] matriz = new int[tamanhoMatriz][tamanhoMatriz];
        SecureRandom random = new SecureRandom();

        for (int l = 0; l < tamanhoMatriz; l++) {
            for (int c = 0; c < tamanhoMatriz; c++) {
                matriz[l][c] = random.nextInt(4);
            }
        }

        return matriz;
    }
}