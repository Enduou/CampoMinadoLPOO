package celulaPacote;

import java.util.ArrayList;

public class Celula {
    private boolean bomba;
    private boolean flag;
    private boolean revelado;
    private char valor;
    private ArrayList<Celula> vizinhos;

    public Celula() {
        this.bomba = false;
        this.flag = false;
        this.revelado = false;
        this.vizinhos = new ArrayList<>();
    }

    public void adicionarVizinhos(Celula vizinha) {
        if (!vizinhos.contains(vizinha)) {
            vizinhos.add(vizinha);
        }
    }

    public int numMinasVizinhos() {
        int n = 0;
        for (Celula vizinha : vizinhos) {
            if (vizinha.bomba) {
                n++;
            }
        }
        
        return n;
    }

    public boolean colocarBomba() {
        if (!bomba) {
            bomba = true;
            return true;
        } else {
            return false;
        }
    }

    public int obterValorCelula() {
        if (bomba) {
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
        this.revelado = false;
    }


    public void checarRevelado() {
        this.revelado = true;
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

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	//Polimorfismo para inserir caracteres na abertura de célula
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
