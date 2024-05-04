public class LineaDeProduccion extends Thread {

    private EstacionDeTrabajo[] estaciones;
    private static Semaforo sacarNumDeAuto;
    private final int MAX_CARROS_PRODUCIDOS = 1000;

    public LineaDeProduccion(EstacionDeTrabajo[] estaciones) {
        this.estaciones = estaciones;
    }

    public void run() {
        int auxNumDeCarro;
        Robot auxRobot;
        Control.getS1().Espera();
        auxNumDeCarro = Control.getNumeroDeCarros() + 1;
        Control.setNumeroDeCarros(auxNumDeCarro);
        Control.getS1().Libera();
        while (auxNumDeCarro <= MAX_CARROS_PRODUCIDOS) {
            for (int i = 0; i < estaciones.length; i++) {
                estaciones[i].getEstacionRobots().Espera();
                estaciones[i].getManejarRemoveCola().Espera();
                auxRobot = estaciones[i].getCola().remove();
                estaciones[i].getManejarRemoveCola().Libera();
                int cantDormir = estaciones[i].getTiempo();
                try {
                    sleep(cantDormir);
                } catch (InterruptedException e) {
                }
                System.out.printf("%1s, %10d, %30s, %30s \n", getName(), auxNumDeCarro, estaciones[i].getNombre(),
                        auxRobot);
                if (estaciones[i].getRobots2() != null) {
                    estaciones[i].getEstacionRobots2().Espera();
                    estaciones[i].getManejarRemoveCola2().Espera();
                    estaciones[i].getManejarAddCola().Espera();
                    estaciones[i].getCola().add(auxRobot);
                    estaciones[i].getManejarAddCola().Libera();
                    auxRobot = estaciones[i].getCola2().remove();
                    estaciones[i].getManejarRemoveCola2().Libera();
                    estaciones[i].getEstacionRobots().Libera();
                    cantDormir = estaciones[i].getTiempo2();
                    try {
                        sleep(cantDormir);
                    } catch (InterruptedException e) {
                    }
                    System.out.printf("%1s, %10d, %30s, %30s \n", "CE" + getName(), auxNumDeCarro,
                            estaciones[i].getNombre(),
                            auxRobot);
                    estaciones[i].getManejarAddCola2().Espera();
                    estaciones[i].getCola2().add(auxRobot);
                    estaciones[i].getManejarAddCola2().Libera();
                    estaciones[i].getEstacionRobots2().Libera();
                    continue;
                }
                estaciones[i].getManejarAddCola().Espera();
                estaciones[i].getCola().add(auxRobot);
                estaciones[i].getManejarAddCola().Libera();
                estaciones[i].getEstacionRobots().Libera();
            }
            Control.getS1().Espera();
            auxNumDeCarro = Control.getNumeroDeCarros() + 1;
            Control.setNumeroDeCarros(auxNumDeCarro);
            Control.getS1().Libera();
        }
    }
}