
package co.edu.udea.peliculas.servicio;

/**
 *
 * @author JuanCamilo
 */
public interface CatalogoPeliculas {
    public static final String NOMBRE_RECURSO = "catalogo-peliculas.txt";
    
    public abstract void agregarPelicula(String nombrePelicula);
    
    public abstract void listarPeliculas();
    
    public abstract void buscarPelicula(String buscar);
    
    public abstract void iniciarCatalogoPeliculas();
    
    public abstract void eliminarCatalogoPeliculas();
}
