import java.util.Scanner;

/**
 * Clase Sala
 */
public class Sala {
    private final String descripcion;
    private final Item[] items;
    private final Monstruo[] monstruos;
    private final Trampa[] trampas;

    private final int fila;
    private final int columna;
    private int numeroMonstruos, numeroTrampas, numeroItems;

    /**
     * Constructor de clase para inicializar los atributos de clase
     * @param descripcion  descripción de la sala
     * @param max_items   número máximo de items que puede tener la sala
     * @param max_monstruos número máximo de monstruos que puede tener la sala
     * @param maxTrampasPorSala número máximo de trampas que puede tener la sala
     * @param fila fila 
     * @param columna columna
     */
    public Sala(String descripcion, int max_items, int max_monstruos, int maxTrampasPorSala, int fila, int columna) {
        this.descripcion = descripcion;
        items = new Item[max_items];
        monstruos = new Monstruo[max_monstruos];
        trampas = new Trampa[maxTrampasPorSala];
        this.fila = fila;
        this.columna = columna;

    }

    /**
     * Método agregarItem para incluir items en la sala
     * comprobar si existe el objeto en la sala o si la lista de items no está ya llena en caso afirmativo
     * devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param item
     * @return boolean resultado
     */
    public boolean agregarItem(Item item) {
        boolean resul = true;
        for (int i = 0; i < numeroItems; i++) { //recorrer la lista de items
            if (items[i] == item) { //Si el item ya está en la lista
                resul = false; //No se puede añadir
            }
        }
        if (resul) { //si se puede añadir
            items[numeroItems] = item; //lo añado en la posicion que le toca
            numeroItems++; //incremento el numero de items
        }
        return resul;
    }

    /**
     * Método agregarMonstruo para incluir un monstruo en la sala
     * comprobar si existe el monstruo en la sala o si la lista de monstruos no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param monstruo
     * @return boolean resultado
     */
    public boolean agregarMonstruo(Monstruo monstruo) {
        boolean resultado = true;
    for (int i = 0; i < numeroMonstruos; i++) { //recorrer la lista de monstruos
        if (monstruos[i] == monstruo) { //Si el monstruo ya está en la lista
            resultado = false; //No se puede añadir
        }
    }
    if (resultado && numeroMonstruos < monstruos.length) { //si se puede añadir
        monstruos[numeroMonstruos] = monstruo; //lo añado en la posicion que le toca
        numeroMonstruos++; //incremento el numero de monstruos
    } else {
        resultado = false;
    }
    return resultado;
    }

    /**
     * Método agregarTrampa para incluir una trampa en la sala
     * comprobar si existe la trampa en la sala o si la lista de trampas no está ya llena en caso afirmativo
     * devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param trampa
     * @return boolean resultado
     */
    public boolean agregarTrampa(Trampa trampa) {
        boolean resultado = true;
        for (int i = 0; i < numeroTrampas; i++) { //recorrer la lista de trampas
            if (trampas[i] == trampa) { //Si la trampa ya está en la lista
                resultado = false; //No se puede añadir
            }
        }
        if (resultado) { //si se puede añadir
            trampas[numeroTrampas] = trampa; //lo añado en la posicion que le toca
            numeroTrampas++; //incremento el numero de trampas
        }
        return resultado;
    }

    /**
     * Método getDescripcion
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método hayMonstruos para comprobar si hay algún monstruo en la sala
     * comprobar si hay algún monstruo en la lista
     * @return boolean resultado 
     */
    public boolean hayMonstruos() {
        for (int i = 0; i < monstruos.length; i++) { //recorrer la lista de monstruos
            if (monstruos[i] != null) { //si hay un monstruo (es decir cualquiera de las posiciones de la lista no es null)
                return true; //devolver true
            }
        }
        return false;
    }


