package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.*;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.impls.Muestra;
import unq.integrador.impls.Opinion;

public class MuestraTest {
    private IMuestra muestra;
    private IUbicacion ubicacion;
    private IBaseDeMuestras baseDeMuestras;

    @BeforeEach
    public void setUp() {
        ubicacion = mock(IUbicacion.class);
        baseDeMuestras = mock(IBaseDeMuestras.class);
        muestra = new Muestra(1, "Foto", ubicacion, baseDeMuestras);
    }

    @Test
    public void testGetters() {
        Opinion op = mock(Opinion.class);
        when(op.getFechaDeCreacion()).thenReturn(LocalDate.now());

        assertEquals(1, muestra.getIDUsuario());
        assertEquals("Foto", muestra.getFotografia());
        assertEquals(ubicacion, muestra.getUbicacion());
        assertEquals(LocalDate.now(), muestra.getFechaCreacion());

        muestra.agregarOpinionBasico(op);
        
        assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());
    }

    @Test
    public void testResultadoActual() {
        // Mock de la opinion
        Opinion op = mock(Opinion.class);
        when(op.imprimirTipo()).thenReturn("Ninguna");
        when(op.getTipo()).thenReturn(TipoOpinion.NINGUNA);
        when(op.getFechaDeCreacion()).thenReturn(LocalDate.now());

        // Antes de agregar la opinión no hay resultado
        assertEquals("No definido", muestra.resultadoActual());

        // Al agregar la opinión el resultado cambia
        muestra.agregarOpinionBasico(op);
        assertEquals("Ninguna", muestra.resultadoActual());

        // Se cambia el tipo de la opinión
        when(op.imprimirTipo()).thenReturn("Imagen poco clara");
        when(op.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);

        // Al agregar más opiniones de otro tipo el resultado vuelve a cambiar
        muestra.agregarOpinionBasico(op);
        muestra.agregarOpinionBasico(op);
        assertEquals("Imagen poco clara", muestra.resultadoActual());


    }

    @Test
    public void testAgregarAlHistorial() {
        Opinion op = mock(Opinion.class);
        when(op.getID()).thenReturn(1);
        when(op.imprimirTipo()).thenReturn("Ninguna");
        when(op.getFechaDeCreacion()).thenReturn(LocalDate.now());
        muestra.agregarOpinionBasico(op);

        assertEquals(
            "Usuario 1 opinó: Ninguna, en la fecha: " + LocalDate.now().toString() + ", con categoría: Básico", 
            muestra.verRegistroNro(1));
        
        muestra.agregarOpinionExperto(op);

        assertEquals(
            "Usuario 1 opinó: Ninguna, en la fecha: " + LocalDate.now().toString() + ", con categoría: Experto", 
            muestra.verRegistroNro(2));
    }

    @Test
    public void testCargarMuestraVerificada() {
        muestra.cargarMuestraVerificada();

        verify(baseDeMuestras).cargarMuestraVerificada(muestra);
    }

    @Test
    public void testLaMuestraNoEstaVerificada() {
        assertFalse(muestra.esVerificada());
    }

    @Test
    public void testLaMuestraEstaVerificada() {
        Opinion op = mock(Opinion.class);
        when(op.getFechaDeCreacion()).thenReturn(LocalDate.now());
        muestra.agregarOpinionExperto(op);
        muestra.agregarOpinionExperto(op);

        assertTrue(muestra.esVerificada());
    }
}
