package vinchuca;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IParticipante;
import unq.integrador.IParticipanteStrategy;
import unq.integrador.Opinion;
import unq.integrador.Participante;

public class ParticipanteTest {
	
	IParticipante participante;
	IParticipanteStrategy iParticipanteStrategy;
	IMuestra muestra;
	IBaseDeMuestras baseDeMuestras;
	Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(IMuestra.class);
		baseDeMuestras = mock(IBaseDeMuestras.class);
		iParticipanteStrategy = mock(IParticipanteStrategy.class);
		participante = new Participante(baseDeMuestras,iParticipanteStrategy);
		opinion = Opinion.Vinchuca;
	}
	
	@Test
	public void participanteEnviaUnaMuestra() {
		participante.enviarMuestra(muestra);
		verify(baseDeMuestras).agregarMuestra(muestra);
	}
	
	@Test
	public void participanteOpinaSobreUnaMuestra() {
		participante.opinarSobreUnaMuestra(muestra, opinion);
		verify(iParticipanteStrategy).opinarSobreUnaMuestra(muestra, opinion);
	}
}
