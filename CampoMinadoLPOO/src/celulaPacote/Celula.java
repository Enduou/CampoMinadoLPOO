package celulaPacote;



public class Celula {

    //Atributos gerais de uma célula [Verificar a possibilidade de mandar para classes filhas]
    private boolean revelado;
    private boolean flag;
   
    


    public boolean getRevelado() {
        return revelado;
    }
    public void setRevelado(boolean revelado) {
        this.revelado = revelado;
    }
    
    public void revelar() {
    	this.revelado = true;
    }
    
    
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public void adicionarflag() {
		this.setFlag(true);
	}

	
	
	
}

