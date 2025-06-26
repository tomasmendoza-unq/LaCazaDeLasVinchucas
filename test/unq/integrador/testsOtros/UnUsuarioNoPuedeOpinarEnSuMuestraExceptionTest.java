package unq.integrador.testsOtros;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;

public class UnUsuarioNoPuedeOpinarEnSuMuestraExceptionTest {
    
    @Test
    public void testConstructorSinParametros() {
        assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class, () -> {
            throw new UnUsuarioNoPuedeOpinarEnSuMuestraException(); });
    }

    @Test
    public void testConstructorConMensaje() {
        assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class, () -> { 
            throw new UnUsuarioNoPuedeOpinarEnSuMuestraException("Mensaje"); });
    }
    
    @Test
    public void testConstructorConCausa() {
        Throwable causa = new Exception("Causa");
        assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class, () -> { 
            throw new UnUsuarioNoPuedeOpinarEnSuMuestraException(causa); });
    }

    @Test
    public void testConstructorConMensajeYCausa() {
        Throwable causa = new Exception("Causa");
        assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class, () -> { 
            throw new UnUsuarioNoPuedeOpinarEnSuMuestraException("Mensaje", causa); });
    }
}
