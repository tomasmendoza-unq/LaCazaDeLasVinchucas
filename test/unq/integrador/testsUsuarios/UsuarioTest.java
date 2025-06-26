package unq.integrador.testsUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import unq.integrador.*;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.Usuario;
import unq.integrador.impls.UsuarioRango;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioTest {
	
	IUsuario usuario;
	UsuarioRango rango;
	IMuestra muestra;
	IUbicacion ubicacion;
	Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(IMuestra.class);
		ubicacion = mock(IUbicacion.class);
		rango = mock(UsuarioRango.class);
		usuario = new Usuario(10);
		usuario.setProximoRango(rango);
		opinion = mock(Opinion.class);
		when(opinion.getFechaDeCreacion()).thenReturn(LocalDate.now());
	}


	@Test
	public void usuarioOpinaSobreUnaMuestra() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
		usuario.opinarSobreUnaMuestra(muestra,opinion);
		verify(rango).opinarSobreUnaMuestra(muestra, opinion);
	}

	@Test
	public void usuarioOpinaSobreUnaMuestraQuePublicoYFalla() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
		usuario.agregarMuestraPublicada(muestra);
		assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class, () -> usuario.opinarSobreUnaMuestra(muestra,opinion));
	}

	@Test
	public void usuarioOpinaSobreUnaMuestraDosVeces() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {

	}


	@Test
	public void usuarioSubeDeRangoCuandoTieneMasDe10PublicacionesY20OpinionesEn30Dias() {
		assertFalse(usuario.subeDeRango());

		for (int i = 0; i < 10; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			when(muestraMock.getFechaCreacion()).thenReturn(LocalDate.now());
			usuario.agregarMuestraPublicada(muestraMock);
		}

		for (int i = 0; i < 20; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			assertDoesNotThrow(() -> usuario.opinarSobreUnaMuestra(muestraMock, opinion));
		}

		assertTrue(usuario.subeDeRango());

		usuario.determinarRango();

		verify(rango).determinarSiguienteRango(usuario);
	}

	@Test
	public void usuarioNoSubeDeRangoCuandoNoCumpleCondiciones() {

		for (int i = 0; i < 5; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			when(muestraMock.getFechaCreacion()).thenReturn(LocalDate.now());
			usuario.agregarMuestraPublicada(muestraMock);
		}

		for (int i = 0; i < 10; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			assertDoesNotThrow(() -> usuario.opinarSobreUnaMuestra(muestraMock, opinion));
		}

		assertFalse(usuario.subeDeRango());

		usuario.determinarRango();

	}

	@Test
	public void usuarioNoSubeDeRangoConOpinionesSuficientesPeroNoPublicaciones() {

		for (int i = 0; i < 20; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			assertDoesNotThrow(() -> usuario.opinarSobreUnaMuestra(muestraMock, opinion));
		}

		assertFalse(usuario.subeDeRango());
	}

	@Test
	public void usuarioNoSubeDeRangoConPublicacionesSuficientesPeroNoOpiniones() {

		for (int i = 0; i < 10; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			when(muestraMock.getFechaCreacion()).thenReturn(LocalDate.now());
			usuario.agregarMuestraPublicada(muestraMock);
		}

		assertFalse(usuario.subeDeRango());
	}


}
