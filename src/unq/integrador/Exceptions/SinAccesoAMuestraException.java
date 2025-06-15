package unq.integrador.Exceptions;

public class SinAccesoAMuestraException extends RuntimeException {
    public SinAccesoAMuestraException() {
        super();
    }

    public SinAccesoAMuestraException(String mensaje) {
        super(mensaje);
    }

    public SinAccesoAMuestraException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public SinAccesoAMuestraException(Throwable causa) {
        super(causa);
    }
}
