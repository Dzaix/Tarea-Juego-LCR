/**
 * Juego Left-Center-Right (LCR)
 * Controla la lógica del juego para 3 jugadores.
 *
 * @author Cecilia Curlango Rosas
 * @version 1.0 agosto 2025
 */
public class LCRGame {
    private DadoLCR dado1, dado2, dado3;
    private Jugador jugador1, jugador2, jugador3;
    private Jugador jugadorActual, jugadorIzquierda, jugadorDerecha;
    private Jugador jugadorGanador;
    private int turno;
    private Centro centro;

    /**
     * Configura el juego a su estado inicial.
     */
    public LCRGame() {
        dado1 = new DadoLCR();
        dado2 = new DadoLCR();
        dado3 = new DadoLCR();
        centro = new Centro();

        jugador1 = new Jugador();
        jugador2 = new Jugador();
        jugador3 = new Jugador();

        crearJugadores();                  // Asigna nombres predeterminados
        int primerJugador = encontrarPrimerJugador(); // Determina quién empieza
        turno = primerJugador;
        establecerJugadores();             // Asigna jugadorActual e izquierda/derecha
    }

    /**
     * Comprueba si el juego ha terminado.
     * @return true si sólo queda un jugador con fichas
     */
    public boolean esFinDeJuego() {
        int jugadoresEnCero = 0;
        boolean seTermino = false;

        if (jugador1.getFichas() == 0) jugadoresEnCero++; else jugadorGanador = jugador1;
        if (jugador2.getFichas() == 0) jugadoresEnCero++; else jugadorGanador = jugador2;
        if (jugador3.getFichas() == 0) jugadoresEnCero++; else jugadorGanador = jugador3;

        if (jugadoresEnCero == 2) seTermino = true;

        return seTermino;
    }

    /**
     * Obtiene el jugador ganador, si existe.
     * @return jugador ganador o null si el juego no terminó
     */
    public Jugador getGanador() {
        if (!esFinDeJuego()) return null;
        return jugadorGanador;
    }

    /**
     * Procesa los resultados del turno actual según los dados lanzados.
     */
    public void procesarResultados() {
        int cuantosDados = jugadorActual.getFichas();
        char cara;
        switch (cuantosDados) {
            case 0: break;
            case 1:
                cara = dado1.getValor();
                procesarCara(cara);
                break;
            case 2:
                cara = dado1.getValor();
                procesarCara(cara);
                cara = dado2.getValor();
                procesarCara(cara);
                break;
            default:
                cara = dado1.getValor();
                procesarCara(cara);
                cara = dado2.getValor();
                procesarCara(cara);
                cara = dado3.getValor();
                procesarCara(cara);
                break;
        }
    }

    /**
     * Distribuye fichas según el resultado de un dado.
     */
    private void procesarCara(char cara) {
        switch (cara) {
            case 'C':
                jugadorActual.retirarFichas(1);
                centro.agregarFichas(1);
                break;
            case 'L':
                jugadorActual.retirarFichas(1);
                jugadorIzquierda.agregarFichas(1);
                break;
            case 'R':
                jugadorActual.retirarFichas(1);
                jugadorDerecha.agregarFichas(1);
                break;
        }
    }

    /**
     * Cambia de turno al siguiente jugador.
     */
    public void cambiarTurno() {
        turno++;
        if (turno > 3) turno = 1;
        establecerJugadores(); // actualiza jugadorActual
    }

    /**
     * Asigna jugadorActual y sus vecinos según el turno.
     */
    public void establecerJugadores() {
        switch (turno) {
            case 1:
                jugadorActual = jugador1;
                jugadorIzquierda = jugador3;
                jugadorDerecha = jugador2;
                break;
            case 2:
                jugadorActual = jugador2;
                jugadorIzquierda = jugador1;
                jugadorDerecha = jugador3;
                break;
            case 3:
                jugadorActual = jugador3;
                jugadorIzquierda = jugador2;
                jugadorDerecha = jugador1;
                break;
            default:
                jugadorActual = jugador1;
                jugadorIzquierda = jugador3;
                jugadorDerecha = jugador2;
                break;
        }
    }

    /**
     * Lanza los dados según la cantidad de fichas del jugador.
     */
    public void lanzarDados() {
        int fichas = jugadorActual.getFichas();
        switch (fichas) {
            case 0: break;
            case 1: lanzar1Dado(); break;
            case 2: lanzar2Dados(); break;
            default: lanzar3Dados(); break;
        }
    }

    private void lanzar1Dado() { dado1.lanzar(); }
    private void lanzar2Dados() { dado1.lanzar(); dado2.lanzar(); }
    private void lanzar3Dados() { dado1.lanzar(); dado2.lanzar(); dado3.lanzar(); }

    /**
     * Crea los jugadores con nombres predeterminados.
     */
    public void crearJugadores() {
        jugador1.setNombre("uno");
        jugador2.setNombre("dos");
        jugador3.setNombre("tres");
    }

    /**
     * Determina por suerte quién será el primer jugador.
     * @return número del jugador que empezará (1-3)
     */
    public int encontrarPrimerJugador() {
        int primerJugador = 1;
        jugadorActual = jugador1;
        lanzar3Dados();
        int maxPuntos = contarPuntos();

        lanzar3Dados();
        if (contarPuntos() > maxPuntos) {
            primerJugador = 2;
            jugadorActual = jugador2;
            maxPuntos = contarPuntos();
        }

        lanzar3Dados();
        if (contarPuntos() > maxPuntos) {
            primerJugador = 3;
            jugadorActual = jugador3;
        }

        return primerJugador;
    }

    /**
     * Cuenta cuántos dados muestran '*'.
     */
    private int contarPuntos() {
        int contador = 0;
        if (dado1.getValor() == '*') contador++;
        if (dado2.getValor() == '*') contador++;
        if (dado3.getValor() == '*') contador++;
        return contador;
    }

    // Métodos para acceder a jugadores y dados
    public Jugador getJugador1() { return jugador1; }
    public Jugador getJugador2() { return jugador2; }
    public Jugador getJugador3() { return jugador3; }
    public DadoLCR getDado1() { return dado1; }
    public DadoLCR getDado2() { return dado2; }
    public DadoLCR getDado3() { return dado3; }
    public Centro getCentro() { return centro; }
}
