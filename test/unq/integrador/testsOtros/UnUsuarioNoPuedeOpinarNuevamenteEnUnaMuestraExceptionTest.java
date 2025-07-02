package unq.integrador.testsOtros;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;

public class UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraExceptionTest {
    
    @Test
    public void testConstructorSinParametros() {
        assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class, () -> {
            throw new UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException(); });
    }

    @Test
    public void testConstructorConMensaje() {
        assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class, () -> { 
            throw new UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException("Mensaje"); });
    }
    
    @Test
    public void testConstructorConCausa() {
        Throwable causa = new Exception("Causa");
        assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class, () -> { 
            throw new UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException(causa); });
    }

    @Test
    public void testConstructorConMensajeYCausa() {
        Throwable causa = new Exception("Causa");
        assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class, () -> { 
            throw new UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException("Mensaje", causa); });
    }
}
