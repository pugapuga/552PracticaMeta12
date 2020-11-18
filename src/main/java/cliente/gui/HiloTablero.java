package cliente.gui;

import cliente.Cliente;
import flujodetrabajo.FlujoDeTrabajo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HiloTablero extends Thread{
    private Cliente cliente;
    private FlujoDeTrabajo flujoDeTrabajo;
    private JTable tableTablero;
    private JComboBox comboBoxActividad;
    private JComboBox comboBoxFase;

    public HiloTablero(Cliente cliente, FlujoDeTrabajo flujoDeTrabajo, JTable tableTablero, JComboBox comboBoxActividad, JComboBox comboBoxFase) {
        this.cliente = cliente;
        this.flujoDeTrabajo = flujoDeTrabajo;
        this.tableTablero = tableTablero;
        this.comboBoxActividad = comboBoxActividad;
        this.comboBoxFase = comboBoxFase;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            flujoDeTrabajo = cliente.getFlujoDeTrabajo();

            comboBoxFase.removeAllItems();
            for (int i = 0; i < flujoDeTrabajo.getFases().size(); i++) {
                comboBoxFase.addItem(flujoDeTrabajo.getFases().get(i).getNombre());
            }

            comboBoxActividad.removeAllItems();
            for (int j = 0; j < flujoDeTrabajo.getActividades().size(); j++) {
                comboBoxActividad.addItem(flujoDeTrabajo.getActividades().get(j).getNombre());
            }

            DefaultTableModel modelo = new DefaultTableModel();
            tableTablero.setModel(modelo);

            for (int k = 0; k < flujoDeTrabajo.getFases().size(); k++) {
                modelo.addColumn(flujoDeTrabajo.getFases().get(k).getNombre(), flujoDeTrabajo.getFases().get(k).getTareas());
            }
        }
    }
}
