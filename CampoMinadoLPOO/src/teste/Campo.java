package teste;

import java.util.Scanner;
import java.security.SecureRandom;

public class Campo {
	
	public void imprimeMatriz() {
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
            		 Celula colocaBomba = new Celula();
            		 colocaBomba.checarBomba();
            		 colocaBomba.setBomba(true);
            		 boolean verifica = colocaBomba.getBomba();
            		 matriz[l][c] = '-'; // Caractere '*' em posições randômicas
            		 System.out.println("A bomba foi colocada: " + verifica);
                 } else {
                     matriz[l][c] = (char) ('-'); // Número aleatório de 0 a 3
                 }
             }
         }

         return matriz;
    }
}