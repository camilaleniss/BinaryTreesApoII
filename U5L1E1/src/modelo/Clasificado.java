package modelo;

import java.util.ArrayList;

public class Clasificado {
	
	private Seleccion seleccion;
	
	private Clasificado izq;
	private Clasificado der;

	public Clasificado(ArrayList<Seleccion> ss, int nivelarbol) {
		generarGanador(ss, nivelarbol);
	}
	
	public Clasificado(Seleccion seleccion) {
		this.seleccion = seleccion;
	}
	
	public Seleccion getSeleccion() {
		return seleccion;
	}
	
	public Clasificado getIzq() {
		return izq;
	}
	
	public Clasificado getDer() {
		return der;
	}
	
	public boolean esHoja() {
		return der==null && izq==null;
	}
	
	public void generarGanador(ArrayList<Seleccion> array, int nivelarbol) {
		if (nivelarbol==4) {
			izq = new Clasificado (array.get(0));
			der = new Clasificado (array.get(1));
			array.remove(0);
			array.remove(0);
		}else {
			izq = new Clasificado(array, nivelarbol+1);
			der = new Clasificado(array, nivelarbol+1);
		}
		int ganador = (int)Math.floor(Math.random()*((10)-0+1)+(0));
		seleccion = (ganador%2==0) ? izq.seleccion: der.seleccion;
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
