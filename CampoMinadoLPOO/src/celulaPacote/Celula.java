package celulaPacote;

import java.util.ArrayList;

public class Celula {

    private boolean Vazio;
    private boolean revelado;
    private boolean flag;
    private ArrayList<Celula> vizinhos;
    
    
    
    
    public Celula() {
        this.flag = false;
        this.revelado = false;

        this.vizinhos = new ArrayList();
    }
    
    public void adicionarVizinhos(Celula a) {
        this.vizinhos.add(a);
    }
    
    public int numMinasVizinhos() {
        int n = 0;
        for (Celula vizinhos : this.vizinhos) {
            if (vizinhos instanceof CelulaBomba) {
                n++;
            }
        }
        return n;
    }
   /* public boolean colocarBomba() {
        if (!getBomba()) {
            setBomba(true);
            return true;
        } else {
            return false;
        }
    }
*/
  
  /* public int selecionar() {
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
*/

    public void inicioJogo() {
        this.Vazio = false;
        this.revelado = false;
    }


    public boolean checarRevelado() {
        return getRevelado();
    }

    public void checarVazio() {
        this.Vazio = true;
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


	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
	
}


