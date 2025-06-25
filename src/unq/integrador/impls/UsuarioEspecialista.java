package unq.integrador.impls;

import unq.integrador.IUsuario;

public class UsuarioEspecialista extends UsuarioExperto{
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
