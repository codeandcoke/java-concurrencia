package org.sfsoft.barraprogreso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Proyecto que muestra como cargar y detener una barra de progreso
 *
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class JBarraProgreso {
    private JPanel panel1;
    private JButton btDetener;
    private JButton btIniciar;
    private JProgressBar pbCarga;

    private boolean cargando;

    public JBarraProgreso() {
        btIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciar();
            }
        });


        btDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detener();
            }
        });
    }

    private void iniciar() {
        Thread hiloCarga = new Thread(){
            public void run() {

                // Se activa la carga
                cargando = true;
                // Mientras la barra de progreso no esté completa y no se haya cancelado la carga, ésta se realiza
                while (pbCarga.getValue() < 100 && cargando) {
                    pbCarga.setValue(pbCarga.getValue() + 5);
                    try {
                        // Una pausa que simula la tarea que haya que realizar
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {}
                }

                // La carga ha terminado, se muestra un mensaje
                if (cargando)
                    JOptionPane.showMessageDialog(null, "Carga completada", "Carga", JOptionPane.INFORMATION_MESSAGE);
                    // El usuario ha cancelado la carga
                else {
                    pbCarga.setValue(0);
                    JOptionPane.showMessageDialog(null, "Carga cancelada por el usuario", "Carga", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        hiloCarga.start();
    }

    private void detener() {
        cargando = false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JBarraProgreso");
        frame.setContentPane(new JBarraProgreso().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
