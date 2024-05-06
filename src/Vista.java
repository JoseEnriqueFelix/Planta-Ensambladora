import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    private EstacionDeTrabajo[] estacionesDeTrabajo;
    private LineaDeProduccion[] lineasDeProduccion;
    private JTextArea[][] matriz;

    public Vista(EstacionDeTrabajo[] estacionesDeTrabajo, LineaDeProduccion[] lineasDeProduccion) {
        super("Planta Ensambladora 1C");
        this.estacionesDeTrabajo = estacionesDeTrabajo;
        this.lineasDeProduccion = lineasDeProduccion;
        matriz = new JTextArea[lineasDeProduccion.length + 1][estacionesDeTrabajo.length + 1];
        //hazInterfaz();
    }

    public void hazInterfaz() {
        setSize(1125, 750);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, estacionesDeTrabajo.length + 1));
        add(matriz[0][0] = new JTextArea());
        for (int i = 1; i < estacionesDeTrabajo.length + 1; i++)
            add(matriz[0][i] = new JTextArea(estacionesDeTrabajo[i - 1].getNombre()));
        for (int i = 1; i < lineasDeProduccion.length + 1; i++) {
            add(matriz[i][0] = new JTextArea(lineasDeProduccion[i-1].getName()));
            for(int j=1; j<estacionesDeTrabajo.length + 1; j++)
                add(matriz[i][j] = new JTextArea());
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actualizar(int idLineaDeProduccion, int carro, int linea, Robot auxRobot){
        matriz[idLineaDeProduccion][0].setText("Linea #" + idLineaDeProduccion + '\n' + "Automovil #" + carro);
        for(int i=1; i<estacionesDeTrabajo.length + 1; i++){
            if(linea == i)
                matriz[idLineaDeProduccion][i].setText("Aqui\n" + auxRobot.getNombreRobot());
            else
                matriz[idLineaDeProduccion][i].setText("");
        }
        revalidate();
    }
}    
