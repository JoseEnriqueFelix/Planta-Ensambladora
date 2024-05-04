import java.util.LinkedList;
import java.util.Queue;

public class EstacionDeTrabajo {
    private String nombre;
    private int tiempo, tiempo2; // tiempo2 para caso especial de motor-transmision
    private Robot[] robots, robots2; // robots2 para caso especial de motor-transmision
    private Semaforo estacionRobots, estacionRobots2; // estacionRobots2 para caso especial de motor-transmision
    private Semaforo manejarRemoveCola, manejarAddCola, manejarRemoveCola2, manejarAddCola2;
    private Queue<Robot> cola, cola2; // cola2 para caso especial de motor-transmision

    public EstacionDeTrabajo(String nombre, int tiempo, int cantidadRobots) {
        cola = new LinkedList<>();
        manejarAddCola = new Semaforo(1);
        manejarRemoveCola = new Semaforo(1);
        this.nombre = nombre;
        this.tiempo = tiempo;
        estacionRobots = new Semaforo(cantidadRobots);
        robots = new Robot[cantidadRobots];
        inicializarRobots(robots, cola);
    }

    public EstacionDeTrabajo(String nombre, int tiempo, int tiempo2, int cantidadRobots, int cantidadRobots2) {
        cola = new LinkedList<>();
        cola2 = new LinkedList<>();
        manejarAddCola = new Semaforo(1);
        manejarRemoveCola = new Semaforo(1);
        manejarAddCola2 = new Semaforo(1);
        manejarRemoveCola2 = new Semaforo(1);
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.tiempo2 = tiempo2;
        estacionRobots = new Semaforo(cantidadRobots);
        estacionRobots2 = new Semaforo(cantidadRobots2);
        robots = new Robot[cantidadRobots];
        inicializarRobots(robots, cola);
        robots2 = new Robot[cantidadRobots2];
        inicializarRobots(robots2, cola2);
    }

    private void inicializarRobots(Robot[] rbts, Queue<Robot> queue) {
        for (int i = 0; i < rbts.length; i++) {
            rbts[i] = new Robot(Rutinas.nextNombre(1), i + 1);
            queue.add(rbts[i]);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getTiempo2() {
        return tiempo2;
    }

    public Robot[] getRobots() {
        return robots;
    }

    public Robot[] getRobots2() {
        return robots2;
    }

    public Semaforo getEstacionRobots() {
        return estacionRobots;
    }

    public Semaforo getEstacionRobots2() {
        return estacionRobots2;
    }

    public Queue<Robot> getCola() {
        return cola;
    }

    public Queue<Robot> getCola2() {
        return cola2;
    }

    public Semaforo getManejarRemoveCola() {
        return manejarRemoveCola;
    }

    public Semaforo getManejarAddCola() {
        return manejarAddCola;
    }

    public Semaforo getManejarRemoveCola2() {
        return manejarRemoveCola2;
    }

    public Semaforo getManejarAddCola2() {
        return manejarAddCola2;
    }
}
