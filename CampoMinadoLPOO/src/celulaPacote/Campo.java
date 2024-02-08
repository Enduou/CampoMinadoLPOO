package celulaPacote;

import java.util.Random;

import excessaoPacote.AtributoException;

public class Campo extends i implements iCampo {

	protected Celula [][] matriz;
	protected int bombasFlag;
	
	public Campo(int linha, int coluna, int bombas) {
		i.linha = linha;
		i.coluna = coluna;
		i.bombas = bombas;
	}
	// Construtor principal do código, gera o tabuleiro e com auxílio de outros
	// métodos define cada célula

	public void adicionarBomba() {

		Random rand = new Random();
		int n = bombas;
		while (n > 0) {
		    int l = rand.nextInt(linha);
		    int c = rand.nextInt(coluna);

		    if (!(matriz[l][c] instanceof CelulaBomba)) {
		        matriz[l][c] = new CelulaBomba();
		        n--;
		    }
		}
		}
	

	public void iniciarJogo() {
		matriz = new Celula[linha][coluna];

		for (int l = 0; l < linha; l++) {
			for (int c = 0; c < coluna; c++) {
				matriz[l][c] = new CelulaVizinha();
			}
		}
		adicionarBomba();
		// aqui teria o metodo de adci

		for (int l = 0; l < linha; l++) {
			for (int c = 0; c < coluna; c++) {
				if (calcularBombas(l, c) == 0 && matriz[l][c] instanceof CelulaVizinha) {
					matriz[l][c] = new CelulaVazia();
				}
			}
		}
	}

	public void selecaoUsuario(int linhaSelecionada, int colunaSelecionada, int escolha) throws AtributoException {
	    linhaSelecionada--;
	    colunaSelecionada--;

	    if (linhaSelecionada >= linha || linhaSelecionada < 0 || colunaSelecionada >= coluna || colunaSelecionada < 0) {
	        // Lançando a exceção AtributoException com a mensagem apropriada
	        throw new AtributoException("Erro na escolha de posição, tente novamente");
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
	    }
	}

	public int calcularBombas(int linhaB, int colunaB) {
		int n = 0;
		for (int l = linhaB - 1; l <= linhaB + 1; l++) {
			for (int c = colunaB - 1; c <= colunaB + 1; c++) {
				if (l >= 0 && l < matriz.length && c >= 0 && c < matriz[l].length && (l != linhaB || c != colunaB)) {
					if (matriz[l][c] instanceof CelulaBomba) {
						n++;
					}
				}
			}
		}

		return n;
	}

	public void explodir(int linha, int coluna) {
		if (linha >= 0 && linha < matriz.length && coluna >= 0 && coluna < matriz[linha].length) {
			Celula celula = matriz[linha][coluna];
			if (!(matriz[linha][coluna] instanceof CelulaBomba) && celula instanceof CelulaVazia
					&& !celula.getRevelado()) {
				celula.revelar();
				for (int l = linha - 1; l <= linha + 1; l++) {
					for (int c = coluna - 1; c <= coluna + 1; c++) {
						if (l != linha || c != coluna) {
							explodir(l, c);
						}
					}
				}
			} else {
				matriz[linha][coluna].revelar();
			}
		}
	}

//Polimorfismo para incrementar os caracteres de espaço na formatação do tabuleiro 
	@Override
	public String toString() {
		String str = "";
		for (int l = 0; l < linha; l++) {
			for (int c = 0; c < coluna; c++) {
				if ((matriz[l][c].getRevelado() && !(matriz[l][c] instanceof CelulaBomba)
						&& !(matriz[l][c].getFlag()))) {
					str += matriz[l][c] + "" + calcularBombas(l, c);
				} else {
					str += matriz[l][c] + "";
				}
			}
			str += "\n";
		}
		return str;
	}

}