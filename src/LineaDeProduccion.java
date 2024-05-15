public class LineaDeProduccion extends Thread {

    private EstacionDeTrabajo[] estaciones;
    private final int MAX_CARROS_PRODUCIDOS = 100;
    private int idLineaDeProduccion;
    private Vista vista;
    private Semaforo[] s;

    public LineaDeProduccion(EstacionDeTrabajo[] estaciones, int idLineaDeProduccion, Vista vista, Semaforo[] s) {
        this.estaciones = estaciones;
        this.idLineaDeProduccion = idLineaDeProduccion;
        this.vista = vista;
        this.s = s;
    }

    public void run() {
        int auxNumDeCarro;
        Robot auxRobot;
        Control.getS1().Espera();
        auxNumDeCarro = Control.getNumeroDeCarros() + 1;
        Control.setNumeroDeCarros(auxNumDeCarro);
        Control.getS1().Libera();
        while (auxNumDeCarro <= MAX_CARROS_PRODUCIDOS) {
            s[0].Espera();
            for (int i = 0; i < estaciones.length; i++) {
                estaciones[i].getEstacionRobots().Espera();
                estaciones[i].getManejarCola().Espera();
                auxRobot = estaciones[i].getCola().remove();
                estaciones[i].getManejarCola().Libera();
                int cantDormir = estaciones[i].getTiempo();
                if (i != 0)
                    vista.soltar(idLineaDeProduccion, i);
                vista.tomar(idLineaDeProduccion, auxNumDeCarro, i + 1, auxRobot);
                System.out
                        .println(getName() + ", " + auxNumDeCarro + ", " + estaciones[i].getNombre() + ", " + auxRobot);

                dormir(cantDormir);

                if (estaciones[i].getRobots2() != null) {
                    estaciones[i].getEstacionRobots2().Espera();
                    estaciones[i].getManejarCola2().Espera();
                    estaciones[i].getManejarCola().Espera();
                    estaciones[i].getCola().add(auxRobot);
                    estaciones[i].getManejarCola().Libera();
                    auxRobot = estaciones[i].getCola2().remove();
                    estaciones[i].getManejarCola2().Libera();
                    estaciones[i].getEstacionRobots().Libera();
                    cantDormir = estaciones[i].getTiempo2();
                    vista.tomar(idLineaDeProduccion, auxNumDeCarro, i + 1, auxRobot);
                    System.out.println(
                            getName() + ", " + auxNumDeCarro + ", " + estaciones[i].getNombre() + ", " +
                                    auxRobot);
                    dormir(cantDormir);
                    estaciones[i].getManejarCola2().Espera();
                    estaciones[i].getCola2().add(auxRobot);
                    estaciones[i].getManejarCola2().Libera();
                    estaciones[i].getEstacionRobots2().Libera();
                    if (i == estaciones.length - 1) {
                        vista.soltar(idLineaDeProduccion, i + 1);
                        s[i].Libera();
                        break;
                    }
                    s[i + 1].Espera();
                    s[i].Libera();
                    continue;
                }

                estaciones[i].getManejarCola().Espera();
                estaciones[i].getCola().add(auxRobot);
                estaciones[i].getManejarCola().Libera();
                estaciones[i].getEstacionRobots().Libera();
                if (i == estaciones.length - 1) {
                    vista.soltar(idLineaDeProduccion, i + 1);
                    s[i].Libera();
                    break;
                }
                s[i + 1].Espera();
                s[i].Libera();
            }
            Control.getS1().Espera();
            auxNumDeCarro = Control.getNumeroDeCarros() + 1;
            Control.setNumeroDeCarros(auxNumDeCarro);
            Control.getS1().Libera();
        }
    }

    private void dormir(int cantDormir) {
        try {
            sleep(cantDormir * 1000);
        } catch (InterruptedException e) {
        }
    }
}