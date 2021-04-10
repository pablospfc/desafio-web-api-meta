package br.com.restcontato.exceptions;

public class ContatoException extends Exception {
	
	     private static final long serialVersionUID = 1L;
		
    public ContatoException(String message) {
        super(message);
    }

    public ContatoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContatoException(Throwable cause) {
        super(cause);
    }

}
