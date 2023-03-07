public class Sprzeglo extends Komponent {

    private boolean stanSprzegla;


    //konstruktor
    public Sprzeglo(String nazwa, int waga, int cena, boolean stanSprzegla) {
        super(nazwa, waga, cena);
        this.stanSprzegla = stanSprzegla;
    }

    //getter
    public boolean getStanSprzegla() {

        return stanSprzegla;
    }

    //setter
    public void setStanSprzegla(boolean stanSprzegla) {
        this.stanSprzegla = stanSprzegla;
    }

    //metody
    public void wcisnij() {
        if(!this.stanSprzegla){
           setStanSprzegla(true);
            System.out.println("Sprzeglo wcisniete");
        }else {
            System.out.println("Sprzeglo juz jest wcisniete");
        }
    }

    public void zwolnij() {
        if (this.stanSprzegla) {
            setStanSprzegla(false);
            System.out.println("Sprzeglo puszczone");
        }else {
            System.out.println("Sprzeglo juz jest puszczone");
        }
    }
}
