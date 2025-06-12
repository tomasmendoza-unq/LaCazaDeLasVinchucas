package unq.integrador;

/**
 * Esta clase representa las muestras en las que solo pueden opinar expertos.
 * Además, si opinaron expertos en esta clase, ninguno tiene más de 1 coincidencia en la
 * misma opinión.
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */
public class MuestraExperto extends Muestra {

    /**
     * Constructor de la clase MuestraExperto
     * @param user usuario que publicó la muestra
     * @param fotografia fotografía del usuario que publicó la muestra
     * @param ubicacion ubicación del usuario que publicó la muestra
     */
    public MuestraExperto(IUsuario user, String fotografia, String ubicacion) {
        super(user, fotografia, ubicacion);
    }

    /**
     * Método para conseguir la opinión con más votos
     *
     * @return retorna el resultado actual que sería la clave del diccionario con el valor más alto
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
     * Agregua una opinión al diccionario de opiniones,
     * Si alguna opinión tiene 2 votos, significa que la votaron 2 expertos
     * por ende, la muestra queda verificada y cambia por MuestraVerificada.
     * 
     * @param op una opinión para agregar a la lista
     */
    @Override
    public void agregarOpinionExperto(Opinion op) {
        this.opiniones.put(op.getTipo(), this.opiniones.getOrDefault(op.getTipo(), 0) + 1);
        
        if (this.opiniones.get(op.getTipo()) == 2) {
            this.user.setMuestraPublicada(new MuestraVerificada(user, fotografia, ubicacion, op.getTipo()));
        }
    }

    /**
     * Lanza una excepción porque los usuarios básicos no pueden
     * opinar en las muestras que ya opinaron expertos.
     * 
     * @param op una opinión que no le sucede nada.
     */
    @Override
    public void agregarOpinionBasico(Opinion op) {
        throw new SinAccesoAMuestraException("Un usuario básico no puede opinar en una muestra con opiniones de expertos");
    }
}
