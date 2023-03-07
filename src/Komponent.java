public class Komponent {

    private String nazwa;
    private int waga;
    private int cena;


    //konstruktor
    public Komponent(String nazwa, int waga, int cena) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
    }

    //gettery
    public String getNazwa() {
        return nazwa;
    }

    public int getWaga() {
        return waga;
    }

    public int getCena() {
        return cena;
    }

    //settery
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
