/**
 * Clase Monstruo
 */
public class Monstruo {
    private int vida;
    private final int ataque;
    private final int defensa;
    private final String nombre;

    /**
     * Constructor clase Monstruo
     * 
     * @param nombre nombre del monstruo
     * @param vida puntos de vida del monstruo
     * @param ataque puntos de ataque del monstruo
     * @param defensa puntos de defensa del monstruo
     */
    public Monstruo(String nombre, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;

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
     * Método getNombre
     * 
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método recibirDanyo para calcular la vida restante
     * actualizar la vida restante del monstruo despues de un ataque, siempre que el
     * valor de ataque sea positivo
     * 
     * @param ataque
     */
    public void recibirDanyo(int ataque) {
        ataque = ataque - defensa; //El daño que recibe el monstruo es el ataque del jugador menos la defensa del monstruo
        if (ataque > 0) { //Si el ataque es mayor que 0, se le resta a la vida del monstruo
            vida -= ataque;
        }
        if (vida < 0) { //Si la vida del monstruo es menor que 0, se pone a 0
            vida = 0; //La vida del monstruo no puede ser negativa
            
        }
    }

    /**
     * Método sobreescrito para devolver la información de un monstruo
     * Método para devolver un String con la información del monstruo en el formato
     * descrito en la memoria de la práctica P.e: "[ Trasgo (V: 20, A: 5, D: 2) ]"
     * 
     * @return nombre vida ataque y defensa del monstruo
     */
    @Override
    public String toString() {
        return "[ "+nombre + " (V: " + vida + ", A: " + ataque + ", D: " + defensa + ") ]";
    }

    /**
     * Método que sobreescribe el comportamiento de equals
     * Método para comparar si el objeto pasado como parámetro es igual a este,
     * hay que comparar los parámetros internos del objeto (nombre, vida, ataque,
     * defensa)
     * 
     * @param obj obejeto monstruo
     * @return True en caso de ser igual, false en otro caso
     */
    @Override
    // CUIDADO: NO SABEMOS COMO FUNCIONA LOS DE OBJECT ASI QUE NO SABEMOS SI ESTA
    // BIEN, PREGUNTAR EN TUTORIA
    public boolean equals(Object obj) { //Son iguales si cualquier objeto pasado como parámetro es igual a este en nombre, vida, ataque y defensa
        Monstruo monstruo = (Monstruo) obj;
        return monstruo.getAtaque() == getAtaque() && monstruo.getVida() == getVida()
                && monstruo.getDefensa() == getDefensa()
                && monstruo.getNombre().equals(getNombre());
    }
}
