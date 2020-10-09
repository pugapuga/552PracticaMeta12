package gui;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.*;

public class TableroGUI extends JDialog {
    private FlujoDeTrabajo flujoDeTrabajo;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonTest;
    private JButton buttonCrearFDT;
    private JButton buttonAgregarFase;
    private JButton buttonAgregarActividad;
    private JButton buttonAgregarTarea;
    private JTable tableTablero;
    private JButton buttonActualizarTablero;
    private JComboBox comboBoxTest;

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
                Fase fase = new Fase("ToDo", flujoDeTrabajo);
                flujoDeTrabajo.getFases().add(fase);
                JOptionPane.showMessageDialog(null,flujoDeTrabajo);
            }
        });
        buttonAgregarActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = new Actividad("Historia de usuario 1", flujoDeTrabajo);
                flujoDeTrabajo.getActividades().add(actividad);
                JOptionPane.showMessageDialog(null,flujoDeTrabajo);
            }
        });
        buttonAgregarTarea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = flujoDeTrabajo.getActividades().get(0);
                Fase fase = flujoDeTrabajo.getFases().get(0);

                Tarea tarea = new Tarea("Tarea 1", actividad, fase, flujoDeTrabajo);

                flujoDeTrabajo.getTareas().add(tarea);
                actividad.getTareas().add(tarea);
                fase.getTareas().add(tarea);
                JOptionPane.showMessageDialog(null,flujoDeTrabajo);
            }
        });
        buttonActualizarTablero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = new DefaultTableModel();
                for (int i = 0; i < flujoDeTrabajo.getFases().size(); i++) {
                    comboBoxTest.addItem(flujoDeTrabajo.getFases().get(i).getNombre());
                }
                tableTablero = new JTable(model);
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
        TableroGUI dialog = new TableroGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
