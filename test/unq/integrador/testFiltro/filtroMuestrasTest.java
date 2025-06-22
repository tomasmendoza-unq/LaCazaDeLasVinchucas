package unq.integrador.testFiltro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.IUsuarioRango;
import unq.integrador.enums.Lapso;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.impls.BaseDeMuestras;
import unq.integrador.impls.FiltroMuestras;
import unq.integrador.impls.Usuario;
import unq.integrador.impls.UsuarioBasico;

public class filtroMuestrasTest {
    IUsuario user;
    IBaseDeMuestras bdm;
    IUsuarioRango rango;

    FiltroMuestras f1;
    FiltroMuestras f2;
    FiltroMuestras f3;

    IMuestra muestra1;
    IMuestra muestra2;
    IMuestra muestra3;
    IMuestra muestra4;
    IMuestra muestra5;
    IMuestra muestra6;

    List<IMuestra> muestras;

    @BeforeEach
    public void setUp() {

        bdm = new BaseDeMuestras();
        rango = new UsuarioBasico();
        user = new Usuario(0, rango, bdm);

        muestra1 = mock(IMuestra.class);
        when(muestra1.resultadoActual()).thenReturn("Ninguna");
        when(muestra1.getIDUsuario()).thenReturn(1);
        when(muestra1.getFechaCreacion()).thenReturn(LocalDate.of(2020, 5, 15));
        when(muestra1.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2024, 6, 20));
        when(muestra1.esVerificada()).thenReturn(false);

