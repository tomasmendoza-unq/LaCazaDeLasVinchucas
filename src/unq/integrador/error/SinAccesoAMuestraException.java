package unq.integrador.error;

public class SinAccesoAMuestraException extends Exception {
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
