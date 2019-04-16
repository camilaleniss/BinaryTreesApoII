package modelo;

import java.util.ArrayList;

public class Jugador {
	
	public static final int ANIOACTUAL = 2018;
	
	private String nombre;
	private String posicion;
	private int puntajeFifa;
	private String fechanac;
	private double altura;
	private String img;
	
	private Jugador siguiente;
	private Jugador anterior;
	
	public Jugador(String nombre, String posicion, int puntajeFifa, String fechanac, double altura, String img) {
		
		this.nombre= nombre;
		this.posicion=posicion;
		this.puntajeFifa=puntajeFifa;
		this.fechanac=fechanac;
		this.altura=altura;
		this.img=img;
		
		siguiente = null;
		anterior=null;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public int getPuntajeFifa() {
		return puntajeFifa;
	}

	public String getFechanac() {
		return fechanac;
	}

	public double getAltura() {
		return altura;
	}

	public String getImg() {
		return img;
	}

	public Jugador getSiguiente() {
		return siguiente;
	}
	
	public Jugador getAnterior(){
		return anterior;
	}
	public int getEdad() {
		String [] edad = fechanac.split("-");
		return (ANIOACTUAL-Integer.parseInt("19"+edad[2]));
	}

	public void setSiguiente(Jugador siguiente) {
		this.siguiente = siguiente;
	}
	
	public void setAnterior (Jugador anterior) {
		this.anterior=anterior;
	}
	
	public void desconectarSiguiente () {
		siguiente=siguiente.siguiente;
	}
	
	public void desconectarAnterior() {
		anterior=anterior.anterior;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	public void toArrayListJugador(ArrayList<Jugador> jugador, Jugador primer){
		jugador.add(this);
		if (siguiente!=primer)
			siguiente.toArrayListJugador(jugador, primer);
	}
	
	public Jugador buscar(String nombre, Jugador primer) {
		if (this.nombre.compareToIgnoreCase(nombre)==0)
			return this;
		else if (siguiente==primer)
			return null;
		else
			return siguiente.buscar(nombre, primer);
	}
	
	public Jugador localizarAnterior(Jugador primero, String nombre) {
		if (siguiente.getNombre().equals(nombre))
			return this;
		else if (siguiente!=primero)
			return siguiente.localizarAnterior(primero, nombre);
		else
			return null;
	}
	
	public int getAcoFifa(Jugador primer) {
		return (siguiente!=primer) ? puntajeFifa+siguiente.getAcoFifa(primer) : puntajeFifa; 
	}
	
	public int getAcoEdad(Jugador primer) {
		return (siguiente!=primer) ? getEdad()+siguiente.getAcoEdad(primer) : getEdad(); 
	}
	
	public double getAcoAltura(Jugador primer) {
		return (siguiente!=primer) ? altura+siguiente.getAcoAltura(primer) : altura;
	}
	
	
	
	
}
