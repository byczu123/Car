public class Samochod extends Thread{

    private boolean stanWlaczenia;
    private String nrRejest;
    private String model;
    private int VMax;

    Silnik silnik;
    SkrzyniaBiegow skrzynia;
    Pozycja aktualnaPozycja;
    Pozycja cel;

    //konstruktor
    public Samochod(boolean stanWlaczenia, String nrRejest, String model ,SkrzyniaBiegow skrzynia, int VMax, Silnik silnik, Pozycja aktualnaPozycja) {
        this.stanWlaczenia = stanWlaczenia;
        this.nrRejest = nrRejest;
        this.model = model;
        this.VMax = VMax;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.aktualnaPozycja = aktualnaPozycja;
        this.cel = aktualnaPozycja;
        start();
    }

    //gettery
    public String getNrRejest() {
        return nrRejest;
    }

    public String getModel() {
        return model;
    }

    public int getWaga() {
        return silnik.getWaga() + skrzynia.getWaga() + skrzynia.sprzeglo.getWaga();
    }

    public double getAktPredkosc(Silnik silnik, SkrzyniaBiegow skrzynia, int srednicaKola){

        if(silnik.getObroty() * skrzynia.getAktualnePrzelozenie() * srednicaKola * 0.025 * 3.14 / 150 < VMax ) {
            return silnik.getObroty() * skrzynia.getAktualnePrzelozenie() * srednicaKola * 0.0254 * 3.14 / 150;
        }else {
            return VMax;
        }
    }



    public Pozycja getAktPozycja() {
        return aktualnaPozycja;
    }

    public Pozycja getCel(){
        return cel;
    }

    public int getVMax() {
        return VMax;
    }

    public boolean getStanWlaczenia() { return stanWlaczenia; }

    //settery
    public void setStanWlaczenia(boolean stanWlaczenia) {
        this.stanWlaczenia = stanWlaczenia;
    }

    public void setNrRejest(String nrRejest) {
        this.nrRejest = nrRejest;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVMax(int VMax) {
        this.VMax = VMax;
    }

    //metody
    public void wlacz() {
        if (!this.stanWlaczenia && this.skrzynia.sprzeglo.getStanSprzegla()) {
            this.stanWlaczenia = true;
            System.out.println("Samochod wlaczony");
            this.silnik.setObroty(800);
            this.skrzynia.setAktualnyBieg(2);
        }
    }

    public void wylacz() {
        if (this.skrzynia.sprzeglo.getStanSprzegla()) {
            this.stanWlaczenia = false;
            System.out.println("Samochod wylaczony");
            this.silnik.setObroty(0);
            this.skrzynia.setAktualnyBieg(0);
        }
    }
    public void udus(){
        this.stanWlaczenia = false;
        this.silnik.setObroty(0);
        this.skrzynia.setAktualnyBieg(0);
    }



    public void jedzDo(Pozycja cel) {
        this.cel = cel;
    }

    public String toString() {
        return nrRejest + " " + model;
    }

    public void run() {
        while (true) {
            if (aktualnaPozycja.getX() != cel.getX() || aktualnaPozycja.getY() != cel.getY()) {
                double V = getAktPredkosc(silnik, skrzynia, 20);
                aktualnaPozycja.przenies(V, 0.001, cel);
                if (Math.abs(aktualnaPozycja.getX() - cel.getX()) < V / 35 && Math.abs(aktualnaPozycja.getY() - cel.getY()) < V / 35) {
                    aktualnaPozycja.setX(cel.getX());
                    aktualnaPozycja.setY(cel.getY());
                    System.out.println("(" + aktualnaPozycja.getX() + ", " + aktualnaPozycja.getY() + ")");

                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }


    public static void main(String[] args) {
        Pozycja aktualnaPozycja = new Pozycja(0, 0);
        Pozycja cel = new Pozycja(200, 200);
        Sprzeglo sprzeglo = new Sprzeglo("sprzeglo", 20, 2500, false);
        Silnik silnik = new Silnik("silnik", 250, 5000, 8000, 2000);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("skrzynia", 100, 3500, 0, 6, sprzeglo);
        Samochod auto = new Samochod(true, "RJS 98YT", "Opel Insignia",skrzynia,200,silnik,aktualnaPozycja);
        auto.jedzDo(cel);

    }
}