    /**
     * Método seleccionarMonstruo para introducir desde pantalla el nombre de un monstruo
     * Mostrar por pantalla todos los monstruos y luego solicitar que se introduzca el nombre del monstruo que se
     *  quiere seleccionar.
     * @param teclado
     * @return Monstruo seleccionado a partir del nombre
     */
    public Monstruo seleccionarMonstruo(Scanner teclado) { 
        Monstruo monstruo = null; //creo un nuevo monstruo que inicializo a null
        String string;
        if(hayMonstruos()){ //si hay mounstros en la sala
            listarMonstruos(); //listo los monstruos
            do{ //hago lo de abajo
                string =Utilidades.leerCadena(teclado, "Escribe el nombre del monstruo que quieres atacar: "); //pido el nombre del monstruo
                monstruo = buscarMonstruo(string);
                if(monstruo == null){ //si el monstruo no existe
                    System.out.println("No se encontró el monstruo. Por favor, intenta de nuevo."); //muestro un mensaje de error
                }
            }while(monstruo == null); //hasta q se seleccione un monstruo
        }
        return monstruo; //devuelvo el monstruo
    }

    /**
     * Método buscarMonstruo para buscar un monstruo dado el nombre del mismo
     * devolver el monstruo según el nombre pasado como parámetro o devolver null si no se encuentra
     * @param nombreMonstruo
     * @return monstruo buscado
     */
    public Monstruo buscarMonstruo(String nombreMonstruo) {
        if (monstruos != null) { //si hay monstruos en la sala
            for (int i = 0; i < monstruos.length; i++) { //recorrer la lista de monstruos
                if (monstruos[i] != null && monstruos[i].getNombre().toLowerCase().contains(nombreMonstruo.toLowerCase())) { //si el nombre del monstruo 
                                                                                                                            // coincide con el nombre pasado como 
                                                                                                                            //parámetro utilizando el metodo contains
                    return monstruos[i]; //devolver el monstruo
                }
            }
        }
        return null; //sino, devolver null
    }

    /**
     * Método listarMonstruos para mostrar por pantalla la información de los monstruos
     * mostrar por pantalla la info de los monstruos utilizando los métodos implementados en la clase "monstruo"
     */
    private void listarMonstruos() {
        System.out.println("Monstruos en la sala: ");
        for (int i = 0; i < monstruos.length; i++) { //recorrer la lista de monstruos
            if (monstruos[i] != null) { //si hay un monstruo en la posición i
                System.out.println(monstruos[i].toString()); //hago el toString del monstruo
            }
        }
    }

    /**
     * Método eliminarMonstruo para eliminar un monstruo de la lista segun un nombre dado
     * buscar en la lista el monstruo segun el nombre pasado como parámetro y eliminarlo.
     * @param nombreMonstruo nombre del monstruo a eliminar
     */
    public void eliminarMonstruo(String nombreMonstruo) {
        int pos = -1; //inicializo la posición a -1
    boolean encontrado = false;
    for (int i = 0; i < monstruos.length && !encontrado; i++) { //recorrer la lista de monstruos mientras q no lo haya encontrado
        if (monstruos[i] != null && monstruos[i].getNombre().equals(nombreMonstruo)) { //busco el nombre del monstruo (igual que el metodo buscarMonstruo)
            pos = i; //me guardo la posicion del monstruo a eliminar
            encontrado = true;
        }
    }
    if (encontrado) { //una vez encontrado
        for (int i = pos; i < monstruos.length - 1; i++) { //recorro la lista de monstruos desde la posición del monstruo a eliminar
                                                        //hasta el final de la lista -1
            monstruos[i] = monstruos[i + 1]; //la posicion del monstruo a eliminar la igualo a la siguiente y asi en bucle
            numeroMonstruos--;
        }
        monstruos[monstruos.length - 1] = null; //pongo la última posición de la lista a null
    }
    }

    /**
 * Método existeMonstruo para verificar si un monstruo existe en la sala
 * @param nombreMonstruo el nombre del monstruo a verificar
 * @return true si el monstruo existe, false en caso contrario
 */
public boolean existeMonstruo(String nombreMonstruo) {
    for (int i = 0; i < monstruos.length; i++) { //recorrer la lista de monstruos
        if (monstruos[i] != null && monstruos[i].getNombre().equals(nombreMonstruo)) { //si el monstruo no es null y el nombre del monstruo coincide 
                                                                                       //con el nombre pasado como parámetro
            return true;
        }
    }
    return false;
}
    /**
     * Método hayTrampas para saber si la sala dispone de alguna trampa
     * mostrar si existe alguna trampa en la sala, false en caso contrario
     * @return boolean resultado
     */
    public boolean hayTrampas() {
        for (int i = 0; i < trampas.length; i++) { //recorrer la lista de trampas
            if(trampas[i] != null){ //si hay una trampa en la posición i
                return true; //devolver true
            }
        }
        return false;
    }

