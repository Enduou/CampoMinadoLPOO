package teste;

public class Casa {
	
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
}


