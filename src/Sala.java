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
     * @param descripcion
     * @param max_items
     * @param max_monstruos
     * @param maxTrampasPorSala
     * @param fila
     * @param columna
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
     * @return
     */
    public boolean agregarItem(Item item) {
        boolean resul = true;
        for (int i = 0; i < numeroItems; i++) {
            if (items[i] == item) {
                resul = false;
            }
        }
        if (resul) {
            items[numeroItems] = item;
            numeroItems++;
        }
        return resul;
    }

    /**
     * Método agregarMonstruo para incluir un monstruo en la sala
     * comprobar si existe el monstruo en la sala o si la lista de monstruos no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param monstruo
     * @return
     */
    public boolean agregarMonstruo(Monstruo monstruo) {
        boolean resultado = true;
    for (int i = 0; i < numeroMonstruos; i++) {
        if (monstruos[i] == monstruo) {
            resultado = false;
        }
    }
    if (resultado && numeroMonstruos < monstruos.length) {
        monstruos[numeroMonstruos] = monstruo;
        numeroMonstruos++;
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
     * @return
     */
    public boolean agregarTrampa(Trampa trampa) {
        boolean resultado = true;
        for (int i = 0; i < numeroTrampas; i++) {
            if (trampas[i] == trampa) {
                resultado = false;
            }
        }
        if (resultado) {
            trampas[numeroTrampas] = trampa;
            numeroTrampas++;
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
     * @return
     */
    public boolean hayMonstruos() {
        for (int i = 0; i < monstruos.length; i++) {
            if (monstruos[i] != null) {
                return true;
            }
        }
        return false;
    }


    /**
     * Método seleccionarMonstruo para introducir desde pantalla el nombre de un monstruo
     * Mostrar por pantalla todos los monstruos y luego solicitar que se introduzca el nombre del monstruo que se
     *  quiere seleccionar.
     * @param teclado
     * @return
     */
    public Monstruo seleccionarMonstruo(Scanner teclado) {
        Monstruo monstruo = null;
        String string;
        if(hayMonstruos()){
            listarMonstruos();
            do{
                string =Utilidades.leerCadena(teclado, "Escribe el nombre del monstruo que quieres atacar: ");
                monstruo = buscarMonstruo(string);
                if(monstruo == null){
                    System.out.println("No se encontró el monstruo. Por favor, intenta de nuevo.");
                }
            }while(monstruo == null);
        }
        return monstruo;
    }

    /**
     * Método buscarMonstruo para buscar un monstruo dado el nombre del mismo
     * devolver el monstruo según el nombre pasado como parámetro o devolver null si no se encuentra
     * @param nombreMonstruo
     * @return
     */
    public Monstruo buscarMonstruo(String nombreMonstruo) {
        if (monstruos != null) {
            for (int i = 0; i < monstruos.length; i++) {
                if (monstruos[i] != null && monstruos[i].getNombre().toLowerCase().contains(nombreMonstruo.toLowerCase())) {
                    return monstruos[i];
                }
            }
        }
        return null;
    }

    /**
     * Método listarMonstruos para mostrar por pantalla la información de los monstruos
     * mostrar por pantalla la info de los monstruos utilizando los métodos implementados en la clase "monstruo"
     */
    private void listarMonstruos() {
        System.out.println("Monstruos en la sala: ");
        for (int i = 0; i < monstruos.length; i++) {
            if (monstruos[i] != null) {
                System.out.println(monstruos[i].toString());
            }
        }
    }

    /**
     * Método eliminarMonstruo para eliminar un monstruo de la lista segun un nombre dado
     * buscar en la lista el monstruo segun el nombre pasado como parámetro y eliminarlo.
     * @param nombreMonstruo
     */
    public void eliminarMonstruo(String nombreMonstruo) {
        int pos = -1;
    boolean encontrado = false;
    for (int i = 0; i < monstruos.length && !encontrado; i++) {
        if (monstruos[i] != null && monstruos[i].getNombre().equals(nombreMonstruo)) {
            pos = i;
            encontrado = true;
        }
    }
    if (encontrado) {
        for (int i = pos; i < monstruos.length - 1; i++) {
            monstruos[i] = monstruos[i + 1];
            numeroMonstruos--;
        }
        monstruos[monstruos.length - 1] = null;
    }
    }

    /**
 * Método existeMonstruo para verificar si un monstruo existe en la sala
 * @param nombreMonstruo el nombre del monstruo a verificar
 * @return true si el monstruo existe, false en caso contrario
 */
public boolean existeMonstruo(String nombreMonstruo) {
    for (int i = 0; i < monstruos.length; i++) {
        if (monstruos[i] != null && monstruos[i].getNombre().equals(nombreMonstruo)) {
            return true;
        }
    }
    return false;
}
    /**
     * Método hayTrampas para saber si la sala dispone de alguna trampa
     * mostrar si existe alguna trampa en la sala, false en caso contrario
     * @return
     */
    public boolean hayTrampas() {
        for (int i = 0; i < trampas.length; i++) {
            if(trampas[i] != null){
                return true;
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
     * @return
     */
    public boolean hayItems() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
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
     * @return
     */
    public Item buscarItem(String descripcion) {
        for (int i = 0; i < numeroItems; i++) {
            if (items[i].getDescripcion().toLowerCase().contains(descripcion.toLowerCase())) {
                return items[i];
            }
        }
        return null;
    }

    /**
     * Método buscarTrampa para obtener una trampa según una descripcion dada
     * buscar en la lista de trampas una trampa con la descripción pasada como parámetro, devolver null si no lo
     *  encuentra
     * @param descripcion
     * @return
     */
    public Trampa buscarTrampa(String descripcion) {
        Trampa trampa = null;
        for (int i = 0; i < trampas.length; i++) {
            if (trampas[i].getDescripcion().equals(descripcion)) {
                trampa = trampas[i];
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
     * @return
     */
    public Item seleccionarItem(Scanner teclado) {
        Item item = null;
    String string;
    if(hayItems()){
        System.out.println("Items que se encuentran en la sala "+ descripcion+":");
        listarItems();
        do {
            string = Utilidades.leerCadena(teclado, "Escribe la descripción del item que quieres coger (NINGUNO  para cancelar):");
            item = buscarItem(string);
            if (item == null && !string.equals("NINGUNO")) {
                System.out.println("No se encontró el item. Por favor, intenta de nuevo.");
            }
        } while (!string.equals("NINGUNO") && item == null);
    }
    return item;
    }

    /**
     * Método listarItems para mostrar por pantalla todos los items
     * utilizar las funciones de la clase Item para poder mostrar por pantalla toda la información de todos los
     *  items que hay en la sala
     */
    private void listarItems() {
        if(hayItems()){
            for (int i = 0; i < numeroItems; i++) {
                System.out.println(items[i].toString());
            }
        }
        
    }

    /**
     * Método eliminarItem para eliminar un item con la descripcion pasada como parámetro
     * buscar el item que coincida con la descripción pasada por parámetro y eliminarlo de la lista de items
     * @param descripcion
     */
    public void eliminarItem(String descripcion) {
        int pos = 0;
        while (!(items[pos].getDescripcion().equals(descripcion)) && items[pos] != null) {
            pos++;
        }
        for (int i = pos; i < numeroItems; i++) {
            items[i] = items[1 + i];
            numeroItems--;
        }
    }
}
