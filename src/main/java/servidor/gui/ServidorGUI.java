package servidor.gui;

import servidor.Servidor;

public class ServidorGUI {
    public static void main(String[] args) {

        Servidor servidor = new Servidor(666);
        servidor.start();
    }
}
