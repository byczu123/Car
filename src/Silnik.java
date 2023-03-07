public class Silnik extends Komponent {

    private int maxObroty;
    private int obroty;


    //konstruktor
    public Silnik(String nazwa, int waga, int cena, int maxObroty, int obroty) {
        super(nazwa, waga, cena);
        this.maxObroty = maxObroty;
        this.obroty = obroty;
    }

    //gettery
    public int getMaxObroty() {
        return maxObroty;
    }

    public int getObroty() {
        return obroty;
    }

    //settery
    public void setMaxObroty(int maxObroty) {
        this.maxObroty = maxObroty;
    }

    public void setObroty(int obroty) {
        this.obroty = obroty;
    }

    //metody
    public void zatrzymaj(Samochod samochod) {
        if (samochod.getStanWlaczenia()) {
            if (this.obroty <= 700) {
                samochod.wylacz();
                samochod.silnik.setObroty(0);
                samochod.skrzynia.setAktualnyBieg(0);
                System.out.println("Silnik zgasł");

            }
        }
    }

    public void zwiekszObroty(Samochod samochod) {
        if (!samochod.skrzynia.sprzeglo.getStanSprzegla() && samochod.getStanWlaczenia()) {
            if (this.obroty < this.maxObroty) {
                this.obroty += 200;
            }else if (!samochod.getStanWlaczenia()){
                System.out.println("Samochod wylaczony");
            }else if(samochod.skrzynia.sprzeglo.getStanSprzegla()){
                System.out.println("Musisz puscic sprzeglo");
            }
        }
    }

    public void zmniejszObroty(Samochod samochod) {
        if (!samochod.skrzynia.sprzeglo.getStanSprzegla() && samochod.getStanWlaczenia()) {
            this.obroty -= 200;
            if (this.obroty < 600) {
                zatrzymaj(samochod);
                }
        } else if (!samochod.getStanWlaczenia()){
            System.out.println("Samochod wylaczony");
        }else if(samochod.skrzynia.sprzeglo.getStanSprzegla()){
            System.out.println("Musisz puscic sprzeglo");
        }
    }
    public void zmniejszObrotyZmiana(Samochod samochod){
        if(samochod.getStanWlaczenia()){
            if(this.obroty>0){
                this.obroty -= 200;
            }
            if (this.obroty <600){
                samochod.udus();
            }
        }
    }
}
