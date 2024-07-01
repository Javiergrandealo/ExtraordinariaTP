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
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(ficheroMapa));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int filas = Integer.parseInt(partes[0]);
                    int columnas = Integer.parseInt(partes[1]);
                    String descripcion = partes[2];
                    Sala sala = new Sala(descripcion, maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala, filas,
                            columnas);
                    if (filas < mapa.length && columnas < mapa[filas].length) {
                        mapa[filas][columnas] = sala;
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
        return mapa;
    }

    /**
     * Metodo cargarItems para agregar los items del fichero en el mapa
     * Método para leer un fichero de items pasado por parámetro y según
     * la fila y columna introducir el item en la sala.
     * 
     * @param ficheroItems fichero con los items
     */
    private void cargarItems(String ficheroItems) {
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(ficheroItems));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                String descripcion = partes[2];
                Double valor = Double.parseDouble(partes[3]);
                int peso = Integer.parseInt(partes[4]);
                Item item = new Item(descripcion, valor, peso);
                if (fila < mapa.length && columna < mapa[fila].length && mapa[fila][columna] != null) {
                    mapa[fila][columna].agregarItem(item);
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
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(ficheroMonstruos));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                String descripcion = partes[2];
                int vida = Integer.parseInt(partes[3]);
                int ataque = Integer.parseInt(partes[4]);
                int defensa = Integer.parseInt(partes[5]);
                Monstruo monstruo = new Monstruo(descripcion, vida, ataque, defensa);
                if (fila < mapa.length && columna < mapa[fila].length && mapa[fila][columna] != null) {
                    mapa[fila][columna].agregarMonstruo(monstruo);
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
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(ficheroTrampas));
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] partes = linea.split(";");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                String descripcion = partes[2];
                int dano = Integer.parseInt(partes[3]);
                Trampa trampa = new Trampa(descripcion, dano);
                mapa[fila][columna].agregarTrampa(trampa);
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
        int filasMapa = mapa.length;
        int columnasMapa = mapa[0].length;
        String a = "";

        a += "╔";
        for (int i = 0; i < columnasMapa; i++) {
            a += "═";
        }
        a += "╗\n";
        for (int i = 0; i < filasMapa; i++) {
            a += "║";
            for (int j = 0; j < columnasMapa; j++) {
                if (i == fila && j == columna) {
                    a += "@"; // posicion del personaje
                } else if (existeSala(i, j)) {
                    a += "░";
                } else {
                    a += " ";
                }
            }
            a += "║\n";
        }
        a += "╚";
        for (int i = 0; i < columnasMapa; i++) {
            a += "═";
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
        System.out.println(mostrarMapa(0, 0));
        Sala salaActual = mapa[0][0];
        while (personaje.getVida() > 0 && salaActual.getFila() <= mapa.length - 1
        && salaActual.getColumna() <= mapa[0].length - 1 && salir == false) {
            System.out.println(salaActual.getDescripcion());
            while (salaActual.hayMonstruos()) {
                Monstruo monstruo = salaActual.seleccionarMonstruo(teclado);
                while (monstruo == null) {
                    monstruo = salaActual.seleccionarMonstruo(teclado);
                }
                while (personaje.getVida() > 0 && monstruo.getVida() > 0) {
                    System.out.println(personaje.toString()+ " ataca a "+ monstruo.toString() +" con "+ personaje.getAtaque() + " puntos de daño");
                    monstruo.recibirDanyo(personaje.getAtaque());
                    if (monstruo.getVida() > 0) {
                        personaje.recibirDanyo(monstruo.getAtaque());
                    }
                    if (monstruo.getVida() <= 0) {
                        System.out.println("¡Has derrotado al monstruo!");
                        salaActual.eliminarMonstruo(monstruo.getNombre());
                        System.out.println("Tienes " + personaje.getVida() + " puntos de vida actualmente");
                        if (personaje.getVida() <= 0) {
                            System.out.println("El mounstro te ha eliminado, fin del juego");
                            salir = true;
                            return;
                        }
                    }
                }
            }

            if (salaActual.hayTrampas()) {
                for (int i = 0; i < salaActual.getTrampas().length; i++) {
                    if (salaActual.getTrampas()[i] != null) {
                        if (random.nextInt(50) < personaje.getDestreza()) {
                            System.out.println("¡Has esquivado la trampa! "+ salaActual.getTrampas()[i].getDescripcion());
                        } else {
                            System.out.println("¡Has caído en una trampa! "+ salaActual.getTrampas()[i].getDescripcion());
                            System.out.println("Te ha hecho " + salaActual.getTrampas()[i].getDanyo() + " puntos de daño");
                            personaje.recibirDanyo(salaActual.getTrampas()[i].getDanyo());
                            if (personaje.getVida() <= 0) {
                                System.out.println("Has caido en una trampa y no has sobrevivido, fin del juego");
                                salir = true;
                                return;
                            }
                        }
                    }
                }
            }
            if (salaActual.hayItems()) {
                Item item;
                item = salaActual.seleccionarItem(teclado);
                while (item != null) {
                    boolean itemAñadido = personaje.anyadirItem(item);
                    if (itemAñadido) {
                        salaActual.eliminarItem(item.getDescripcion());
                        System.out.println("¡Te guardas el objeto! "+ item.toString());
                        System.out.println("Mochila de "+ personaje.getNombre() + ": "+ personaje.infoMochila());
                    }else{
                        System.out.println("No puedes añadir el item "+ item.toString() +" a tu mochila");
                    }
                    item = salaActual.seleccionarItem(teclado);
                }
            }
            if(salaActual.getFila() == mapa.length - 1 && salaActual.getColumna() == mapa[0].length - 1){
                System.out.println("Has llegado a la sala final y has salido victorioso fin del juego");
                salir = true;
                return;
            }

            salaActual = seleccionarMovimiento(teclado, salaActual);
            System.out.println(mostrarMapa(salaActual.getFila(), salaActual.getColumna()));
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
        Sala nuevaSala;
        do {
            System.out.println("Introduce el movimiento (N, E, S, O):");
            String movimiento = teclado.nextLine();
            switch (movimiento) {
                case "N":
                    if (!existeSala(salaActual.getFila() - 1, salaActual.getColumna())) {
                        System.out.println("No puedes moverte al norte");
                        nuevaSala = salaActual;
                    } else {
                        nuevaSala = mapa[salaActual.getFila() - 1][salaActual.getColumna()];
                    }
                    break;
                case "S":
                    if (!existeSala(salaActual.getFila() + 1, salaActual.getColumna())) {
                        System.out.println("No puedes moverte al sur");
                        nuevaSala = salaActual;
                    } else {
                        nuevaSala = mapa[salaActual.getFila() + 1][salaActual.getColumna()];
                    }
                    break;
                case "E":
                    if (!existeSala(salaActual.getFila(), salaActual.getColumna() + 1)) {
                        System.out.println("No puedes moverte al este");
                        nuevaSala = salaActual;
                    } else {
                        nuevaSala = mapa[salaActual.getFila()][salaActual.getColumna() + 1];
                    }
                    break;
                case "O":
                    if (!existeSala(salaActual.getFila(), salaActual.getColumna() - 1)) {
                        System.out.println("No puedes moverte al oeste");
                        nuevaSala = salaActual;
                    } else {
                        nuevaSala = mapa[salaActual.getFila()][salaActual.getColumna() - 1];
                    }
                    break;
                default:
                    System.out.println("Movimiento no válido");
                    nuevaSala = salaActual;
                    break;
            }
        } while (nuevaSala == salaActual);
        return nuevaSala;
    }
}
