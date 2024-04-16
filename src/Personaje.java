import java.util.Scanner;

/**
 * Clase Personaje
 */
public class Personaje {
    private final String nombre;
    private int vida;
    private final int ataque, defensa, destreza;
    private final Item[] items;

    private final double maxPesoPorPersonaje;

    /**
     * Constructor de la clase para inicializar todos los atributos
     * 
     * @param nombre
     * @param vida
     * @param ataque
     * @param defensa
     * @param destreza
     * @param maxItemsPorPersonaje
     * @param maxPesoPorPersonaje
     */
    public Personaje(String nombre, int vida, int ataque, int defensa, int destreza, int maxItemsPorPersonaje,
            double maxPesoPorPersonaje) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.destreza = destreza;
        maxItemsPorPersonaje = destreza / 4;
        this.maxPesoPorPersonaje = ataque / 2;
        items = new Item[maxItemsPorPersonaje];
    }

    /**
     * Metodo crearPersonaje que administra toda la generación de personajes
     * El metodo tiene que ser capaz de recoger todas las características del
     * personaje mediante preguntas y
     * respuestas por pantalla y se debe controlar que los valores introducidos sean
     * validos. Una vez recabados
     * todos los datos del personaje generar un objeto con dichas características.
     * 
     * @param teclado
     * @return
     */
    public static Personaje crearPersonaje(Scanner teclado) {
        Personaje personaje;
        int suma = 0;
        int vida = 0;
        int ataque = 0;
        int defensa = 0;
        int destreza = 0;
        System.out.println("¿Cómo te llamas?");
        String nombre = teclado.nextLine();
        System.out.println(
                "Hola " + nombre + "! Tienes 250 puntos para repartir entre vida, ataque, defensa y destreza.");
        while (suma < 53 || suma > 250) {
            System.out.println("¿Cuánta vida quieres tener? (50-247)");
            vida = teclado.nextInt();
            while (vida < 50 || vida > 247) {
                System.out.println("La vida debe estar entre 50 y 247. Introduce un valor válido: ");
                vida = teclado.nextInt();

            }
            suma += vida;
            System.out.println("¿Cuánto ataque quieres tener? (1-" + (250 - suma - 2) + ")");
            ataque = teclado.nextInt();
            while (ataque < 1 || ataque > 148) {
                System.out.println("El ataque debe estar entre 1 y 148. Introduce un valor válido: ");
                ataque = teclado.nextInt();
            }
            suma += ataque;
            System.out.println("¿Cuánta defensa quieres tener? (1-" + (250 - suma - 1) + ")");
            defensa = teclado.nextInt();
            while (defensa < 1 || defensa > 49) {
                System.out.println("La defensa debe estar entre 1 y 49. Introduce un valor válido: ");
                defensa = teclado.nextInt();
            }
            suma += defensa;
            System.out.println("¿Cuánta destreza quieres tener? (1-" + (250 - suma) + ")");
            destreza = teclado.nextInt();
            while (destreza < 1 || destreza > 25) {
                System.out.println("La destreza debe estar entre 1 y 25. Introduce un valor válido: ");
                destreza = teclado.nextInt();
            }
            suma += destreza;

            if (suma > 250) {
                System.out.println("Has superado el límite de puntos. Vuelve a introducir los valores.");
            }
        }
        personaje = new Personaje(nombre, vida, ataque, defensa, destreza, destreza / 4, ataque / 2);
        return personaje;
    }

    /**
     * Método getNombre
     * 
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método getVida
     * 
     * @return int vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método getAtaque
     * 
     * @return int ataque
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Método getDefensa
     * 
     * @return int defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Método getDestreza
     * 
     * @return int destreza
     */
    public int getDestreza() {
        return destreza;
    }

    /**
     * Método getItems
     * 
     * @return Item[] items
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Método getItem para devolver un Item según un índice dado
     * TODO devolver null si el índice no es válido, y el item si el índice es correcto
     * @param indice
     * @return
     */
    public Item getItem(int indice) {

            return
    }

    /**
     * Método recibirDanyo para actualizar la vida de un personaje
     * TODO Si el daño no es positivo, no hacer nada. En caso contrario reducir la
     * vida según el daño pasado
     * 
     * @param danyo
     */
    public void recibirDanyo(int danyo) {
        danyo = danyo - defensa;
        if (danyo > 0) {
            vida -= danyo;
        }

    }

    /**
     * Método anyadirItem para incluir un item en la mochila del personaje
     * TODO Comprobar si el item es valido y si el peso max del personaje no se supera para poder incluir el item,
     *  en caso negativo ddevolver false, en caso de que se pueda incluir, añadir el item a la lista de items del
     *  personaje y devolver true
     * @param item
     * @return
     */
    public boolean anyadirItem(Item item) {

            return
    }

    /**
     * Método sobreescrito para devolver la información de un personaje
     * TODO Método para devolver un String con la información del personaje en el
     * formato
     * descrito en la memoria de la práctica P.e: "{ Edgar (V: 20, A: 5, D: 2, X: 5)
     * }"
     * 
     * @return
     */
    @Override
    public String toString() {
        String stats = "{ " + nombre + " (V: " + vida + ", A: " + ataque + ", D: " + defensa + ", X: " + destreza
                + ") }";
        return stats;
    }

    /**
     * Método getPesoMochila para obtener el peso total que carga en la mochila el personaje
     * TODO recorrer la lista de items para obtener el peso total de todos y devolverlo
     * @return
     */
    public double getPesoMochila() {

        return
    }

    /**
     * Método getValorMochila para obtener el valor total que lleva entre todos los items el personaje
     * TODO recorrer la lista de items para obtener el valor total de todos y devolverlo
     * @return
     */
    public double getValorMochila() {

        return
    }

    /**
     * Método infoMochila para obtener en formato String la información de la mochila
     * TODO recorrer toda la lista de items del personaje para ir añadiendo la información de los items según el
     *  formato mostrado en la memoria. P.e. "Mochila de Edgar:
     *                                        Espada Mágica Peso: 1.5, Valor: 100
     *                                        Armadura de Gromril Peso: 4, Valor: 300
     *                                        Peso total: 5.5 Kg
     *                                        Tu mochila vale 400 monedas"
     * @return
     */
    public String infoMochila() {

        return
    }
}
