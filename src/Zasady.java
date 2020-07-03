import java.awt.FlowLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.GridLayout;

public class Zasady extends JFrame implements ActionListener {
    JButton Wroc;

    public Zasady() {
        super("Zasady");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(700, 700);//X Y

        setLocationRelativeTo(null);

        setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        panel.add(new JLabel(
                "<html>Zasady gry<br/>" +
                        "1.Ulokowanie floty.<br/>" +
                        "Wyświetlona zostanie plansza na której należy rozmieścić maszty.<br/>" +
                        "Maszt jest to pojedynczy kwadrat na planszy a okręt to zbiór tych masztów.<br/>" +
                        " <br/>" +
                        "Do dyspozycji są:<br/>" +
                        "-jeden okręt składający się z czterech masztów,<br/>" +
                        "-dwa okręty składające się z trzech masztów,<br/>" +
                        "-trzy okręty składające się z dwóch masztów,<br/>" +
                        "-cztery okręty składające się z jednego masztu.<br/>" +
                        " <br/>" +
                        "Okręty stawia się na planszy za pomocą lewego przycisku myszy.<br/>" +
                        "W chwili w której źle zostanie ulokowany maszt można go zdjąć klikając ponownie na niego.<br/>" +
                        "Okręty nie mogą się stykać rogami.<br/>" +
                        "Można też automatycznie ulokować okręty za pomocą przycisku 'Generuj'.<br/>" +
                        "W chwili w której gracz będzie gotowy do rozgrywki należy nacisnąć przycisk 'Graj'.<br/>" +
                        " <br/>" +
                        "2.Bitwa.<br/>" +
                        "Celem gry jest odgadnięcie gdzie są ulokowane wszystkie okręty przeciwnika.<br/>" +
                        "Wyświetlone zostaną dwie plansze.<br/>" +
                        "Jedna plansza z ulokowanymi przez gracza masztami (która została stworzona we wcześniejszym etapie gry).<br/>" +
                        "oraz plansza na której gracz oddaje 'strzał'.<br/>" +
                        "Strzał jest to kliknięcie lewym przyciskiem myszy na planszy.<br/>" +
                        "Można za pomocą prawego przycisku myszy zaznaczyć/odznaczyć kolorem żółtym gdzie nie wystąpią maszty wroga.<br/>" +
                        " <br/>" +
                        "Gracz wraz z przeciwnikiem komputerowym ostrzeliwują się na zmiane.<br/>" +
                        "W chwili odgadnięcia położenia jednego okrętu atak jest kontynuowany tak długo dopóki gracz oddaje celne 'strzały'.<br/>" +
                        "Pole czarne oznacza ulokowanie okrętów gracza.,<br/>" +
                        "Pole niebieskie oznacza oddany 'strzał' ale nie celny.<br/>" +
                        "Pole czerwone oznacza trafienie w okręt.<br/>" +
                        "Pole żółte jest znacznikiem gracza (używany w panelu bitwy przez prawy przycisk myszy).<br/>" +
                        " <br/>" +
                        "Gra kończy się w chwili w której jeden z uczestników odgadnie rozmieszczenie wszystkich okręów przeciwnika.<br/></html>"
        ));

        add(panel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 1));

        Wroc = new JButton("Wróć");
        Wroc.addActionListener(this);
        panel2.add(Wroc);

        add(panel2);

        JMenuBar menubar = new JMenuBar();

        JMenu menuPomoc = new JMenu("Więcej");
        JMenuItem menuOProgramie = new JMenuItem("Autor");
        menuOProgramie.addActionListener(this);
        menuPomoc.add(menuOProgramie);
        menubar.add(menuPomoc);

        setJMenuBar(menubar);

        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if (label.equals("Autor")) {
            JOptionPane.showMessageDialog(null, "Adrian Zmysłowski");
        }

        Object przycisk = e.getSource();
        if (przycisk == Wroc) {
            try {
                Panel_Glowny pg = new Panel_Glowny();
                pg.setVisible(true);

                setVisible(false);
                dispose();
            } catch (NumberFormatException ee) {
            }
        }
    }
}