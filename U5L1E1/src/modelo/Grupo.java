package modelo;

import java.util.ArrayList;

public class Grupo {

	public static final int NSELECCIONES=4;
	
	private ArrayList<Seleccion> selecciones;
	
	public Grupo() {
		selecciones = new ArrayList<Seleccion>();
	}
	
	public ArrayList<Seleccion> getSelecciones() {
		return selecciones;
	}
	
	public Seleccion getPrimer() {
		return selecciones.get(0);
	}
	
	public Seleccion getSegundo() {
		return selecciones.get(1);
	}

}
