package servidor.gui;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import servidor.Servidor;

import javax.swing.*;
import java.awt.event.*;

public class ServidorDesktopGUI extends JDialog {
    private Servidor servidor;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonIniciar;
    private JTabbedPane tabbedPaneFlujoDeTrabajo;
    private JPanel panelActividad;
    private JPanel panelFase;
    private JPanel panelTarea;
    private JPanel panelUsuario;
    private JTextField textFieldActividad;
    private JButton buttonAgregarActividad;
    private JTextField textFieldFase;
    private JButton buttonAgregarFase;

    public ServidorDesktopGUI() {
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
        buttonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                servidor = new Servidor(666);
                servidor.start();
            }
        });
        buttonAgregarActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = new Actividad(textFieldActividad.getText(), servidor.getFlujoDeTrabajo());
                servidor.getFlujoDeTrabajo().getActividades().add(actividad);
            }
        });
        buttonAgregarFase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fase fase = new Fase(textFieldFase.getText(), servidor.getFlujoDeTrabajo());
                servidor.getFlujoDeTrabajo().getFases().add(fase);
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
        ServidorDesktopGUI dialog = new ServidorDesktopGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
