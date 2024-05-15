import java.awt.GridLayout;

import javax.swing.*;

public class ComponenteVista extends JPanel {

    private JLabel imgCarro, imgRobot, numDeCarro, numSerieRobot, labelAbsoluto;

    public ComponenteVista() {
        setLayout(new GridLayout(2, 2));
        imgCarro = new JLabel();
        imgRobot = new JLabel();
        numDeCarro = new JLabel();
        numSerieRobot = new JLabel();
        add(imgCarro);
        add(numDeCarro);
        add(imgRobot);
        add(numSerieRobot);
    }

    public ComponenteVista(String texto) {
        setLayout(new GridLayout(1, 1));
        labelAbsoluto = new JLabel(texto);
        add(labelAbsoluto);
    }

    public void tomar(String dirImgCarro, String dirImgRobot, int carro, int serieRobot) {
        int w = imgCarro.getWidth();
        int h = imgCarro.getHeight();
        ImageIcon imagen = Rutinas.AjustarImagen(dirImgCarro, w, h);
        imgCarro.setIcon(imagen);
        w = imgRobot.getWidth();
        h = imgRobot.getHeight();
        imagen = Rutinas.AjustarImagen(dirImgRobot, w, h);
        imgRobot.setIcon(imagen);
        numDeCarro.setText("" + carro);
        numSerieRobot.setText("" + serieRobot);
    }

    public void soltar() {
        imgCarro.setIcon(null);
        imgRobot.setIcon(null);
        numDeCarro.setText("");
        numSerieRobot.setText("");
    }
}
