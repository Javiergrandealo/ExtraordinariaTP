import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Aventuras desde donde lanzar la ejecución de la práctica
 */
public class Aventuras {

    /**
     * Main desde donde ejecutar el programa
     * TODO instanciación e inicialización de objetos para la ejecución,
     * ejecución del motor, muestra de puntuaciones y lectura de instrucciones
     * por teclado para jugar. Finalmente guardar la puntuación
     * 
     * @param args
     */
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        Motor motor = new Motor(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        Personaje jugador = null;
        jugador.crearPersonaje(sc);
        motor.cargarMapa(args[5]);
        motor.iniciar(args[5], args[6], args[7], args[8]);
        motor.jugar(sc, jugador, r);
        

    }

    /**
     * Metodo guardarPuntuación en fichero
     * abrir y guardar en el fichero pasado como parametro el personaje
     * siguiendo el formato descrito en la memoria de la práctica
     * 
     * @param ficheroPuntuaciones
     * @param jugador
     */
    private static void guardarPuntuacion(String ficheroPuntuaciones, Personaje jugador) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(ficheroPuntuaciones, true));
            bw.write(LocalDate.now() + "\t" + jugador.toString() + ", " + jugador.getValorMochila() + " monedas");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la puntuación");
            e.printStackTrace();

        }
    }

    /**
     * Metodo mostrarPuntuaciones del fichero puntuaciones
     * Mostrar por pantalla todas las puntuaciones almacenadas en el fichero
     * pasado como parámetro. P.e:
     * "Puntuaciones:
     * 2024-04-04 { Raul (V: -4, A: 50, D: 40, X: 20) }, 420.0 monedas"
     * 
     * @param ficheroPuntuaciones
     */
    private static void mostrarPuntuaciones(String ficheroPuntuaciones) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ficheroPuntuaciones));
            System.out.println("Puntuaciones:");
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
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
