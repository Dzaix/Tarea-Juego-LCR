
/**
 * Modela el centro del juego.
 *
 * @author (Cecilia Curlango)
 * @version 1.0 (agosto 2025)
 */
public class Centro
{
    private int fichas;
    
    public Centro() {
        fichas = 0;
    }
    
    public int getFichas() {
        return fichas;
    }
    public int retirarFichas() {
        int cuantas = fichas;
        fichas = 0;
        return cuantas;
    }
    public void agregarFichas(int cantidad) {
        fichas += cantidad;
    }
}