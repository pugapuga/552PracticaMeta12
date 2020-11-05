package servidor;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidor extends Thread{
    private Socket socket;
    private FlujoDeTrabajo flujoDeTrabajo;

    public HiloServidor(Socket socket, FlujoDeTrabajo flujoDeTrabajo) {
        this.socket = socket;
        this.flujoDeTrabajo = flujoDeTrabajo;
    }

    @Override
    public void run() {
        System.out.println("Cliente conectado");

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String mensaje = null;

            mensaje = (String) objectInputStream.readObject();

            System.out.println("El cliente envio el siguiente mensaje: " + mensaje);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            if (mensaje.contains("GET FLU")) {
                objectOutputStream.writeObject(flujoDeTrabajo);
                System.out.println("El servidor respondio el siguiente flujo de trabajo: " + flujoDeTrabajo);
            } else if (mensaje.contains("ADD FAS")) {
                flujoDeTrabajo.getFases().add(new Fase(mensaje.substring(8), this.flujoDeTrabajo));
                objectOutputStream.writeObject(flujoDeTrabajo);
                System.out.println("El servidor agrego la fase: " + mensaje.substring(8));
            } else if (mensaje.contains("ADD ACT")) {
                flujoDeTrabajo.getActividades().add(new Actividad(mensaje.substring(8), this.flujoDeTrabajo));
                objectOutputStream.writeObject(flujoDeTrabajo);
                System.out.println("El servidor agrego la actividad: " + mensaje.substring(8));
            } else if (mensaje.contains("ADD TAR")) {
                Actividad actividad = flujoDeTrabajo.getActividades().get(Integer.parseInt(mensaje.substring(8, 9)));
                Fase fase = flujoDeTrabajo.getFases().get(Integer.parseInt(mensaje.substring(9, 10)));
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
            objectInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Cliente desconectado");
    }
}
