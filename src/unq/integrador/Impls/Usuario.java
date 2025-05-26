package unq.integrador.Impls;

import unq.integrador.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public  class Usuario implements IUsuario {

	private IBaseDeMuestras bdm;
	private IUsuarioRango participanteState;
	private List<IOpinion> opinionList;
	private List<IMuestra> publicaciones;
	private LocalDate fechaDeCreacion;
	
	public Usuario(IBaseDeMuestras bdm, IUsuarioRango state, LocalDate fechaDeCreacion) {
		this.bdm = bdm;
		this.participanteState = state;
		this.fechaDeCreacion = fechaDeCreacion;
		opinionList = new ArrayList<>();
		publicaciones = new ArrayList<>();
	}

	public void opinarSobreUnaMuestra(IMuestra muestra, IOpinion opinion) {
           participanteState.opinarSobreUnaMuestra(muestra,opinion);
    }

	public void enviarMuestra(IMuestra muesta) {
		bdm.agregarMuestra(muesta);
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

	private boolean subeDeRango() {
		return this.opionesNecesarias() && this.publicacionesNecesarias();
	}

	private boolean publicacionesNecesarias() {
		return this.publicacionesDentroDeLos30Dias().count() >=10;
	}

	private Stream<IMuestra> publicacionesDentroDeLos30Dias() {
		return publicaciones.stream()
				.filter(iMuestra -> iMuestra.getFecha().isAfter(LocalDate.now().minusDays(30)));
	}


	private boolean opionesNecesarias() {
		return this.opionesHechasDentroDeLos30Dias().count() >=20;
	}

	private Stream<IOpinion> opionesHechasDentroDeLos30Dias() {
		return  opinionList.stream()
				.filter(iOpinion -> iOpinion.getFecha().isAfter(LocalDate.now().minusDays(30)));
	}

}
