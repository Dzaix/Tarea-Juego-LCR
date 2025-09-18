
/**
 * Jugador de Left-Center-Right.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (1.0 agosto 2025)
 */
public class Jugador
{
    private int fichas;
    private String nombre;
    
    /**
     * Crea al jugador con 3 fichas.
     */
    public Jugador() {
        fichas = 3;
    }
    
    /**
     * Cambia el nombre del jugador.
     * @param nombre El nombre que tendrá el jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el nombre del jugador.
     * @return el nombre actual del jugador.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la cantidad de fichas que tiene el jugador.
     * @return fichas que tiene el jugador.
     */
    public int getFichas() {
        return fichas;
    }
    
    /**
     * Resta fichas a las que tiene.
     * @param las fichas que se van a quitar.
     */
    public void retirarFichas(int cuantas) {
        fichas -= cuantas;
    }
    /**
     * Agrega fichas a las que tiene.
     * @param las fichas que se van a agregar.
     */
    public void agregarFichas(int cantidad) {
        fichas += cantidad;
    }
}