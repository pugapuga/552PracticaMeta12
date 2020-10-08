package gui;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.Tarea;
import tablerodetareas.Columna;
import tablerodetareas.Fila;
import tablerodetareas.Tablero;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TableroDeTareasGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonTest;
    private JTabbedPane tabbedPane1;
    private JPanel panelTablero;

    public TableroDeTareasGUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonTest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Fase> columnas = new ArrayList<Fase>();
                ArrayList<Actividad> filas = new ArrayList<Actividad>();
                ArrayList<Tarea> tarjetas = new ArrayList<Tarea>();

                Tablero tablero = new Tablero("Mi Tablero",columnas, filas, tarjetas);
                System.out.print(tablero);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TableroDeTareasGUI dialog = new TableroDeTareasGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
