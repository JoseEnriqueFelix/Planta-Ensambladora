/*
 * Nombre : Jose Enrique Felix Esparragoza
 * NoControl : 21170315
 * Materia : Topicos avanzados de programacion
 * Unidad : 3 HILOS
 * Proyecto :  Escenario planta Nisson 1-A
 * Fecha : 29, abril, 2024
 * Maestro : Clemente Garcia Gerardo
 */

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    private static final int NUMERO_ESTACIONES = 6;
    private static final int LIM_INFERIOR_N = 8;
    private static final int LIM_SUPERIOR_N = 15;

    public static void main(String[] args) {
        EstacionDeTrabajo[] estacionesDeTrabajo = new EstacionDeTrabajo[NUMERO_ESTACIONES];
        estacionesDeTrabajo[0] = new EstacionDeTrabajo("Chasis y cableado", 20, 5);
        // estacionesDeTrabajo[1] = new EstacionDeTrabajo("Motor-Transmision", 6, 4, 4, 2);
        estacionesDeTrabajo[1] = new EstacionDeTrabajo("Motor-Transmision", 6, 4);
        estacionesDeTrabajo[2] = new EstacionDeTrabajo("Carroceria", 10, 3);
        estacionesDeTrabajo[3] = new EstacionDeTrabajo("Interiores", 5, 3);
        estacionesDeTrabajo[4] = new EstacionDeTrabajo("Llantas", 5, 2);
        estacionesDeTrabajo[5] = new EstacionDeTrabajo("Pruebas", 10, 5);
        int n = Rutinas.nextInt(LIM_INFERIOR_N, LIM_SUPERIOR_N);
        Vista vista = new Vista(estacionesDeTrabajo, n);
        ArrayList<LineaDeProduccion> arrListLineasDeProduccion = new ArrayList<LineaDeProduccion>();
        for (int i = 0; i < n; i++) {
            Semaforo[] s = new Semaforo[estacionesDeTrabajo.length];
            // Arrays.fill(s, new Semaforo(1));
            for (int j=0; j<s.length; j++){
                s[j] = new Semaforo(1);
            }
            for (int j = 0; j < estacionesDeTrabajo.length; j++) {
                LineaDeProduccion auxDeProduccion = new LineaDeProduccion(estacionesDeTrabajo, i + 1, vista, s);
                arrListLineasDeProduccion.add(auxDeProduccion);
                arrListLineasDeProduccion.get((i * estacionesDeTrabajo.length) + j).setName("Linea #" + (i + 1));
            }
        }
        vista.hazInterfaz();
        for (int i = 0; i < arrListLineasDeProduccion.size(); i++) {
            arrListLineasDeProduccion.get(i).start();
        }

    }
}
