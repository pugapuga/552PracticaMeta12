package servidor;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private FlujoDeTrabajo flujoDeTrabajo = null;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private int PUERTO = 666;

    public Servidor(int PUERTO) {
        this.PUERTO = PUERTO;
        this.iniciar();
    }

    public void iniciar(){
        try {
            flujoDeTrabajo = new FlujoDeTrabajo("Mi flujo de trabajo");
            //Creamos el socket del servidor
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                System.out.println("Servidor esperando a que se conecte un cliente");
                socket = serverSocket.accept();

                System.out.println("Cliente conectado");

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                String mensaje = (String) objectInputStream.readObject();
                System.out.println("El cliente envio el siguiente mensaje: " + mensaje);

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                if(mensaje.contains("GET FLU")){
                    objectOutputStream.writeObject(flujoDeTrabajo);
                    System.out.println("El servidor respondio el siguiente flujo de trabajo: " + flujoDeTrabajo);
                } else if (mensaje.contains("ADD FAS")){
                    flujoDeTrabajo.getFases().add(new Fase(mensaje.substring(8), this.flujoDeTrabajo));
                    objectOutputStream.writeObject(flujoDeTrabajo);
                    System.out.println("El servidor agrego la fase: " + mensaje.substring(8));
                } else if (mensaje.contains("ADD ACT")){
                    flujoDeTrabajo.getActividades().add(new Actividad(mensaje.substring(8), this.flujoDeTrabajo));
                    objectOutputStream.writeObject(flujoDeTrabajo);
                    System.out.println("El servidor agrego la actividad: " + mensaje.substring(8));
                } else if (mensaje.contains("ADD TAR")){
                    Actividad actividad = flujoDeTrabajo.getActividades().get(Integer.parseInt(mensaje.substring(8,9)));
                    Fase fase = flujoDeTrabajo.getFases().get(Integer.parseInt(mensaje.substring(9,10)));
                    String nombreTarea = mensaje.substring(10);
                    Tarea tarea = new Tarea(nombreTarea, actividad, fase, flujoDeTrabajo);
                    flujoDeTrabajo.getTareas().add(tarea);
                    fase.getTareas().add(tarea);
                    actividad.getTareas().add(tarea);
                    objectOutputStream.writeObject(flujoDeTrabajo);
                    System.out.println("El servidor agrego la actividad: " + mensaje.substring(8));
                } else {
                    objectOutputStream.writeObject(flujoDeTrabajo);
                    System.out.println("El servidor respondio el siguiente flujo de trabajo: " + flujoDeTrabajo);
                }

                //Cierro el socket
                objectOutputStream.close();
                socket.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
