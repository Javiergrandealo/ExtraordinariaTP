import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Motor
 */
public class Motor {
    Sala[][] mapa;
    private final int maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala;

    /**
     * Constructor Clase Motor
     * @param filas
     * @param columnas
     * @param maxItemsPorSala
     * @param maxMonstruosPorSala
     * @param maxTrampasPorSalas
     */
    public Motor(int filas, int columnas, int maxItemsPorSala, int maxMonstruosPorSala, int maxTrampasPorSalas) {
        mapa = new Sala[filas][columnas];
        this.maxItemsPorSala = maxItemsPorSala;
        this.maxMonstruosPorSala = maxMonstruosPorSala;
        this.maxTrampasPorSala = maxTrampasPorSalas;

    }

    /**
     * Clase cargarMapa para construir la matriz de mapa a traves del fichero
     * leer los datos del fichero de mapa pasado por parametro y generar una matriz Sala[][]
     *  con dimension Sala[fila][columna] e inicializar la sala con los valores con la descripción del fichero
     *  y los parámetros de maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala.
     * @param ficheroMapa
     * @return sala generada
     */

     //Creo q esta bien, pero no estoy seguro
    Sala[][] cargarMapa(String ficheroMapa) {
        BufferedReader entrada = null;
        try{
            entrada = new BufferedReader(new FileReader(ficheroMapa));
            String linea;
            while((linea = entrada.readLine()) != null){
                String[] partes = linea.split(";");
                if(partes.length ==3){
                    int filas = Integer.parseInt(partes[0]);
                    int columnas = Integer.parseInt(partes[1]);
                    String descripcion = partes[2];
                    Sala sala = new Sala(descripcion, maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala, filas, columnas);
                    mapa[filas][columnas] = sala;
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("No se ha encontrado el fichero");
        }catch (IOException e){
            System.out.println("Error de lectura");
        }finally {
            try{
                if(entrada != null){
                    entrada.close();
                }
            }catch (IOException e){
                System.out.println("Error al cerrar el fichero");
            }
        }
        return mapa;
    }

    /**
     * Metodo cargarItems para agregar los items del fichero en el mapa
     * Método para leer un fichero de items pasado por parámetro y según
     *  la fila y columna introducir el item en la sala.
     * @param ficheroItems
     */
    private void cargarItems(String ficheroItems) {
        BufferedReader entrada = null;
        try{
            entrada = new BufferedReader(new FileReader(ficheroItems));
            String linea;
            while((linea = entrada.readLine()) != null){
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                String descripcion = partes[2];
                int valor = Integer.parseInt(partes[3]);
                int peso = Integer.parseInt(partes[4]);
                Item item = new Item(descripcion, valor, peso);
                mapa[fila][columna].agregarItem(item);
            }
        }catch (FileNotFoundException e){
            System.out.println("No se ha encontrado el fichero");
        }catch (IOException e){
            System.out.println("Error de lectura");
        }finally {
            try{
                if(entrada != null){
                    entrada.close();
                }
            }catch (IOException e){
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    /**
     * Método cargarMonstruos para agregar los monstruos del fichero en el mapa
     * Método para leer un fichero de Monstruos pasado por parámetro y según
     *  la fila y columna introducir el monstruo en la sala.
     * @param ficheroMonstruos
     */
    private void cargarMonstruos(String ficheroMonstruos) {
        BufferedReader entrada = null;
        try{
            entrada = new BufferedReader(new FileReader(ficheroMonstruos));
            String linea;
            while((linea = entrada.readLine()) != null){
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                String descripcion = partes[2];
                int vida = Integer.parseInt(partes[3]);
                int ataque = Integer.parseInt(partes[4]);
                int defensa = Integer.parseInt(partes[5]);
                Monstruo monstruo = new Monstruo(descripcion, vida, ataque, defensa);
                mapa[fila][columna].agregarMonstruo(monstruo);
            }
        }catch (FileNotFoundException e){
            System.out.println("No se ha encontrado el fichero");
        }catch (IOException e){
            System.out.println("Error de lectura");    
        }finally {
            try{
                if(entrada != null){
                    entrada.close();
                }
            }catch (IOException e){
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    /**
     * Método cargarTrampas para agregar las trampas del fichero en el mapa
     * Método para leer un fichero de trampas pasado por parámetro y según
     *   la fila y columna introducir la trampa en la sala.
     * @param ficheroTrampas
     */
    private void cargarTrampas(String ficheroTrampas) {
        BufferedReader entrada = null;
        try{
            entrada = new BufferedReader(new FileReader(ficheroTrampas));
            String linea;
            while((linea = entrada.readLine())!=null){
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                String descripcion = partes[2];
                int dano = Integer.parseInt(partes[3]);
                Trampa trampa = new Trampa(descripcion, dano);
                mapa[fila][columna].agregarTrampa(trampa);
            }
        }catch (FileNotFoundException e){
            System.out.println("No se ha encontrado el fichero");
        }catch (IOException e){
            System.out.println("Error de lectura");
        }finally {
            try{
                if(entrada != null){
                    entrada.close();
                }
            }catch (IOException e){
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    /**
     * Metodo iniciar, para preparar el mapa
     * instanciación del parametro mapa y carga de datos con los ficheros pasados como parámetros
     * @param ficheroMapa
     * @param ficheroItems
     * @param ficheroMonstruos
     * @param ficheroTrampas
     */
    public void iniciar(String ficheroMapa, String ficheroItems, String ficheroMonstruos, String ficheroTrampas) {
        cargarMapa(ficheroMapa);
        cargarItems(ficheroItems);
        cargarMonstruos(ficheroMonstruos);
        cargarTrampas(ficheroTrampas);
    }

    /**
     * Método getSala para obtener una sala concreta del mapa
     * devolver una Sala concreta del mapa
     * @param fila
     * @param columna
     * @return
     */
    public Sala getSala(int fila, int columna) {
        return mapa[fila][columna];
    }

    /** TODO
     * Método mostrarMapa para transformar el mapa en String
     * construir un String con la información contenida en el mapa
     *  respetando el formato que aparece en la memoria de la práctica
     * @param fila
     * @param columna
     * @return
     */
    public String mostrarMapa(int fila, int columna) {
        String mapa = "";
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                mapa += this.mapa[i][j].toString();
            }
            mapa += "\n";
        }

    return mapa;
}

    /**
     * Método jugar para empezar a jugar con el personaje
     * TODO método complejo en el que hay que seguir la siguiente ejecución:
     *  1. mostrar el mapa por pantalla
     *  2. Obtener la sala actual y mientras el personaje tenga vida y no haya llegado a la casilla final
     *  3. Durante una jugada mostrar la descripcion de la sala actual
     *  4. Comprobar si hay monstruos en la sala y si es así entrar en combate
     *  4.a El combate acaba cuando la vida del monstruo o la vida del personaje llega a 0
     *  4.b cada turno en el combate el personaje ataca al monstruo y restamos su vida
     *  4.c Si la vida no llega a 0 el monstruo hace daño al personaje
     *  5. Las salas pueden tener trampas
     *  5.a Si hay trampa hay que comprobar si un valor aleatorio entre 1 y 50 es inferior a la destreza del personaje, si es asi esquiva la trampa
     *  5.b Si no esquiva la trampa el personaje recibe daño
     *  5.c al igual que en combate hay que tener en cuenta si la vida del personaje lleva a 0
     *  6. Por último puede haber items en la sala, en cuyo caso habrá que preguntar al usuario qué ítems quiere guardarse (o NINGUNO para terminar)
     *  ¡IMPORTANTE! se debe mostrar por pantalla avisos para cada opción dando feedback al usuario de todo lo que ocurra (consultar enunciado)
     * @param teclado
     * @param personaje
     * @param random
     */
    public void jugar(Scanner teclado, Personaje personaje, Random random) {
        iniciar(, null, null, null);
        

    }

    /**
     * Metodo seleccionarMovimiento para establecer las acciones que tome el jugador con su personaje
     * TODO El desplazamiento del personaje se entiende como norte (N), sur (S), este (E) u oeste (O)
     *  en este método hay que capturar por pantalla la acción que va a tomar el usuario de entre las posibles
     *  para ello hay que tener en cuenta que se debe avisar al usuario si puede realizar o no la acción.
     *  Se devolverá la sala destino a la que se ha movido el personaje.
     * @param teclado
     * @param salaActual
     * @return
     */
    public Sala seleccionarMovimiento(Scanner teclado, Sala salaActual) {

        return
    }
}
