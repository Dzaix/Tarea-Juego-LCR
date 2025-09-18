
/**
 * Dado para el juego Left-Center-Right.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (1.0 agosto 2025)
 */
public class DadoLCR
{
    private Dado dado;

    /**
     * Crea el dado regular de 6 lados.
     */
    public DadoLCR() {
        dado = new Dado();
    }

    /**
     * Obtiene la cara actual del dado.
     * Esto se hace convirtiendo el valor
     * del dado interno al valor del dado LCR.
     * @return El carácter de la cara actual.
     */
    public char getValor() {
        char valor = 'C';
        int elValorQueTieneElDado = dado.getValor();

        switch (elValorQueTieneElDado) {
            case 1:
            case 2:
            case 3:
                valor = '*';
                break;
            case 4:
                valor = 'L';
                break;
            case 5:
                valor = 'C';
                break;
            case 6:
                valor = 'R';
                break;
        }
        return valor;
    }
    /**
     * Lanza el dado y regresa el carácter
     * que cae en la cara.
     * @return El carácter de la cara actual.
     */
    public char lanzar() {
        char valor = 'C';

        dado.lanzar();
        valor = getValor();
        
        return valor;
    }
}