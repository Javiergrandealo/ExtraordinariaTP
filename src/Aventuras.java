import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
     *  ejecución del motor, muestra de puntuaciones y lectura de instrucciones
     *  por teclado para jugar. Finalmente guardar la puntuación
     * @param args
     */
    public static void main(String[] args) {
        Motor motor = new Motor(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        motor.cargarMapa(args[5]);
        motor.iniciar(args[5], args[6], args[7], args[8]);

    }

    /**
     * Metodo guardarPuntuación en fichero
     * abrir y guardar en el fichero pasado como parametro el personaje
     *  siguiendo el formato descrito en la memoria de la práctica
     * @param ficheroPuntuaciones
     * @param jugador
     */
    private static void guardarPuntuacion(String ficheroPuntuaciones, Personaje jugador) {
        BufferedWriter bw = null;
        FileWriter fw = null;
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
     * TODO Mostrar por pantalla todas las puntuaciones almacenadas en el fichero
     *  pasado como parámetro. P.e:
     *              "Puntuaciones:
     *                  2024-04-04	{ Raul (V: -4, A: 50, D: 40, X: 20) }, 420.0 monedas"
     * @param ficheroPuntuaciones
     */
    private static void mostrarPuntuaciones(String ficheroPuntuaciones) {

    }
}
