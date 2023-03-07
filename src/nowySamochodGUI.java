import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class nowySamochodGUI extends JFrame {

    private JPanel nowyForm;
    private JTextField model;
    private JTextField rejestracja;
    private JTextField marka;
    private JRadioButton slabszaSkrzynia;
    private JRadioButton mocniejszaSkrzynia;
    private JRadioButton mocniejszySilnik;
    private JRadioButton slabszySilnik;
    private JTextField VMax;
    private JButton dodajButton;
    private JButton anulujButton;


    public boolean poprawnosc() {
        try {
            Integer.parseInt(VMax.getText());
            if (!mocniejszySilnik.isSelected() && !slabszySilnik.isSelected()) {
                return false;
            }
            if (!slabszaSkrzynia.isSelected() && !mocniejszaSkrzynia.isSelected()) {
                return false;
            }
            if (model.getText().equals("") || rejestracja.getText().equals("")) {
                return false;
            }
            if ((Integer.parseInt(VMax.getText()) < 200 || Integer.parseInt(VMax.getText()) > 350)) {
                return false;
            }
        } catch (Exception numberFormatException) {
            return false;
        }
        return true;
    }


    public void czyszczenie() {
        model.setText("");
        rejestracja.setText("");
        marka.setText("");
        VMax.setText("");

       slabszaSkrzynia.setSelected(false);
       mocniejszaSkrzynia.setSelected(false);
       mocniejszySilnik.setSelected(false);
       slabszySilnik.setSelected(false);
    }

    public nowySamochodGUI(JComboBox comboBox) throws HeadlessException {
        setContentPane(nowyForm);


        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (poprawnosc()) {

                    String nazwaSilnika;
                    int wagaSilnika;
                    int cenaSilnika;
                    int maksymalneObroty;

                    String nazwaSprzegla;
                    int wagaSprzegla;
                    int cenaSprzegla;

                    String nazwaSkrzyni;
                    int wagaSkrzyni;
                    int cenaSkrzyni;
                    int iloscBiegow;

                    if (slabszaSkrzynia.isSelected()) {
                        nazwaSkrzyni = "Słabsza skrzynia";
                        wagaSkrzyni = 100;
                        cenaSkrzyni = 3000;
                        iloscBiegow = 5;
                        nazwaSprzegla = "Słabsze sprzęgło";
                        wagaSprzegla = 20;
                        cenaSprzegla = 1000;
                    } else {
                        nazwaSkrzyni = "Mocniejsza skrzynia";
                        wagaSkrzyni = 200;
                        cenaSkrzyni = 6000;
                        iloscBiegow = 6;
                        nazwaSprzegla = "Mocniejsze sprzęgło";
                        wagaSprzegla = 40;
                        cenaSprzegla = 2000;
                    }

                    if (mocniejszySilnik.isSelected()) {
                        nazwaSilnika = "Mocniejszy silnik";
                        wagaSilnika = 200;
                        cenaSilnika = 4000;
                        maksymalneObroty = 9500;
                    } else {
                        nazwaSilnika = "Słabszy silnik";
                        wagaSilnika = 100;
                        cenaSilnika = 2000;
                        maksymalneObroty = 7500;
                    }

                    Pozycja aktualnaPozycja = new Pozycja(0, 0);
                    Sprzeglo sprzeglo = new Sprzeglo(nazwaSprzegla, wagaSprzegla, cenaSprzegla, false);
                    SkrzyniaBiegow skrzynia = new SkrzyniaBiegow(nazwaSkrzyni, wagaSkrzyni, cenaSkrzyni, 0, iloscBiegow, sprzeglo);
                    Silnik silnik = new Silnik(nazwaSilnika, wagaSilnika, cenaSilnika, maksymalneObroty, 0);
                    Samochod dodawanySamochod = new Samochod(false, rejestracja.getText(), model.getText(), skrzynia, Integer.parseInt(VMax.getText()), silnik, aktualnaPozycja);

                    comboBox.addItem(dodawanySamochod);

                    czyszczenie();
                }
                else {
                    System.out.println("Wprowadź poprawne dane");
                }
            }
        });
    }

}

