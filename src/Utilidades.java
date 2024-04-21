import java.util.Scanner;

/**
 * Clase Utilidades
 */
public class Utilidades {

    /**
     * Método estático leerCadena para leer un cadena de carecteres por pantalla
     * leer por pantalla y comprobar que es una cadena de caracteres válida. 
     * @param teclado
     * @param s
     * @return
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.println(s);
        String cadena = teclado.nextLine();
        return cadena;
    }

    /**
     * Método estático leerNumero para leer un numero pasado por pantalla
     * leer por pantalla y comprobar que es un número valido. Solicita un número repetidamente hasta que se
     *  introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return
     */
    // Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        System.out.println(mensaje);
        int numero = teclado.nextInt();
        while (numero < minimo || numero > maximo) {
            System.out.println("Número incorrecto. " + mensaje);
            numero = teclado.nextInt();
        }
        return numero;
    }
}
