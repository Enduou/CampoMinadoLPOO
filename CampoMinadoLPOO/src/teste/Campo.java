package teste;

import java.util.Scanner;
import java.security.SecureRandom;

public class Campo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o tamanho da matriz");
        final int tamanhoMatriz = sc.nextInt();
        sc.close();

        char[][] matriz = gerarMatriz(tamanhoMatriz);

        
        for (int l = 0; l < tamanhoMatriz; l++) {
            for (int c = 0; c < tamanhoMatriz; c++) {
                System.out.print(matriz[l][c] + " ");
            }
            System.out.println(); 
        }
    }

    public static char[][] gerarMatriz(int tamanhoMatriz) {
        char[][] matriz = new char[tamanhoMatriz][tamanhoMatriz];
        SecureRandom random = new SecureRandom();

        for (int l = 0; l < tamanhoMatriz; l++) {
            for (int c = 0; c < tamanhoMatriz; c++) {
            	 if (random.nextInt(4) == 0) {
                     matriz[l][c] = '*'; // Caractere '*' em algumas posições
                 } else {
                     matriz[l][c] = (char) (random.nextInt(4) + '0'); // Número aleatório de 0 a 3
                 }
             }
         }

         return matriz;
    }
}

