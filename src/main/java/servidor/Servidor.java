package servidor;

import flujodetrabajo.FlujoDeTrabajo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
    private FlujoDeTrabajo flujoDeTrabajo = null;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private int PUERTO = 666;

    public Servidor(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    public FlujoDeTrabajo getFlujoDeTrabajo() {
        return flujoDeTrabajo;
    }

    public void setFlujoDeTrabajo(FlujoDeTrabajo flujoDeTrabajo) {
        this.flujoDeTrabajo = flujoDeTrabajo;
    }

    @Override
    public void run() {
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
                HiloServidor hiloServidor = new HiloServidor(socket, flujoDeTrabajo);
                hiloServidor.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
