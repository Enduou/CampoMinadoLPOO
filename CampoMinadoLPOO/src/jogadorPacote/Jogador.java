package jogadorPacote;

import celulaPacote.Celula;

public class Jogador extends Celula {

    //Definição de atributos e objeto Jogador, com métodos get e set.
	
	private boolean clique;
    private String nome;
    private int pontos;
    
   //Construtor que define o nome e [Futuramente irá definir quantos pontos cada jogador tem]
    public Jogador(String nome, int pontos) {
    	this.nome = nome;
    	this.pontos = pontos;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getPontos(){
    	return pontos;
    }
    
    public void setPontos(int pontos) {
    	this.pontos = pontos;
    }

    
}