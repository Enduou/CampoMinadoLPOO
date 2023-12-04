package celulaPacote;

import java.util.ArrayList;

public class CelulaVizinha extends Celula {

    private ArrayList<Celula> vizinhos;

    public CelulaVizinha() {
        this.vizinhos = new ArrayList<>();
    }

    public void adicionarVizinhos(Celula a) {
        this.vizinhos.add(a);
    }

    public int numMinasVizinhos() {
        int n = 0;
        for (Celula a : this.vizinhos) {
            if (a.getBomba()) {
                n++;
            }
        }
        return n;
    }

	
	
}
