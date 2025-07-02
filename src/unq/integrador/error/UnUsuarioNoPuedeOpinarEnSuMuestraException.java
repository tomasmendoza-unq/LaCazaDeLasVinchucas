package unq.integrador.error;

public class UnUsuarioNoPuedeOpinarEnSuMuestraException extends Exception {
    public UnUsuarioNoPuedeOpinarEnSuMuestraException() {
        super();
    }

    public UnUsuarioNoPuedeOpinarEnSuMuestraException(String mensaje) {
        super(mensaje);
    }

    public UnUsuarioNoPuedeOpinarEnSuMuestraException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public UnUsuarioNoPuedeOpinarEnSuMuestraException(Throwable causa) {
        super(causa);
    }
}
