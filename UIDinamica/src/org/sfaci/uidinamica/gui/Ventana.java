package org.sfaci.uidinamica.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal de la aplicación
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class Ventana implements ActionListener {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton btIniciar;
    private JButton btParar;
    private JButton btAnadir;
    private JButton btReiniciar;
    private JPanel paneles;

    public Ventana() {

        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        anadirListeners();
    }

    private void anadirListeners() {

        btAnadir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = ((JButton) e.getSource()).getActionCommand();

        switch (actionCommand) {
            case "Anadir":
                // Añade el panel a la ventana y fuerza el refresco de la UI (revalidate)
                PanelWorker panel = new PanelWorker();
                paneles.add(panel.panel1);
                paneles.revalidate();
                break;
            default:
                break;
        }
    }
}
