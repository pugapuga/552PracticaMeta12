package gui;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;
import paquete.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;

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
    private JButton buttonEjemplo1;
    private JButton buttonEjemplo2;
    private JButton buttonGrabar;
    private JButton buttonRecuperar;

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
        buttonEjemplo1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("flujodetrabajo.puga");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    //byte b = 00;
                    //fileOutputStream.write(b);
                    DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

                    dataOutputStream.writeUTF("Hola mundo!");
                    bufferedOutputStream.flush();

                    dataOutputStream.close();
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                
                String s = null;
                
                try {
                    FileInputStream fileInputStream = new FileInputStream("flujodetrabajo.puga");
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    
                    s = dataInputStream.readUTF();
                    
                    dataInputStream.close();
                    fileInputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,s);
            }
        });
        buttonEjemplo2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Alumno alumno = new Alumno("Pepito");

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("flujodetrabajo.puga");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    //byte b = 00;
                    //fileOutputStream.write(b);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

                    objectOutputStream.writeObject(alumno);
                    bufferedOutputStream.flush();

                    objectOutputStream.close();
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                Alumno a = null;

                try {
                    FileInputStream fileInputStream = new FileInputStream("flujodetrabajo.puga");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    a = (Alumno) objectInputStream.readObject();

                    objectInputStream.close();
                    fileInputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,a);

            }
        });
        buttonGrabar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("flujodetrabajo.puga");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    //byte b = 00;
                    //fileOutputStream.write(b);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

                    objectOutputStream.writeObject(flujoDeTrabajo);
                    bufferedOutputStream.flush();

                    objectOutputStream.close();
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        buttonRecuperar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    FileInputStream fileInputStream = new FileInputStream("flujodetrabajo.puga");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    flujoDeTrabajo = (FlujoDeTrabajo) objectInputStream.readObject();

                    objectInputStream.close();
                    fileInputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                actualizarTablero();

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
