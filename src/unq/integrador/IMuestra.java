package unq.integrador;

import java.time.LocalDate;

public interface IMuestra {
	
	void agregarOpinionBasico(IOpinion opinion);

	void agregarOpinionExperto(IOpinion opinion);

	LocalDate getFecha();
}
