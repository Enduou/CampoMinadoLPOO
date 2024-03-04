package celulaPacote;

import java.util.Random;

import javax.management.InvalidAttributeValueException;

public class Campo implements iCampo, InterfaceCampo {

	protected Celula[][] matriz;
	protected int bombasFlag;
	protected boolean jogoAtivo;
	protected int linha;
	protected int coluna;
	protected int bombas;

	public Campo(int linha, int coluna, int bombas) {
		this.linha = linha;
		this.coluna = coluna;
		this.bombas = bombas;
	}

	public int getBombas() {
		return bombas;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public Celula[][] getMatriz() {
		return this.matriz;
	}

	public void adicionarBomba() throws InvalidAttributeValueException {

		if (bombas > (linha * coluna)) {
			throw new InvalidAttributeValueException(
					"Número de bombas maior do que o número total de células no tabuleiro.");
		}
		bombasFlag = 0;

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
		jogoAtivo = true;
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
		// aqui teria o metodo de adci

		for (int l = 0; l < linha; l++) {
			for (int c = 0; c < coluna; c++) {
				if (calcularBombas(l, c) == 0 && matriz[l][c] instanceof CelulaVizinha) {
					matriz[l][c] = new CelulaVazia();
				}
			}
		}
	}

	public void selecaoUsuario(int linhaSelecionada, int colunaSelecionada, int escolha) {
		linhaSelecionada--;
		colunaSelecionada--;

		if (linhaSelecionada >= linha || linhaSelecionada < 0 || colunaSelecionada >= coluna || colunaSelecionada < 0) {

		} else if (escolha == 0) {
			explodir(linhaSelecionada, colunaSelecionada);

			if ((matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba)
					&& (matriz[linhaSelecionada][colunaSelecionada].getRevelado())
					&& (!matriz[linhaSelecionada][colunaSelecionada].getFlag())) {
				System.out.println("VocÃª perdeu");
				jogoAtivo = false;
			}
		} else if (escolha == 1) {
			if (!matriz[linhaSelecionada][colunaSelecionada].getFlag()) {
				matriz[linhaSelecionada][colunaSelecionada].setFlag(true);
				matriz[linhaSelecionada][colunaSelecionada].revelar();
				System.out.println(matriz[linhaSelecionada][colunaSelecionada].getFlag());

				if (matriz[linhaSelecionada][colunaSelecionada] instanceof CelulaBomba) {
					bombasFlag++;
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
			if (!(matriz[linha][coluna] instanceof CelulaBomba) && (matriz[linha][coluna] instanceof CelulaVazia)  && (!matriz[linha][coluna].getRevelado())) {
				matriz[linha][coluna].revelar();
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

//Polimorfismo para incrementar os caracteres de espaÃ§o na formataÃ§Ã£o do tabuleiro 
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