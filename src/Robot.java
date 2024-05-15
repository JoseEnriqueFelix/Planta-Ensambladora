public class Robot {
    private String nombreRobot;
    private int numeroDeSerie;

    public Robot(String nombreRobot, int numeroDeSerie){
        this.nombreRobot = nombreRobot;
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getNombreRobot() {
        return nombreRobot;
    }

    public int getNumeroDeSerie(){
        return numeroDeSerie;
    }

    public String toString(){
        return "Robot: " + nombreRobot + " " + numeroDeSerie;
    }
}
