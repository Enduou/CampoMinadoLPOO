package celulaPacote;


public class CelulaVazia extends Celula {
	
	private boolean vazia;
	
	public CelulaVazia() {
		super();
	}

	public boolean getVazia() {
		return vazia;
	}

	public void setVazia(boolean vazia) {
		this.vazia = vazia;
	}

	@Override
	public String toString() {
		if (getRevelado() && !getFlag()) {
			return "";
		}
		else if(getFlag()) {
			return "%";		
		}else {
			return "#";
		}
	}
   
	
	
}