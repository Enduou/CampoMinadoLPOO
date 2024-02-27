package celulaPacote;

import java.util.Random;

import javax.management.InvalidAttributeValueException;

public class CampoMaluco extends Campo {
	
	private int numCelulasMalucas;

	public CampoMaluco(int linha, int coluna, int bombas, int numCelulasMalucas) {
		super(linha, coluna, bombas);
		this.setNumCelulasMalucas(numCelulasMalucas);
		
		
	}

	public int getNumCelulasMalucas() {
		return numCelulasMalucas;
	}

	public void setNumCelulasMalucas(int numCelulasMalucas) {
		this.numCelulasMalucas = numCelulasMalucas;
	}
	
	public void iniciarJogo() {
		matriz = new Celula[linha][coluna];
		
		for(int l = 0; l < linha; l++) {
			for(int c =0; c < coluna; c++) {
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
		int n = numCelulasMalucas;
		while (n > 0) {
		    int l = rand.nextInt(linha);
		    int c = rand.nextInt(coluna);

		    if (!(matriz[l][c].getCelulaMaluca())) {
		        matriz[l][c].setCelulaMaluca(true);
		        n--;
		    }
		}	
		
	}
	
	public void selecaoUsuario(int linhaSelecionada, int colunaSelecionada, int escolha) {
	    linhaSelecionada--;
	    colunaSelecionada--;

	    if (linhaSelecionada >= linha || linhaSelecionada < 0 || colunaSelecionada >= coluna || colunaSelecionada < 0) {
	        
	        System.out.println("erro na escolha de posição, tente novamente"); 
	    } else if (escolha == 0) {
	        explodir(linhaSelecionada, colunaSelecionada);

	        if ((matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba)
	                && (matriz[linhaSelecionada][colunaSelecionada].getRevelado())
	                && (!matriz[linhaSelecionada][colunaSelecionada].getFlag())) {
	            System.out.println("Você perdeu");
	        }
	    } else if (escolha == 1) {
	        if (!matriz[linhaSelecionada][colunaSelecionada].getFlag()) {
	            matriz[linhaSelecionada][colunaSelecionada].setFlag(true);
	            matriz[linhaSelecionada][colunaSelecionada].revelar();
	            System.out.println(matriz[linhaSelecionada][colunaSelecionada].getFlag());
	            
	            if(matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba) {
	            	bombasFlag ++;
	            }
	        }
	        
	        
	    }  else if(!(matriz[linhaSelecionada][colunaSelecionada].getRevelado()) && (matriz[linhaSelecionada][colunaSelecionada].getCelulaMaluca())) {
	    	Random maluca = new Random(); 	
	    	double probabilidadeMaluca = 0.5;
	    	matriz[linhaSelecionada][colunaSelecionada].setFlag(true);
	    	matriz[linhaSelecionada][colunaSelecionada].revelar();
	    	if(maluca.nextDouble() <= probabilidadeMaluca) {
	    		if(matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba) {
	    			bombasFlag++;
	    		}
	    	}
	    	if(bombasFlag == bombas) {
	    		System.out.println("O usuario venceu");
	    	}else {
	    		if(calcularBombas(linhaSelecionada,colunaSelecionada) == 0) {
	    			matriz[linhaSelecionada][colunaSelecionada] = new CelulaVazia();
	    			matriz[linhaSelecionada][colunaSelecionada].setRevelado(false);
	    			matriz[linhaSelecionada][colunaSelecionada].setFlag(true);
	    			
	    		}else {	
	    			matriz[linhaSelecionada][colunaSelecionada] = new CelulaVizinha();
	    			matriz[linhaSelecionada][colunaSelecionada].setRevelado(false);
	    			matriz[linhaSelecionada][colunaSelecionada].setFlag(true);
	    		}
	    		
	    	}
	    }
	    
	    
	

	
			
	}
}

