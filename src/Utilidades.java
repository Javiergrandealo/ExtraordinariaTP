import java.util.Scanner;

/**
 * Clase Utilidades
 */
public class Utilidades {

    /**
     * Método estático leerCadena para leer un cadena de carecteres por pantalla
     * leer por pantalla y comprobar que es una cadena de caracteres válida. 
     * @param teclado
     * @param s mensaje a mostrar
     * @return cadena de texto
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.println(s); //mostramos el mensaje
        String cadena; //declaramos la variable cadena
        cadena =teclado.nextLine(); //leemos la cadena
        return cadena; //devolvemos la cadena
    }

    /**
     * Método estático leerNumero para leer un numero pasado por pantalla
     * leer por pantalla y comprobar que es un número valido. Solicita un número repetidamente hasta que se
     *  introduzca uno correcto (dentro de los límites)
     * @param teclado Scanner
     * @param mensaje mensaje a mostrar
     * @param minimo valor mínimo
     * @param maximo valor máximo
     * @return número
     */
    // Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        System.out.println(mensaje); //mostramos el mensaje
        int numero = teclado.nextInt(); //leemos el número
        teclado.nextLine(); //parece ser que hay que limpiar el buffer
        while (numero < minimo || numero > maximo) { //mientras el número no esté en el rango
            System.out.println("Número incorrecto. " + mensaje); //mostramos mensaje de error
            numero = teclado.nextInt(); //leemos el número
            teclado.nextLine(); //limpiamos el buffer
        }
        return numero; //devolvemos el número
    }
}
