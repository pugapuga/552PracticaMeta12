package cliente.gui;

import cliente.Cliente;

public class ClienteGUI {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("localhost", 666);
        cliente.enviarMensaje("ADD FAS ToDo");
    }
}
