package teste;

import java.util.Scanner;
import java.security.SecureRandom;

public class Campo {
	
	
	private int linha = 7;
	private int coluna = 7;

	public void imprimeMatriz() {
       
   
        char[][] matriz = gerarMatriz(linha, coluna);


        for (int l = 0; l < linha; l++) {
            for (int c = 0; c < coluna; c++) {
                System.out.print(matriz[l][c] + " ");
            }
            System.out.println(); 
        }
    }

    public static char[][] gerarMatriz(int linha , int coluna) {
        char[][] matriz = new char[linha][coluna];
        SecureRandom random = new SecureRandom();

        for (int l = 0; l < linha; l++) {
            for (int c = 0; c < coluna; c++) {
            	 if (random.nextInt(4) == 0) {
            		 Celula colocaBomba = new Celula();
            		 colocaBomba.checarBomba();
            		 colocaBomba.setBomba(true);
            		 boolean verifica = colocaBomba.getBomba();
            		 matriz[l][c] = '-'; 
            		 System.out.println("A bomba foi colocada: " + verifica);
                 } else {
                     matriz[l][c] = (char) ('-'); 
                 }
             }
         }

         return matriz;
    }
}