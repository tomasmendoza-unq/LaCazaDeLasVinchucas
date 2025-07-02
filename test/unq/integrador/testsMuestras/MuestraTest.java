package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.*;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Muestra;
import unq.integrador.impls.Opinion;

public class MuestraTest {
    private IMuestra muestra;
    private IUsuario usuario1;
    private IUsuario usuario2;
    private IUsuario usuario3;
    private IUsuario owner;
    private IUbicacion ubicacion;
    private ISistema baseDeMuestras;
    private Opinion op1;
    private Opinion op2;
    private Opinion op3;


    @BeforeEach
    public void setUp() {
        ubicacion = mock(IUbicacion.class);
        op1 = mock(Opinion.class);
        op2 = mock(Opinion.class);
        op3 = mock(Opinion.class);
        usuario1 = mock(IUsuario.class);
        usuario2 = mock(IUsuario.class);
        usuario3 = mock(IUsuario.class);
        owner = mock(IUsuario.class);
        baseDeMuestras = mock(ISistema.class);
        muestra = new Muestra( owner,"Foto", ubicacion, baseDeMuestras);
        when(op1.imprimirTipo()).thenReturn("Ninguna");
        when(op1.getTipo()).thenReturn(TipoOpinion.NINGUNA);
        when(op1.getFechaDeCreacion()).thenReturn(LocalDate.now());
        when(op2.imprimirTipo()).thenReturn("Imagen poco clara");
        when(op2.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
        when(op2.getFechaDeCreacion()).thenReturn(LocalDate.now());
        when(op3.imprimirTipo()).thenReturn("Imagen poco clara");
        when(op3.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
        when(op3.getFechaDeCreacion()).thenReturn(LocalDate.now());
    }

    @Test
    public void testGetters() {
        assertEquals("Foto", muestra.getFotografia());
        assertEquals(ubicacion, muestra.getUbicacion());
        assertEquals(LocalDate.now(), muestra.getFechaCreacion());
        assertEquals(owner, muestra.getOwner());

        assertDoesNotThrow(() -> muestra.agregarOpinionBasico(usuario1,op1));
        assertEquals(op1, muestra.getHistorial().get(usuario1));

        assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());
    }

    @Test
    public void testResultadoActual() {

        // Antes de agregar la opinión no hay resultado
        assertEquals("No definido", muestra.resultadoActual());

        // Al agregar la opinión el resultado cambia
        assertDoesNotThrow(() -> muestra.agregarOpinionBasico(usuario1,op1));
        assertEquals("Ninguna", muestra.resultadoActual());


        // Al agregar más opiniones de otro tipo el resultado vuelve a cambiar
         assertDoesNotThrow(() -> muestra.agregarOpinionBasico(usuario2,op2));
         assertDoesNotThrow(() -> muestra.agregarOpinionBasico(usuario3,op3));
        assertEquals("Imagen poco clara", muestra.resultadoActual());


    }

    @Test
    public void muestraRecibeUnaOpinionDelQueLaPublico(){
        assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class, () -> muestra.agregarOpinionBasico(owner,op1));
        assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class, () -> muestra.agregarOpinionExperto(owner,op1));
    }

    @Test
    public void muestraRecibeUnaOpinionDeUnUsuarioBasicoQueYaOpinio(){
        assertDoesNotThrow(() -> muestra.agregarOpinionBasico(usuario1,op1));
        assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class, () -> muestra.agregarOpinionBasico(usuario1,op1));
    }

    @Test
    public void muestraRecibeUnaOpinionDeUnUsuarioExpertoQueYaOpinio(){
        assertDoesNotThrow(() -> muestra.agregarOpinionExperto(usuario1,op1));
        assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class, () -> muestra.agregarOpinionExperto(usuario1,op1));
    }

    @Test
    public void testCargarMuestraVerificada() {
        muestra.cargarMuestraVerificada();

        verify(baseDeMuestras).notificarVerificacion(muestra);
    }

    @Test
    public void testLaMuestraNoEstaVerificada() {
        assertFalse(muestra.esVerificada());
    }

    @Test
    public void testLaMuestraEstaVerificada() {
        assertDoesNotThrow(() -> muestra.agregarOpinionExperto(usuario1,op3));
        assertDoesNotThrow(() -> muestra.agregarOpinionExperto(usuario2,op2));

        assertTrue(muestra.esVerificada());
    }
}
