package unq.integrador;

public  class Participante implements IParticipante{

	private IBaseDeMuestras bdm;
	private IParticipanteStrategy participanteStrategy;
	
	public Participante(IBaseDeMuestras bdm, IParticipanteStrategy strategy) {
		this.bdm = bdm;
		this.participanteStrategy = strategy;
	}
	

	public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) {
		participanteStrategy.opinarSobreUnaMuestra(muestra, opinion);
	}

	public void enviarMuestra(IMuestra muesta) {
		bdm.agregarMuestra(muesta);
	}
	
}
