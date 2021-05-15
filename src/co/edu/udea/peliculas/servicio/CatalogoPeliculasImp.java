
package co.edu.udea.peliculas.servicio;

import co.edu.udea.peliculas.datos.*;
import co.edu.udea.peliculas.dominio.Pelicula;
import co.edu.udea.peliculas.excepciones.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanCamilo
 */
public class CatalogoPeliculasImp implements CatalogoPeliculas{
    private final AccesoDatos datos;

    public CatalogoPeliculasImp() {
        this.datos = new AccesoDatosImp();
    }
    
    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = datos.existeCatalogo(NOMBRE_RECURSO);
            datos.agregarPelicula(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            List<Pelicula> peliculas = this.datos.listarPeliculas(NOMBRE_RECURSO);
            String acumuladorPeliculas = "";
            for(Pelicula pelicula: peliculas){
                acumuladorPeliculas += pelicula.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null,"Las películas que hay actualmente en el sistema son: \n" + acumuladorPeliculas);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = this.datos.buscarPelicula(NOMBRE_RECURSO, buscar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso datos");
            ex.printStackTrace(System.out);
        }
        JOptionPane.showMessageDialog(null,resultado);
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if(this.datos.existeCatalogo(NOMBRE_RECURSO)){
                datos.borrarCatalogo(NOMBRE_RECURSO);
                datos.crearCatalogo(NOMBRE_RECURSO);
            }
            else{
                datos.crearCatalogo(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar catalogo de peliculas");
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void eliminarCatalogoPeliculas() {
        try {
            if(this.datos.existeCatalogo(NOMBRE_RECURSO)){
                datos.borrarCatalogo(NOMBRE_RECURSO);
            }            
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al eliminar el catalogo de peliculas");
            ex.printStackTrace(System.out);
        }
    }
}
