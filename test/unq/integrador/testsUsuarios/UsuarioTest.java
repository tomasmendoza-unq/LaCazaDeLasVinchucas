package unq.integrador.testsUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.IUsuarioRango;
import unq.integrador.error.OpinionRepetidaException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.Usuario;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioTest {
	
	IUsuario usuario;
	IUsuarioRango rango;
	IMuestra muestra;
	IBaseDeMuestras baseDeMuestras;
	Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(IMuestra.class);
		baseDeMuestras = mock(IBaseDeMuestras.class);
		rango = mock(IUsuarioRango.class);
		usuario = new Usuario(baseDeMuestras,rango, 10);
		opinion = mock(Opinion.class);
	}
	
	@Test
	public void usuarioUnaMuestra() {
		usuario.enviarMuestra("10", "22");
		verify(baseDeMuestras).agregarMuestra(any(IMuestra.class));
	}
	
	@Test
	public void usuarioOpinaSobreUnaMuestra() {
		usuario.opinarSobreUnaMuestra(muestra,opinion);
		verify(rango).opinarSobreUnaMuestra(muestra,opinion);
	}

	@Test
	public void usuarioOpinaSobreUnaMuestraDosVeces() {
		doNothing()
				.doThrow(new OpinionRepetidaException())
				.when(rango).opinarSobreUnaMuestra(muestra,opinion);

		usuario.opinarSobreUnaMuestra(muestra,opinion);

		assertThrows(OpinionRepetidaException.class, () -> {
			usuario.opinarSobreUnaMuestra(muestra,opinion);
		});

		verify(rango, times(2)).opinarSobreUnaMuestra(muestra,opinion);

	}

	@Test
	public void usuarioSubeDeRangoCuandoTieneMasDe10PublicacionesY20OpinionesEn30Dias() {

		assertFalse(usuario.subeDeRango());

		for (int i = 0; i < 10; i++) {
			usuario.enviarMuestra("asd", "22");
		}

		for (int i = 0; i < 20; i++) {
			Opinion opinion = mock(Opinion.class);
			IMuestra muestraMock = mock(IMuestra.class);
			when(opinion.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(3));
			usuario.opinarSobreUnaMuestra(muestraMock, opinion);
		}

		assertTrue(usuario.subeDeRango());

		usuario.determinarRango();
	}
	@Test
	public void usuarioNoSubeDeRangoCuandoNoCumpleCondiciones() {

		for (int i = 0; i < 5; i++) {
			usuario.enviarMuestra("asd", "22");
		}

		for (int i = 0; i < 10; i++) {
			Opinion opinion = mock(Opinion.class);
			IMuestra muestraMock = mock(IMuestra.class);
			when(opinion.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(3));
			usuario.opinarSobreUnaMuestra(muestraMock, opinion);
		}

		assertFalse(usuario.subeDeRango());

		usuario.determinarRango();

	}

	@Test
	public void usuarioNoSubeDeRangoConOpinionesSuficientesPeroNoPublicaciones() {

		for (int i = 0; i < 20; i++) {
			Opinion opinion = mock(Opinion.class);
			IMuestra muestraMock = mock(IMuestra.class);
			when(opinion.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(3));
			usuario.opinarSobreUnaMuestra(muestraMock, opinion);
		}

		assertFalse(usuario.subeDeRango());
	}

	@Test
	public void usuarioNoSubeDeRangoConPublicacionesSuficientesPeroNoOpiniones() {

		for (int i = 0; i < 10; i++) {
			usuario.enviarMuestra("asd", "22");
		}

		assertFalse(usuario.subeDeRango());
	}

	@Test
	public void usuarioObtieneSuId(){
		assertEquals(10, usuario.getID());
	}

	@Test
	public void usuarioEliminaYAgregaMuestra(){
		usuario.agregarMuestraPublicada(muestra);
		assertTrue(usuario.publicoEstaMuestra(muestra));
		usuario.quitarMuestra(muestra);
		assertFalse(usuario.publicoEstaMuestra(muestra));
	}

}
