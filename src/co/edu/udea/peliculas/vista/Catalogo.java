
package co.edu.udea.peliculas.vista;

import co.edu.udea.peliculas.servicio.*;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanCamiloC
 */
public class Catalogo {
    
    private static CatalogoPeliculas catalogoPeliculas;    
    
    public static void main(String[] args) {              
        catalogoPeliculas = new CatalogoPeliculasImp();
        char opcion = mostrarMenuOpciones(); 
        do{
            ejecutarOpcion(opcion);
            opcion = mostrarMenuOpciones(); 
        } while(opcion != '0');
    }
    public static char mostrarMenuOpciones() {
        char opcion;
        do{
            opcion = JOptionPane.showInputDialog("Ingrese por favor una opción: \n" +
                                                 "\t1.- Iniciar catalogo películas\n" +
                                                 "\t2.- Agregar película\n" +
                                                 "\t3.- Listar Películas\n" +
                                                 "\t4.- Buscar Película\n" +
                                                 "\t0.- Salir").charAt(0);            
        }while((opcion < '0') || (opcion > '4'));
        return(opcion);
    }
    public static void ejecutarOpcion(char opcion) {
        switch(opcion) {
            case '1':
                catalogoPeliculas.iniciarCatalogoPeliculas();                
                break;
            case '2': 
                String nombrePelicula = JOptionPane.showInputDialog("Ingrese el nombre de la nueva película a agregar: ");
                Catalogo.catalogoPeliculas.agregarPelicula(nombrePelicula);                
                break;
            case '3': 
                Catalogo.catalogoPeliculas.listarPeliculas();                
                break;
            case '4': 
                String peliculaBuscar = JOptionPane.showInputDialog("Ingrese el nombre de la nueva película a buscar: ");
                catalogoPeliculas.buscarPelicula(peliculaBuscar);                
                break;
            case '0': 
                int verificacion = JOptionPane.showConfirmDialog(null, "¿Desea salir y finalizar la ejecución del programa?");
                if(verificacion == 0) {
                    JOptionPane.showMessageDialog(null,"Ha decidido salir, hasta luego.");
                    catalogoPeliculas.eliminarCatalogoPeliculas();
                    System.exit(0);
                }    
                break;
            default:
                break;                
        }
    }
}
