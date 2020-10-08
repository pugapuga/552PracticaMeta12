package tablerodetareas;

import flujodetrabajo.*;

import java.util.ArrayList;

public class Columna extends Fase {

    public Columna(String nombre, FlujoDeTrabajo flujoDeTrabajo, ArrayList<Tarea> tareas) {
        super(nombre, flujoDeTrabajo, tareas);
    }
}