package teste;

public class Celula {

    private boolean Bomba;
    private int numero;
    private boolean Vazio;
    private boolean Revelado;

    public void inicioJogo() {
        this.Bomba = false;
        this.Vazio = false;
        this.Revelado = false;
    }


    public void checarBomba() {
        this.Bomba = true;
    }

    public void checarRevelado() {
        this.Revelado = true;
    }

    public void checarVazio() {
        this.Vazio = true;
    }

    public boolean getBomba() {
        return Bomba;
    }

    public void setBomba(boolean verifica) {
        this.Bomba = verifica;
    }

    public boolean getRevelado() {
        return Revelado;
    }
    public void setRevelado(boolean marcado) {
        this.Revelado = marcado;
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
}
    



