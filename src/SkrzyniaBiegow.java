public class SkrzyniaBiegow extends Komponent {

    private int aktualnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;
    double[] przelozenie = new double[]{0, 0.5, 1, 1.5, 2, 2.5, 3};
    Sprzeglo sprzeglo;

    //konstruktor
    public SkrzyniaBiegow(String nazwa, int waga, int cena, int aktualnyBieg, int iloscBiegow, Sprzeglo sprzeglo) {
        super(nazwa, waga, cena);
        this.aktualnyBieg = aktualnyBieg;
        this.iloscBiegow = iloscBiegow + 1;
        this.aktualnePrzelozenie = przelozenie[aktualnyBieg];
        this.sprzeglo = sprzeglo;
    }

    //gettery
    public int getAktualnyBieg() {
        return aktualnyBieg;
    }

    public int getIloscBiegow() {
        return iloscBiegow;
    }

    public double getAktualnePrzelozenie() {
        return aktualnePrzelozenie;
    }

    //settery
    public void setAktualnyBieg(int aktualnyBieg) {
        this.aktualnyBieg = aktualnyBieg;
        this.aktualnePrzelozenie = przelozenie[aktualnyBieg];
    }

    public void setIloscBiegow(int iloscBiegow) {
        this.iloscBiegow = iloscBiegow;
    }

    public void setAktualnePrzelozenie(double aktualnePrzelozenie) {
        this.aktualnePrzelozenie = aktualnePrzelozenie;
    }

    //metody
    public void zwiekszBieg(Samochod samochod) {
        if(sprzeglo.getStanSprzegla()) {
            if (this.aktualnyBieg != this.iloscBiegow - 1) {
                this.aktualnyBieg = aktualnyBieg + 1;
                this.aktualnePrzelozenie = przelozenie[aktualnyBieg];
                samochod.silnik.zmniejszObrotyZmiana(samochod);
                System.out.println("Bieg zwiekszony");
            }
        }
    }

    public void zmniejszBieg(Samochod samochod) {
        if(sprzeglo.getStanSprzegla()) {
            if (this.aktualnyBieg > 0) {
                this.aktualnyBieg = aktualnyBieg - 1;
                this.aktualnePrzelozenie = przelozenie[aktualnyBieg];
                System.out.println("Bieg zmniejszony");
            }
        }
    }

}
