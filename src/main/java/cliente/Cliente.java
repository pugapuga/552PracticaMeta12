package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    //Host del servidor
    private String HOST = "127.0.0.1";
    private int PUERTO = 666;

    public Cliente(String HOST, int PUERTO) {
        this.HOST = HOST;
        this.PUERTO = PUERTO;
    }

    public void enviarMensaje(String mensaje){
        try {
            //Creo el socket para conectarme con el cliente
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Cliente Conectado");

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(mensaje);
            System.out.println("El cliente envio el siguiente mensaje: " + mensaje);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String respuesta = dataInputStream.readUTF();
            System.out.println("El servidor respondio el siguiente mensaje: " + respuesta);

            socket.close();
            System.out.println("Cliente desconectado");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
