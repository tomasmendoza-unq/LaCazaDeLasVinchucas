package unq.integrador.testsUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.*;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.Usuario;
import unq.integrador.impls.UsuarioRango;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UsuarioTest {
	
	IUsuario usuario;
	UsuarioRango rango;
	IMuestra muestra;
	IBaseDeMuestras baseDeMuestras;
	IUbicacion ubicacion;
	Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(IMuestra.class);
		ubicacion = mock(IUbicacion.class);
		baseDeMuestras = mock(IBaseDeMuestras.class);
		rango = mock(UsuarioRango.class);
		usuario = new Usuario(10, baseDeMuestras);
		usuario.setProximoRango(rango);
		opinion = mock(Opinion.class);
	}
	
	@Test
	public void usuarioUnaMuestra() {
		usuario.enviarMuestra("10", ubicacion);
		verify(baseDeMuestras).cargarMuestra(any(IMuestra.class));
	}
	
	@Test
	public void usuarioOpinaSobreUnaMuestra() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
		usuario.opinarSobreUnaMuestra(muestra,TipoOpinion.IMAGEN_POCO_CLARA);
		verify(rango).opinarSobreUnaMuestra(eq(muestra), argThat(op ->
				op.getTipo() == TipoOpinion.IMAGEN_POCO_CLARA &&
						op.getID() == usuario.getId()
		));
	}

	@Test
	public void usuarioOpinaSobreUnaMuestraQuePublicoYFalla() throws UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, SinAccesoAMuestraException {
		doThrow(new UnUsuarioNoPuedeOpinarEnSuMuestraException())
		.when(rango).opinarSobreUnaMuestra(any(IMuestra.class), any(Opinion.class));
		
		assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class,
			() -> usuario.opinarSobreUnaMuestra(muestra, null));
	}

	@Test
	public void usuarioOpinaSobreUnaMuestraDosVeces() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
		doNothing()
		.doThrow(new UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException())
		.when(rango).opinarSobreUnaMuestra(eq(muestra), 
			argThat(op ->
				op.getTipo() == TipoOpinion.IMAGEN_POCO_CLARA &&
				op.getID() == usuario.getId()
			)
		);

		usuario.opinarSobreUnaMuestra(muestra, TipoOpinion.IMAGEN_POCO_CLARA);

		assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class, () -> {
			usuario.opinarSobreUnaMuestra(muestra, TipoOpinion.IMAGEN_POCO_CLARA);
		});

		// eq -> se espera que la muestra sea la misma que envio el usuario
		// argthat -> esperamos un argumento op tal que su tipo de opinión sea IMAGEN_POCO_CLARA y su ID coincida con el del usuario."
		verify(rango, times(2)).opinarSobreUnaMuestra(eq(muestra), argThat(op ->
				op.getTipo() == TipoOpinion.IMAGEN_POCO_CLARA &&
						op.getID() == usuario.getId()
		));
	}


	@Test
	public void usuarioSubeDeRangoCuandoTieneMasDe10PublicacionesY20OpinionesEn30Dias() {
		assertFalse(usuario.subeDeRango());

		for (int i = 0; i < 10; i++) {
			usuario.enviarMuestra("asd", ubicacion);
		}

		for (int i = 0; i < 20; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			assertDoesNotThrow(() -> usuario.opinarSobreUnaMuestra(muestraMock, TipoOpinion.IMAGEN_POCO_CLARA));
		}

		assertTrue(usuario.subeDeRango());

		usuario.determinarRango();

		verify(rango).determinarSiguienteRango(usuario);
	}
	@Test
	public void usuarioNoSubeDeRangoCuandoNoCumpleCondiciones() {

		for (int i = 0; i < 5; i++) {
			usuario.enviarMuestra("asd", ubicacion);
		}

		for (int i = 0; i < 10; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			assertDoesNotThrow(() -> usuario.opinarSobreUnaMuestra(muestraMock, TipoOpinion.IMAGEN_POCO_CLARA));
		}

		assertFalse(usuario.subeDeRango());

		usuario.determinarRango();

	}

	@Test
	public void usuarioNoSubeDeRangoConOpinionesSuficientesPeroNoPublicaciones() {

		for (int i = 0; i < 20; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			assertDoesNotThrow(() -> usuario.opinarSobreUnaMuestra(muestraMock, TipoOpinion.IMAGEN_POCO_CLARA));
		}

		assertFalse(usuario.subeDeRango());
	}

	@Test
	public void usuarioNoSubeDeRangoConPublicacionesSuficientesPeroNoOpiniones() {

		for (int i = 0; i < 10; i++) {
			usuario.enviarMuestra("asd", ubicacion);
		}

		assertFalse(usuario.subeDeRango());
	}


	@Test
	public void usuarioEliminaYAgregaMuestra(){
		usuario.agregarMuestraPublicada(muestra);
		assertTrue(usuario.publicoEstaMuestra(muestra));
		usuario.quitarMuestra(muestra);
		assertFalse(usuario.publicoEstaMuestra(muestra));
	}

}
