package celulaPacote;

import java.util.ArrayList;

public class CelulaVizinha extends Celula {
	
	
	
	
	
	
	
	
	
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
