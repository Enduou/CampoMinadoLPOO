package excessaoPacote;

public class AtributoException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public AtributoException(String message) {
        super("Erro, selecione um dos valores solicitados"+message);
    }
}
