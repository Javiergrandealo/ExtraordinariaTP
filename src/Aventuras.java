import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Aventuras desde donde lanzar la ejecución de la práctica
 */
public class Aventuras {

    /**
     * Main desde donde ejecutar el programa
     * instanciación e inicialización de objetos para la ejecución,
     * ejecución del motor, muestra de puntuaciones y lectura de instrucciones
     * por teclado para jugar. Finalmente guardar la puntuación
     * 
     * Estudio: es el metodo principal, donde se crea el objeto motor y se da inicio al juego
     * @param args argumentos que se pasan al programa
     */
    public static void main(String[] args) {
        Random r = new Random(); //se genera un objeto de la clase Random para generar numeros aleatorios
        Scanner sc = new Scanner(System.in);
        if(args.length!=10){ //si no hay 10 argumentos se muestra un mensaje de error y se sale del programa
            System.out.println("Error en el numero de argumentos. Deben ser 10.");
            System.exit(1);
        }
        mostrarPuntuaciones(args[9]); //se muestran las puntuaciones almacenadas en el fichero pasado como parametro
        Motor motor = new Motor(Integer.parseInt(args[0])+1, Integer.parseInt(args[1])+1, Integer.parseInt(args[2]),
                Integer.parseInt(args[3]), Integer.parseInt(args[4])); //se crea un objeto de la clase Motor
        Personaje jugador =Personaje.crearPersonaje(sc) ; //se crea un objeto de la clase Personaje a traves del metodo crearPersonaje
        motor.iniciar(args[5], args[6], args[7], args[8]); //se inicia el motor con los argumentos pasados
        motor.jugar(sc, jugador, r); //se inicia el juego con el Scanner, el personaje y el objeto Random
        System.out.println("Se va a guardar la partida en el fichero de puntuaciones");
        guardarPuntuacion(args[9], jugador); //se guarda la puntuacion en el fichero pasado como parametro
        
    }

    /**
     * Metodo guardarPuntuación en fichero
     * abrir y guardar en el fichero pasado como parametro el personaje
     * siguiendo el formato descrito en la memoria de la práctica
     * 
     * @param ficheroPuntuaciones fichero txt que contiene las puntuaciones
     * @param jugador jugador creado pasado por parametro
     */
    private static void guardarPuntuacion(String ficheroPuntuaciones, Personaje jugador) {
        BufferedWriter bw = null; //se hace el bufferedWriter
        try {
            bw = new BufferedWriter(new FileWriter(ficheroPuntuaciones, true)); //se le pasa como parametro el fichero y true para que no sobreescriba
            bw.write(LocalDate.now() + "\t" + jugador.toString() + ", " + jugador.getValorMochila() + " monedas"); //se escribe en el fichero la fecha, el personaje y el valor de la mochila
            bw.newLine(); //se salta de linea
        } catch (IOException e) {
            System.out.println("Error al guardar la puntuación");
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Metodo mostrarPuntuaciones del fichero puntuaciones
     * Mostrar por pantalla todas las puntuaciones almacenadas en el fichero
     * pasado como parámetro. P.e:
     * "Puntuaciones:
     * 2024-04-04 { Raul (V: -4, A: 50, D: 40, X: 20) }, 420.0 monedas"
     * 
     * @param ficheroPuntuaciones fichero con las puntuaciones de los personajes
     */
    private static void mostrarPuntuaciones(String ficheroPuntuaciones) {
        BufferedReader br = null; //se crea el BufferedReader
        try {
            br = new BufferedReader(new FileReader(ficheroPuntuaciones)); //se le pasa como parametro el fichero
            System.out.println("Puntuaciones:"); //se imprime por pantalla
            String linea;
            while ((linea = br.readLine()) != null) { //mientras haya lineas en el fichero
                System.out.println(linea); //se imprime la linea
            }
        } catch (FileNotFoundException e) { //excepcion que se lanza si no se encuentra el fichero
            System.out.println("Error al leer el fichero de puntuaciones");
            e.printStackTrace();
        } catch (IOException e) { //excepcion que se lanza si hay un error al leer el fichero 
            System.out.println("Error al leer el fichero de puntuaciones");
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero de puntuaciones");
                e.printStackTrace();
            }
        }
    }
}
