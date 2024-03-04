package celulaPacote;



public class Celula {

    //Atributos gerais de uma célula [Verificar a possibilidade de mandar para classes filhas]
    private boolean revelado;
    private boolean flag;
    private boolean celulaMaluca;
    


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
	public void setCelulaMaluca(boolean celulaMaluca) {
		this.celulaMaluca = celulaMaluca;
		
	}
	public boolean getCelulaMaluca() {
		
		return celulaMaluca;
	}

	
	
	
}

