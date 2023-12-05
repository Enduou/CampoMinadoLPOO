package celulaPacote;


import java.util.Random;

public class Campo {

    private int linha = 7;
    private int coluna = 7;
    private Celula[][] matriz;
    private int numMinas = 4;
    private boolean[][] celulasAbertas;
 
    public Campo() {
        matriz = new Celula[linha][coluna];
        celulasAbertas = new boolean[linha][coluna];

    
        for (int l = 0; l < linha; l++) {
            for (int c = 0; c < coluna; c++) {
                matriz[l][c] = new Celula();
            }
        }


        for (int l = 0; l < linha; l++) {
            for (int c = 0; c < coluna; c++) {
                if (l > 0) {
                    if (c > 0) matriz[l][c].adicionarVizinhos(matriz[l - 1][c - 1]);
                    matriz[l][c].adicionarVizinhos(matriz[l - 1][c]);
                    if (c < coluna - 1) matriz[l][c].adicionarVizinhos(matriz[l - 1][c + 1]);
                }

                if (c > 0) matriz[l][c].adicionarVizinhos(matriz[l][c - 1]);
                if (c < coluna - 1) matriz[l][c].adicionarVizinhos(matriz[l][c + 1]);

                if (l < linha - 1) {
                    if (c > 0) matriz[l][c].adicionarVizinhos(matriz[l + 1][c - 1]);
                    matriz[l][c].adicionarVizinhos(matriz[l + 1][c]);
                    if (c < coluna - 1) matriz[l][c].adicionarVizinhos(matriz[l + 1][c + 1]);
                }
            }
        }    
    }

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public Celula[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Celula[][] matriz) {
		this.matriz = matriz;
	}
	



	public void adicionarBomba() {
		Random rand = new Random();
		int n = numMinas;
	    while (n > 0) {
	        int l = rand.nextInt(linha);
	        int c = rand.nextInt(coluna);
	
	        if (matriz[l][c].colocarBomba()) {
	            n--;    
	    
	        }  
	    }  
	}
	public void selecionar(int linha, int coluna) {
	    if (linha >= 0 && linha < this.linha && coluna >= 0 && coluna < this.coluna) {
	        Celula celula = matriz[linha][coluna];


	        if (!celula.getRevelado()) {
	        	celula.setRevelado(true);
	        } else {
	            System.out.println("Essa célula já foi descoberta.");
	        }
	    } else {
	        System.out.println("Coordenadas inválidas.");
	    }
	}
	
	public void explodir(int linha, int coluna) {
	    if (linha >= 0 && linha< matriz.length && coluna >= 0 && coluna < matriz[linha].length) {
	        if (!(matriz[linha][coluna] instanceof CelulaBomba) && (matriz[linha][coluna] instanceof CelulaVazia) && (!matriz[linha][coluna].getRevelado())) {
	            matriz[linha][coluna].getRevelado();
	            for (int l = linha - 1; l <= linha + 1; l++) {
	                for (int c = coluna - 1; c <= coluna + 1; c++) {
	                    if (l != linha || c != coluna) {
	                        explodir(l, c);
	                    }
	                }
	            }
	        } 
	        else {
	            matriz[linha][coluna].getRevelado();
	        }
	    }
	}
	
	
	
	public void colocarFlag(int linha, int coluna) {
	    if (linha >= 0 && linha < this.linha && coluna >= 0 && coluna < this.coluna) {
	        Celula flag = matriz[linha][coluna];
	        if(!flag.getFlag()) {
	        	flag.setFlag(true);
	        }else {
	        	System.out.println("Ja tem bandeira neste local");	
	        }
	      
	            }
	        } 
	

@Override
public String toString() {
	String str = "";
		for (int l = 0; l < linha; l++) {
			for(int c = 0; c < coluna; c++) {
				str = str + matriz[l][c] + " ";
			}
			str += "\n";
		}
	return str;
}


}   