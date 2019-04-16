package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modelo.Jugador;
import modelo.Mundial;
import modelo.NoExisteException;

class TestSeleccion {

	private Mundial mundial;
	
	TestSeleccion(){
		mundial = new Mundial();
		mundial.crearSeleccion("Colombia", "img/seleccioncolombia", 0);
	}
	
	void setUpEscenario1(){
		mundial.getPrimerseleccion().addjugador("Faryd", "Delantero", 10, "26-08-1987", 1.80, "img/faryd");
	}
	
	void setUpEscenario2() {
		setUpEscenario1();
		mundial.getPrimerseleccion().addjugador("Messi", "Delantero", 9, "26-07-1987", 1.90, "img/messi");
	}
	
	void setUpEscenario3() {
		setUpEscenario2();
		mundial.getPrimerseleccion().addjugador("Camilo", "Defensa", 8, "27-07-1990", 1.90, "img/camilo");
	}
	
	void setUpEscenario4() {
		setUpEscenario3();
		mundial.getPrimerseleccion().addjugador("Juan Manuel", "Defensa", 7, "31-01-1993", 1.92, "img/juanma");
		
	}

	@Test
	void testAddJugador1() {
		mundial.getPrimerseleccion().addjugador("Faryd", "Delantero", 10, "26-08-1987", 1.80, "img/faryd");
		assertTrue(mundial.getPrimerseleccion().getNumjugadores()==1);
	}
	
	@Test
	void testAddJugador2() {
		setUpEscenario1();
		
		mundial.getPrimerseleccion().addjugador("Messi", "Delantero", 9, "26-07-1987", 1.90, "img/messi");
		
		Jugador jugador = mundial.getPrimerseleccion().getPrimerjugador().getSiguiente();
		
		assertEquals(jugador.getNombre(), "Messi");
		assertEquals(jugador.getPosicion(), "Delantero");
		assertEquals(jugador.getPuntajeFifa(), 9);
		assertEquals(jugador.getFechanac(), "26-07-1987");
		assertEquals(jugador.getImg(), "img/messi");
		
		assertTrue(mundial.getPrimerseleccion().getNumjugadores()==2);
		assertEquals(jugador.getAnterior().getNombre(), "Faryd");
	}
	
	@Test
	void testAddJugador3() {
		setUpEscenario2();
		mundial.getPrimerseleccion().addjugador("Camilo", "Defensa", 8, "27-07-1990", 1.90, "img/camilo");
		
		Jugador jugador = mundial.getPrimerseleccion().localizarultimo();
		
		assertEquals(jugador.getNombre(), "Camilo");
		assertEquals(jugador.getSiguiente().getNombre(), "Faryd");
		assertEquals(jugador.getAnterior().getNombre(), "Messi");
		assertTrue(mundial.getPrimerseleccion().getNumjugadores()==3);
	}
	
	@Test
	void testBuscar1() {
		setUpEscenario4();
		Jugador jugador = mundial.getPrimerseleccion().buscarjugador("Faryd");
		Jugador jugador1 = mundial.getPrimerseleccion().getPrimerjugador();
		assertTrue (jugador==jugador1);
	}
	
	@Test
	void testBuscar2() {
		setUpEscenario4();
		Jugador jugador = mundial.getPrimerseleccion().buscarjugador("Camilo");
		Jugador jugador1 = mundial.getPrimerseleccion().getPrimerjugador().getSiguiente().getSiguiente();
		assertTrue (jugador==jugador1);
	}
	@Test
	void testBuscar3() {
		setUpEscenario4();
		Jugador jugador = mundial.getPrimerseleccion().buscarjugador("Juan Manuel");
		Jugador jugador1 = mundial.getPrimerseleccion().localizarultimo();
		assertTrue (jugador==jugador1);
	}
	
	@Test
	void testBuscar4() {
		setUpEscenario4();
		try {
		Jugador jugador = mundial.getPrimerseleccion().buscarjugador("Juan");
		fail("Deberia lanzar excepción");
		Jugador jugador1 = mundial.getPrimerseleccion().getPrimerjugador();
		assertTrue (jugador==jugador1);
		}catch (NoExisteException e) {
			
		}
	}
	
	@Test
	void testEliminar1() {
		setUpEscenario4();
		mundial.getPrimerseleccion().eliminarjugador("Faryd");
		Jugador jugador = mundial.getPrimerseleccion().getPrimerjugador();
		assertEquals(jugador.getNombre(), "Messi");
		assertEquals(jugador.getAnterior().getNombre(), "Juan Manuel");
		assertTrue(mundial.getPrimerseleccion().getNumjugadores()==3);
	}
	
	@Test
	void testEliminar2() {
		setUpEscenario4();
		mundial.getPrimerseleccion().eliminarjugador("Camilo");
		Jugador jugador = mundial.getPrimerseleccion().getPrimerjugador().getSiguiente().getSiguiente();
		assertEquals(jugador.getNombre(), "Juan Manuel");
		assertEquals(jugador.getAnterior().getNombre(), "Messi");
		assertTrue(mundial.getPrimerseleccion().getNumjugadores()==3);
	}
	
	@Test
	void testEliminar3() {
		setUpEscenario4();
		mundial.getPrimerseleccion().eliminarjugador("Juan Manuel");
		Jugador jugador = mundial.getPrimerseleccion().localizarultimo();
		assertEquals(jugador.getNombre(), "Camilo");
		assertEquals(jugador.getAnterior().getNombre(), "Messi");
		assertEquals(jugador.getSiguiente().getNombre(), "Faryd");
		assertTrue(mundial.getPrimerseleccion().getNumjugadores()==3);
	}
	
	@Test
	void testLocalizarAnt1() {
		setUpEscenario4();
		Jugador jugador = mundial.getPrimerseleccion().localizaranterior("Faryd");
		Jugador jugador1 = mundial.getPrimerseleccion().localizarultimo();
		assertTrue(jugador==jugador1);
	}
	
	@Test
	void testLocalizarAnt2() {
		setUpEscenario4();
		Jugador jugador = mundial.getPrimerseleccion().localizaranterior("Camilo");
		Jugador jugador1 = mundial.getPrimerseleccion().getPrimerjugador().getSiguiente();
		assertTrue(jugador==jugador1);
	}
	
	@Test
	void testLocalizarAnt3() {
		setUpEscenario4();
		Jugador jugador = mundial.getPrimerseleccion().localizaranterior("Juan Manuel");
		Jugador jugador1 = mundial.getPrimerseleccion().getPrimerjugador().getSiguiente().getSiguiente();
		assertTrue(jugador==jugador1);
	}
	
	@Test
	void testLocalizarAnt4() {
		setUpEscenario4();
		Jugador jugador = mundial.getPrimerseleccion().localizaranterior("Messi");
		Jugador jugador1 = mundial.getPrimerseleccion().getPrimerjugador();
		assertTrue(jugador==jugador1);
	}
	

}
