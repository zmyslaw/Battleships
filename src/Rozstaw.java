import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;

public class Rozstaw extends JFrame implements ActionListener {
    JLabel wyswietl;
    JButton Graj;
    JButton Generuj;

    int wykorzystane_statki = 0;

    int pozostalo;
    int poprawnosc = 0;

    int maszty_czworki = 0;
    int maszty_trojki = 0;
    int maszty_dwojki = 0;
    int maszty_jedynki = 0;

    int poziom;

    JButton panel_gracza_Button[][] = new JButton[30][30];
    int panel_gracza[][] = new int[30][30];

    public Rozstaw(int poziom) {
        super("Rozstaw masztów");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300, 400);//X Y
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setLocation(new Point(300, 100));

        JPanel panel_alfanumeryczny = new JPanel();
        JPanel panel_przyciskow = new JPanel();

        panel_alfanumeryczny.setLayout(new GridLayout(12, 12));
        panel_przyciskow.setLayout(new GridLayout(3, 1));
        ;

        panel_alfanumeryczny.add(new JLabel(" "));
        panel_alfanumeryczny.add(new JLabel("A"));
        panel_alfanumeryczny.add(new JLabel("B"));
        panel_alfanumeryczny.add(new JLabel("C"));
        panel_alfanumeryczny.add(new JLabel("D"));
        panel_alfanumeryczny.add(new JLabel("E"));
        panel_alfanumeryczny.add(new JLabel("F"));
        panel_alfanumeryczny.add(new JLabel("G"));
        panel_alfanumeryczny.add(new JLabel("H"));
        panel_alfanumeryczny.add(new JLabel("I"));
        panel_alfanumeryczny.add(new JLabel("J"));

        this.poziom = poziom;

        for (int i = 1; i < 28; i++) {
            for (int j = 1; j < 28; j++) {
                panel_gracza[i][j] = 0;
            }
        }

        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                panel_gracza_Button[i][j] = new JButton();
                panel_gracza_Button[i][j].setBackground(Color.white);
                panel_gracza_Button[i][j].setPreferredSize(new Dimension(20, 20));
                panel_gracza[i][j] = 0;

                if (j == 10) {
                    panel_alfanumeryczny.add(new JLabel("" + (i - 9)));
                }