    /**
     * Método getFila
     * @return int fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Método getColumna
     * @return int columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método hayItems para mostrar si existe algún item en la sala
     * buscar si hay algún item en la lista de items, false en caso contrario
     * @return boolean resultado si hay items en la sala
     */
    public boolean hayItems() {
        for (int i = 0; i < items.length; i++) { //recorrer la lista de items
            if (items[i] != null) { //si hay un item en la posición i
                return true;
            }
        }
        return false;
    }

    /**
     * Método buscarItem para obtener un item según una descripcion dada
     * buscar en la lista de items un item con la descripción pasada como parámetro, devolver null si no lo
     * encuentra
     * @param descripcion
     * @return Item item buscado según la descripción
     */
    public Item buscarItem(String descripcion) {
        for (int i = 0; i < numeroItems; i++) { //recorrer la lista de items
            if (items[i].getDescripcion().toLowerCase().contains(descripcion.toLowerCase())) { //si la descripción del item coincide con la 
                                                                                               //descripción pasada como parámetro
                return items[i]; //devolver el item
            }
        }
        return null;
    }

    /**
     * Método buscarTrampa para obtener una trampa según una descripcion dada
     * buscar en la lista de trampas una trampa con la descripción pasada como parámetro, devolver null si no lo
     *  encuentra
     * @param descripcion
     * @return Trampa trampa buscada según la descripción
     */
    public Trampa buscarTrampa(String descripcion) {
        Trampa trampa = null;
        for (int i = 0; i < trampas.length; i++) { //recorrer la lista de trampas
            if (trampas[i].getDescripcion().equals(descripcion)) { //si la descripción de la trampa coincide con la descripción pasada como parámetro
                trampa = trampas[i]; //devolver la trampa
            }
        }
        return trampa;
    }

    /**
     * Método getTrampas
     * @return Trampa[] trampas
     */
    public Trampa[] getTrampas() {
        return trampas;
    }

    /**
     * Método seleccionarItem para obtener un item concreto con parámetro pasados por pantalla
     * Mostrar por pantalla todos los items de la sala para despues pedir que se introduzca una descripcion del
     *  item que se quiere seleccionar
     * @param teclado
     * @return Item item seleccionado según la descripción
     */
    public Item seleccionarItem(Scanner teclado) {
        Item item = null;
    String string;
    if(hayItems()){ //si hay items en la sala
        System.out.println("Items que se encuentran en la sala "+ descripcion+":");
        listarItems(); //listo los items
        do { //haces lo de abajo
            string = Utilidades.leerCadena(teclado, "Escribe la descripción del item que quieres coger (NINGUNO  para cancelar):");
            item = buscarItem(string);
            if (item == null && !string.equals("NINGUNO")) {
                System.out.println("No se encontró el item. Por favor, intenta de nuevo.");
            }
        } while (!string.equals("NINGUNO") && item == null); //mientras que el usuario no escriba NINGUNO o no haya mas items en la sala
    }
    return item;
    }

    /**
     * Método listarItems para mostrar por pantalla todos los items
     * utilizar las funciones de la clase Item para poder mostrar por pantalla toda la información de todos los
     *  items que hay en la sala
     */
    private void listarItems() {
        if(hayItems()){ //si hay items en la sala
            for (int i = 0; i < numeroItems; i++) { //recorro la array de items
                System.out.println(items[i].toString()); //muestro la información de los items
            }
        }
        
    }

    /**
     * Método eliminarItem para eliminar un item con la descripcion pasada como parámetro
     * buscar el item que coincida con la descripción pasada por parámetro y eliminarlo de la lista de items
     * @param descripcion descripción del item a eliminar
     */
    public void eliminarItem(String descripcion) { 
        int pos = 0; //inicializo la posición a 0
        while (!(items[pos].getDescripcion().equals(descripcion)) && items[pos] != null) { //mientras que la descripción del item no coincida con 
                                                                                         //la descripción pasada como parámetro y el item no sea null
            pos++; //incremento la posición
        }
        for (int i = pos; i < numeroItems; i++) { //recorro la lista de items desde la posición del item a eliminar
                                                //hasta el final de la lista
            items[i] = items[1 + i]; //la posición del item a eliminar la igualo a la siguiente y asi en bucle
            numeroItems--; // decremento el número de items
        }
    }
}
