package celulaPacote;

public class CelulaVizinha extends Celula {

    //Lógica para verificação de adjacência usando arraylist
	
	private boolean vizinho;

    public CelulaVizinha() {
        super();
    }

    
    
    public boolean isVizinho() {
		return vizinho;
	}



	@Override
	public String toString() {
		if(getRevelado() && !(getFlag())) {
			return "";
		}else if(getFlag()) {
			return "";
		}else {
			return "?";
		}
		
	}
}



	



