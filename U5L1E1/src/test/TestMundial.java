package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Mundial;
import modelo.NoExisteException;
import modelo.Seleccion;

class TestMundial {

	private Mundial mundial;
	
	TestMundial(){
		mundial = new Mundial();
	}
	
	void setUpEscenario1(){
		Seleccion selec = new Seleccion ("Colombia", "img/seleccioncolombia.jpg", 0);
		selec.addjugador("Camilo", "Defensa", 8, "27-07-1990", 1.90, "img/camilo");
		selec.addjugador("Juan Manuel", "Defensa", 7, "31-01-1993", 1.92, "img/juanma");
		mundial.addSeleccion(selec);
	}
	
	void setUpEscenario2() {
		setUpEscenario1();
		Seleccion selec = new Seleccion ("Alemania", "img/seleccionalemania.jpg", 0);
		selec.addjugador("Camilo", "Defensa", 20, "27-07-1990", 1.90, "img/camilo");
		selec.addjugador("Juan Manuel", "Defensa", 40, "31-01-1993", 1.92, "img/juanma");
		mundial.addSeleccion(selec);
	}
	
	void setUpEscenario3() {
		setUpEscenario2();
		Seleccion selec = new Seleccion ("Brasil", "img/seleccionbrasil.jpg", 0);
		selec.addjugador("Camilo", "Defensa", 3, "27-07-1990", 1.90, "img/camilo");
		selec.addjugador("Juan Manuel", "Defensa", 1, "31-01-1993", 1.92, "img/juanma");
		mundial.addSeleccion(selec);
	}
	
	void setUpEscenario4() {
		setUpEscenario3();
		Seleccion selec = new Seleccion ("Korea", "img/seleccionkorea.jpg", 0);
		selec.addjugador("Camilo", "Defensa", 10, "27-07-1990", 1.90, "img/camilo");
		selec.addjugador("Juan Manuel", "Defensa", 20, "31-01-1993", 1.92, "img/juanma");
		mundial.addSeleccion(selec);
	}
	
	@Test
	void testAddSeleccion1() {
		setUpEscenario1();
		assertEquals(mundial.getPrimerseleccion().getNombre(), "Colombia");
		assertTrue(mundial.getPrimerseleccion().darPeso()==1);
	}
	
	
	@Test
	void testAddSeleccion2() {
		setUpEscenario2();
		Seleccion selec = mundial.getPrimerseleccion().getDer();
		assertTrue(selec.getNombre().equals("Alemania"));
		assertTrue(selec.getImagen().equals("img/seleccionalemania.jpg"));
		assertTrue(selec.getPuntos()==0);
	}
	
	
	@Test
	void testAddSeleccion3() {
		setUpEscenario3();			
		Seleccion selec = mundial.getPrimerseleccion().getIzq();
		assertTrue(selec.getNombre().equals("Brasil"));
		assertTrue(selec.getImagen().equals("img/seleccionbrasil.jpg"));
		assertTrue(selec.getPuntos()==0);
	}
	
	@Test
	void testAddSeleccion4() {
		setUpEscenario4();			
		Seleccion selec = mundial.getPrimerseleccion().getDer().getIzq();
		assertTrue(selec.getNombre().equals("Korea"));
		assertTrue(selec.getImagen().equals("img/seleccionkorea.jpg"));
		assertTrue(selec.getPuntos()==0);
	}
	
	@Test
	void testModificar1() {
		setUpEscenario4();
		String [] array = {"Colombia", "Colombia", "img/seleccioncolombia.jpg", "10"};
		mundial.modificarseleccion(array);
		Seleccion selec = mundial.getPrimerseleccion();
		assertTrue(selec.getNombre().equals("Colombia"));
		assertTrue(selec.getImagen().equals("img/seleccioncolombia.jpg"));
		assertTrue(selec.getPuntos()==10);
	}
	
	
	@Test
	void testModificar2() {
		setUpEscenario4();
		try {
			String [] array = {"Croatia", "Cr", "img/seleccioncroat.jpg", "10"};
			mundial.modificarseleccion(array);
			fail("Deberia fallar aqui");
		}catch (NoExisteException e) {
			
		}
	}
	
	@Test
	void testModificar3() {
		setUpEscenario4();
		String [] array = {"Alemania", "Allemania", "img/seleccionalemania.jpg", "0"};
		mundial.modificarseleccion(array);
		Seleccion selec = mundial.getPrimerseleccion().getDer();
		assertEquals(selec.getNombre(), "Allemania");
	}
	
	@Test
	void testModificar4() {
		setUpEscenario4();
		String [] array = {"Brasil", "Brasil", "img/seleccionbra.jpg", "0"};
		mundial.modificarseleccion(array);
		Seleccion selec = mundial.getPrimerseleccion().getIzq();
		assertEquals(selec.getImagen(), "img/seleccionbra.jpg");
	}
	
	@Test
	void testModificar5() {
		setUpEscenario4();
		String [] array = {"Korea", "Corea", "img/seleccionkorea.jpg", "0"};
		mundial.modificarseleccion(array);
		Seleccion selec = mundial.getPrimerseleccion().getDer().getIzq();
		assertEquals(selec.getNombre(), "Corea");
	}
	
	
	
	@Test
	void testBuscar1() {
		setUpEscenario4();
		Seleccion selec = mundial.buscarseleccion("Colombia");
		assertTrue(mundial.getPrimerseleccion()==selec);
	}
	
	@Test
	void testBuscar2() {
		setUpEscenario4();
		Seleccion selec = mundial.buscarseleccion("Alemania");
		assertEquals(selec, mundial.getPrimerseleccion().getDer());
	}
	
	@Test
	void testBuscar3() {
		setUpEscenario4();
		Seleccion selec = mundial.buscarseleccion("Brasil");
		assertEquals(selec, mundial.getPrimerseleccion().getIzq());
	}
	
	@Test
	void testBuscar4() {
		setUpEscenario4();
		Seleccion selec = mundial.buscarseleccion("Korea");
		assertEquals(selec, mundial.getPrimerseleccion().getDer().getIzq());
	}
	
	@Test
	void testBuscar5() {
		setUpEscenario4();
		Seleccion selec = mundial.buscarseleccion("Japon");
		assertTrue (selec==null);
	}
	
	
	@Test
	void testEliminar1() {
		setUpEscenario4();
		mundial.eliminarseleccion("Colombia");
		
		assertEquals(mundial.getPrimerseleccion().getNombre(), "Korea" );
		assertTrue(mundial.getPrimerseleccion().darPeso()==3);
	}
	
	@Test
	void testEliminar2() {
		setUpEscenario4();
		mundial.eliminarseleccion("Brasil");
		assertTrue(mundial.getPrimerseleccion().getIzq()==null);
	}
	
	@Test
	void testEliminar3() {
		setUpEscenario4();
		mundial.eliminarseleccion("Alemania");
		assertEquals(mundial.getPrimerseleccion().getDer().getNombre(), "Korea");
	}
	
	@Test
	void testEliminar4() {
		setUpEscenario4();
		try {
		mundial.eliminarseleccion("Rusia");
		fail("No existe esa seleccion");
		}catch (NoExisteException e) {
			assertTrue(mundial.getPrimerseleccion().darPeso()==4);
		}
	}
	
	
	
	
	

}
