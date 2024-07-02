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
     * 
     * @param filas filas del mapa
     * @param columnas columnas del mapa
     * @param maxItemsPorSala ites maximos por sala
     * @param maxMonstruosPorSala mosntruos maximos por sala
     * @param maxTrampasPorSalas trampas maximas por salas
     */
    public Motor(int filas, int columnas, int maxItemsPorSala, int maxMonstruosPorSala, int maxTrampasPorSalas) {
        mapa = new Sala[filas][columnas];
        this.maxItemsPorSala = maxItemsPorSala;
        this.maxMonstruosPorSala = maxMonstruosPorSala;
        this.maxTrampasPorSala = maxTrampasPorSalas;

    }

    /**
     * Clase cargarMapa para construir la matriz de mapa a traves del fichero
     * leer los datos del fichero de mapa pasado por parametro y generar una matriz
     * Sala[][]
     * con dimension Sala[fila][columna] e inicializar la sala con los valores con
     * la descripción del fichero
     * y los parámetros de maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala.
     * 
     * @param ficheroMapa fichero con las salas
     * @return sala generada
     */

    Sala[][] cargarMapa(String ficheroMapa) {
        BufferedReader entrada = null; // inicializamos el buffer de lectura
        try {
            entrada = new BufferedReader(new FileReader(ficheroMapa)); // abrimos el fichero
            String linea;
            while ((linea = entrada.readLine()) != null) { // leemos el fichero linea a linea
                String[] partes = linea.split(";"); // separamos la linea por el caracter ;
                if (partes.length == 3) { // si la linea tiene 3 partes
                    int filas = Integer.parseInt(partes[0]); // la primera parte es el numero de filas
                    int columnas = Integer.parseInt(partes[1]); // la segunda parte es el numero de columnas
                    String descripcion = partes[2]; // la tercera parte es la descripcion de la sala
                    Sala sala = new Sala(descripcion, maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala, filas,
                            columnas); // creamos la sala
                    if (filas < mapa.length && columnas < mapa[filas].length) { // si la sala cabe en el mapa
                        mapa[filas][columnas] = sala; // la añadimos al mapa
                    } else {
                        System.out.println(
                                "La sala en la fila " + filas + " y columna " + columnas + " no cabe en el mapa");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
        return mapa; // devolvemos el mapa
    }

    /**
     * Metodo cargarItems para agregar los items del fichero en el mapa
     * Método para leer un fichero de items pasado por parámetro y según
     * la fila y columna introducir el item en la sala.
     * 
     * @param ficheroItems fichero con los items
     */
    private void cargarItems(String ficheroItems) {
        BufferedReader entrada = null; // inicializamos el buffer de lectura
        try {
            entrada = new BufferedReader(new FileReader(ficheroItems)); // abrimos el fichero
            String linea;
            while ((linea = entrada.readLine()) != null) { // leemos el fichero linea a linea
                String[] partes = linea.split(";"); // separamos la linea por el caracter ;
                int fila = Integer.parseInt(partes[0]); // la primera parte es la fila
                int columna = Integer.parseInt(partes[1]); // la segunda parte es la columna
                String descripcion = partes[2]; // la tercera parte es la descripcion del item
                Double valor = Double.parseDouble(partes[3]); // la cuarta parte es el valor del item
                int peso = Integer.parseInt(partes[4]); // la quinta parte es el peso del item
                Item item = new Item(descripcion, valor, peso); // creamos el item
                if (fila < mapa.length && columna < mapa[fila].length && mapa[fila][columna] != null) { // si la sala
                                                                                                        // existe
                    mapa[fila][columna].agregarItem(item); // añadimos el item a la sala
                } else {
                    System.out.println("La sala en la fila " + fila + " y columna " + columna + " no existe");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    /**
     * Método cargarMonstruos para agregar los monstruos del fichero en el mapa
     * Método para leer un fichero de Monstruos pasado por parámetro y según
     * la fila y columna introducir el monstruo en la sala.
     * 
     * @param ficheroMonstruos fichero con los monstruos
     */
    private void cargarMonstruos(String ficheroMonstruos) {
        BufferedReader entrada = null;  // inicializamos el buffer de lectura
        try {
            entrada = new BufferedReader(new FileReader(ficheroMonstruos)); // abrimos el fichero
            String linea;
            while ((linea = entrada.readLine()) != null) { // leemos el fichero linea a linea
                String[] partes = linea.split(";"); // separamos la linea por el caracter ;
                int fila = Integer.parseInt(partes[0]); // la primera parte es la fila
                int columna = Integer.parseInt(partes[1]); // la segunda parte es la columna
                String descripcion = partes[2]; // la tercera parte es la descripcion del monstruo
                int vida = Integer.parseInt(partes[3]); // la cuarta parte es la vida del monstruo
                int ataque = Integer.parseInt(partes[4]); // la quinta parte es el ataque del monstruo
                int defensa = Integer.parseInt(partes[5]); // la sexta parte es la defensa del monstruo
                Monstruo monstruo = new Monstruo(descripcion, vida, ataque, defensa); // creamos el monstruo
                if (fila < mapa.length && columna < mapa[fila].length && mapa[fila][columna] != null) { // si la sala
                                                                                                        // existe
                    mapa[fila][columna].agregarMonstruo(monstruo); // añadimos el monstruo a la sala
                } else {
                    System.out.println("La sala en la fila " + fila + " y columna " + columna + " no existe");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    /**
     * Método cargarTrampas para agregar las trampas del fichero en el mapa
     * Método para leer un fichero de trampas pasado por parámetro y según
     * la fila y columna introducir la trampa en la sala.
     * 
     * @param ficheroTrampas fichero con las trampas
     */
    private void cargarTrampas(String ficheroTrampas) {
        BufferedReader entrada = null; // inicializamos el buffer de lectura
        try {
            entrada = new BufferedReader(new FileReader(ficheroTrampas)); // abrimos el fichero
            String linea; 
            while ((linea = entrada.readLine()) != null) { // leemos el fichero linea a linea
                String[] partes = linea.split(";"); // separamos la linea por el caracter ;
                int fila = Integer.parseInt(partes[0]); // la primera parte es la fila
                int columna = Integer.parseInt(partes[1]); // la segunda parte es la columna
                String descripcion = partes[2]; // la tercera parte es la descripcion de la trampa
                int dano = Integer.parseInt(partes[3]); // la cuarta parte es el daño de la trampa
                Trampa trampa = new Trampa(descripcion, dano); // creamos la trampa
                mapa[fila][columna].agregarTrampa(trampa); // añadimos la trampa a la sala
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    /**
     * Metodo iniciar, para preparar el mapa
     * instanciación del parametro mapa y carga de datos con los ficheros pasados
     * como parámetros
     * 
     * @param ficheroMapa fichero con el mapa de las salas
     * @param ficheroItems fichero con los items
     * @param ficheroMonstruos fichero con los monstruos
     * @param ficheroTrampas fichero con las trampas
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
     * 
     * @param fila filas
     * @param columna columnas
     * @return una matriz de filas x columnas
     */
    public Sala getSala(int fila, int columna) {
        return mapa[fila][columna];
    }

    /**
     * 
     * Método mostrarMapa para transformar el mapa en String
     * construir un String con la información contenida en el mapa
     * respetando el formato que aparece en la memoria de la práctica
     * 
     * @param fila fila en la que esta el personaje
     * @param columna columna en la que esta el personaje
     * @return a, un string q contiene el mapa que se va a mostrar por pantalla
     */

    private boolean existeSala(int fila, int columna) { // metodo auxuliar para saber si existe una sala en la posicion,
                                                        // ayuda a la comprensón del metodo mostrarMapa
        if (fila < 0 || columna < 0 || fila >= mapa.length || columna >= mapa[0].length) {
            System.out.println("Fuera del mapa");
            return false;
        }
        return mapa[fila][columna] != null;
    }

    public String mostrarMapa(int fila, int columna) {
        int filasMapa = mapa.length; // filas del mapa
        int columnasMapa = mapa[0].length; // columnas del mapa
        String a = "";

        a += "╔";
        for (int i = 0; i < columnasMapa; i++) { // construimos la parte superior del mapa
            a += "═";
        }
        a += "╗\n";
        for (int i = 0; i < filasMapa; i++) { // construimos las filas del mapa
            a += "║";
            for (int j = 0; j < columnasMapa; j++) { // construimos las columnas del mapa
                if (i == fila && j == columna) { // si la posicion es la del personaje
                    a += "@"; // dibujamos un @
                } else if (existeSala(i, j)) { //si existe una sala
                    a += "░"; //dibujamos esto
                } else {
                    a += " "; //si no existe sala dibujamos un espacio
                }
            }
            a += "║\n"; //acabamos de construir la fila
        }
        a += "╚";
        for (int i = 0; i < columnasMapa; i++) {
            a += "═"; //construimos la parte inferior del mapa
        }
        a += "╝\n";
        return a;
    }

    /**
     * Método jugar para empezar a jugar con el personaje
     * método complejo en el que hay que seguir la siguiente ejecución:
     * 1. mostrar el mapa por pantalla HECHO
     * 2. Obtener la sala actual y mientras el personaje tenga vida y no haya
     * llegado a la casilla final HECHO
     * 3. Durante una jugada mostrar la descripcion de la sala actual HECHO
     * 4. Comprobar si hay monstruos en la sala y si es así entrar en combate HECHO
     * 4.a El combate acaba cuando la vida del monstruo o la vida del personaje
     * llega a 0
     * 4.b cada turno en el combate el personaje ataca al monstruo y restamos su
     * vida
     * 4.c Si la vida no llega a 0 el monstruo hace daño al personaje
     * 5. Las salas pueden tener trampas
     * 5.a Si hay trampa hay que comprobar si un valor aleatorio entre 1 y 50 es
     * inferior a la destreza del personaje, si es asi esquiva la trampa
     * 5.b Si no esquiva la trampa el personaje recibe daño
     * 5.c al igual que en combate hay que tener en cuenta si la vida del personaje
     * lleva a 0
     * 6. Por último puede haber items en la sala, en cuyo caso habrá que preguntar
     * al usuario qué ítems quiere guardarse (o NINGUNO para terminar)
     * ¡IMPORTANTE! se debe mostrar por pantalla avisos para cada opción dando
     * feedback al usuario de todo lo que ocurra (consultar enunciado)
     * 
     * @param teclado teclado
     * @param personaje personaje
     * @param random numero aleatorio
     */
    public void jugar(Scanner teclado, Personaje personaje, Random random) {
        boolean salir = false;
        System.out.println(mostrarMapa(0, 0)); //mostramos el mapa en la posicion 0,0 que es en la que empieza el personaje
        Sala salaActual = mapa[0][0];
        while (personaje.getVida() > 0 && salaActual.getFila() <= mapa.length - 1
        && salaActual.getColumna() <= mapa[0].length - 1 && salir == false) { //mientras el personaje tenga vida y no haya llegado a la sala final o no lo hayan matado
            System.out.println(salaActual.getDescripcion()); //mostramos la descripcion de la sala
            while (salaActual.hayMonstruos()) { //mientras haya monstruos en la sala
                Monstruo monstruo = salaActual.seleccionarMonstruo(teclado); //seleccionamos un monstruo
                while (monstruo == null) { //mientras no seleccionemos un monstruo
                    monstruo = salaActual.seleccionarMonstruo(teclado); //volvemos a seleccionar un monstruo
                }
                while (personaje.getVida() > 0 && monstruo.getVida() > 0) { //mientras el personaje y el monstruo tengan vida
                    System.out.println(personaje.toString()+ " ataca a "+ monstruo.toString() +" con "+ personaje.getAtaque() + " puntos de daño"); //el personaje ataca al monstruo
                    monstruo.recibirDanyo(personaje.getAtaque()); //el monstruo recibe daño
                    if (monstruo.getVida() > 0) { //si el monstruo sigue vivo
                        personaje.recibirDanyo(monstruo.getAtaque()); //el personaje recibe daño
                    }
                    if (monstruo.getVida() <= 0) { //si el monstruo muere
                        System.out.println("¡Has derrotado al monstruo!"); //mostramos que hemos derrotado al monstruo
                        salaActual.eliminarMonstruo(monstruo.getNombre()); //eliminamos el monstruo de la sala
                        System.out.println("Tienes " + personaje.getVida() + " puntos de vida actualmente"); //mostramos los puntos de vida del personaje
                        if (personaje.getVida() <= 0) { //si el personaje muere
                            System.out.println("El mounstro te ha eliminado, fin del juego"); //mostramos que el monstruo nos ha eliminado
                            salir = true; //salimos del bucle
                            return;
                        }
                    }
                }
            }

            if (salaActual.hayTrampas()) { //si hay trampas en la sala
                for (int i = 0; i < salaActual.getTrampas().length; i++) { //recorremos las trampas
                    if (salaActual.getTrampas()[i] != null) { //si hay trampa
                        if (random.nextInt(50) < personaje.getDestreza()) { //si el numero aleatorio es menor que la destreza del personaje
                            System.out.println("¡Has esquivado la trampa! "+ salaActual.getTrampas()[i].getDescripcion()); //mostramos que hemos esquivado la trampa
                        } else { //si no es menor
                            System.out.println("¡Has caído en una trampa! "+ salaActual.getTrampas()[i].getDescripcion()); //mostramos que hemos caido en la trampa
                            System.out.println("Te ha hecho " + salaActual.getTrampas()[i].getDanyo() + " puntos de daño"); //mostramos los puntos de daño
                            personaje.recibirDanyo(salaActual.getTrampas()[i].getDanyo()); //el personaje recibe daño
                            if (personaje.getVida() <= 0) { //si el personaje muere
                                System.out.println("Has caido en una trampa y no has sobrevivido, fin del juego"); //mostramos que hemos muerto
                                salir = true; //salimos del bucle
                                return;
                            }
                        }
                    }
                }
            }
            if (salaActual.hayItems()) { //si hay items en la sala
                Item item;
                item = salaActual.seleccionarItem(teclado); //seleccionamos un item
                while (item != null) { //mientras no seleccionemos un item
                    boolean itemAñadido = personaje.anyadirItem(item); //añadimos el item a la mochila
                    if (itemAñadido) { //si se ha añadido
                        salaActual.eliminarItem(item.getDescripcion()); //eliminamos el item de la sala
                        System.out.println("¡Te guardas el objeto! "+ item.toString()); //mostramos que nos guardamos el objeto
                        System.out.println("Mochila de "+ personaje.getNombre() + ": "+ personaje.infoMochila()); //mostramos la mochila del personaje
                    }else{ //si no se ha añadido
                        System.out.println("No puedes añadir el item "+ item.toString() +" a tu mochila"); //mostramos que no se ha podido añadir el item
                    }
                    item = salaActual.seleccionarItem(teclado); //volvemos a seleccionar un item
                }
            }
            if(salaActual.getFila() == mapa.length - 1 && salaActual.getColumna() == mapa[0].length - 1){ //si llegamos a la sala final
                System.out.println("Has llegado a la sala final y has salido victorioso fin del juego"); //mostramos que hemos llegado a la sala final
                salir = true; //salimos del bucle
                return; 
            }

            salaActual = seleccionarMovimiento(teclado, salaActual); //seleccionamos un movimiento
            System.out.println(mostrarMapa(salaActual.getFila(), salaActual.getColumna())); //mostramos el mapa en la posicion de la sala actual
        }
    }

    /**
     * Metodo seleccionarMovimiento para establecer las acciones que tome el jugador
     * con su personaje
     * El desplazamiento del personaje se entiende como norte (N), sur (S),
     * este (E) u oeste (O)
     * en este método hay que capturar por pantalla la acción que va a tomar el
     * usuario de entre las posibles
     * para ello hay que tener en cuenta que se debe avisar al usuario si puede
     * realizar o no la acción.
     * Se devolverá la sala destino a la que se ha movido el personaje.
     * 
     * @param teclado teclado
     * @param salaActual salaActual en la que se encuentra el personaje
     * @return sala nueva a la que se va a dirgir el personaje
     */
    public Sala seleccionarMovimiento(Scanner teclado, Sala salaActual) {
        Sala nuevaSala; //sala a la que se va a mover el personaje
        do { //hacemos un bucle para que se repita mientras la nueva sala sea la misma que la actual
            System.out.println("Introduce el movimiento (N, E, S, O):");
            String movimiento = teclado.nextLine();
            switch (movimiento) {
                case "N": //si el movimiento es norte
                    if (!existeSala(salaActual.getFila() - 1, salaActual.getColumna())) { //si no existe sala al norte (es decir no hay fila arriba)
                        System.out.println("No puedes moverte al norte");
                        nuevaSala = salaActual; //la nueva sala es la misma en la que estamos
                    } else { //sino
                        nuevaSala = mapa[salaActual.getFila() - 1][salaActual.getColumna()];  //se mueve al norte
                    }
                    break;
                case "S": //si quiere moverse al sur
                    if (!existeSala(salaActual.getFila() + 1, salaActual.getColumna())) { //si no existe el sur (es decir si no hay fila abajo)
                        System.out.println("No puedes moverte al sur");
                        nuevaSala = salaActual; //la nueva sala es la misma en la que estamos
                    } else { //sino
                        nuevaSala = mapa[salaActual.getFila() + 1][salaActual.getColumna()]; //se mueve al sur
                    }
                    break;
                case "E": //si quiere moverse al este
                    if (!existeSala(salaActual.getFila(), salaActual.getColumna() + 1)) { //si no existe sala al este (es decir si no hay columna a la derecha)
                        System.out.println("No puedes moverte al este"); 
                        nuevaSala = salaActual; //la nueva sala es la misma en la que estamos
                    } else { //sino
                        nuevaSala = mapa[salaActual.getFila()][salaActual.getColumna() + 1]; //se mueve al este
                    }
                    break;
                case "O": //si quiere moverse al oeste
                    if (!existeSala(salaActual.getFila(), salaActual.getColumna() - 1)) { //si no existe sala al oeste (es decir si no hay columna a la izquierda)
                        System.out.println("No puedes moverte al oeste");
                        nuevaSala = salaActual; //la nueva sala es la misma en la que estamos
                    } else { //sino
                        nuevaSala = mapa[salaActual.getFila()][salaActual.getColumna() - 1]; //se mueve al oeste
                    }
                    break;
                default: //si no introduce un movimiento válido
                    System.out.println("Movimiento no válido");
                    nuevaSala = salaActual; //la nueva sala es la misma en la que estamos
                    break;
            }
        } while (nuevaSala == salaActual);
        return nuevaSala; //devolvemos la nueva sala
    }
}
