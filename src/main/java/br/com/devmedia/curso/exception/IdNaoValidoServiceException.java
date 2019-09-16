package br.com.devmedia.curso.exception;

public class IdNaoValidoServiceException extends RuntimeException {
 
    private static final long serialVersionUID = 1L;

    public IdNaoValidoServiceException(String message) {
        super(message);
    }
}