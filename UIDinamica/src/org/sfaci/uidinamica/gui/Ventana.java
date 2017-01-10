package org.sfaci.uidinamica.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Ventana principal de la aplicación
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class Ventana implements ActionListener, KeyListener {
    private JPanel panel1;
    private JTextField tfNombre;
    private JTextField tfVelocidad;
    private JButton btIniciar;
    private JButton btParar;
    private JButton btAnadir;
    private JButton btReiniciar;
    private JPanel paneles;

    private ArrayList<PanelWorker> listaPaneles;

    public Ventana() {

        Thread hiloSplash = new Thread(new Runnable() {
            @Override
            public void run() {
                SplashScreen splash = new SplashScreen();
                splash.mostrar();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                splash.ocultar();
            }
        });
        hiloSplash.start();
        try {
            hiloSplash.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        anadirListeners();

        listaPaneles = new ArrayList<PanelWorker>();
    }

    private void anadirListeners() {

        btAnadir.addActionListener(this);
        btIniciar.addActionListener(this);
        tfVelocidad.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = ((JButton) e.getSource()).getActionCommand();

        switch (actionCommand) {
            case "Anadir":
                // Añade el panel a la ventana y fuerza el refresco de la UI (revalidate)
                PanelWorker panel =
                        new PanelWorker(tfNombre.getText(),
                                Integer.parseInt(tfVelocidad.getText()));
                tfNombre.setText("");
                tfVelocidad.setText("");
                tfNombre.requestFocus();
                paneles.add(panel.panel1);
                paneles.revalidate();
                listaPaneles.add(panel);
                break;
            case "Iniciar":
                for (PanelWorker panelWorker : listaPaneles)
                    panelWorker.iniciar();
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == tfVelocidad) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                btAnadir.doClick();
            }
        }
    }
}
