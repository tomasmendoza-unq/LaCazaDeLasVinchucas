package unq.integrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.impls.MuestraLibre;
import unq.integrador.impls.Opinion;

public class MuestraTest {
    private IMuestra muestra;
    private IUsuario user;

    @BeforeEach
    public void setUp() {
        user = mock(IUsuario.class);
        muestra = new MuestraLibre(user, "Foto", "Calle 123");
    }

    @Test
    public void testGetters() {
        when(user.getID()).thenReturn(1);
        
        assertEquals(1, muestra.getIDUsuario());
        assertEquals("Foto", muestra.getFotografia());
        assertEquals("Calle 123", muestra.getUbicacion());
        assertEquals(LocalDate.now(), muestra.getFechaCreacion());
    }

    @Test
    public void testAgregarAlHistorial() {
        Opinion op = mock(Opinion.class);
        when(op.getID()).thenReturn(1);
        when(op.imprimirTipo()).thenReturn("Ninguna");
        when(op.getFechaDeCreacion()).thenReturn(LocalDate.now());
        when(op.getCategoria()).thenReturn("Básico");

        muestra.agregarOpinionBasico(op);

        assertEquals(
            "Usuario 1 opinó: Ninguna, en la fecha: 2025-06-15, con categoría: Básico", 
            muestra.verRegistroN(1));
    }
}
