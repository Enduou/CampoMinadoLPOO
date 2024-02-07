package celulaPacote;


public class CelulaVazia extends Celula {
	
	private boolean estaVazio;
	
	public CelulaVazia() {
		super();
	}
	
	public boolean getEstaVazio() {
		return estaVazio;
	}

	public void setEstaVazio(boolean estaVazio) {
		this.estaVazio = estaVazio;
	}

	@Override
	public String toString() {
		if(getRevelado() && !getFlag()) {
			return "";
		}else if (getFlag()){
			return "=";
		}else {
			return "?";
	 }
	}
}