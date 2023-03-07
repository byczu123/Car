import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SamochodGUI extends Thread {
    private JPanel samochod;
    //samochod
    private JTextField modelSamochodu;
    private JTextField numerRejestracyjny;
    private JTextField aktualnaPredkosc;
    private JTextField wagaSamochodu;
    private JButton wlaczSamochod;
    private JButton wylaczSamochod;
    //skrzynia
    private JTextField nazwaSkrzyni;
    private JTextField cenaSkrzyni;
    private JTextField wagaSkrzyni;
    private JButton zwiekszBieg;
    private JButton zmniejszBieg;
    private JTextField numerBiegu;
    //sprzeglo
    private JTextField nazwaSprzegla;
    private JButton wcisnijSprzeglo;
    private JButton zwolnijSprzeglo;
    private JTextField wagaSprzegla;
    private JTextField stanSprzegla;
    private JTextField cenaSprzegla;
    //silnik
    private JButton przyspiesz;
    private JButton zwolnij;
    private JTextField nazwaSilnika;
    private JTextField cenaSilnika;
    private JTextField wagaSilnika;
    private JTextField obrotySilnika;
    //inne
    private JLabel jazda;
    private JPanel mapa;
    private JButton dodawanieSamochodu;
    private JButton usuwanieSamochodu;
    private JComboBox wybieranieSamochodu;


    Samochod auto;

    public SamochodGUI() {
        //......................................samochod.............................
        wlaczSamochod.addActionListener(e -> {
            if (auto != null){
                auto.wlacz();
                refresh();
            }
            System.out.println(" ");

        });
        wylaczSamochod.addActionListener(e -> {
            if (auto != null) {
                auto.wylacz();
                refresh();
            }
            System.out.println(" ");
        });
        //...........................................sprzeglo...............................
        wcisnijSprzeglo.addActionListener(e -> {
            if (auto != null) {
                auto.skrzynia.sprzeglo.wcisnij();
                refresh();
            }
            System.out.println(" ");
        });
        zwolnijSprzeglo.addActionListener(e -> {
            if (auto != null) {
                auto.skrzynia.sprzeglo.zwolnij();
                refresh();
            }
        });
        //...........................................skrzynia.........................
        zwiekszBieg.addActionListener(e -> {
            if (auto != null) {
               auto.skrzynia.zwiekszBieg(auto);
                refresh();
            }
        });
        zmniejszBieg.addActionListener(e -> {
            if (auto != null) {
                auto.skrzynia.zmniejszBieg(auto);
                refresh();
            }
        });
        //.................................................silnik.....................
        przyspiesz.addActionListener(e -> {
            if (auto != null) {
                    auto.silnik.zwiekszObroty(auto);
                }
                refresh();
            });
        zwolnij.addActionListener(e -> {
            if (auto != null)
                auto.silnik.zmniejszObroty(auto);
            refresh();
        });

        mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (auto != null) {
                    super.mousePressed(e);
                    auto.jedzDo(new Pozycja(e.getX(), e.getY()));
                }
            }
        });

        //..........................gorny panel.........................
        dodawanieSamochodu.addActionListener(e -> {
            JFrame f = new nowySamochodGUI(wybieranieSamochodu);
            f.pack();
            f.setVisible(true);
            f.setResizable(false);
        });

        usuwanieSamochodu.addActionListener(e -> {
            if (auto != null) {
                auto.interrupt();
                wybieranieSamochodu.removeItem(auto);
            }
        });
        wybieranieSamochodu.addActionListener(e -> {
            auto = (Samochod) wybieranieSamochodu.getSelectedItem();
            refresh();
        });
        start();
    }

    public void run() {
        super.run();
        while (true) {
            if (auto != null) {
                refresh();
            }
            else {
                usuwaniePol();

            }
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void refresh() {

        if (auto != null) {
            jazda.setLocation((int) auto.getAktPozycja().getX(), (int) auto.getAktPozycja().getY());
            jazda.setVisible(true);

            //samochod
            modelSamochodu.setText(auto.getModel());
            numerRejestracyjny.setText(auto.getNrRejest());
            aktualnaPredkosc.setText(Math.round(auto.getAktPredkosc(auto.silnik, auto.skrzynia, 15)) +" km/h");
            wagaSamochodu.setText(Integer.toString(auto.getWaga()));

            //skrzynia
            nazwaSkrzyni.setText(auto.skrzynia.getNazwa());
            wagaSkrzyni.setText(auto.skrzynia.getWaga() +" kg");
            cenaSkrzyni.setText(auto.skrzynia.getCena() +" zł");
            numerBiegu.setText(Integer.toString(auto.skrzynia.getAktualnyBieg()));

            //silnik
            nazwaSilnika.setText(auto.silnik.getNazwa());
            wagaSilnika.setText(auto.silnik.getWaga() +" kg");
            cenaSilnika.setText(auto.silnik.getCena() +" zł");
            obrotySilnika.setText(Integer.toString(auto.silnik.getObroty()));

            //sprzeglo
            nazwaSprzegla.setText(auto.skrzynia.sprzeglo.getNazwa());
            wagaSprzegla.setText(auto.skrzynia.sprzeglo.getWaga()+" kg");
            cenaSprzegla.setText(auto.skrzynia.sprzeglo.getCena()+" zł");
            if (auto.skrzynia.sprzeglo.getStanSprzegla()){
                stanSprzegla.setText("Wciśnięte");
            }else {
                stanSprzegla.setText("Puszczone");
            }
        }
    }

    public void usuwaniePol() {

        numerRejestracyjny.setText("");
        aktualnaPredkosc.setText("");
        modelSamochodu.setText("");
        wagaSamochodu.setText("");

        nazwaSkrzyni.setText("");
        wagaSkrzyni.setText("");
        cenaSkrzyni.setText("");
        numerBiegu.setText("");

        nazwaSilnika.setText("");
        wagaSilnika.setText("");
        cenaSilnika.setText("");
        obrotySilnika.setText("");

        nazwaSprzegla.setText("");
        wagaSprzegla.setText("");
        cenaSprzegla.setText("");
        stanSprzegla.setText("");

        jazda.setVisible(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SamochodGUI");
        frame.setContentPane(new SamochodGUI().samochod);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
