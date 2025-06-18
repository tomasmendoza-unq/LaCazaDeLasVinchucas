package unq.integrador.testsUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.*;
import unq.integrador.enums.TipoOpinion;
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
	IUbicacion ubicacion;
	Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(IMuestra.class);
		ubicacion = mock(IUbicacion.class);
		baseDeMuestras = mock(IBaseDeMuestras.class);
		rango = mock(IUsuarioRango.class);
		usuario = new Usuario(baseDeMuestras,rango, 10);
		opinion = mock(Opinion.class);
	}
	
	@Test
	public void usuarioUnaMuestra() {
		usuario.enviarMuestra("10", ubicacion);
		verify(baseDeMuestras).cargarMuestra(any(IMuestra.class));
	}
	
	@Test
	public void usuarioOpinaSobreUnaMuestra() {
		usuario.opinarSobreUnaMuestra(muestra,TipoOpinion.IMAGEN_POCO_CLARA);
		verify(rango).opinarSobreUnaMuestra(eq(muestra), argThat(op ->
				op.getTipo() == TipoOpinion.IMAGEN_POCO_CLARA &&
						op.getID() == usuario.getId()
		));
	}

	@Test
	public void usuarioOpinaSobreUnaMuestraDosVeces() {
		doNothing()
				.doThrow(new OpinionRepetidaException())
				.when(rango).opinarSobreUnaMuestra(eq(muestra), argThat(op ->
						op.getTipo() == TipoOpinion.IMAGEN_POCO_CLARA &&
								op.getID() == usuario.getId()
				));

		usuario.opinarSobreUnaMuestra(muestra, TipoOpinion.IMAGEN_POCO_CLARA);

		assertThrows(OpinionRepetidaException.class, () -> {
			usuario.opinarSobreUnaMuestra(muestra, TipoOpinion.IMAGEN_POCO_CLARA);
		});

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
			usuario.opinarSobreUnaMuestra(muestraMock, TipoOpinion.IMAGEN_POCO_CLARA);
		}

		assertTrue(usuario.subeDeRango());

		usuario.determinarRango();
	}
	@Test
	public void usuarioNoSubeDeRangoCuandoNoCumpleCondiciones() {

		for (int i = 0; i < 5; i++) {
			usuario.enviarMuestra("asd", ubicacion);
		}

		for (int i = 0; i < 10; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			usuario.opinarSobreUnaMuestra(muestraMock, TipoOpinion.IMAGEN_POCO_CLARA);
		}

		assertFalse(usuario.subeDeRango());

		usuario.determinarRango();

	}

	@Test
	public void usuarioNoSubeDeRangoConOpinionesSuficientesPeroNoPublicaciones() {

		for (int i = 0; i < 20; i++) {
			IMuestra muestraMock = mock(IMuestra.class);
			usuario.opinarSobreUnaMuestra(muestraMock, TipoOpinion.IMAGEN_POCO_CLARA);
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
