package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private int PUERTO = 666;

    public Servidor(int PUERTO) {
        this.PUERTO = PUERTO;
        this.iniciar();
    }

    public void iniciar(){
        try {
            //Creamos el socket del servidor
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                System.out.println("Servidor esperando a que se conecte un cliente");
                socket = serverSocket.accept();

                System.out.println("Cliente conectado");

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String mensaje = dataInputStream.readUTF();
                System.out.println("El cliente envio el siguiente mensaje: " + mensaje);

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String respuesta = "Te estamos esperando!";
                dataOutputStream.writeUTF(respuesta);
                System.out.println("El servidor respondio el siguiente mensaje: " + respuesta);

                //Cierro el socket
                socket.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
