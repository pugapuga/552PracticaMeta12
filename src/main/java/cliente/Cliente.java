package cliente;

import flujodetrabajo.FlujoDeTrabajo;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    private FlujoDeTrabajo flujoDeTrabajo = null;
    //Host del servidor
    private String HOST = "127.0.0.1";
    private int PUERTO = 666;

    public Cliente(String HOST, int PUERTO) {
        this.HOST = HOST;
        this.PUERTO = PUERTO;
        this.flujoDeTrabajo = new FlujoDeTrabajo("Default");
    }

    public void enviarMensaje(String mensaje){
        try {
            //Creo el socket para conectarme con el cliente
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Cliente Conectado");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(mensaje);
            System.out.println("El cliente envio el siguiente mensaje: " + mensaje);


            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();

            if(object.getClass().getName().equalsIgnoreCase("FlujoDeTrabajo")){
                this.flujoDeTrabajo = (FlujoDeTrabajo) object;
                System.out.println("El servidor respondio el siguiente flujo de trabajo: " + this.flujoDeTrabajo);
            } else {
                System.out.println("El servidor respondio el siguiente objeto: " + object);
            }

            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
            System.out.println("Cliente desconectado");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
