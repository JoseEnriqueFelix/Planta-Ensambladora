import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    private EstacionDeTrabajo[] estacionesDeTrabajo;
    private JTextArea[][] matriz;
    private int n;

    public Vista(EstacionDeTrabajo[] estacionesDeTrabajo, int n) {
        super("Planta Ensambladora 1C");
        this.estacionesDeTrabajo = estacionesDeTrabajo;
        this.n = n;
        matriz = new JTextArea[n + 1][estacionesDeTrabajo.length + 1];
    }

    public void hazInterfaz() {
        setSize(1125, 750);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, estacionesDeTrabajo.length + 1));
        add(matriz[0][0] = new JTextArea());
        for (int i = 1; i < estacionesDeTrabajo.length + 1; i++)
            add(matriz[0][i] = new JTextArea(estacionesDeTrabajo[i - 1].getNombre()));
        for (int i = 1; i < n + 1; i++) {
            add(matriz[i][0] = new JTextArea("Linea #" + i));
            for (int j = 1; j < estacionesDeTrabajo.length + 1; j++)
                add(matriz[i][j] = new JTextArea());
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void soltar(int idLineaDeProduccion, int linea) {
        matriz[idLineaDeProduccion][linea].setText("");
        revalidate();
    }

    public void tomar(int idLineaDeProduccion, int carro, int linea, Robot auxRobot) {
        matriz[idLineaDeProduccion][linea].setText("Aqui: " + carro + "\n" + auxRobot);
        revalidate();
    }
}
