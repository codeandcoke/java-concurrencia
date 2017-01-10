package org.sfaci.uidinamica.gui;

import javax.swing.*;

/**
 * Created by mto on 20/11/15.
 */
public class SplashScreen {
    private JPanel panel;

    private JFrame frame;

    public SplashScreen() {
        frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void mostrar() {

        frame.setVisible(true);
    }

    public void ocultar() {
        frame.setVisible(false);
        frame.dispose();
    }
}
