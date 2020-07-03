import java.awt.FlowLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class Panel_Glowny extends JFrame implements ActionListener {
    JButton Graj;
    JButton Zasady;

    public Panel_Glowny() {
        super("Menu główne");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(333, 111);

        setLocationRelativeTo(null);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Graj = new JButton("Graj");
        Graj.addActionListener(this);

        Zasady = new JButton("Zasady");
        Zasady.addActionListener(this);

        add(Graj);
        add(Zasady);

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
        if (przycisk == Graj) {
            try {
                Wybor wyb = new Wybor();
                wyb.setVisible(true);

                setVisible(false);
                dispose();
            } catch (NumberFormatException ee) {
            }
        } else if (przycisk == Zasady) {
            try {
                Zasady zasady = new Zasady();
                zasady.setVisible(true);

                setVisible(false);
                dispose();
            } catch (NumberFormatException ee) {
            }
        }
    }
}