package celulaPacote;


import java.util.Random;

public class Campo {

    
	//Atributos que definem as especificações para inicialização da matriz campo
	private int linha = 7;
    private int coluna = 7;
    private Celula[][] matriz;
    private int numMinas = 4;
    private boolean[][] celulasAbertas;
 
    //Construtor principal do código, gera o tabuleiro e com auxílio de outros métodos define cada célula
    public Campo() {
        matriz = new Celula[linha][coluna];
        celulasAbertas = new boolean[linha][coluna];

        //Loop de criação do tabuleiro
        for (int l = 0; l < linha; l++) {
            for (int c = 0; c < coluna; c++) {
                matriz[l][c] = new Celula();
            }
        }

        //Loop que identifica cada célula como vizinhas umas as outras
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

	//Métodos get e set para acessar e modificar atributos
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
	


	// Método para randomizar o número de bombas e posicionar elas nas suas devidas células
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
	
	// Método para descobrir cada célula, verificando se já tiveram seus valores modificados ou não
	public void descobrirCelula(int linha, int coluna) {
	    if (linha >= 0 && linha < this.linha && coluna >= 0 && coluna < this.coluna) {
	        Celula celula = matriz[linha][coluna];


	        if (!celula.getRevelado()) {
	        	celula.setRevelado(true);
	        } else {
	            System.out.println("Essa celula ja foi descoberta.");
	        }
	    } else {
	        System.out.println("Coordenadas invalidas.");
	    }
	}
	
	
	// Mesmo trecho de código do anterior, verificar se posso apagar.
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
	
	
	// testar funcionamento do método de explosão
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
	
	
	// Coloca Flag na célula solicitada
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
	
//Polimorfismo para incrementar os caracteres de espaço na formatação do tabuleiro 
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