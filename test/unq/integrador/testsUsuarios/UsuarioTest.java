package unq.integrador.testsUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.*;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.usuario.Usuario;
import unq.integrador.impls.usuario.UsuarioRango;

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
		usuario = new Usuario(rango);
		usuario.setProximoRango(rango);
		opinion = mock(Opinion.class);
		when(opinion.getFechaDeCreacion()).thenReturn(LocalDate.now());
	}


	@Test
	public void usuarioOpinaSobreUnaMuestra() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
		usuario.opinarSobreUnaMuestra(muestra,opinion);
		verify(rango).opinarSobreUnaMuestra(muestra, opinion, usuario);
	}

	@Test
	public void usuarioOpinaSobreUnaMuestraQuePublicoYFalla() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
		doThrow(new UnUsuarioNoPuedeOpinarEnSuMuestraException())
		.when(rango).opinarSobreUnaMuestra(muestra, opinion, usuario);

		usuario.agregarMuestraPublicada(muestra);

		assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class, 
			() -> usuario.opinarSobreUnaMuestra(muestra, opinion));
	}
	@Test
	public void usuarioOpinaSobreUnaMuestraQueYaVoto() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
		doThrow(new UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException())
		.when(rango).opinarSobreUnaMuestra(muestra, opinion, usuario);
		
		usuario.agregarMuestraPublicada(muestra);
		
		assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class,
			() -> usuario.opinarSobreUnaMuestra(muestra, opinion));
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