        muestra2 = mock(IMuestra.class);
        when(muestra2.resultadoActual()).thenReturn("Vinchuca Sordida");
        when(muestra2.getIDUsuario()).thenReturn(2);
        when(muestra2.getFechaCreacion()).thenReturn(LocalDate.of(2020, 6, 1));
        when(muestra2.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2023, 6, 20));
        when(muestra2.esVerificada()).thenReturn(false);

        muestra3 = mock(IMuestra.class);
        when(muestra3.resultadoActual()).thenReturn("Ninguna");
        when(muestra3.getIDUsuario()).thenReturn(3);
        when(muestra3.getFechaCreacion()).thenReturn(LocalDate.of(2018, 1, 1));
        when(muestra3.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 6, 20));
        when(muestra3.esVerificada()).thenReturn(true);

        muestra4 = mock(IMuestra.class);
        when(muestra4.resultadoActual()).thenReturn("Vinchuca Sordida");
        when(muestra4.getIDUsuario()).thenReturn(4);
        when(muestra4.getFechaCreacion()).thenReturn(LocalDate.of(2022, 5, 15));
        when(muestra4.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2021, 6, 20));
        when(muestra4.esVerificada()).thenReturn(true);

        muestra5 = mock(IMuestra.class);
        when(muestra5.resultadoActual()).thenReturn("Vinchuca Sordida");
        when(muestra5.getIDUsuario()).thenReturn(5);
        when(muestra5.getFechaCreacion()).thenReturn(LocalDate.of(2023, 5, 15));
        when(muestra5.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2020, 6, 20));
        when(muestra5.esVerificada()).thenReturn(false);

        muestra6 = mock(IMuestra.class);
        when(muestra6.resultadoActual()).thenReturn("Vinchuca Sordida");
        when(muestra6.getIDUsuario()).thenReturn(6);
        when(muestra6.getFechaCreacion()).thenReturn(LocalDate.of(2024, 5, 15));
        when(muestra6.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2020, 6, 20));
        when(muestra6.esVerificada()).thenReturn(false);

        bdm.cargarMuestra(muestra1);
        bdm.cargarMuestra(muestra2);
        bdm.cargarMuestra(muestra3);
        bdm.cargarMuestra(muestra4);
        bdm.cargarMuestra(muestra5);
        bdm.cargarMuestra(muestra6);

    }

    // Filtro de tipo de insecto
    @Test
    public void testFiltrarPorTipoDeInsecto() {
        f1 = user.crearFiltroParaInsectoDetectado(TipoOpinion.NINGUNA);
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras deberían haber quedado 2
        assertEquals(2, muestras.size());

        // Se comprueba que los elementos sean las muestras 1 y 3
        assertEquals(1, muestras.get(0).getIDUsuario());
        assertEquals(3, muestras.get(1).getIDUsuario());
        
        assertTrue(f1.test(muestra1));
        assertFalse(f1.test(muestra2));
        assertTrue(f1.test(muestra3));
        assertFalse(f1.test(muestra4));
        assertFalse(f1.test(muestra5));
        assertFalse(f1.test(muestra6));
    }

    // Filtros de fecha de creación
    @Test
    public void testFiltrarPorFechaDeCreacionCreadasDespuesDe() {
        f1 = user.crearFiltroParaFechaDeCreacion(Lapso.DESPUES, LocalDate.of(2020, 6, 15));
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras deberían haber quedado 3
        assertEquals(3, muestras.size());

        // Se comprueba que los elementos sean las muestras 4, 5 y 6
        assertEquals(4, muestras.get(0).getIDUsuario());
        assertEquals(5, muestras.get(1).getIDUsuario());
        assertEquals(6, muestras.get(2).getIDUsuario());

    }

    @Test
    public void testFiltrarPorFechaDeCreacionIguales() {
        f1 = user.crearFiltroParaFechaDeCreacion(Lapso.IGUAL, LocalDate.of(2023, 5, 15));
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras debería haber quedado 1
        assertEquals(1, muestras.size());

        // Se comprueba que los elementos sea únicamente la muestra 5
        assertEquals(5, muestras.get(0).getIDUsuario());
    }

    @Test
    public void testFiltrarPorFechaDeCreacionAnterioresDe() {
        f1 = user.crearFiltroParaFechaDeCreacion(Lapso.ANTES, LocalDate.of(2020, 1, 1));
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras debería haber quedado 1
        assertEquals(1, muestras.size());

        // Se comprueba que los elementos sea únicamente la muestra 3
        assertEquals(3, muestras.get(0).getIDUsuario());
    }

    // Filtro de fecha del último voto
    @Test
    void testFiltrarPorFechaDeUltimoVotoDespuesDe() {
        f1 = user.crearFiltroParaFechaDeUltimaVotacion(Lapso.DESPUES, LocalDate.of(2023, 1, 1));
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras deberían haber quedado 2
        assertEquals(2, muestras.size());

        // Se comprueba que los elementos sea únicamente las muestras 1 y 2
        assertEquals(1, muestras.get(0).getIDUsuario());
        assertEquals(2, muestras.get(1).getIDUsuario());
    }

    @Test
    void testFiltrarPorFechaDeUltimoVotoIgual() {
        f1 = user.crearFiltroParaFechaDeUltimaVotacion(Lapso.IGUAL, LocalDate.of(2020, 6, 20));
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras deberían haber quedado 2
        assertEquals(2, muestras.size());

        // Se comprueba que los elementos sea únicamente las muestras 5 y 6
        assertEquals(5, muestras.get(0).getIDUsuario());
        assertEquals(6, muestras.get(1).getIDUsuario());
    }

    @Test
    void testFiltrarPorFechaDeUltimoVotoAntesDe() {
        f1 = user.crearFiltroParaFechaDeUltimaVotacion(Lapso.ANTES, LocalDate.of(2022, 1, 1));
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras deberían haber quedado 2
        assertEquals(3, muestras.size());

        // Se comprueba que los elementos sea únicamente las muestras 4, 5 y 6
        assertEquals(4, muestras.get(0).getIDUsuario());
        assertEquals(5, muestras.get(1).getIDUsuario());
        assertEquals(6, muestras.get(2).getIDUsuario());
    }

    // Filtro del nivel de verificación
    @Test
    public void testFiltrarPorNivelDeVerificacionVotado() {
        f1 = user.crearFiltroParaNivelDeVerificacion(false);
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras deberían haber quedado 4
        assertEquals(4, muestras.size());

        // Se comprueba que los elementos sea únicamente las muestras 1, 2, 5 y 6
        assertEquals(1, muestras.get(0).getIDUsuario());
        assertEquals(2, muestras.get(1).getIDUsuario());
        assertEquals(5, muestras.get(2).getIDUsuario());
        assertEquals(6, muestras.get(3).getIDUsuario());
    }

    @Test
    public void testFiltrarPorNivelDeVerificacionVerificado() {
        f1 = user.crearFiltroParaNivelDeVerificacion(true);
        muestras = user.buscarMuestra(f1);

        // De las 6 muestras deberían haber quedado 2
        assertEquals(2, muestras.size());

        // Se comprueba que los elementos sea únicamente las muestras 3 y 4
        assertEquals(3, muestras.get(0).getIDUsuario());
        assertEquals(4, muestras.get(1).getIDUsuario());
    }

    // Operaciones lógicas OR y AND
    @Test
    public void testORSobreFiltros() {
        f1 = user.crearFiltroParaInsectoDetectado(TipoOpinion.NINGUNA);
        f2 = user.crearFiltroParaFechaDeCreacion(Lapso.DESPUES, LocalDate.of(2024, 1, 1));
        FiltroMuestras filtroFinal = f1.or(f2);
        muestras = user.buscarMuestra(filtroFinal);

        // De las 6 muestras deberían haber quedado 3
        assertEquals(3, muestras.size());

        // Se comprueba que los elementos sea únicamente las muestras 1, 3 y 6
        assertEquals(1, muestras.get(0).getIDUsuario());
        assertEquals(3, muestras.get(1).getIDUsuario());
        assertEquals(6, muestras.get(2).getIDUsuario());
    }

    @Test
    public void testANDSobreFiltros() {
        f1 = user.crearFiltroParaNivelDeVerificacion(false);
        f2 = user.crearFiltroParaFechaDeUltimaVotacion(Lapso.IGUAL, LocalDate.of(2020, 6, 20));
        FiltroMuestras filtroFinal = f1.and(f2);
        muestras = user.buscarMuestra(filtroFinal);

        // De las 6 muestras deberían haber quedado 2
        assertEquals(2, muestras.size());

        // Se comprueba que los elementos sea únicamente las muestras 5 y 6
        assertEquals(5, muestras.get(0).getIDUsuario());
        assertEquals(6, muestras.get(1).getIDUsuario());
    }

    @Test
    public void testAnidandoOperadores() {
        f1 = user.crearFiltroParaInsectoDetectado(TipoOpinion.VINCHUCA_SORDIDA);
        f2 = user.crearFiltroParaFechaDeUltimaVotacion(Lapso.ANTES, LocalDate.of(2019, 4, 20));
        f3 = user.crearFiltroParaNivelDeVerificacion(true);
        FiltroMuestras filtroFinal = f1.and(f3.or(f2));

        muestras = user.buscarMuestra(filtroFinal);

        // De las 6 muestras debería haber quedado 1
        assertEquals(1, muestras.size());

        // Se comprueba que los elementos sea únicamente la muestra 4
        assertEquals(4, muestras.get(0).getIDUsuario());
    }
}
