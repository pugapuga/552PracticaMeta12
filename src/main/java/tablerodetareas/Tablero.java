package tablerodetareas;

import flujodetrabajo.*;

import java.util.ArrayList;

public class Tablero extends FlujoDeTrabajo {

    public Tablero(String nombre, ArrayList<Fase> fases, ArrayList<Actividad> actividades, ArrayList<Tarea> tareas) {
        super(nombre, fases, actividades, tareas);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}