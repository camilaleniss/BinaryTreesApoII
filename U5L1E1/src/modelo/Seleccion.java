package modelo;

import java.util.ArrayList;

public class Seleccion implements Comparable{

	private String nombre;
	private String imagen;
	private int puntos;
	private boolean escogida;
	private double promaltura;
	private double promedad;
	private double promfifa;
	
	private int numjugadores;

	private Seleccion izq;
	private Seleccion der;
	
	private Jugador primerjugador;
	
	public Seleccion(String nombre, String imagen, int puntos) {
		this.nombre=nombre;
		this.imagen=imagen;
		this.puntos=puntos;
		escogida=false;
		numjugadores=0;
		izq=null;
		der=null;
		primerjugador= null;
	}
	
	public Seleccion getIzq() {
		return izq;
	}
	
	public Seleccion getDer() {
		return der;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre (String nombre) {
		this.nombre=nombre;
	}

	public String getImagen() {
		return imagen;
	}
	
	public void setImagen (String imagen) {
		this.imagen=imagen;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int puntos) {
		this.puntos=puntos;
	}

	public double getPromaltura() {
		return promaltura;
	}
	
	public boolean getEscogida() {
		return escogida;
	}
	
	public void setEscogida(boolean escogida) {
		this.escogida=escogida;
	}
	
	public void setPromaltura () {
		
		promaltura = ((primerjugador!=null) ? primerjugador.getAcoAltura(primerjugador) : 0 ) / numjugadores;
	}

	public double getPromedad() {
		return promedad;
	}
	
	public void setPromedad () {
		promedad =  ((primerjugador!=null) ? primerjugador.getAcoEdad(primerjugador) : 0 ) / numjugadores;
	}

	public double getPromfifa() {
		return promfifa;
	}
	
	public void setPromfifa() {
		promfifa =  ((primerjugador!=null) ? primerjugador.getAcoFifa(primerjugador) : 0 ) / numjugadores;
	}
	
	public Jugador getPrimerjugador() {
		return primerjugador;
	}
	
	public int getNumjugadores() {
		return numjugadores;
	}
	
	public void addjugador(String nombre, String posicion, int puntajeFifa, String fechanac, double altura, String img) {
		
		Jugador jugador = new Jugador (nombre, posicion, puntajeFifa, fechanac, altura, img);
		
		if (primerjugador==null) {
			jugador.setSiguiente(jugador);
			jugador.setAnterior(jugador);
			primerjugador =jugador;
			
		}else {
			jugador.setSiguiente(primerjugador);
			jugador.setAnterior(localizarultimo());
			Jugador ultimo = localizarultimo();
			ultimo.setSiguiente(jugador);
			primerjugador.setAnterior(jugador);
		}
		
		numjugadores++;
		actualizardatos();
	}
	
	public Jugador localizarultimo() {
		return primerjugador.getAnterior();
	}
	
	public Jugador localizaranterior(String nombre){
		if (primerjugador==null)
			return null;
		else
			return primerjugador.localizarAnterior(primerjugador, nombre);
	}
	
	public Jugador buscarjugador (String nombre) throws NoExisteException{
		Jugador jugador=null;
		if (primerjugador!=null)
			jugador = primerjugador.buscar(nombre, primerjugador);
		else
			throw new NoExisteException();
		if (jugador==null)
			throw new NoExisteException();
		else
			return jugador;
	}
	
	public void eliminarjugador (String nombre) throws NoExisteException{
		Jugador anterior = localizaranterior (nombre);
		if (anterior == null) {
			throw new NoExisteException ();
		}else if (primerjugador.getNombre().equals (nombre)) {
			primerjugador = primerjugador.getSiguiente();
			primerjugador.desconectarAnterior();
		}else {
			anterior.desconectarSiguiente();
			anterior.getSiguiente().desconectarAnterior();
		}
		numjugadores--;
		actualizardatos();
	}
	
	public void actualizardatos() {
		setPromaltura();
		setPromedad();
		setPromfifa();	
	}
	
	public String toString() {
		return nombre;
	}
	
	public ArrayList<Jugador> toArrayList() {
		if (primerjugador ==null)
			return new ArrayList<Jugador>();
		else {
			ArrayList<Jugador> resp = new ArrayList<Jugador>();
			primerjugador.toArrayListJugador(resp, primerjugador);
			return resp;
		}
	}
	
	public String [] datosJugador (String j) {
		String [] datos = new String [6];
		Jugador jugador = buscarjugador(j);
		datos[0] = jugador.getNombre();
		datos[1] = jugador.getPosicion();
		datos[2] = ""+jugador.getPuntajeFifa();
		datos[3] = jugador.getFechanac();
		datos[4] = ""+jugador.getAltura();
		datos[5] = jugador.getImg();
		
		return datos;
	}
	
	
	public void inorden (ArrayList<Seleccion> acomulado) {
		if (izq!=null)
			izq.inorden(acomulado);
		
		acomulado.add(this);
		
		if (der!=null)
			der.inorden(acomulado);
	}

	@Override
	public int compareTo(Object o) {
		Seleccion selec = (Seleccion)o;
		return (int) (promfifa-selec.getPromfifa());
	}
	
	public void insertar (Seleccion selec) throws YaExisteException{
		
		if (compareTo(selec)==0 && nombre.compareToIgnoreCase(selec.getNombre())==0)
			throw new YaExisteException();
		
		if (compareTo(selec)>=0) {
			if (izq == null) 
				izq = selec;
			else
				izq.insertar(selec);
		}else {
			if (der==null)
				der = selec;
			else
				der.insertar(selec);
		}
	}
	
	public boolean esHoja() {
		return izq==null && der==null;
	}
	
	public Seleccion buscar(String unnombre) {
		if (nombre.compareToIgnoreCase(unnombre)==0)
			return this;
		if (esHoja())
			return null;
		Seleccion s1 = (izq!=null) ? izq.buscar(unnombre) : null;
		Seleccion s2 = (der!=null) ? der.buscar(unnombre) : null;
		return (s1==null && s2==null) ? null : (s1==null) ? s2 : s1;
	}
	
	public Seleccion eliminarSeleccion (Seleccion selec) {
		if (esHoja())
			return null;
		if (compareTo(selec)==0) {
			if (izq == null)
				return der;
			if (der==null)
				return izq;
			
			Seleccion sucesor = der.darMenor();
			der = der.eliminarSeleccion(sucesor);
			sucesor.izq=izq;
			sucesor.der=der;
			return sucesor;
		}
		else if (compareTo(selec)>0) {
			if (izq!=null)
				izq = izq.eliminarSeleccion(selec);
		}else {
			if (der!=null)
			der = der.eliminarSeleccion(selec);
		}
		return this;
	}
	
	public Seleccion darMenor() {
		return (izq == null) ? this : izq.darMenor();
	}

	public int darPeso() {
		int p1 = (izq==null) ? 0 : izq.darPeso();
		int p2 = (der==null) ? 0: der.darPeso();
		return 1+p1+p2;
	}
	
	public void reiniciarClasificados() {
		if (izq!=null)
			izq.reiniciarClasificados();
		escogida=false;
		if (der!=null)
			der.reiniciarClasificados();
	}
	
	public int darAltura() {
		if (esHoja())
			return 1;
		else {
			int a1 = (izq==null) ? 0 : izq.darAltura();
			int a2 = (der==null) ? 0 : der.darAltura();
			return 1+Math.max(a1, a2);
		}
	}

}
