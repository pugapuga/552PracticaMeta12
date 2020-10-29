package servidor;

import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;

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
            this.flujoDeTrabajo = new FlujoDeTrabajo("Mi flujo de trabajo");
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
                    this.flujoDeTrabajo.getFases().add(new Fase(mensaje.substring(8), this.flujoDeTrabajo));
                    objectOutputStream.writeObject(flujoDeTrabajo);
                    System.out.println("El servidor agrego la fase: " + mensaje.substring(8));
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
