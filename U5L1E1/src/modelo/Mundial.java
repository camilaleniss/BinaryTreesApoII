package modelo;

import java.util.ArrayList;

public class Mundial {
	
	public static final int CLASIFICANAOCTAVOS = 16;
	public static final int NUMSELECCIONES =32;
	
	private Seleccion raizseleccion;
	private Clasificado raizclasificatoria;
	private ArrayList<Seleccion> clasificados;
	
	private Grupo[] grupos;
	
	public Mundial() {
		raizseleccion = null;
		raizclasificatoria = null;
		grupos = new Grupo[8];
		for (int i =0; i<grupos.length; i++) {
			grupos[i] = new Grupo();
		}
		clasificados = new ArrayList<Seleccion>();
	
	}
	
	public Seleccion getPrimerseleccion() {
		return raizseleccion;
	}
	
	public Clasificado getRaizClasificatoria() {
		return raizclasificatoria;
	}
	
	public ArrayList<Seleccion> getClasificados(){
		return clasificados;
	}

	public Grupo[] getGrupos() {
		return grupos;
	}

	public void addSeleccion (Seleccion selec) {
		if (raizseleccion == null)
			raizseleccion = selec;
		else
			raizseleccion.insertar(selec);
	}
	
	public void crearSeleccion (String nombre, String imagen, int puntos) {
		Seleccion selec = new Seleccion (nombre, imagen,  puntos);	
		addSeleccion(selec);
	}
	
	public void modificarseleccion(String [] array) throws NoExisteException{
		Seleccion actual = buscarseleccion (array[0]);
		
		if (actual==null)
			throw new NoExisteException();
		
		actual.setNombre(array[1]);
		actual.setImagen(array[2]);
		actual.setPuntos(Integer.parseInt(array[3]));
	}
	
	public Seleccion buscarseleccion (String nombre){
		if (raizseleccion == null)
			return null;
		else {
			return raizseleccion.buscar(nombre);
		}
			
	}
	
	public void eliminarseleccion(String nombre) throws NoExisteException {
		
		if (raizseleccion == null) {
			throw new NoExisteException ();
		}else {
			Seleccion buscada = raizseleccion.buscar(nombre);
			if (buscada==null)
				throw new NoExisteException();
			
			if (buscada == raizseleccion)
				raizseleccion = raizseleccion.eliminarSeleccion(buscada);
			else
				buscada = raizseleccion.eliminarSeleccion(buscada);
		}
	}
	
	public ArrayList<Seleccion> toArrayList(){
		if (raizseleccion ==null)
			return new ArrayList<Seleccion>();
		else {
			ArrayList<Seleccion> resp = new ArrayList<Seleccion>();
			raizseleccion.inorden(resp);
			return resp;
		}
	}
	
	public String [] darinfojugador(String s, String j) {
		Seleccion selec = buscarseleccion (s);
		return selec.datosJugador(j);
	}
	
	public void generarMundial() {
		reiniciarClasificatoria();
		generarGrupos();
		generarClasificados();
		generarCopa();
	}
	
	public void generarGrupos() throws FaltanSeleccionesException {
		ArrayList<Seleccion> selec = toArrayList();
		
		if (selec.size()<NUMSELECCIONES) 
			throw new FaltanSeleccionesException();
		for (int i=0; i<grupos.length; i++) {
			int n=0;
			while (n!=Grupo.NSELECCIONES) {
				boolean escogida = true;
				Seleccion s = null;

				while (escogida) {
					s = selec.get((int)Math.floor(Math.random()*((selec.size()-1)-0+1)+(0)));
					if (!s.getEscogida()) {
						escogida=false;
						s.setEscogida(true);
					}	
				}
				grupos[i].getSelecciones().add(s);
				n++;
			}	
		}
	}

	public void generarClasificados() {
		int par1=0, par2=1;
		Seleccion selec1=null, selec2=null;
		for (int i=0; i<CLASIFICANAOCTAVOS/4; i++) {
			selec1 = grupos[par1].getPrimer();
			selec2 = grupos[par2].getSegundo();
			clasificados.add(selec1);
			clasificados.add(selec2);
			selec1 = grupos[par2].getPrimer();
			selec2 = grupos[par1].getSegundo();
			clasificados.add(selec1);
			clasificados.add(selec2);
			par1+=2;
			par2+=2;
		}
	}
	
	public void reiniciarClasificatoria() {
		raizseleccion.reiniciarClasificados();
	}
	
	public void generarCopa() {
		raizclasificatoria = new Clasificado (clasificados, 1);
	}
	
