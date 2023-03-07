public class Pozycja {

    private double x;
    private double y;

    //konstruktor
    public Pozycja(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //gettery
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //settery
    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

    //metody
    public void przenies(double V, double delta, Pozycja cel) {
        double deltaX = V * delta * (cel.x - this.x) / Math.sqrt(Math.pow(cel.x - this.x, 2) + Math.pow(cel.y - this.y, 2));
        double deltaY = V * delta * (cel.y - this.y) / Math.sqrt(Math.pow(cel.x - this.x, 2) + Math.pow(cel.y - this.y, 2));
        this.x += deltaX;
        this.y += deltaY;
    }
}
