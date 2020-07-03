import java.awt.FlowLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class Wybor extends JFrame implements ActionListener {
    JButton Latwy;
    JButton Trudny;
    int poziom = 0;

    public Wybor() {
        super("Wybór trudności");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(333, 111);

        setLocationRelativeTo(null);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Latwy = new JButton("Łatwy");
        Latwy.addActionListener(this);

        Trudny = new JButton("Trudny");
        Trudny.addActionListener(this);

        add(Latwy);
        add(Trudny);

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
        if (przycisk == Latwy) {
            try {
                Rozstaw rozstaw = new Rozstaw(poziom = 0);
                rozstaw.setVisible(true);

                setVisible(false);
                dispose();
            } catch (NumberFormatException ee) {
            }
        } else if (przycisk == Trudny) {
            try {
                Rozstaw rozstaw = new Rozstaw(poziom = 1);
                rozstaw.setVisible(true);

                setVisible(false);
                dispose();
            } catch (NumberFormatException ee) {
            }
        }
    }
}