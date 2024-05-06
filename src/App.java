/*
 * Nombre : Jose Enrique Felix Esparragoza
 * NoControl : 21170315
 * Materia : Topicos avanzados de programacion
 * Unidad : 3 HILOS
 * Proyecto :  Escenario planta Nisson 1-A
 * Fecha : 29, abril, 2024
 * Maestro : Clemente Garcia Gerardo
 */

public class App {
    private static final int NUMERO_ESTACIONES = 6;
    private static final int LIM_INFERIOR_N = 8;
    private static final int LIM_SUPERIOR_N = 15;
    private static int n;

    public static void main(String[] args) throws Exception {
        EstacionDeTrabajo[] estacionesDeTrabajo = new EstacionDeTrabajo[NUMERO_ESTACIONES];
        estacionesDeTrabajo[0] = new EstacionDeTrabajo("Chasis y cableado", 20, 5);
        estacionesDeTrabajo[1] = new EstacionDeTrabajo("Motor-Transmision", 6, 4, 4, 2);
        estacionesDeTrabajo[2] = new EstacionDeTrabajo("Carroceria", 10, 3);
        estacionesDeTrabajo[3] = new EstacionDeTrabajo("Interiores", 5, 3);
        estacionesDeTrabajo[4] = new EstacionDeTrabajo("Llantas", 5, 2);
        estacionesDeTrabajo[5] = new EstacionDeTrabajo("Pruebas", 10, 5);
        n = Rutinas.nextInt(LIM_INFERIOR_N, LIM_SUPERIOR_N);
        LineaDeProduccion[] lineasDeProduccion = new LineaDeProduccion[n];
        Vista vista = new Vista(estacionesDeTrabajo, lineasDeProduccion);
        for(int i=0; i<lineasDeProduccion.length; i++){
            lineasDeProduccion[i] = new LineaDeProduccion(estacionesDeTrabajo, i+1, vista);
            lineasDeProduccion[i].setName("Linea de produccion " + (i+1) + ": ");
        }
        vista.hazInterfaz();
        //System.out.println(lineasDeProduccion.length);
        for(int i=0; i<lineasDeProduccion.length; i++){
            lineasDeProduccion[i].start();
        }
        
    }
}
