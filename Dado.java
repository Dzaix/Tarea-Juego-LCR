import java.util.Random;

/**
 * Modela un dado. Por default es un dado
 * de 6 lados. Se puede crear un dado con
 * una cantidad de lados específica.
 *
 * @author (Cecy Curlango Rosas)
 * @version (1.0 agosto 2025)
 */
public class Dado
{
    private int caras;
    private int valor;

    /**
     * Crea un dado de 6 lados.
     */
    public Dado()
    {
        caras = 6;
        valor = 1;
    }

    /**
     * Crea un dado con la cantidad de lados
     * especificada.
     * @param Cantidad de lados que tendrá el dado.
     */
    public Dado(int lados) {
        caras = lados;
        valor = 1;
    }
    /**
     * Regresa el valor de la cara actual.
     * @return valor de la cara actual.
     */
    public int getValor() {
        return valor;
    }
    /**
     * Cambia el valor de la cara actual
     * al que recibe.
     * @param nuevo valor de la cara actual.
     */
    public void setValor(int nuevoValor) {
        valor = nuevoValor;
    }
    /**
     * Regresa el tamaño del dado.
     * @return cantidad de caras que tiene el dado.
     */
    public int getCaras() {
        return caras;
    }
    /**
     * Cambia la cantidad de caras que tiene el dado.
     * @return cantidad de caras que tendrá el dado
     */
    public void setCaras(int caras) {
        this.caras = caras;
    }
    /**
     * Lanza el dado y regresa el valor que cae.
     * @param nuevo valor de la cara
     */
    public int lanzar() {
        Random aleatorio;
        aleatorio = new Random();
        
        valor = aleatorio.nextInt(caras) +1;
        return valor;
    }
    
}