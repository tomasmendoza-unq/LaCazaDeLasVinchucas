package unq.integrador.impls.usuario;

import unq.integrador.IUsuario;

/*
 * Clase que representa a los usuarios que son especialistas
 * aquellos que nunca cambian su rango dentro de la aplicación
 */
public class UsuarioEspecialista extends UsuarioExperto {
    
    /**
     * Método para que un usuario determine su proximo rango
     *
     * @param usuario Usuario al cual se le modifica el rango
     * @return rango para settear al usuario dado
     */
    @Override
    protected UsuarioRango determinarRango(IUsuario usuario) {
        return this;
    }
}