                panel_gracza_Button[i][j].setVisible(true);
                panel_alfanumeryczny.add(panel_gracza_Button[i][j]);
                panel_gracza_Button[i][j].addActionListener(this);
            }
        }

        add(panel_alfanumeryczny);

        Graj = new JButton("Graj");
        wyswietl = new JLabel(" ", SwingConstants.CENTER);
        Graj.addActionListener(this);
        panel_przyciskow.add(Graj, BorderLayout.CENTER);

        Generuj = new JButton("Generuj");
        wyswietl = new JLabel(" ", SwingConstants.CENTER);
        Generuj.addActionListener(this);
        panel_przyciskow.add(Generuj, BorderLayout.CENTER);

        panel_przyciskow.add(wyswietl, BorderLayout.CENTER);

        add(panel_przyciskow);

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
        if (przycisk != null) {
            for (int i = 10; i < 20; i++) {
                for (int j = 10; j < 20; j++) {
                    if (przycisk == panel_gracza_Button[i][j]) {
                        if (panel_gracza[i][j] == 0 && wykorzystane_statki == 20) {
                            JOptionPane.showMessageDialog(null, "Wykorzystano limit masztow.", "Brak masztów.", JOptionPane.INFORMATION_MESSAGE);
                        } else if (panel_gracza[i][j] == 0 && wykorzystane_statki < 20) {
                            if (panel_gracza[i + 1][j + 1] == 1 ||
                                    panel_gracza[i - 1][j - 1] == 1 ||
                                    panel_gracza[i + 1][j - 1] == 1 ||
                                    panel_gracza[i - 1][j + 1] == 1
                            ) {
                            } else {
                                //ulokowanie okretu
                                panel_gracza[i][j] = 1;
                                wykorzystane_statki++;
                                panel_gracza_Button[i][j].setBackground(Color.black);

                                pozostalo = 20 - wykorzystane_statki;
                            }
                        } else if (panel_gracza[i][j] == 1 && wykorzystane_statki <= 20) {
                            //cofniecie okretu
                            panel_gracza[i][j] = 0;
                            wykorzystane_statki--;
                            panel_gracza_Button[i][j].setBackground(Color.white);

                            pozostalo = 20 - wykorzystane_statki;
                        }
                    }
                }
            }
            if (przycisk == Graj && wykorzystane_statki < 20) {
                try {
                    JOptionPane.showMessageDialog(null, "Nierozstawiono wszystkich masztów.", "Błąd", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ee) {
                }
            } else if (przycisk == Graj && wykorzystane_statki == 20) {
                for (int i = 10; i < 20; i++) {
                    for (int j = 10; j < 20; j++) {
                        if (panel_gracza[i][j] == 1) {
                            if (
                                    (panel_gracza[i][j - 1] == 0 &&
                                            panel_gracza[i][j + 1] == 1 &&
                                            panel_gracza[i][j + 2] == 1 &&
                                            panel_gracza[i][j + 3] == 1 &&
                                            panel_gracza[i][j + 4] == 0) ||

                                            (panel_gracza[i][j - 2] == 0 &&
                                                    panel_gracza[i][j - 1] == 1 &&
                                                    panel_gracza[i][j + 1] == 1 &&
                                                    panel_gracza[i][j + 2] == 1 &&
                                                    panel_gracza[i][j + 3] == 0) ||

                                            (panel_gracza[i][j - 3] == 0 &&
                                                    panel_gracza[i][j - 2] == 1 &&
                                                    panel_gracza[i][j - 1] == 1 &&
                                                    panel_gracza[i][j + 1] == 1 &&
                                                    panel_gracza[i][j + 2] == 0) ||

                                            (panel_gracza[i][j - 4] == 0 &&
                                                    panel_gracza[i][j - 3] == 1 &&
                                                    panel_gracza[i][j - 2] == 1 &&
                                                    panel_gracza[i][j - 1] == 1 &&
                                                    panel_gracza[i][j + 1] == 0) ||

                                            (panel_gracza[i - 1][j] == 0 &&
                                                    panel_gracza[i + 1][j] == 1 &&
                                                    panel_gracza[i + 2][j] == 1 &&
                                                    panel_gracza[i + 3][j] == 1 &&
                                                    panel_gracza[i + 4][j] == 0) ||

                                            (panel_gracza[i - 2][j] == 0 &&
                                                    panel_gracza[i - 1][j] == 1 &&
                                                    panel_gracza[i + 1][j] == 1 &&
                                                    panel_gracza[i + 2][j] == 1 &&
                                                    panel_gracza[i + 3][j] == 0) ||

                                            (panel_gracza[i - 3][j] == 0 &&
                                                    panel_gracza[i - 2][j] == 1 &&
                                                    panel_gracza[i - 1][j] == 1 &&
                                                    panel_gracza[i + 1][j] == 1 &&
                                                    panel_gracza[i + 2][j] == 0) ||

                                            (panel_gracza[i - 4][j] == 0 &&
                                                    panel_gracza[i - 3][j] == 1 &&
                                                    panel_gracza[i - 2][j] == 1 &&
                                                    panel_gracza[i - 1][j] == 1 &&
                                                    panel_gracza[i + 1][j] == 0)
                            ) {
                                maszty_czworki++;
                            }

                            if (
                                    (panel_gracza[i - 2][j] == 0 &&
                                            panel_gracza[i - 1][j] == 1 &&
                                            panel_gracza[i + 1][j] == 1 &&
                                            panel_gracza[i + 2][j] == 0) ||

                                            (panel_gracza[i - 1][j] == 0 &&
                                                    panel_gracza[i + 1][j] == 1 &&
                                                    panel_gracza[i + 2][j] == 1 &&
                                                    panel_gracza[i + 3][j] == 0) ||

                                            (panel_gracza[i - 3][j] == 0 &&
                                                    panel_gracza[i - 2][j] == 1 &&
                                                    panel_gracza[i - 1][j] == 1 &&
                                                    panel_gracza[i + 1][j] == 0) ||

                                            (panel_gracza[i][j - 2] == 0 &&
                                                    panel_gracza[i][j - 1] == 1 &&
                                                    panel_gracza[i][j + 1] == 1 &&
                                                    panel_gracza[i][j + 2] == 0) ||

                                            (panel_gracza[i][j - 1] == 0 &&
                                                    panel_gracza[i][j + 1] == 1 &&
                                                    panel_gracza[i][j + 2] == 1 &&
                                                    panel_gracza[i][j + 3] == 0) ||

                                            (panel_gracza[i][j - 3] == 0 &&
                                                    panel_gracza[i][j - 2] == 1 &&
                                                    panel_gracza[i][j - 1] == 1 &&
                                                    panel_gracza[i][j + 1] == 0)
                            ) {
                                maszty_trojki++;
                            }

                            if (
                                    (panel_gracza[i - 1][j] == 0 &&
                                            panel_gracza[i + 1][j] == 1 &&
                                            panel_gracza[i + 2][j] == 0) ||

                                            (panel_gracza[i - 2][j] == 0 &&
                                                    panel_gracza[i - 1][j] == 1 &&
                                                    panel_gracza[i + 1][j] == 0) ||

                                            (panel_gracza[i][j - 1] == 0 &&
                                                    panel_gracza[i][j + 1] == 1 &&
                                                    panel_gracza[i][j + 2] == 0) ||

                                            (panel_gracza[i][j - 2] == 0 &&
                                                    panel_gracza[i][j - 1] == 1 &&
                                                    panel_gracza[i][j + 1] == 0)
                            ) {
                                maszty_dwojki++;
                            }

                            if (panel_gracza[i - 1][j] == 0 &&
                                    panel_gracza[i][j - 1] == 0 &&
                                    panel_gracza[i][j + 1] == 0 &&
                                    panel_gracza[i + 1][j] == 0
                            ) {
                                maszty_jedynki++;
                            }
                        }
                    }
                }

                if (maszty_czworki == 4 &&
                        maszty_trojki == 6 &&
                        maszty_dwojki == 6 &&
                        maszty_jedynki == 4) {
                    poprawnosc = 1;
                }

                maszty_czworki = 0;
                maszty_trojki = 0;
                maszty_dwojki = 0;
                maszty_jedynki = 0;

                if (poprawnosc == 0) {
                    JOptionPane.showMessageDialog(null, "Złe ustawienie masztów.", "Błąd", JOptionPane.ERROR_MESSAGE);
                } else if (poprawnosc > 0) {
                    poprawnosc = 0;
                    Generuj Gen_SI = new Generuj();

                    if (poziom == 0) {
                        Latwy lat = new Latwy(panel_gracza, panel_gracza_Button, Gen_SI.zwroc_panel());
                        lat.setVisible(true);
                    }
                    if (poziom == 1) {
                        Trudny tru = new Trudny(panel_gracza, panel_gracza_Button, Gen_SI.zwroc_panel());
                        tru.setVisible(true);
                    }

                    for (int i = 10; i < 20; i++) {
                        for (int j = 10; j < 20; j++) {
                            panel_gracza_Button[i][j].setEnabled(false);
                        }
                    }

                    setVisible(false);
                    dispose();
                }
            } else if (przycisk == Generuj) {
                poprawnosc = 0;
                wykorzystane_statki = 0;

                Generuj Gen_PC = new Generuj();
                Generuj Gen_SI = new Generuj();

                for (int i = 10; i < 20; i++) {
                    for (int j = 10; j < 20; j++) {
                        panel_gracza[i][j] = 0;
                        panel_gracza_Button[i][j].setBackground(Color.white);

                        panel_gracza_Button[i][j].setEnabled(false);
                        if (Gen_PC.zwroc_panel()[i][j] == 1) {
                            panel_gracza_Button[i][j].setBackground(Color.black);
                        }
                    }
                }

                if (poziom == 0) {
                    Latwy lat = new Latwy(Gen_PC.zwroc_panel(), panel_gracza_Button, Gen_SI.zwroc_panel());
                    lat.setVisible(true);

                    setVisible(false);
                    dispose();
                }
                if (poziom == 1) {
                    Trudny tru = new Trudny(Gen_PC.zwroc_panel(), panel_gracza_Button, Gen_SI.zwroc_panel());
                    tru.setVisible(true);

                    setVisible(false);
                    dispose();
                }
            }
        }
        wyswietl.setText("Pozostało " + pozostalo + " masztów.");
    }
}