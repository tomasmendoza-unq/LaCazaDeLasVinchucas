package unq.integrador;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SinAccesoAMuestraExceptionTest {
    
    @Test
    public void testConstructorSinParametros() {
        assertThrows(SinAccesoAMuestraException.class, () -> { 
            throw new SinAccesoAMuestraException(); });
    }

    @Test
    public void testConstructorConMensaje() {
        assertThrows(SinAccesoAMuestraException.class, () -> { 
            throw new SinAccesoAMuestraException("Mensaje"); });
    }
    
    @Test
    public void testConstructorConCausa() {
        Throwable causa = new RuntimeException("Causa");
        assertThrows(SinAccesoAMuestraException.class, () -> { 
            throw new SinAccesoAMuestraException(causa); });
    }

    @Test
    public void testConstructorConMensajeYCausa() {
        Throwable causa = new RuntimeException("Causa");
        assertThrows(SinAccesoAMuestraException.class, () -> { 
            throw new SinAccesoAMuestraException("Mensaje", causa); });
    }
}
