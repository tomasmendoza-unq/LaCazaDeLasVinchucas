package unq.integrador.error;

public class UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException extends Exception {
    public UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException() {
        super();
    }

    public UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException(String mensaje) {
        super(mensaje);
    }

    public UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException(Throwable causa) {
        super(causa);
    }
}
