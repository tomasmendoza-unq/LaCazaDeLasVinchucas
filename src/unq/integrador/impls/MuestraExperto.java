package unq.integrador.impls;

import java.util.HashMap;

import unq.integrador.*;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.SinAccesoAMuestraException;

/**
 * Esta clase representa el estado de las muestras en las que solo pueden
 * opinar usuarios de categoría 'Experto'.
 * 
 * Además, si dos expertos coinciden en voto en la muestra, esta cambiará a estar verificada.
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */
public class MuestraExperto implements IEstadoDeMuestra {
    private HashMap<TipoOpinion, Integer> opiniones;
    private IMuestra muestra;

    /**
     * Constructor de la clase MuestraExperto
     * @param muestra Una muestra a la que se le retornará el resultado de opiniones y
     * cambiará el estado según lo amerite.
     */
    public MuestraExperto(IMuestra muestra) {
        this.opiniones = new HashMap<>();
        this.muestra = muestra;
    }

    /**
     * Método para conseguir la opinión con más votos
     *
     * @return Un String con el resultado actual que sería 
     * la clave del diccionario con el valor más alto
     */
    @Override
    public String resultadoActual() {
        
        if(this.opiniones.values().size() > 1) {
            return "No definido";
        } else {
            TipoOpinion resultado = null;

            for (TipoOpinion op : this.opiniones.keySet()) {
                resultado = op;
            }

            return resultado.imprimirTipo();
        }
    }

    /**
     * Agregua una opinión al diccionario de opiniones.
     * 
     * Si alguna opinión tiene 2 votos, significa que la votaron 2 expertos
     * por ende, la muestra queda verificada y cambia por MuestraVerificada.
     * 
     * @param op una opinión para agregar a la muestra
     */
    @Override
    public void agregarOpinionExperto(Opinion op) {
        TipoOpinion tipo = op.getTipo();
        this.opiniones.put(tipo, this.opiniones.getOrDefault(tipo, 0) + 1);

        if (this.opiniones.get(tipo) == 2) {            
            MuestraVerificada muestraNueva = new MuestraVerificada(tipo);
            this.muestra.setEstado(muestraNueva);
        }
    }

    /**
     * Lanza una excepción porque los usuarios básicos no pueden
     * opinar en las muestras que ya opinaron expertos.
     * 
     * @param op una opinión.
     */
    @Override
    public void agregarOpinionBasico(Opinion op) {
        throw new SinAccesoAMuestraException("Un usuario básico no puede opinar en una muestra con opiniones de expertos");
    }

    /**
     * Método para indicar que la muestra no está verificada
     * 
     * @return False porque la muestra sigue siendo votada 
     */
    @Override
    public boolean esVerificada() {
        return false;
    }
}
