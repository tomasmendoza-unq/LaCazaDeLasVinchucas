package vinchuca;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IMuestra;
import unq.integrador.IParticipanteStrategy;
import unq.integrador.Opinion;
import unq.integrador.ParticipanteBasico;
import unq.integrador.ParticipanteExperto;

public class ParticipanteStrategyTest {
	
	IParticipanteStrategy participanteExperto;
	IParticipanteStrategy participanteBasico;
	IMuestra muestra;
	Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		participanteExperto = new ParticipanteExperto();
		participanteBasico = new ParticipanteBasico();
		muestra = mock(IMuestra.class);
		opinion = Opinion.Vinchuca;
	}
	
	@Test
	public void participanteExpertoOpinaSobreUnaMuestra() {
		participanteExperto.opinarSobreUnaMuestra(muestra, opinion);
		
	}
}
