package unq.integrador.impls;

import java.time.LocalDate;

/**
 * Clase para la creación de filtros que se utilizaran en la base de muestras
 * por los usuarios del sistema.
 * <br><br>
 * La clase tiene 4 opciones de filtros
 * <ul>
 * <li>Por tipo de muestra (Votada - Verificada)</li>
 * <li>Por fecha de creación</li>
 * <li>Por fecha de la última votación</li>
 * <li>Por el tipo de insecto detectado</li>
 * </ul>
 */
public class FiltroDeMuestrasFactory {

    /**
     * Este filtro aplicaría a las muestras que han sido verificadas
     * 
     * @return Un filtro para muestras verificadas
     */
    public FiltroMuestras crearFiltroParaMuestrasVerificadas() {
        return new FiltroMuestras(m -> m.esVerificada());
    }

    /**
     * Este filtro aplicaría a las muestras que no están verificadas, véase, las muestras votadas
     * 
     * @return Un filtro para muestras votadas
     */
    public FiltroMuestras crearFiltroParaMuestrasVotadas() {
        return new FiltroMuestras(m -> !m.esVerificada());
    }

    /**
     * Este filtro aplicaría a las muestras que se crearon después de la fecha pasada
     * 
     * @param fecha Una fecha por la que comparar la creación de la muestra
     * @return Un filtro para muestras que fueron creadas después de la fecha
     */
    public FiltroMuestras crearFiltroParaFechaDeCreacionDespuesDe(LocalDate fecha) {
        return new FiltroMuestras(m -> m.getFechaCreacion().isAfter(fecha));
    }

    /**
     * Este filtro aplicaría a las muestras que se crearon antes de la fecha pasada
     * 
     * @param fecha Una fecha por la que comparar la creación de la muestra
     * @return Un filtro para muestras que fueron creadas antes de la fecha
     */
    public FiltroMuestras crearFiltroParaFechaDeCreacionAnteriorA(LocalDate fecha) {
        return new FiltroMuestras(m -> m.getFechaCreacion().isBefore(fecha));
    }

    /**
     * Este filtro aplicaría a las muestras que se crearon en la misma fecha
     * 
     * @param fecha Una fecha por la que comparar la creación de la muestra
     * @return Un filtro para muestras que fueron creadas en la misma fecha
     */
    public FiltroMuestras crearFiltroParaFechaDeCreacionIgualA(LocalDate fecha) {
        return new FiltroMuestras(m -> m.getFechaCreacion().equals(fecha));
    }

    /**
     * Este filtro aplicaría a las muestras en las que se votó después de la fecha indicada
     * 
     * @param fecha Una fecha por la que comparar la última votación de la muestra
     * @return Un filtro para muestras que fueron votadas después de la fecha
     */
    public FiltroMuestras crearFiltroParaFechaDeUltimaVotacionDespuesDe(LocalDate fecha) {
        return new FiltroMuestras(m -> m.getFechaUltimaVotacion().isAfter(fecha));
    }

    /**
     * Este filtro aplicaría a las muestras en las que se votó antes de la fecha indicada
     * 
     * @param fecha Una fecha por la que comparar la última votación de la muestra
     * @return Un filtro para muestras que fueron votadas antes de la fecha
     */
    public FiltroMuestras crearFiltroParaFechaDeUltimaVotacionAnteriorA(LocalDate fecha) {
        return new FiltroMuestras(m -> m.getFechaUltimaVotacion().isBefore(fecha));
    }

    /**
     * Este filtro aplicaría a las muestras en las que se votó en la misma fecha indicada
     * 
     * @param fecha Una fecha por la que comparar la última votación de la muestra
     * @return Un filtro para muestras que fueron votadas en la misma fecha
     */
    public FiltroMuestras crearFiltroParaFechaDeUltimaVotacionIgualA(LocalDate fecha)  {
        return new FiltroMuestras(m -> m.getFechaUltimaVotacion().equals(fecha));
    }

    /**
     * Este filtro aplicaría a las muestras en las cuales se detectó el tipo pasado
     *  
     * @param tipo String que representa el insecto que se compara
     * @return Un filtro para muestras que el insecto detectado es el mismo
     */
    public FiltroMuestras crearFiltroParaInsectoDetectado(String tipo) {
        return new FiltroMuestras(m -> m.resultadoActual().equalsIgnoreCase(tipo));
    }


}
