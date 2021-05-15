
package co.edu.udea.peliculas.datos;

import co.edu.udea.peliculas.dominio.Pelicula;
import co.edu.udea.peliculas.excepciones.*;
import java.util.List;

/**
 *
 * @author JuanCamiloC
 */
public interface AccesoDatos {
    public abstract boolean existeCatalogo(String nombreRecurso) throws AccesoDatosEx;

    public abstract List<Pelicula> listarPeliculas(String nombreRecurso) throws LecturaDatosEx;

    public abstract void agregarPelicula(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx;

    public abstract String buscarPelicula(String nombreRecurso, String buscar) throws LecturaDatosEx;

    public abstract void crearCatalogo(String nombreRecurso) throws AccesoDatosEx;

    public abstract void borrarCatalogo(String nombreRecurso) throws AccesoDatosEx;
}
