package br.com.devmedia.curso.exception;

/**
 * NaoExisteDAOException
 */
public class NaoExisteDAOException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NaoExisteDAOException(String message) {
        super(message);
    }
}