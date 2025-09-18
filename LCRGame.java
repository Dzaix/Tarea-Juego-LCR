
/**
 * Juego Left-Center-Write.
 *
 * Esta versión se desarrolló en el salón de clase.
 * 
 * @author (Cecilia Curlango Rosas)
 * @version (1.0 agosto 2025)
 */
public class LCRGame
{
    private DadoLCR dado1, dado2, dado3;
    private Jugador jugador1, jugador2, jugador3;
    private Jugador jugadorActual, jugadorIzquierda, jugadorDerecha;
    private Jugador jugadorGanador;
    private int turno;
    private Centro centro;

    /**
     * Configura el juego a su estado inicial.
     * Crea los dados y los jugadores.
     */
    public LCRGame() {
        dado1 = new DadoLCR();
        dado2 = new DadoLCR();
        dado3 = new DadoLCR();
        centro = new Centro();

        jugador1 = new Jugador();
        jugador2 = new Jugador();
        jugador3 = new Jugador();
    }

    /**
     * Determina si dos de los 3 jugadores se
     * quedaron sin fichas. En ese caso, hay 
     * ganador. El jugador ganador se asgina.
     * @return true si sólo queda un jugador con fichas.
     */
    public boolean esFinDeJuego() {
        int jugadoresEnCero = 0;
        boolean seTermino = false;
        if (jugador1.getFichas()==0) {
            jugadoresEnCero++;
        } else{
            jugadorGanador = jugador1;
        }
        if (jugador2.getFichas()==0) {
            jugadoresEnCero++;
        } else {
            jugadorGanador = jugador2;
        }
        if (jugador3.getFichas()==0) {
            jugadoresEnCero++;
        } else {
            jugadorGanador = jugador3;
        }
        if (jugadoresEnCero==2) {
            seTermino = true;
        }
        return seTermino;
    }
    /**
     * Regresa al jugador que ganó la partida.
     * @return jugador ganador; si no se ha terminado
     * el juego, regresa null.
     */
    public Jugador getGanador() {
        if (!esFinDeJuego()) {
            return null;
        }
        return jugadorGanador;
    }
    /**
     * Según los valores de los dados que lanzó
     * el jugador actual, reparte las fichas.
     */
    public void procesarResultados() {
        int cuantosDados = jugadorActual.getFichas();
        char cara;
        switch(cuantosDados) {
            case 0:
                break;
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
     * Quita una ficha al jugador actual y 
     * se la entrega al centro, al de la izquierda
     * o al de la derecha, según lo que valga la cara.
     * @param cara del dado.
     */
    private void procesarCara(char cara){
        switch(cara) {
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
     * Cambia el número del jugador en turno.
     */
    public void cambiarTurno() {
        turno++;
        if (turno==4) {
            turno = 1;
        }
    }

    /**
     * Según quien sea el jugador en turno,
     * asigna al jugador actual, al de la izquierda y
     * al de la derecha.
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
        }
    }

    /**
     * Lanza los dados del juego. La cantidad
     * de dados que se lanza depende de la cantidad
     * de fichas que tiene el jugador actual.
     */
    public void lanzarDados() {
        int fichas = jugadorActual.getFichas();
        switch (fichas) {
            case 0: 
                break;
            case 1:
                lanzar1Dado();
                break;
            case 2:
                lanzar2Dados();
                break;
            default:
                lanzar3Dados();
        }
    }

    /**
     * Método de prueba que simula un juego.
     * 
     */
    private String jugar() {
        crearJugadores();
        Jugador inicial = null;
        switch (encontrarPrimerJugador()) {
            case 1:
                inicial = jugador1;
                break;
            case 2:
                inicial = jugador2;
                break;
            case 3:
                inicial = jugador3;
                break;
        }
        return inicial.getNombre();

    }

    /**
     * Simula la creación de los jugadores. 
     * Le asigna un nombre predeterminado a cada uno.
     */
    public void crearJugadores() {
        jugador1.setNombre("uno");
        jugador2.setNombre("dos");
        jugador3.setNombre("tres");
    }

    /**
     * Determina por suerte cuál de los tres
     * jugadores iniciará el juego. Lanza los
     * tres dados para cada jugador y el que
     * tenga la mayor cantidad de puntos es el 
     * que empieza el juego.
     * @return el número del jugador que iniciará (1-3).
     */
    public int encontrarPrimerJugador() {
        // turno del primer jugador
        int primerJugador = 1;
        jugadorActual = jugador1;
        lanzar3Dados();
        int cuantosPuntosHay = contarPuntos();

        // turno del segundo jugador
        lanzar3Dados();
        if (cuantosPuntosHay < contarPuntos()) {
            primerJugador = 2;
            jugadorActual = jugador2;
            cuantosPuntosHay = contarPuntos();
        }

        // turno del tercer jugador
        lanzar3Dados();
        if (cuantosPuntosHay < contarPuntos()) {
            primerJugador = 3;
            jugadorActual = jugador3;
            cuantosPuntosHay = contarPuntos();
        }
        turno = primerJugador;
        establecerJugadores();
        return primerJugador;
    }

    /**
     * Cuenta cuantos dados caen en punto.
     * @return cantidad de dados que tienen un punto.
     */
    private int contarPuntos() {
        int contador = 0;
        if (dado1.getValor() == '*') {
            contador++;
        }
        if (dado2.getValor() == '*') {
            contador++;
        }
        if (dado3.getValor() == '*') {
            contador++;
        }
        return contador;
    }

    /**
     * Lanza los 1 dado.
     */
    private void lanzar1Dado() {
        dado1.lanzar();
    }

    /**
     * Lanza los 2 dados.
     */
    private void lanzar2Dados() {
        dado1.lanzar();
        dado2.lanzar();
    }

    /**
     * Lanza los 3 dados.
     */
    private void lanzar3Dados() {
        dado1.lanzar();
        dado2.lanzar();
        dado3.lanzar();
    }
    /**
     * Regresa al primer jugador.
     * @return primer jugador
     */
    public Jugador getJugador1() {
        return jugador1;
    }
    /**
     * Regresa al segundo jugador.
     * @return segundo jugador
     */
    public Jugador getJugador2() {
        return jugador2;
    }
    /**
     * Regresa al tercer jugador.
     * @return tercer jugador
     */
    public Jugador getJugador3() {
        return jugador3;
    }
    /**
     * Regresa el primer dado.
     * @return primer dado del juego
     */
    public DadoLCR getDado1() {
        return dado1;
    }
    /**
     * Regresa el segundo dado.
     * @return segundo dado del juego
     */
    public DadoLCR getDado2() {
        return dado2;
    }
    /**
     * Regresa el tercer dado.
     * @return tercer dado del juego
     */
    public DadoLCR getDado3() {
        return dado3;
    }
    /**
     * Regresa el centro del juego.
     * @return centro del juego
     */
    public Centro getCentro() {
        return centro;
    }
    
}