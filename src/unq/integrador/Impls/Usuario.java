package unq.integrador.impls;

import unq.integrador.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Usuario implements IUsuario {

	private int id;
	private IBaseDeMuestras bdm;
	private IUsuarioRango participanteState;
	private List<Opinion> opinionList;
	private List<IMuestra> publicaciones;
	
	public Usuario(IBaseDeMuestras bdm, IUsuarioRango state, int id) {
		this.bdm = bdm;
		this.participanteState = state;
		opinionList = new ArrayList<>();
		publicaciones = new ArrayList<>();
		this.id = id;
	}

	public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) {
		participanteState.opinarSobreUnaMuestra(muestra,opinion);
		opinionList.add(opinion);
    }

	public void enviarMuestra(String fotografia, String ubicacion) {
		MuestraLibre muestra = new MuestraLibre(this, fotografia, ubicacion);
		bdm.agregarMuestra(muestra);
		publicaciones.add(muestra);
	}

	public void determinarRango(){
		this.participanteState = this.subeDeRango() ? this.setExperto() : this.setBasico();
	}

	private IUsuarioRango setBasico() {
		return new UsuarioBasico();
	}

	private IUsuarioRango setExperto() {
		return new UsuarioExperto();
	}

	public boolean subeDeRango() {
		return this.opinionesNecesarias() && this.publicacionesNecesarias();
	}

	@Override
	public void setMuestraPublicada(IMuestra muestra) {
		publicaciones.add(muestra);
	}

	@Override
	public int getID() {
		return this.id;
	}

	protected boolean publicacionesNecesarias() {
		return this.publicacionesDentroDeLos30Dias().count() >=10;
	}

	protected Stream<IMuestra> publicacionesDentroDeLos30Dias() {
		return publicaciones.stream()
				.filter(iMuestra -> iMuestra.getFechaCreacion().isAfter(LocalDate.now().minusDays(30)));
	}


	protected boolean opinionesNecesarias() {
		return this.opionesHechasDentroDeLos30Dias().count() >=20;
	}

	protected Stream<Opinion> opionesHechasDentroDeLos30Dias() {
		return  opinionList.stream()
				.filter(iOpinion -> iOpinion.getFechaDeCreacion().isAfter(LocalDate.now().minusDays(30)));
	}

}
