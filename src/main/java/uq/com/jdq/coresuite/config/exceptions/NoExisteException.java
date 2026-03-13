package uq.com.jdq.coresuite.config.exceptions;

/**
 * Define la estructura y comportamiento de class NoExisteException.
 */
public class NoExisteException extends Exception{

    /**
     * Ejecuta la operacion NoExisteException.
     * @param msg parametro de entrada.
     */
    public NoExisteException(String msg){
        super(msg);
    }
}
