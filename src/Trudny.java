import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;

public class Trudny extends JFrame implements ActionListener {
    JLabel wyswietl_maszty_si;
    JLabel wyswietl_maszty_gracza;

    JButton Nowa;
    JButton panel_gracza_Button[][] = new JButton[30][30];
    JButton panel_si_Button[][] = new JButton[30][30];
    int stos_I[] = new int[30];
    int stos_J[] = new int[30];
    int stos[] = new int[30];
    int panel_si[][] = new int[30][30];
    int panel_gracza[][] = new int[30][30];
    int zaznaczenie_na_zolto[][] = new int[30][30];

    int zatopione_gracza = 20;
    int zatopione_si = 20;

    int i1;
    int i2;
    int i3;
    int i4;
    int j1;
    int j2;
    int j3;
    int j4;

    int strzal_i;
    int strzal_j;

    int licz_czworki = 0;
    int licz_trojki = 0;
    int licz_dwojki = 0;
    int licz_jedynki = 0;

    Random wartosc = new Random();
    Object przycisk;
    JPanel panel_alfanumeryczny_si;

    public Trudny(int panel_gracza[][], JButton panel_gracza_Button[][], int panel_si[][]) {
        super("Bitwa poziom trudny");

        setSize(500, 400);//X Y
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setLocation(new Point(300, 100));

        JPanel panel_alfanumeryczny_gracza = new JPanel();
        JPanel panel_alfanumeryczny_si = new JPanel();
        JPanel panel_przyciskow = new JPanel();

        panel_alfanumeryczny_gracza.setLayout(new GridLayout(12, 10));
        panel_alfanumeryczny_si.setLayout(new GridLayout(12, 12));
        panel_przyciskow.setLayout(new GridLayout(3, 3));

        panel_alfanumeryczny_gracza.add(new JLabel("A"));
        panel_alfanumeryczny_gracza.add(new JLabel("B"));
        panel_alfanumeryczny_gracza.add(new JLabel("C"));
        panel_alfanumeryczny_gracza.add(new JLabel("D"));
        panel_alfanumeryczny_gracza.add(new JLabel("E"));
        panel_alfanumeryczny_gracza.add(new JLabel("F"));
        panel_alfanumeryczny_gracza.add(new JLabel("G"));
        panel_alfanumeryczny_gracza.add(new JLabel("H"));
        panel_alfanumeryczny_gracza.add(new JLabel("I"));
        panel_alfanumeryczny_gracza.add(new JLabel("J"));

        panel_alfanumeryczny_si.add(new JLabel(" "));
        panel_alfanumeryczny_si.add(new JLabel("A"));
        panel_alfanumeryczny_si.add(new JLabel("B"));
        panel_alfanumeryczny_si.add(new JLabel("C"));
        panel_alfanumeryczny_si.add(new JLabel("D"));
        panel_alfanumeryczny_si.add(new JLabel("E"));
        panel_alfanumeryczny_si.add(new JLabel("F"));
        panel_alfanumeryczny_si.add(new JLabel("G"));
        panel_alfanumeryczny_si.add(new JLabel("H"));
        panel_alfanumeryczny_si.add(new JLabel("I"));
        panel_alfanumeryczny_si.add(new JLabel("J"));

        for (int i = 1; i < 28; i++) {
            for (int j = 1; j < 28; j++) {
                this.panel_si[i][j] = panel_si[i][j];
                this.panel_gracza[i][j] = panel_gracza[i][j];
            }
        }

        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                this.panel_gracza_Button[i][j] = panel_gracza_Button[i][j];
                this.panel_si[i][j] = panel_si[i][j];
                this.panel_gracza[i][j] = panel_gracza[i][j];
                zaznaczenie_na_zolto[i][j] = 0;

                if (j == 10) {
                    panel_alfanumeryczny_si.add(new JLabel("  " + (i - 9)));
                }

                panel_gracza_Button[i][j].setVisible(true);
                panel_alfanumeryczny_gracza.add(panel_gracza_Button[i][j]);
                panel_gracza_Button[i][j].addActionListener(this);

                panel_alfanumeryczny_si.add(panel_si_Button[i][j] = new JButton());
                panel_si_Button[i][j].setBackground(Color.white);
                panel_si_Button[i][j].setPreferredSize(new Dimension(20, 20));

                panel_si_Button[i][j].setVisible(true);
                panel_alfanumeryczny_si.add(panel_si_Button[i][j]);
                panel_si_Button[i][j].addActionListener(this);

                panel_gracza_Button[i][j].setEnabled(false);
                if (panel_gracza[i][j] == 1) {
                    panel_gracza_Button[i][j].setBackground(Color.black);
                }
            }
        }

        Nowa = new JButton("Nowa gra");
        Nowa.addActionListener(this);

        wyswietl_maszty_si = new JLabel(" ", SwingConstants.CENTER);
        panel_przyciskow.add(wyswietl_maszty_si, BorderLayout.CENTER);

        wyswietl_maszty_gracza = new JLabel(" ", SwingConstants.CENTER);
        panel_przyciskow.add(wyswietl_maszty_gracza, BorderLayout.CENTER);

        panel_przyciskow.add(Nowa, BorderLayout.CENTER);

        add(panel_alfanumeryczny_gracza);
        add(panel_alfanumeryczny_si);
        add(panel_przyciskow);

        JMenuBar menubar = new JMenuBar();
        JMenu menuWiecej = new JMenu("Więcej");
        JMenuItem menuAutor = new JMenuItem("Autor");

        menuAutor.addActionListener(this);

        menuWiecej.add(menuAutor);
        menubar.add(menuWiecej);
        menuWiecej.add(menuAutor);

        setJMenuBar(menubar);

        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if (label.equals("Autor")) {
            JOptionPane.showMessageDialog(null, "Adrian Zmysłowski");
        }

        przycisk = e.getSource();
        if (przycisk != null) {
            Wyczysc_Stosy();
            for (int i = 10; i < 20; i++) {
                for (int j = 10; j < 20; j++) {
                    Szukaj_Trafionego();
                    if (przycisk == Nowa) {
                        Panel_Glowny pg = new Panel_Glowny();
                        pg.setVisible(true);
                        setVisible(false);
                        dispose();

                        zatopione_gracza = 20;
                        zatopione_si = 20;

                        przycisk = null;
                    }

                    if (przycisk == panel_si_Button[i][j]) {
                        Zolty_Znacznik();
                        if (panel_si[i][j] == 0 || panel_si[i][j] == 4) {
                            panel_si_Button[i][j].setBackground(Color.blue);
                            panel_si[i][j] = 3;
                            if (panel_gracza[strzal_i][strzal_j] == 0 || panel_gracza[strzal_i][strzal_j] == 4) {
                                panel_gracza[strzal_i][strzal_j] = 3;
                                panel_gracza_Button[strzal_i][strzal_j].setBackground(Color.blue);
                            } else if (panel_gracza[strzal_i][strzal_j] == 1) {
                                panel_gracza[strzal_i][strzal_j] = 2;
                                zatopione_gracza--;
                                Krawedzie();
                                Policz_Okrety();

                                Dodaj_Na_Stos();

                                Si_Wygralo();
                                Kontynuuj_Ostrzal();
                            }
                        } else if (panel_si[i][j] == 1) {
                            panel_si_Button[i][j].setBackground(Color.red);
                            panel_si[i][j] = 2;
                            zatopione_si--;
                            Gracz_Wygral();
                        } else if (panel_si[i][j] == 3 || panel_si[i][j] == 2) {
                        }
                    }
                }
            }
        }
        wyswietl_maszty_gracza.setText("Maszty gracza " + zatopione_gracza + ".");
        wyswietl_maszty_si.setText("Maszty przeciwnika " + zatopione_si + ".");
    }

    class Przerwa implements ActionListener {
        int i = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            Blokuj();
            if (i < 30) {
                if (stos[i] != 0) {
                    if (stos[i] == 3) {
                        panel_gracza_Button[stos_I[i]][stos_J[i]].setBackground(Color.blue);
                    }
                    if (stos[i] == 2) {
                        panel_gracza_Button[stos_I[i]][stos_J[i]].setBackground(Color.red);
                    }
                } else {
                    ((Timer) e.getSource()).stop();
                    Odblokuj();
                }
                i++;
            }
        }
    }

    public void Szukaj_Trafionego() {
        strzal_i = wartosc.nextInt((19 - 10) + 1) + 10;
        strzal_j = wartosc.nextInt((19 - 10) + 1) + 10;
        while (panel_gracza[strzal_i][strzal_j] == 3 || panel_gracza[strzal_i][strzal_j] == 2) {
            strzal_i = wartosc.nextInt((19 - 10) + 1) + 10;
            strzal_j = wartosc.nextInt((19 - 10) + 1) + 10;
        }

        main:
        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                if (panel_gracza[i][j] == 2) {
                    i1 = i - 1;
                    j1 = j;
                    i2 = i;
                    j2 = j - 1;
                    i3 = i;
                    j3 = j + 1;
                    i4 = i + 1;
                    j4 = j;
                    if (i1 > 9 && i1 < 20 && j1 > 9 && j1 < 20) {
                        if (panel_gracza[i1][j1] == 1 || panel_gracza[i1][j1] == 0 || panel_gracza[i1][j1] == 4) {
                            strzal_i = i1;
                            strzal_j = j1;
                            break main;
                        }
                    }
                    if (i2 > 9 && i2 < 20 && j2 > 9 && j2 < 20) {
                        if (panel_gracza[i2][j2] == 1 || panel_gracza[i2][j2] == 0 || panel_gracza[i2][j2] == 4) {
                            strzal_i = i2;
                            strzal_j = j2;
                            break main;
                        }
                    }
                    if (i3 > 9 && i3 < 20 && j3 > 9 && j3 < 20) {
                        if (panel_gracza[i3][j3] == 1 || panel_gracza[i3][j3] == 0 || panel_gracza[i3][j3] == 4) {
                            strzal_i = i3;
                            strzal_j = j3;
                            break main;
                        }
                    }
                    if (i4 > 9 && i4 < 20 && j4 > 9 && j4 < 20) {
                        if (panel_gracza[i4][j4] == 1 || panel_gracza[i4][j4] == 0 || panel_gracza[i4][j4] == 4) {
                            strzal_i = i4;
                            strzal_j = j4;
                            break main;
                        }
                    }
                }
            }
        }
    }

    public void Policz_Okrety() {
        licz_czworki = 0;
        licz_trojki = 0;
        licz_dwojki = 0;
        licz_jedynki = 0;

        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                if (panel_gracza[i][j] == 2) {
                    if ((panel_gracza[i][j - 1] == 0 || panel_gracza[i][j - 1] == 3 || panel_gracza[i][j - 1] == 4) &&
                            panel_gracza[i][j + 1] == 2 &&
                            panel_gracza[i][j + 2] == 2 &&
                            panel_gracza[i][j + 3] == 2 &&
                            (panel_gracza[i][j + 4] == 0 || panel_gracza[i][j + 4] == 3 || panel_gracza[i][j + 4] == 4)) {
                        licz_czworki++;
                    }

                    if ((panel_gracza[i][j - 2] == 0 || panel_gracza[i][j - 2] == 3 || panel_gracza[i][j - 2] == 4) &&
                            panel_gracza[i][j - 1] == 2 &&
                            panel_gracza[i][j + 1] == 2 &&
                            panel_gracza[i][j + 2] == 2 &&
                            (panel_gracza[i][j + 3] == 0 || panel_gracza[i][j + 3] == 3 || panel_gracza[i][j + 3] == 4)) {
                        licz_czworki++;
                    }

                    if ((panel_gracza[i][j - 3] == 0 || panel_gracza[i][j - 3] == 3 || panel_gracza[i][j - 3] == 4) &&
                            panel_gracza[i][j - 2] == 2 &&
                            panel_gracza[i][j - 1] == 2 &&
                            panel_gracza[i][j + 1] == 2 &&
                            (panel_gracza[i][j + 2] == 0 || panel_gracza[i][j + 2] == 3 || panel_gracza[i][j + 2] == 4)) {
                        licz_czworki++;
                    }

                    if ((panel_gracza[i][j - 4] == 0 || panel_gracza[i][j - 4] == 3 || panel_gracza[i][j - 4] == 4) &&
                            panel_gracza[i][j - 3] == 2 &&
                            panel_gracza[i][j - 2] == 2 &&
                            panel_gracza[i][j - 1] == 2 &&
                            (panel_gracza[i][j + 1] == 0 || panel_gracza[i][j + 1] == 3 || panel_gracza[i][j + 1] == 4)) {
                        licz_czworki++;
                    }

                    if ((panel_gracza[i - 1][j] == 0 || panel_gracza[i - 1][j] == 3 || panel_gracza[i - 1][j] == 4) &&
                            panel_gracza[i + 1][j] == 2 &&
                            panel_gracza[i + 2][j] == 2 &&
                            panel_gracza[i + 3][j] == 2 &&
                            (panel_gracza[i + 4][j] == 0 || panel_gracza[i + 4][j] == 3 || panel_gracza[i + 4][j] == 4)) {
                        licz_czworki++;
                    }

                    if ((panel_gracza[i - 2][j] == 0 || panel_gracza[i - 2][j] == 3 || panel_gracza[i - 2][j] == 4) &&
                            panel_gracza[i - 1][j] == 2 &&
                            panel_gracza[i + 1][j] == 2 &&
                            panel_gracza[i + 2][j] == 2 &&
                            (panel_gracza[i + 3][j] == 0 || panel_gracza[i + 3][j] == 3 || panel_gracza[i + 3][j] == 4)) {
                        licz_czworki++;
                    }

                    if ((panel_gracza[i - 3][j] == 0 || panel_gracza[i - 3][j] == 3 || panel_gracza[i - 3][j] == 4) &&
                            panel_gracza[i - 2][j] == 2 &&
                            panel_gracza[i - 1][j] == 2 &&
                            panel_gracza[i + 1][j] == 2 &&
                            (panel_gracza[i + 2][j] == 0 || panel_gracza[i + 2][j] == 3 || panel_gracza[i + 2][j] == 4)) {
                        licz_czworki++;
                    }

                    if ((panel_gracza[i - 4][j] == 0 || panel_gracza[i - 4][j] == 3 || panel_gracza[i - 4][j] == 4) &&
                            panel_gracza[i - 3][j] == 2 &&
                            panel_gracza[i - 2][j] == 2 &&
                            panel_gracza[i - 1][j] == 2 &&
                            (panel_gracza[i + 1][j] == 0 || panel_gracza[i + 1][j] == 3 || panel_gracza[i + 1][j] == 4)) {
                        licz_czworki++;
                    }
                    ///////
                    if ((panel_gracza[i - 2][j] == 0 || panel_gracza[i - 2][j] == 3 || panel_gracza[i - 2][j] == 4) &&
                            panel_gracza[i - 1][j] == 2 &&
                            panel_gracza[i + 1][j] == 2 &&
                            (panel_gracza[i + 2][j] == 0 || panel_gracza[i + 2][j] == 3 || panel_gracza[i + 2][j] == 4)) {
                        licz_trojki++;
                    }

                    if ((panel_gracza[i - 1][j] == 0 || panel_gracza[i - 1][j] == 3 || panel_gracza[i - 1][j] == 4) &&
                            panel_gracza[i + 1][j] == 2 &&
                            panel_gracza[i + 2][j] == 2 &&
                            (panel_gracza[i + 3][j] == 0 || panel_gracza[i + 3][j] == 3 || panel_gracza[i + 3][j] == 4)) {
                        licz_trojki++;
                    }

                    if ((panel_gracza[i - 3][j] == 0 || panel_gracza[i - 3][j] == 3 || panel_gracza[i - 3][j] == 4) &&
                            panel_gracza[i - 2][j] == 2 &&
                            panel_gracza[i - 1][j] == 2 &&
                            (panel_gracza[i + 1][j] == 0 || panel_gracza[i + 1][j] == 3 || panel_gracza[i + 1][j] == 4)) {
                        licz_trojki++;
                    }

                    if ((panel_gracza[i][j - 2] == 0 || panel_gracza[i][j - 2] == 3 || panel_gracza[i][j - 2] == 4) &&
                            panel_gracza[i][j - 1] == 2 &&
                            panel_gracza[i][j + 1] == 2 &&
                            (panel_gracza[i][j + 2] == 0 || panel_gracza[i][j + 2] == 3 || panel_gracza[i][j + 2] == 4)) {
                        licz_trojki++;
                    }

                    if ((panel_gracza[i][j - 1] == 0 || panel_gracza[i][j - 1] == 3 || panel_gracza[i][j - 1] == 4) &&
                            panel_gracza[i][j + 1] == 2 &&
                            panel_gracza[i][j + 2] == 2 &&
                            (panel_gracza[i][j + 3] == 0 || panel_gracza[i][j + 3] == 3 || panel_gracza[i][j + 3] == 4)) {
                        licz_trojki++;
                    }

                    if ((panel_gracza[i][j - 3] == 0 || panel_gracza[i][j - 3] == 3 || panel_gracza[i][j - 3] == 4) &&
                            panel_gracza[i][j - 2] == 2 &&
                            panel_gracza[i][j - 1] == 2 &&
                            (panel_gracza[i][j + 1] == 0 || panel_gracza[i][j + 1] == 3 || panel_gracza[i][j + 1] == 4)) {
                        licz_trojki++;
                    }
                    //////
                    if ((panel_gracza[i - 1][j] == 0 || panel_gracza[i - 1][j] == 3 || panel_gracza[i - 1][j] == 4) &&
                            panel_gracza[i + 1][j] == 2 &&
                            (panel_gracza[i + 2][j] == 0 || panel_gracza[i + 2][j] == 3 || panel_gracza[i + 2][j] == 4)) {
                        licz_dwojki++;
                    }

                    if ((panel_gracza[i - 2][j] == 0 || panel_gracza[i - 2][j] == 3 || panel_gracza[i - 2][j] == 4) &&
                            panel_gracza[i - 1][j] == 2 &&
                            (panel_gracza[i + 1][j] == 0 || panel_gracza[i + 1][j] == 3 || panel_gracza[i + 1][j] == 4)) {
                        licz_dwojki++;
                    }

                    if ((panel_gracza[i][j - 1] == 0 || panel_gracza[i][j - 1] == 3 || panel_gracza[i][j - 1] == 4) &&
                            panel_gracza[i][j + 1] == 2 &&
                            (panel_gracza[i][j + 2] == 0 || panel_gracza[i][j + 2] == 3 || panel_gracza[i][j + 2] == 4)) {
                        licz_dwojki++;
                    }

                    if ((panel_gracza[i][j - 2] == 0 || panel_gracza[i][j - 2] == 3 || panel_gracza[i][j - 2] == 4) &&
                            panel_gracza[i][j - 1] == 2 &&
                            (panel_gracza[i][j + 1] == 0 || panel_gracza[i][j + 1] == 3 || panel_gracza[i][j + 1] == 4)) {
                        licz_dwojki++;
                    }

                    if ((panel_gracza[i][j + 1] == 0 || panel_gracza[i][j + 1] == 3 || panel_gracza[i][j + 1] == 4) &&
                            (panel_gracza[i][j - 1] == 0 || panel_gracza[i][j - 1] == 3 || panel_gracza[i][j - 1] == 4) &&
                            (panel_gracza[i - 1][j] == 0 || panel_gracza[i - 1][j] == 3 || panel_gracza[i - 1][j] == 4) &&
                            (panel_gracza[i + 1][j] == 0 || panel_gracza[i + 1][j] == 3 || panel_gracza[i + 1][j] == 4)) {
                        licz_jedynki++;
                    }
                }
            }
        }

        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                if (panel_gracza[i][j] == 2) {
                    if (licz_czworki == 4) {
                        if ((panel_gracza[i - 4][j] == 0 || panel_gracza[i - 4][j] == 3 || panel_gracza[i - 4][j] == 4) &&
                                panel_gracza[i - 3][j] == 2 &&
                                panel_gracza[i - 2][j] == 2 &&
                                panel_gracza[i - 1][j] == 2 &&
                                (panel_gracza[i + 1][j] == 0 || panel_gracza[i + 1][j] == 3 || panel_gracza[i + 1][j] == 4)) {
                            panel_gracza[i - 4][j] = 3;
                            panel_gracza[i + 1][j] = 3;
                        }

                        if ((panel_gracza[i][j - 1] == 0 || panel_gracza[i][j - 1] == 3 || panel_gracza[i][j - 1] == 4) &&
                                panel_gracza[i][j + 1] == 2 &&
                                panel_gracza[i][j + 2] == 2 &&
                                panel_gracza[i][j + 3] == 2 &&
                                (panel_gracza[i][j + 4] == 0 || panel_gracza[i][j + 4] == 3 || panel_gracza[i][j + 4] == 4)) {
                            panel_gracza[i][j - 1] = 3;
                            panel_gracza[i][j + 4] = 3;
                        }
                    }

                    if (licz_trojki == 6) {
                        if ((panel_gracza[i][j - 3] == 0 || panel_gracza[i][j - 3] == 3 || panel_gracza[i][j - 3] == 4) &&
                                panel_gracza[i][j - 2] == 2 &&
                                panel_gracza[i][j - 1] == 2 &&
                                (panel_gracza[i][j + 1] == 0 || panel_gracza[i][j + 1] == 3 || panel_gracza[i][j + 1] == 4)) {
                            panel_gracza[i][j - 3] = 3;
                            panel_gracza[i][j + 1] = 3;
                        }

                        if ((panel_gracza[i - 2][j] == 0 || panel_gracza[i - 2][j] == 3 || panel_gracza[i - 2][j] == 4) &&
                                panel_gracza[i - 1][j] == 2 &&
                                panel_gracza[i + 1][j] == 2 &&
                                (panel_gracza[i + 2][j] == 0 || panel_gracza[i + 2][j] == 3 || panel_gracza[i + 2][j] == 4)) {
                            panel_gracza[i - 2][j] = 3;
                            panel_gracza[i + 2][j] = 3;
                        }
                    }

                    if (licz_dwojki == 6) {
                        if ((panel_gracza[i][j - 2] == 0 || panel_gracza[i][j - 2] == 3 || panel_gracza[i][j - 2] == 4) &&
                                panel_gracza[i][j - 1] == 2 &&
                                (panel_gracza[i][j + 1] == 0 || panel_gracza[i][j + 1] == 3 || panel_gracza[i][j + 1] == 4)) {
                            panel_gracza[i][j - 2] = 3;
                            panel_gracza[i][j + 1] = 3;
                        }

                        if ((panel_gracza[i - 1][j] == 0 || panel_gracza[i - 1][j] == 3 || panel_gracza[i - 1][j] == 4) &&
                                panel_gracza[i + 1][j] == 2 &&
                                (panel_gracza[i + 2][j] == 0 || panel_gracza[i + 2][j] == 3 || panel_gracza[i + 2][j] == 4)) {
                            panel_gracza[i - 1][j] = 3;
                            panel_gracza[i + 2][j] = 3;
                        }
                    }
                }
            }
        }

        if (licz_czworki == 4 && licz_dwojki == 6 && licz_trojki == 6 && panel_gracza[strzal_i][strzal_j] == 2) {
            if (panel_gracza[strzal_i - 1][strzal_j] == 0 || panel_gracza[strzal_i - 1][strzal_j] == 3 || panel_gracza[strzal_i - 1][strzal_j] == 4) {
                panel_gracza[strzal_i - 1][strzal_j] = 3;
            }
            if (panel_gracza[strzal_i + 1][strzal_j] == 0 || panel_gracza[strzal_i + 1][strzal_j] == 3 || panel_gracza[strzal_i + 1][strzal_j] == 4) {
                panel_gracza[strzal_i + 1][strzal_j] = 3;
            }
            if (panel_gracza[strzal_i][strzal_j - 1] == 0 || panel_gracza[strzal_i][strzal_j - 1] == 3 || panel_gracza[strzal_i][strzal_j - 1] == 4) {
                panel_gracza[strzal_i][strzal_j - 1] = 3;
            }
            if (panel_gracza[strzal_i][strzal_j + 1] == 0 || panel_gracza[strzal_i][strzal_j + 1] == 3 || panel_gracza[strzal_i][strzal_j + 1] == 4) {
                panel_gracza[strzal_i][strzal_j + 1] = 3;
            }
        }
    }

    public void Dodaj_Na_Stos() {
        main:
        for (int i = 0; i < 30; i++) {
            if (stos[i] == 0) {
                stos_I[i] = strzal_i;
                stos_J[i] = strzal_j;
                stos[i] = panel_gracza[strzal_i][strzal_j];
                break main;
            }
        }
    }

    public void Wyczysc_Stosy() {
        for (int i = 0; i < 30; i++) {
            stos[i] = 0;
            stos_I[i] = 0;
            stos_J[i] = 0;
        }
    }

    public void Kontynuuj_Ostrzal() {
        main:
        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                Szukaj_Trafionego();
                if (panel_gracza[strzal_i][strzal_j] == 0 || panel_gracza[strzal_i][strzal_j] == 4) {
                    panel_gracza[strzal_i][strzal_j] = 3;
                    Dodaj_Na_Stos();
                    new Timer(300, new Przerwa()).start();
                    break main;
                } else if (panel_gracza[strzal_i][strzal_j] == 1) {
                    panel_gracza[strzal_i][strzal_j] = 2;
                    zatopione_gracza--;
                    Krawedzie();
                    Policz_Okrety();

                    Dodaj_Na_Stos();
                    Si_Wygralo();
                    i = 9;
                    j = 9;
                }
            }
        }
    }

    public void Gracz_Wygral() {
        if (zatopione_si == 0) {
            wyswietl_maszty_gracza.setText("Maszty gracza " + zatopione_gracza + ".");
            wyswietl_maszty_si.setText("Maszty przeciwnika " + zatopione_si + ".");

            JOptionPane.showMessageDialog(null, "Pokonano przeciwnika. Pozostało " + zatopione_gracza + ". ", "Wygrana", JOptionPane.INFORMATION_MESSAGE);

            Panel_Glowny pg = new Panel_Glowny();
            pg.setVisible(true);
            setVisible(false);
            dispose();

            zatopione_gracza = 20;
            zatopione_si = 20;
        }
    }

    public void Si_Wygralo() {
        if (zatopione_gracza == 0) {
            new Timer(300, new Przerwa()).start();

            wyswietl_maszty_gracza.setText("Maszty gracza " + zatopione_gracza + ".");
            wyswietl_maszty_si.setText("Maszty przeciwnika " + zatopione_si + ".");

            JOptionPane.showMessageDialog(null, "Przeciwnik wygrał. Niezatopiono " + zatopione_si + ". ", "Przegrana", JOptionPane.ERROR_MESSAGE);

            Panel_Glowny pg = new Panel_Glowny();
            pg.setVisible(true);
            setVisible(false);
            dispose();

            zatopione_gracza = 20;
            zatopione_si = 20;
        }
    }

    public void Blokuj() {
        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                panel_si_Button[i][j].setEnabled(false);
            }
        }
    }

    public void Odblokuj() {
        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                panel_si_Button[i][j].setEnabled(true);
            }
        }
    }

    public void Zolty_Znacznik() {
        main:
        for (int i = 10; i < 20; i++) {
            for (int j = 10; j < 20; j++) {
                if (panel_si_Button[i][j].getMouseListeners().length > 1) {
                    panel_si_Button[i][j].removeMouseListener(panel_si_Button[i][j].getMouseListeners()[panel_si_Button[i][j].getMouseListeners().length - 1]);
                }
                panel_si_Button[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        przycisk = e.getSource();
                        for (int i = 10; i < 20; i++) {
                            for (int j = 10; j < 20; j++) {
                                if (e.getButton() == 3 && przycisk == panel_si_Button[i][j]) {
                                    if (zaznaczenie_na_zolto[i][j] == 0 && panel_si[i][j] != 3 && panel_si[i][j] != 2) {
                                        panel_si_Button[i][j].setBackground(Color.yellow);
                                        zaznaczenie_na_zolto[i][j] = 1;
                                    } else if (zaznaczenie_na_zolto[i][j] == 1 && panel_si[i][j] != 3 && panel_si[i][j] != 2) {
                                        panel_si_Button[i][j].setBackground(Color.white);
                                        zaznaczenie_na_zolto[i][j] = 0;
                                    }
                                }
                            }
                        }
                    }
                });
                if (przycisk == null) {
                    break main;
                }
            }
        }
    }

    public void Krawedzie() {
        if (panel_gracza[strzal_i + 1][strzal_j + 1] == 0 || panel_gracza[strzal_i + 1][strzal_j + 1] == 4) {
            panel_gracza[strzal_i + 1][strzal_j + 1] = 3;
        }
        if (panel_gracza[strzal_i - 1][strzal_j - 1] == 0 || panel_gracza[strzal_i - 1][strzal_j - 1] == 4) {
            panel_gracza[strzal_i - 1][strzal_j - 1] = 3;
        }
        if (panel_gracza[strzal_i + 1][strzal_j - 1] == 0 || panel_gracza[strzal_i + 1][strzal_j - 1] == 4) {
            panel_gracza[strzal_i + 1][strzal_j - 1] = 3;
        }
        if (panel_gracza[strzal_i - 1][strzal_j + 1] == 0 || panel_gracza[strzal_i - 1][strzal_j + 1] == 4) {
            panel_gracza[strzal_i - 1][strzal_j + 1] = 3;
        }
    }
}
