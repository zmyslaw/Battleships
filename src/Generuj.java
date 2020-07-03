import javax.swing.*;
import java.util.*;

public class Generuj {
    int panel[][] = new int[30][30];

    int wykorzystane_statki = 0;
    int pozostalo;
    int poprawnosc;

    int maszty_czworki = 0;
    int maszty_trojki = 0;
    int maszty_dwojki = 0;
    int maszty_jedynki = 0;

    JFrame oknoPc;

    public Generuj() {
        Random wartosc = new Random();

        for (int i = 1; i < 28; i++) {
            for (int j = 1; j < 28; j++) {
                panel[i][j] = 0;
            }
        }

        while (poprawnosc == 0) {
            while (wykorzystane_statki < 20) {
                int i = wartosc.nextInt((19 - 10) + 1) + 10;
                int j = wartosc.nextInt((19 - 10) + 1) + 10;
                if (panel[i][j] == 0) {
                    panel[i][j]++;
                    wykorzystane_statki++;

                    if (panel[i + 1][j + 1] == 0) {
                        panel[i + 1][j + 1] = 4;
                    }
                    if (panel[i - 1][j - 1] == 0) {
                        panel[i - 1][j - 1] = 4;
                    }
                    if (panel[i + 1][j - 1] == 0) {
                        panel[i + 1][j - 1] = 4;
                    }
                    if (panel[i - 1][j + 1] == 0) {
                        panel[i - 1][j + 1] = 4;
                    }
                }
            }

            for (int i = 10; i < 20; i++) {
                for (int j = 10; j < 20; j++) {
                    if (panel[i][j] == 1) {
                        if (
                                (panel[i][j - 1] == 0 &&
                                        panel[i][j + 1] == 1 &&
                                        panel[i][j + 2] == 1 &&
                                        panel[i][j + 3] == 1 &&
                                        panel[i][j + 4] == 0) ||

                                        (panel[i][j - 2] == 0 &&
                                                panel[i][j - 1] == 1 &&
                                                panel[i][j + 1] == 1 &&
                                                panel[i][j + 2] == 1 &&
                                                panel[i][j + 3] == 0) ||

                                        (panel[i][j - 3] == 0 &&
                                                panel[i][j - 2] == 1 &&
                                                panel[i][j - 1] == 1 &&
                                                panel[i][j + 1] == 1 &&
                                                panel[i][j + 2] == 0) ||

                                        (panel[i][j - 4] == 0 &&
                                                panel[i][j - 3] == 1 &&
                                                panel[i][j - 2] == 1 &&
                                                panel[i][j - 1] == 1 &&
                                                panel[i][j + 1] == 0) ||

                                        (panel[i - 1][j] == 0 &&
                                                panel[i + 1][j] == 1 &&
                                                panel[i + 2][j] == 1 &&
                                                panel[i + 3][j] == 1 &&
                                                panel[i + 4][j] == 0) ||

                                        (panel[i - 2][j] == 0 &&
                                                panel[i - 1][j] == 1 &&
                                                panel[i + 1][j] == 1 &&
                                                panel[i + 2][j] == 1 &&
                                                panel[i + 3][j] == 0) ||

                                        (panel[i - 3][j] == 0 &&
                                                panel[i - 2][j] == 1 &&
                                                panel[i - 1][j] == 1 &&
                                                panel[i + 1][j] == 1 &&
                                                panel[i + 2][j] == 0) ||

                                        (panel[i - 4][j] == 0 &&
                                                panel[i - 3][j] == 1 &&
                                                panel[i - 2][j] == 1 &&
                                                panel[i - 1][j] == 1 &&
                                                panel[i + 1][j] == 0)
                        ) {
                            maszty_czworki++;
                        }

                        if (
                                (panel[i - 2][j] == 0 &&
                                        panel[i - 1][j] == 1 &&
                                        panel[i + 1][j] == 1 &&
                                        panel[i + 2][j] == 0) ||

                                        (panel[i - 1][j] == 0 &&
                                                panel[i + 1][j] == 1 &&
                                                panel[i + 2][j] == 1 &&
                                                panel[i + 3][j] == 0) ||

                                        (panel[i - 3][j] == 0 &&
                                                panel[i - 2][j] == 1 &&
                                                panel[i - 1][j] == 1 &&
                                                panel[i + 1][j] == 0) ||

                                        (panel[i][j - 2] == 0 &&
                                                panel[i][j - 1] == 1 &&
                                                panel[i][j + 1] == 1 &&
                                                panel[i][j + 2] == 0) ||

                                        (panel[i][j - 1] == 0 &&
                                                panel[i][j + 1] == 1 &&
                                                panel[i][j + 2] == 1 &&
                                                panel[i][j + 3] == 0) ||

                                        (panel[i][j - 3] == 0 &&
                                                panel[i][j - 2] == 1 &&
                                                panel[i][j - 1] == 1 &&
                                                panel[i][j + 1] == 0)
                        ) {
                            maszty_trojki++;
                        }

                        if (
                                (panel[i - 1][j] == 0 &&
                                        panel[i + 1][j] == 1 &&
                                        panel[i + 2][j] == 0) ||

                                        (panel[i - 2][j] == 0 &&
                                                panel[i - 1][j] == 1 &&
                                                panel[i + 1][j] == 0) ||

                                        (panel[i][j - 1] == 0 &&
                                                panel[i][j + 1] == 1 &&
                                                panel[i][j + 2] == 0) ||

                                        (panel[i][j - 2] == 0 &&
                                                panel[i][j - 1] == 1 &&
                                                panel[i][j + 1] == 0)
                        ) {
                            maszty_dwojki++;
                        }

                        if (panel[i - 1][j] == 0 &&
                                panel[i][j - 1] == 0 &&
                                panel[i][j + 1] == 0 &&
                                panel[i + 1][j] == 0
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
                maszty_czworki = 0;
                maszty_trojki = 0;
                maszty_dwojki = 0;
                maszty_jedynki = 0;
                break;
            }

            maszty_czworki = 0;
            maszty_trojki = 0;
            maszty_dwojki = 0;
            maszty_jedynki = 0;

            while (wykorzystane_statki <= 20) {
                for (int i = 10; i < 20; i++) {
                    for (int j = 10; j < 20; j++) {
                        if (panel[i][j] == 1) {
                            panel[i][j]--;
                            wykorzystane_statki--;

                            if (panel[i + 1][j + 1] == 4) {
                                panel[i + 1][j + 1] = 0;
                            }
                            if (panel[i - 1][j - 1] == 4) {
                                panel[i - 1][j - 1] = 0;
                            }
                            if (panel[i + 1][j - 1] == 4) {
                                panel[i + 1][j - 1] = 0;
                            }
                            if (panel[i - 1][j + 1] == 4) {
                                panel[i - 1][j + 1] = 0;
                            }
                        }
                        if (wykorzystane_statki == 0) {
                            wykorzystane_statki = 22;
                        }
                    }
                }
            }
            wykorzystane_statki = 0;
        }
    }

    public int[][] zwroc_panel() {
        return this.panel;
    }
}