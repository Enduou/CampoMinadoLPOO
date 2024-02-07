package celulaPacote;


public class CelulaBomba extends Celula {
	//Classe onde os atributos e métodos referentes a bombas irão ficar
	
	private boolean temBomba;
	
	public CelulaBomba() {
		super();
		adicionarBomba();
	}
	
	public boolean checarBomba() {
		return temBomba;
		
	}
	
	public void adicionarBomba() {
		this.temBomba = true;
	}

	@Override
	public String toString() {
		if (getRevelado() && checarBomba() && !getFlag()){
			return "-1";
			
		}else if(getFlag()) {
			return "=";
		}else {
			return "?";
		}
	}
}
		
	
 



