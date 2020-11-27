package cliente.gui;

import cliente.Cliente;
import flujodetrabajo.FlujoDeTrabajo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ClienteTableroGUI extends JDialog {
    private Cliente cliente;
    private FlujoDeTrabajo flujoDeTrabajo;
    private DefaultTableModel modelo;
    private Component component;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tableTablero;
    private JButton buttonAgregarActividad;
    private JTextField textFieldActividad;
    private JButton buttonAgregarFase;
    private JTextField textFieldFase;
    private JComboBox comboBoxActividad;
    private JComboBox comboBoxFase;
    private JButton buttonTarea;
    private JTextField textFieldTarea;
    private JButton buttonActualizarTablero;
    private JTabbedPane tabbedPane1;
    private JPanel panelTabla;
    private JPanel panelListas;
    private JList listToDo;
    private JList listInProgress;
    private JList listDone;

    public ClienteTableroGUI() {
        cliente = new Cliente("localhost", 666);
        flujoDeTrabajo = new FlujoDeTrabajo("Default");
        actualizarTablero();

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
        buttonAgregarActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.enviarMensaje("ADD ACT " + textFieldActividad.getText());
                actualizarTablero();
            }
        });
        buttonAgregarFase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.enviarMensaje("ADD FAS " + textFieldFase.getText());
                actualizarTablero();
            }
        });
        buttonTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.enviarMensaje("ADD TAR "+comboBoxActividad.getSelectedIndex()+comboBoxFase.getSelectedIndex()+ textFieldTarea.getText());
                actualizarTablero();
            }
        });
        buttonActualizarTablero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HiloTablero hiloTablero = new HiloTablero(cliente,flujoDeTrabajo,tableTablero,comboBoxActividad,comboBoxFase);
                hiloTablero.start();
            }
        });
        listInProgress.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                component = ((JList)e.getComponent());
                System.out.println(component);
            }
        });
        listInProgress.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                listInProgress.remove(component);
                listDone.add(component);

                //System.out.println(name);
            }
        });
    }

    private void actualizarTablero(){
        flujoDeTrabajo = cliente.getFlujoDeTrabajo();

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

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ClienteTableroGUI dialog = new ClienteTableroGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