	public void setElements() {
		Seleccion selec = new Seleccion ("Nigeria", "img/banderas/nigeria.png", 0);
		selec.addjugador("Victor Moses", "Centrocampista", 50, "12-12-90", 1.77, "img/victor.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Serbia", "img/banderas/serbia.png", 0);
		selec.addjugador("Aleksandar Kolarov", "Defensa", 	30, "10-11-85", 1.87, "img/kolarov.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("España", "img/banderas/españa.png", 0);
		selec.addjugador("Isco", "Centrocampista", 80, "21-04-92", 1.76, "img/isco.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Colombia", "img/banderas/colombia.png", 0);
		selec.addjugador("James Rodriguez", "Delantero", 15, "30-08-95", 1.80, "img/james.jpg");
		//selec.addjugador("Cuadrado", "Centro campista", 82, "26-05-88", 1.80, "img/cuadrado.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Alemania", "img/banderas/alemania.png", 0);
		selec.addjugador("Muller", "Delantero", 40, "13-09-89", 1.86, "img/muller.jpg");
		//selec.addjugador("Kevin Trapp", "Portero",	82 , "08-07-90", 1.89, "img/trapp.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Rusia", "img/banderas/rusia.png", 0);
		//selec.addjugador("Konstantin Rausch", "Defensa", 74, "15-03-90", 1.82, "img/rausch.jpg");
		selec.addjugador("Igor Akinfeev", "Portero", 65, "08-04-86", 1.86, "img/akinfeev.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Arabia Saudi", "img/banderas/arabiasaudi.png", 0);
		//selec.addjugador("Osama Hawsawi", "Defensa", 50, "31-08-84", 1.85, "img/osama.jpg");
		selec.addjugador("Yasir Al-Shahrani", "Lateral izquierdo", 90, "25-05-92", 1.71, "img/yasir.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Egipto", "img/banderas/egipto.png", 0);
		selec.addjugador("Mohamed Salah", "Centrocampista", 10, "15-06-92", 1.75, "img/mohamed.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Uruguay", "img/banderas/uruguay.png", 0);
		selec.addjugador("Luis Suarez", "Delantero", 20, "21-01-87", 1.82, "img/suarez.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Marruecos", "img/banderas/marruecos.png", 0);
		selec.addjugador("Mehdi Carcela", "Defensa", 35, "01-07-89", 1.76, "img/mehdi.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Irán", "img/banderas/iran.png", 0);
		selec.addjugador("Sardar Azmoun", "Delantero", 45, "01-01-95", 1.86, "img/sardar.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Brasil", "img/banderas/brasil.png", 0);
		selec.addjugador("Neymar", "Delantero", 60, "05-02-92", 1.76, "img/neymar.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Suiza", "img/banderas/suiza.png", 0);
		selec.addjugador("Granit Xhaka", "Centrocampista", 70, "27-09-92", 1.85, "img/granit.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Costa Rica", "img/banderas/costarica.png", 0);
		selec.addjugador("Bryan Ruiz", "Delantero", 85, "17-09-93", 1.70, "img/bryan.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("México", "img/banderas/mexico.png", 0);
		selec.addjugador("Rafael Marquez", "Centrocampista", 95, "13-02-79", 1.70, "img/rafael.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Suecia", "img/banderas/suecia.png", 0);
		selec.addjugador("Marcus Berg", "Delantero", 5, "17-08-86", 1.84, "img/berg.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Corea del sur", "img/banderas/coreadelsur.png", 0);
		selec.addjugador("Lee Chung-yong", "Centrocampista", 72, "02-07-88", 1.80, "img/lee.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Francia", "img/banderas/francia.png", 0);
		selec.addjugador("Hugo Lloris", "Portero", 13, "26-12-86", 1.88, "img/hugo.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Australia", "img/banderas/australia.png", 0);
		selec.addjugador("Tim Cahill", "Delantero", 18, "06-12-79", 1.78, "img/cahill.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Perú", "img/banderas/peru.png", 0);
		selec.addjugador("Paolo Guerrero", "Delantero", 23, "01-01-84", 1.85, "img/paolo.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Dinamarca", "img/banderas/dinamarca.png", 0);
		selec.addjugador("Kevin Conboy", "Defensa", 33, "15-10-87", 1.85, "img/kevin.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Argentina", "img/banderas/argentina.png", 0);
		selec.addjugador("Leonel Messi", "Delantero", 38, "24-06-87", 1.70, "img/messi.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Islandia", "img/banderas/islandia.png", 0);
		selec.addjugador("Birkir Bjarnason", "Centrocampista", 43, "27-05-88", 1.82, "img/birkir.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Croacia", "img/banderas/croacia.png", 0);
		selec.addjugador("Luka Modric", "Centrocampista", 48, "09-09-85", 1.74, "img/luka.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Polonia", "img/banderas/polonia.png", 0);
		selec.addjugador("Robin Lewandowski", "Delantero", 55, "21-08-88", 1.85, "img/robin.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Bélgica", "img/banderas/belgica.png", 0);
		selec.addjugador("Eden Hazard", "Centrocampista", 62, "07-01-91", 1.73, "img/eden.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Panamá", "img/banderas/panama.png", 0);
		selec.addjugador("Roman Torres", "Defensa", 68, "20-03-86", 1.87, "img/roman.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Túnez", "img/banderas/tunez.png", 0);
		selec.addjugador("Wahbi Khazri", "Centrocampista", 73, "08-02-91", 1.82, "img/wahbi.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Inglaterra", "img/banderas/inglaterra.jpg", 0);
		selec.addjugador("Harry Kane", "Delantero", 83, "28-07-93", 1.88, "img/harry.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Senegal", "img/banderas/senegal.png", 0);
		selec.addjugador("Moussa Sow", "Delantero", 88, "19-01-86", 1.80, "img/moussa.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Japón", "img/banderas/japon.png", 0);
		selec.addjugador("Atsuto Uchida", "Defensa", 93, "27-03-88", 1.76, "img/atsuto.jpg");
		addSeleccion(selec);
		
		selec = new Seleccion ("Portugal", "img/banderas/portugal.png", 0);
		selec.addjugador("Cristiano Ronaldo", "Delantero", 99, "05-02-85", 1.85, "img/cristiano.jpg");
		addSeleccion(selec);
	}
}
