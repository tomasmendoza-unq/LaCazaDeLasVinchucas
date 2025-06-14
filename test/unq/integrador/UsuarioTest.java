package unq.integrador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.Exceptions.OpinionRepetidaException;
import unq.integrador.Enums.TipoOpinion;
import unq.integrador.Impls.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioTest {
	
	IUsuario usuario;
	IUsuarioRango rango;
	IMuestra muestra;
	IBaseDeMuestras baseDeMuestras;
	IOpinion opinion;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(IMuestra.class);
		baseDeMuestras = mock(IBaseDeMuestras.class);
		rango = mock(IUsuarioRango.class);
		usuario = new Usuario(baseDeMuestras,rango, LocalDate.of(2003, 12 , 03));
		opinion = mock(IOpinion.class);
	}
	
	@Test
	public void usuarioUnaMuestra() {
		usuario.enviarMuestra(muestra);
		verify(baseDeMuestras).agregarMuestra(muestra);
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
			IMuestra muestra = mock(IMuestra.class);
			when(muestra.getFecha()).thenReturn(LocalDate.now().minusDays(5));
			usuario.enviarMuestra(muestra);
		}

		for (int i = 0; i < 20; i++) {
			IOpinion opinion = mock(IOpinion.class);
			when(opinion.getFecha()).thenReturn(LocalDate.now().minusDays(3));
			usuario.opinarSobreUnaMuestra(muestra, opinion);
		}

		assertTrue(usuario.subeDeRango());

		usuario.determinarRango();
	}

	@Test
	public void usuarioNoSubeDeRangoCuandoNoCumpleCondiciones() {

		for (int i = 0; i < 5; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			when(muestraMock.getFecha()).thenReturn(LocalDate.now().minusDays(5));
			usuario.enviarMuestra(muestraMock);
		}

		for (int i = 0; i < 10; i++) {
			IOpinion opinionMock = mock(IOpinion.class);
			when(opinionMock.getFecha()).thenReturn(LocalDate.now().minusDays(5));
			usuario.opinarSobreUnaMuestra(mock(IMuestra.class), opinionMock);
		}

		assertFalse(usuario.subeDeRango());

		usuario.determinarRango();

	}

	@Test
	public void usuarioNoSubeDeRangoConOpinionesSuficientesPeroNoPublicaciones() {

		for (int i = 0; i < 20; i++) {
			IOpinion opinionMock = mock(IOpinion.class);
			when(opinionMock.getFecha()).thenReturn(LocalDate.now().minusDays(5));
			usuario.opinarSobreUnaMuestra(mock(IMuestra.class), opinionMock);
		}

		assertFalse(usuario.subeDeRango());
	}

	@Test
	public void usuarioNoSubeDeRangoConPublicacionesSuficientesPeroNoOpiniones() {

		for (int i = 0; i < 10; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			when(muestraMock.getFecha()).thenReturn(LocalDate.now().minusDays(5));
			usuario.enviarMuestra(muestraMock);
		}

		assertFalse(usuario.subeDeRango());
	}

}
