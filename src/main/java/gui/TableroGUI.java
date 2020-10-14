package gui;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class TableroGUI extends JDialog {
    private FlujoDeTrabajo flujoDeTrabajo;
    private JPanel contentPane;
    private JPanel panelPrincipal;
    private JPanel panelBotones;
    private JPanel panelSecundario;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonTest;
    private JButton buttonAgregarFase;
    private JButton buttonAgregarActividad;
    private JButton buttonAgregarTarea;
    private JTable tableTablero;
    private JButton buttonActualizarTablero;
    private JTextField textFieldFase;
    private JTextField textFieldActividad;
    private JComboBox comboBoxFase;
    private JComboBox comboBoxActividad;
    private JTextField textFieldTarea;

    private DefaultTableModel modelo;

    public TableroGUI() {
        flujoDeTrabajo = new FlujoDeTrabajo("Mi flujo de trabajo");


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
                buttonTest.setText("Ouch!");
            }
        });
        buttonTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonTest.setText("Hola!");
            }
        });
        buttonTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonTest.setText("Bye!");
            }
        });

        buttonAgregarFase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fase fase = new Fase(textFieldFase.getText(), flujoDeTrabajo);
                flujoDeTrabajo.getFases().add(fase);
                actualizarTablero();
               // JOptionPane.showMessageDialog(null,flujoDeTrabajo);
            }
        });
        buttonAgregarActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = new Actividad(textFieldActividad.getText(), flujoDeTrabajo);
                flujoDeTrabajo.getActividades().add(actividad);
                actualizarTablero();
                // JOptionPane.showMessageDialog(null,flujoDeTrabajo);
            }
        });
        buttonAgregarTarea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fase fase = flujoDeTrabajo.getFases().get(comboBoxFase.getSelectedIndex());
                Actividad actividad = flujoDeTrabajo.getActividades().get(comboBoxActividad.getSelectedIndex());

                Tarea tarea = new Tarea(textFieldTarea.getText(), actividad, fase, flujoDeTrabajo);
                flujoDeTrabajo.getTareas().add(tarea);
                actividad.getTareas().add(tarea);
                fase.getTareas().add(tarea);
                actualizarTablero();
                // JOptionPane.showMessageDialog(null,flujoDeTrabajo);
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

    private void actualizarTablero(){
        comboBoxFase.removeAllItems();
        for (int i = 0; i < flujoDeTrabajo.getFases().size(); i++) {
            comboBoxFase.addItem(flujoDeTrabajo.getFases().get(i).getNombre());
        }

        comboBoxActividad.removeAllItems();
        for (int j = 0; j < flujoDeTrabajo.getActividades().size(); j++) {
            comboBoxActividad.addItem(flujoDeTrabajo.getActividades().get(j).getNombre());
        }

        modelo = new DefaultTableModel();
        tableTablero.setModel(modelo);

        for (int k = 0; k < flujoDeTrabajo.getFases().size(); k++) {
            modelo.addColumn(flujoDeTrabajo.getFases().get(k).getNombre(), flujoDeTrabajo.getFases().get(k).getTareas());
        }
    }

    public static void main(String[] args) {
        TableroGUI dialog = new TableroGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
