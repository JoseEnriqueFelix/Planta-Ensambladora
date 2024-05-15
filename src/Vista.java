import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    private EstacionDeTrabajo[] estacionesDeTrabajo;
    private ComponenteVista[][] matriz;
    private int n;

    public Vista(EstacionDeTrabajo[] estacionesDeTrabajo, int n) {
        super("Planta Ensambladora 1C");
        this.estacionesDeTrabajo = estacionesDeTrabajo;
        this.n = n;
        matriz = new ComponenteVista[n + 1][estacionesDeTrabajo.length + 1];
    }

    public void hazInterfaz() {
        setSize(1125, 750);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, estacionesDeTrabajo.length + 1));
        add(matriz[0][0] = new ComponenteVista());
        for (int i = 1; i < estacionesDeTrabajo.length + 1; i++)
            add(matriz[0][i] = new ComponenteVista(estacionesDeTrabajo[i - 1].getNombre()));
        for (int i = 1; i < n + 1; i++) {
            add(matriz[i][0] = new ComponenteVista("Linea #" + i));
            for (int j = 1; j < estacionesDeTrabajo.length + 1; j++)
                add(matriz[i][j] = new ComponenteVista());
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void soltar(int idLineaDeProduccion, int linea) {
        matriz[idLineaDeProduccion][linea].soltar();
        //pack();
        revalidate();
    }

    public void tomar(int idLineaDeProduccion, int carro, int linea, Robot auxRobot) {
        matriz[idLineaDeProduccion][linea].tomar("src/Imagenes/carro.png", "src/Imagenes/robot.png", carro, auxRobot.getNumeroDeSerie());
        //pack();
        revalidate();
    }
}
