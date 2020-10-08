package flujodetrabajo;

import java.util.*;

public class Actividad {

	private String nombre;
	private FlujoDeTrabajo flujoDeTrabajo;
	private ArrayList<Tarea> tareas;

	public Actividad(String nombre, FlujoDeTrabajo flujoDeTrabajo, ArrayList<Tarea> tareas) {
		this.nombre = nombre;
		this.flujoDeTrabajo = flujoDeTrabajo;
		this.tareas = tareas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public FlujoDeTrabajo getFlujoDeTrabajo() {
		return flujoDeTrabajo;
	}

	public void setFlujoDeTrabajo(FlujoDeTrabajo flujoDeTrabajo) {
		this.flujoDeTrabajo = flujoDeTrabajo;
	}

	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}

	@Override
	public String toString() {
		return "Actividad{" +
				"nombre='" + nombre + '\'' +
				'}';
	}

	/**
	 * 
	 * @param nombre
	 * @param flujoDeTrabajo
	 */
	public Actividad(String nombre, FlujoDeTrabajo flujoDeTrabajo) {
		// TODO - implement Actividad.Actividad
		throw new UnsupportedOperationException();
	}
}