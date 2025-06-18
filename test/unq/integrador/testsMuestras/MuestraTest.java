package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.Muestra;
import unq.integrador.impls.Opinion;

public class MuestraTest {
    private IMuestra muestra;
    private IUbicacion ubicacion;
    private IBaseDeMuestras baseDeMuestras;

    @BeforeEach
    public void setUp() {
        ubicacion = mock(IUbicacion.class);
        baseDeMuestras = mock(IBaseDeMuestras.class);
        muestra = new Muestra(1, "Foto", ubicacion,baseDeMuestras);
    }

    @Test
    public void testGetters() {
        assertEquals(1, muestra.getIDUsuario());
        assertEquals("Foto", muestra.getFotografia());
        assertEquals(ubicacion, muestra.getUbicacion());
        assertEquals(LocalDate.now(), muestra.getFechaCreacion());
    }

    @Test
    public void testAgregarAlHistorial() {
        Opinion op = mock(Opinion.class);
        when(op.getID()).thenReturn(1);
        when(op.imprimirTipo()).thenReturn("Ninguna");
        when(op.getFechaDeCreacion()).thenReturn(LocalDate.now());
        // when(op.getCategoria()).thenReturn("Básico");

        muestra.agregarOpinionBasico(op);

        assertEquals(
            "Usuario 1 opinó: Ninguna, en la fecha: " + LocalDate.now().toString() + ", con categoría: Básico", 
            muestra.verRegistroN(1));
    }

    @Test
    public void testCargarMuestraVerificada(){
        muestra.cargarMuestraVerificada();

        verify(baseDeMuestras).cargarMuestraVerificada(muestra);
    }
    
}
