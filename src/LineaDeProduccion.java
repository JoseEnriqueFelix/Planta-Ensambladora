public class LineaDeProduccion extends Thread {

    private EstacionDeTrabajo[] estaciones;
    private final int MAX_CARROS_PRODUCIDOS = 100;
    private int idLineaDeProduccion;
    private Vista vista;

    public LineaDeProduccion(EstacionDeTrabajo[] estaciones, int idLineaDeProduccion, Vista vista) {
        this.estaciones = estaciones;
        this.idLineaDeProduccion = idLineaDeProduccion;
        this.vista = vista;
    }

    public void run() {
        int auxNumDeCarro;
        Robot auxRobot;
        Control.getS1().Espera();
        auxNumDeCarro = Control.getNumeroDeCarros() + 1;
        Control.setNumeroDeCarros(auxNumDeCarro);
        // vista.actualizar(idLineaDeProduccion, auxNumDeCarro);
        Control.getS1().Libera();
        while (auxNumDeCarro <= MAX_CARROS_PRODUCIDOS) {
            for (int i = 0; i < estaciones.length; i++) {
                estaciones[i].getEstacionRobots().Espera();
                estaciones[i].getManejarCola().Espera();
                auxRobot = estaciones[i].getCola().remove();
                estaciones[i].getManejarCola().Libera();
                int cantDormir = estaciones[i].getTiempo();
                vista.actualizar(idLineaDeProduccion, auxNumDeCarro, i+1, auxRobot);
                System.out.printf("%1s, %10d, %30s, %30s \n", getName(), auxNumDeCarro, estaciones[i].getNombre(),
                        auxRobot);
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
                    vista.actualizar(idLineaDeProduccion, auxNumDeCarro, i+1, auxRobot);
                    System.out.printf("%1s, %10d, %30s, %30s \n", "CE" + getName(), auxNumDeCarro,
                            estaciones[i].getNombre(),
                            auxRobot);
                    dormir(cantDormir);
                    estaciones[i].getManejarCola2().Espera();
                    estaciones[i].getCola2().add(auxRobot);
                    estaciones[i].getManejarCola2().Libera();
                    estaciones[i].getEstacionRobots2().Libera();
                    continue;
                }
                estaciones[i].getManejarCola().Espera();
                estaciones[i].getCola().add(auxRobot);
                estaciones[i].getManejarCola().Libera();
                estaciones[i].getEstacionRobots().Libera();
            }
            Control.getS1().Espera();
            auxNumDeCarro = Control.getNumeroDeCarros() + 1;
            Control.setNumeroDeCarros(auxNumDeCarro);
            // vista.actualizar(idLineaDeProduccion, auxNumDeCarro);
            Control.getS1().Libera();
        }
    }

    private void dormir(int cantDormir) {
        try {
            sleep(cantDormir * 100);
        } catch (InterruptedException e) {
        }
    }
}