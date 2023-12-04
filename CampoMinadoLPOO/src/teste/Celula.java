package teste;

import java.util.ArrayList;

public class Celula {

    private boolean bomba;
    private int numero;
    private boolean Vazio;
    private boolean revelado;
    private char valor;
    private boolean flag;
    private boolean clicou;
    private boolean clicado;
    private ArrayList<Celula> vizinhos;
    
    
    
    
    public Celula() {
        this.bomba = false;
        this.flag = false;
        this.revelado = false;
        this.clicou = false;
        this.clicado = false;
        this.vizinhos = new ArrayList();
    }
    
    public void adicionarVizinhos(Celula a) {
        this.vizinhos.add(a);
    }
    
    public int numMinasVizinhos() {
        int n = 0;
        for (Celula vizinhos : this.vizinhos) {
            if (vizinhos.bomba) {
                n++;
            }
        }
        return n;
    }
    public boolean colocarBomba() {
        if (!getBomba()) {
            setBomba(true);
            return true;
        } else {
            return false;
        }
    }

  
    public int selecionar() {
        this.clicou = true;

        if (getBomba()) {
            return -1;
        } else {
            return numMinasVizinhos();
        }
    }
    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }
    public void inicioJogo() {
        this.bomba = false;
        this.Vazio = false;
        this.revelado = false;
    }


    public void checarBomba() {
        this.bomba = true;
    }

    public void checarRevelado() {
        this.revelado = true;
    }

    public void checarVazio() {
        this.Vazio = true;
    }

    public boolean getBomba() {
        return bomba;
    }

    public void setBomba(boolean verifica) {
        this.bomba = verifica;
    }

    public boolean getRevelado() {
        return revelado;
    }
    public void setRevelado(boolean marcado) {
        this.revelado = marcado;
    }
    public boolean getVazio() {
        return Vazio;
    }
    public void setVazio (boolean vazio) {
        this.Vazio = vazio;
    }


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}
	

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
    public String toString() {
		if(this.flag) {
			return "&";
		}
        if (this.revelado) {
            if (this.bomba) {
                return "-1"; 
            } else {
                return "+" + this.numMinasVizinhos(); 
            }
        } else {
            return "?"; 
        }
	
}
	
	
}


