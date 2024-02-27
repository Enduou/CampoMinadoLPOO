package celulaPacote;

import javax.management.InvalidAttributeValueException;

import excessaoPacote.AtributoException;

public interface iCampo {
	
	public void adicionarBomba() throws InvalidAttributeValueException;
	public void iniciarJogo();
	public void selecaoUsuario(int linhaSelecionada, int colunaSelecionada, int escolha) throws AtributoException;
	public int calcularBombas(int linhaB, int colunaB);
	public void explodir(int linha, int coluna);
	
}
