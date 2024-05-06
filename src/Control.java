public class Control {
    private static int numeroDeCarros = 0;
    private static Semaforo s1 = new Semaforo(1);

    public static int getNumeroDeCarros() {
        return numeroDeCarros;
    }

    public static void setNumeroDeCarros(int numeroDeCarros) {
        Control.numeroDeCarros = numeroDeCarros;
    }

    public static Semaforo getS1() {
        return s1;
    }
}
