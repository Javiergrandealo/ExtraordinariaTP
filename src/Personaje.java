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
     * @param nombre nombre del personaje
     * @param vida vida del personaje
     * @param ataque ataque del personaje
     * @param defensa defensa del personaje
     * @param destreza destreza del personaje
     * @param maxItemsPorPersonaje maximo numero de items que se puede llevar
     * @param maxPesoPorPersonaje peso maximo que puede llevar el personaje
     */
    public Personaje(String nombre, int vida, int ataque, int defensa, int destreza, int maxItemsPorPersonaje,
            double maxPesoPorPersonaje) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.destreza = destreza;
        maxItemsPorPersonaje = destreza / 4; // maximo numero de items que se puede llevar es la destreza entre 4
        this.maxPesoPorPersonaje = ataque / 2; // peso maximo que puede llevar el personaje es el ataque entre 2
        items = new Item[maxItemsPorPersonaje]; // se crea un array de items con el maximo de items que puede llevar el personaje
    }

    /**
     * Metodo crearPersonaje que administra toda la generación de personajes
     * El metodo tiene que ser capaz de recoger todas las características del
     * personaje mediante preguntas y
     * respuestas por pantalla y se debe controlar que los valores introducidos sean
     * validos. Una vez recabados
     * todos los datos del personaje generar un objeto con dichas características.
     * 
     * @param teclado teclado
     * @return personaje nuevo
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
            vida = Utilidades.leerNumero(teclado,"¿Cuánta vida quieres tener? (50-247)" , 50, 247);
            suma += vida;
            ataque = Utilidades.leerNumero(teclado,"¿Cuánto ataque quieres tener? (1-148):", 1, 148);
            suma += ataque;
            defensa = Utilidades.leerNumero(teclado,"¿Cuánta defensa quieres tener? (1-49):", 1, 49);
            suma += defensa;
            destreza = Utilidades.leerNumero(teclado,"¿Cuánta destreza quieres tener? (1-25):", 1, 25);
            suma += destreza;
            if (suma > 250) {
                System.out.println("Has superado el límite de puntos. Vuelve a introducir los valores.");
                suma = 0;
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
     * Tdevolver null si el índice no es válido, y el item si el índice es correcto
     * @param indice posicion del indice en la array
     * @return el item en la posición indicada
     */
    public Item getItem(int indice) {
        if (indice < 0 || indice >= items.length) { // si el indice es menor que 0 o mayor que la longitud de la array
            return null; //devolver null
        } else { // si no
            return items[indice]; //devolver el item en la posicion indicada
        }
    }

    /**
     * Método recibirDanyo para actualizar la vida de un personaje
     * Si el daño no es positivo, no hacer nada. En caso contrario reducir la
     * vida según el daño pasado
     * 
     * @param danyo daño recibido
     */
    public void recibirDanyo(int danyo) {
        danyo = danyo - defensa; //El daño que recibe el personaje es el daño menos la defensa del personaje
        if (danyo > 0) { //Si el daño es mayor que 0, se le resta a la vida del personaje
            vida -= danyo;
            System.out.println("¡" + nombre + " ha recibido " + danyo + " puntos de daño!");
        }

    }

    /**
     * Método anyadirItem para incluir un item en la mochila del personaje
     * Comprobar si el item es valido y si el peso max del personaje no se supera
     * para poder incluir el item,
     * en caso negativo ddevolver false, en caso de que se pueda incluir, añadir el
     * item a la lista de items del
     * personaje y devolver true
     * 
     * @param item item que se quiere añadir a la array de items del personaje
     * @return valido
     */
    public boolean anyadirItem(Item item) {
        boolean valido = false;
    if (items.length <= destreza / 4 && getPesoMochila() + item.getPeso() < maxPesoPorPersonaje) { //Si el personaje puede llevar el item
        int i = 0;
        while (i < items.length && items[i] != null) { //Recorrer la lista de items del personaje hasta que haya un hueco
            i++;
        }
        if (i < items.length) { //Si hay hueco, añadir el item
            items[i] = item;
            valido = true;
        }
    }
    return valido;
    }

    /**
     * Método sobreescrito para devolver la información de un personaje
     * Método para devolver un String con la información del personaje en el
     * formato
     * descrito en la memoria de la práctica P.e: "{ Edgar (V: 20, A: 5, D: 2, X: 5)
     * }"
     * 
     * @return nombre vida ataque defensa y destreza
     */
    @Override
    public String toString() {
        String stats = "{ " + nombre + " (V: " + vida + ", A: " + ataque + ", D: " + defensa + ", X: " + destreza
                + ") }";
        return stats;
    }

    /**
     * Método getPesoMochila para obtener el peso total que carga en la mochila el
     * personaje
     * recorrer la lista de items para obtener el peso total de todos y devolverlo
     * 
     * @return peso de todos los objetos en la mochila
     */
    public double getPesoMochila() {
        double peso = 0;
        for (int i = 0; i < items.length; i++) { //Recorrer la lista de items del personaje
            if (items[i] != null) { //Si el item no es nulo, sumar su peso al total
                peso += items[i].getPeso();
            }
        }
        return peso;
    }

    /**
     * Método getValorMochila para obtener el valor total que lleva entre todos los
     * items el personaje
     * recorrer la lista de items para obtener el valor total de todos y devolverlo
     * 
     * @return valor de la mochila en monedas
     */
    public double getValorMochila() {
        double valor = 0;
        for (int i = 0; i < items.length; i++) { //Recorrer la lista de items del personaje
            if (items[i] != null) { //Si el item no es nulo, sumar su valor al total
                valor += items[i].getValor();
            }
        }
        return valor;
    }

    /**
     * Método infoMochila para obtener en formato String la información de la
     * mochila
     * recorrer toda la lista de items del personaje para ir añadiendo la
     * información de los items según el
     * formato mostrado en la memoria. P.e. "Mochila de Edgar:
     * Espada Mágica Peso: 1.5, Valor: 100
     * Armadura de Gromril Peso: 4, Valor: 300
     * Peso total: 5.5 Kg
     * Tu mochila vale 400 monedas"
     * 
     * @return toda la mochila en forma de string
     */
    public String infoMochila() {
        String mensaje = "Mochila de " + nombre + ":" + "\n";
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                mensaje += items[i].toString() + "\n";
            }
        }
        return mensaje;
    }
}
