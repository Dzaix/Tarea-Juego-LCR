
/**
 * Presenta interfaz de usuario en consola para el 
 * juego Left-Center-Right.
 *
 * @author (Cecilia Curlango Rosas)
 * @version 1.0 (agosto 2025)
 */
public class UILCRGameApplicacion
{
    private LCRGame juego;

    public UILCRGameApplicacion() {
        juego = new LCRGame();
    }

    /**
     * Juega el juego LEFT-CENTER-RIGHT
     */
    public void jugar() {
        juego.crearJugadores();
        juego.encontrarPrimerJugador();

        do {
            juego.lanzarDados();
            juego.procesarResultados();
            mostrarJugada();
            juego.cambiarTurno();
            juego.establecerJugadores();
        }while(!juego.esFinDeJuego());

        // mostrar ganador
        System.out.println("Ganador " + juego.getGanador().getNombre());
    }

    /**
     * Muestra los dados, fichas de los jugadores y del centro.
     */
    private void mostrarJugada() {
        System.out.print("Dados " + juego.getDado1().getValor() + " ");
        System.out.print(juego.getDado2().getValor() + " ");
        System.out.println(juego.getDado3().getValor());
        System.out.println("Primer jugador tiene " + juego.getJugador1().getFichas());
        System.out.println("Segundo jugador tiene " + juego.getJugador2().getFichas());
        System.out.println("Tercer jugador tiene " + juego.getJugador3().getFichas());
        System.out.println("Centro " + juego.getCentro().getFichas());
    }
}

