package unq.integrador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.Exceptions.OpinionRepetidaException;
import unq.integrador.Enums.TipoOpinion;
import unq.integrador.Impls.Usuario;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

}
