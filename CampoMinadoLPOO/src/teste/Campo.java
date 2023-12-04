package teste;


import java.util.Random;

public class Campo {

    private int linha = 7;
    private int coluna = 7;
    private Celula[][] matriz;
    private int numMinas = 4;
 
    public Campo() {
        matriz = new Celula[linha][coluna];

    
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
public void descobrirCelula(int linha, int coluna) {
    if (linha >= 0 && linha < this.linha && coluna >= 0 && coluna < this.coluna) {
        Celula celula = matriz[linha][coluna];


        if (!celula.getRevelado()) {
        	celula.setRevelado(true);
        } else {
            System.out.println("Essa c�lula j� foi descoberta.");
        }
    } else {
        System.out.println("Coordenadas inv�lidas.");
    }
}

public void colocarFlag(int linha, int coluna) {
    if (linha >= 0 && linha < this.linha && coluna >= 0 && coluna < this.coluna) {
        Celula flag = matriz[linha][coluna];
        if(!flag.getFlag()) {
        	flag.setFlag(true);
        }else {
        	System.out.println("J� tem bandeira neste local");	
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   /* public char escolha(int linhaEscolhida, int colunaEscolhida) {
        if (linhaEscolhida >= 0 && linhaEscolhida < linha && colunaEscolhida >= 0 && colunaEscolhida < coluna) {
            char valorEscolhido = matriz[linhaEscolhida][colunaEscolhida].getValor();
            System.out.println("Voc� escolheu a posi��o [" + linhaEscolhida + "][" + colunaEscolhida + "] com valor: " + valorEscolhido);
            return valorEscolhido;
        } else {
            System.out.println("Posi��o inv�lida.");
            return '0';
        }
    }
    */
    /*public char escolhaFlag(int linhaEscolhida, int colunaEscolhida) {
        if (linhaEscolhida >= 0 && linhaEscolhida < linha && colunaEscolhida >= 0 && colunaEscolhida < coluna) {
            Celula valorEscolhido = matriz[linhaEscolhida][colunaEscolhida];
            System.out.println("Voce escolheu a posicao [" + linhaEscolhida + "][" + colunaEscolhida + "] BANDEIRA COLOCADA");
            return valorEscolhido;
        } else {
            System.out.println("Posicao invalida.");
            return '0'; 
        }
   */ 
    

