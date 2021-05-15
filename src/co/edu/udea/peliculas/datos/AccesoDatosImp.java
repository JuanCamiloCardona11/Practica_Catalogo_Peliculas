
package co.edu.udea.peliculas.datos;

import co.edu.udea.peliculas.dominio.Pelicula;
import co.edu.udea.peliculas.excepciones.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanCamiloC
 */
public class AccesoDatosImp implements AccesoDatos{
    
    @Override
    public boolean existeCatalogo(String nombreRecurso) {
        File archivo = new File(nombreRecurso);
        return(archivo.exists());
    }

    @Override
    public List<Pelicula> listarPeliculas(String nombreRecurso) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>();
        BufferedReader lectura = null;
        try {
            lectura = new BufferedReader(new FileReader(archivo));            
            String linea = lectura.readLine();
            while (linea != null) {
                Pelicula pelicula = new Pelicula(linea);
                peliculas.add(pelicula);
                linea = lectura.readLine();
            }            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al listar peliculas:" + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al listar peliculas:" + ex.getMessage());
        } finally {
            try {
                if(lectura != null) {
                    lectura.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
                throw new LecturaDatosEx("Error al cerrar el catálogo de películas");
            }
        }
        return(peliculas);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreRecurso);
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());           
            JOptionPane.showMessageDialog(null,"Se ha agregado exitosamente la película");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new EscrituraDatosEx("Excepcion al escribir peliculas:" + ex.getMessage());
        } finally {
            if(salida != null) {
                salida.close();
            }
        }
    }

    @Override
    public String buscarPelicula(String nombreRecurso, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        String resultado = "La película con nombre " + buscar + " no ha sido encontrada en el catálogo de películas.";
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(archivo));            
            String linea = entrada.readLine();
            int indice = 1;
            while (linea != null) {
                if (buscar != null && buscar.equalsIgnoreCase(linea)) {
                    resultado = "La pelicula " + linea + " encontrada en el indice " + indice;
                    break;
                }
                linea = entrada.readLine();
                indice++;
            }            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al buscar pelicula:" + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al buscar pelicula:" + ex.getMessage());
        } finally {
            try {
                if(entrada!= null) {
                    entrada.close();
                }
            } catch (IOException ex) {
                throw new LecturaDatosEx("Excepcion al buscar pelicula:" + ex.getMessage());
            }
        }
        return(resultado);
    }

    @Override
    public void crearCatalogo(String nombreRecurso) throws AccesoDatosEx {
        File archivo = new File(nombreRecurso);
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new FileWriter(archivo));            
            JOptionPane.showMessageDialog(null,"Se ha creado un nuevo catálogo para agregar películas");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoDatosEx("Excepcion al crear archivo:" + ex.getMessage());
        } finally {
            if(salida != null) { 
                salida.close();
            }
        }
    }

    @Override
    public void borrarCatalogo(String nombreRecurso) {
        File archivo = new File(nombreRecurso);
        if(archivo.exists()) {
            archivo.delete();
        }        
    }
}
