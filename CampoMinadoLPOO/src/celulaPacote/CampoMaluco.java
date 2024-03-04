package celulaPacote;

import java.util.Random;

import javax.management.InvalidAttributeValueException;

public class CampoMaluco extends Campo implements InterfaceCampo {

	private int numCelulasMalucas;
	
	public int getNumCelulasMalucas() {
		return numCelulasMalucas;
	}

	public void setNumCelulasMalucas(int numCelulasMalucas) {
		this.numCelulasMalucas = numCelulasMalucas;
	}


	public CampoMaluco(int linha, int coluna, int bombas, int numCelulasMalucas) {
		super(linha, coluna, bombas);
		this.numCelulasMalucas = numCelulasMalucas;

	}
	
	
	
	
	public void iniciarJogo() {
		matriz = new Celula[linha][coluna];

		for (int l = 0; l < linha; l++) {
			for (int c = 0; c < coluna; c++) {
				matriz[l][c] = new CelulaVizinha();
			}
		}

		try {
			adicionarBomba();
		} catch (InvalidAttributeValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inserirCelulaMaluca();

		for (int l = 0; l < linha; l++) {
			for (int c = 0; c < coluna; c++) {
				if (calcularBombas(l, c) == 0 && matriz[l][c] instanceof CelulaVizinha) {
					matriz[l][c] = new CelulaVazia();
				}
			}

		}
	}

	public void inserirCelulaMaluca() {

		Random rand = new Random();
		
		int cont = 0;
		
		System.out.println("...");
		
		while(cont < numCelulasMalucas) {
			int linhaAleatoria = rand.nextInt(getLinha());
			int colunaAleatoria = rand.nextInt(getColuna());
			
			if(!(getMatriz()[linhaAleatoria][colunaAleatoria].getCelulaMaluca())) {
				getMatriz()[linhaAleatoria][colunaAleatoria].setCelulaMaluca(true);
				cont++;
			}
		}
	}

	public void selecaoUsuario(int linhaSelecionada, int colunaSelecionada, int intention) {
	    linhaSelecionada--;
	    colunaSelecionada--;

	    if (linhaSelecionada < 0 || linhaSelecionada >= linha || colunaSelecionada < 0 || colunaSelecionada >= coluna) {
	        System.out.println();
	        System.out.println("Posição inválida! Tente novamente!!");
	        System.out.println();
	        return;
	    }

	    // adicionar flag
	    
	    else if (intention == 1) {
	        if (!(matriz[linhaSelecionada][colunaSelecionada].getRevelado()) && !(matriz[linhaSelecionada][colunaSelecionada].getCelulaMaluca())) {
	            matriz[linhaSelecionada][colunaSelecionada].setFlag(true);
	            matriz[linhaSelecionada][colunaSelecionada].revelar();
	            
	            // Incrementar o número de CelulaBombaas marcadas com bandeiras
	            
	            if (matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba) {
	               bombasFlag++;
	            }

	            // Verificar se todas as CelulaBombaas foram marcadas com bandeiras para vencer o jogo
	            
	            if (bombasFlag ==  bombas) {
	            	System.out.println(this);
	            	System.exit(0);
	            }
	        } 
	        
	   // lógica para fazer a probabilidade da célula maluca alterar seu status de CelulaBombaa
	        
	        else if (!(matriz[linhaSelecionada][colunaSelecionada].getRevelado()) && (matriz[linhaSelecionada][colunaSelecionada].getCelulaMaluca())) {
	        	identificadorCelulaMaluca(linhaSelecionada, colunaSelecionada);
	        }
	    }

	    // propagação automática
	    
	    else if (intention == 0) {
	        explodir(linhaSelecionada, colunaSelecionada);
	        if ((matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba) && (matriz[linhaSelecionada][colunaSelecionada].getRevelado()) && (!matriz[linhaSelecionada][colunaSelecionada].getFlag())) {
	            System.out.println(this);
//	            gameOver(player);
	        }
	    }

	    // remover flag
	    
	    else if (intention == 2) {
	        if (matriz[linhaSelecionada][colunaSelecionada].getFlag()) {
	            matriz[linhaSelecionada][colunaSelecionada].setFlag(false);
	            matriz[linhaSelecionada][colunaSelecionada].setRevelado(false);
	        }
	    }

	    // revelar casa
	    
	    else {
	        if (matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba) {
	            System.out.println(this);
//	            gameOver(player);
	        } 
	        else {
	            matriz[linhaSelecionada][colunaSelecionada].revelar();
	        }
	    }
	}

	public void identificadorCelulaMaluca(int linhaSelecionada, int colunaSelecionada) {
	    Random rand = new Random();
	    double probabilidadeMaluca = 0.5;
	    System.out.println("Handling Crazy Cell...");
	    matriz[linhaSelecionada][colunaSelecionada].setFlag(true);
	    matriz[linhaSelecionada][colunaSelecionada].revelar();

	    if (rand.nextDouble() <= probabilidadeMaluca) {
	        System.out.println("Crazy Cell switched status!");
	        if (matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba) {
	            bombasFlag++;
	        }

	        if (calcularBombas(linhaSelecionada, colunaSelecionada) == 0) {
	            matriz[linhaSelecionada][colunaSelecionada] = new CelulaVazia();
	        } else {
	            matriz[linhaSelecionada][colunaSelecionada] = new CelulaVizinha();
	        }
	        matriz[linhaSelecionada][colunaSelecionada].setRevelado(false);
	        matriz[linhaSelecionada][colunaSelecionada].setFlag(true);
	    }
	}
	
}