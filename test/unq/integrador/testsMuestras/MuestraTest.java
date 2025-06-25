package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.*;
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
    private Opinion op1;
    private Opinion op2;
    private Opinion op3;


    @BeforeEach
    public void setUp() {
        ubicacion = mock(IUbicacion.class);
        op1 = mock(Opinion.class);
        op2 = mock(Opinion.class);
        op3 = mock(Opinion.class);
        baseDeMuestras = mock(IBaseDeMuestras.class);
        muestra = new Muestra(1, "Foto", ubicacion, baseDeMuestras);
        when(op1.getID()).thenReturn(40);
        when(op1.imprimirTipo()).thenReturn("Ninguna");
        when(op1.getTipo()).thenReturn(TipoOpinion.NINGUNA);
        when(op1.getFechaDeCreacion()).thenReturn(LocalDate.now());
        when(op2.getID()).thenReturn(20);
        when(op2.imprimirTipo()).thenReturn("Imagen poco clara");
        when(op2.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
        when(op2.getFechaDeCreacion()).thenReturn(LocalDate.now());
        when(op3.getID()).thenReturn(10);
        when(op3.imprimirTipo()).thenReturn("Imagen poco clara");
        when(op3.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
        when(op3.getFechaDeCreacion()).thenReturn(LocalDate.now());
    }

    @Test
    public void testGetters() {
        assertEquals(1, muestra.getIDUsuario());
        assertEquals("Foto", muestra.getFotografia());
        assertEquals(ubicacion, muestra.getUbicacion());
        assertEquals(LocalDate.now(), muestra.getFechaCreacion());

         assertDoesNotThrow(() -> muestra.agregarOpinionBasico(op1));
        
        assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());
    }

    @Test
    public void testResultadoActual() {

        // Antes de agregar la opinión no hay resultado
        assertEquals("No definido", muestra.resultadoActual());

        // Al agregar la opinión el resultado cambia
        assertDoesNotThrow(() -> muestra.agregarOpinionBasico(op1));
        assertEquals("Ninguna", muestra.resultadoActual());


        // Al agregar más opiniones de otro tipo el resultado vuelve a cambiar
         assertDoesNotThrow(() -> muestra.agregarOpinionBasico(op2));
         assertDoesNotThrow(() -> muestra.agregarOpinionBasico(op3));
        assertEquals("Imagen poco clara", muestra.resultadoActual());


    }

    @Test
    public void testAgregarAlHistorial() {
        assertDoesNotThrow(() -> muestra.agregarOpinionBasico(op1));

        assertEquals(
            "Usuario 40 opinó: Ninguna, en la fecha: " + LocalDate.now().toString() + ", con categoría: Básico",
            muestra.verRegistroNro(1));
        
        assertDoesNotThrow(() -> muestra.agregarOpinionExperto(op2));

        assertEquals(
            "Usuario 20 opinó: Imagen poco clara, en la fecha: " + LocalDate.now().toString() + ", con categoría: Experto",
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
        assertDoesNotThrow(() -> muestra.agregarOpinionExperto(op3));
        assertDoesNotThrow(() -> muestra.agregarOpinionExperto(op2));

        assertTrue(muestra.esVerificada());
    }
}
