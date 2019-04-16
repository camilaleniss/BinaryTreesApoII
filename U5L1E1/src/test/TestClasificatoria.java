package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Clasificado;
import modelo.FaltanSeleccionesException;
import modelo.Grupo;
import modelo.Mundial;
import modelo.Seleccion;

class TestClasificatoria {
	
	private Mundial mundial;
	
	TestClasificatoria() {
		mundial = new Mundial();
	}
	
	void setUpEscenario1() {
		mundial.addSeleccion(new Seleccion("Colombia", "img/colombia", 0));
	}

	void setUpEscenario2(){
		mundial.setElements();
	}
	
	void setUpEscenario3() {
		Grupo g = new Grupo();
		g.getSelecciones().add(new Seleccion ("Rusia", "img", 0));
		g.getSelecciones().add(new Seleccion ("Arabia Saudi", "img", 0));
		g.getSelecciones().add(new Seleccion ("Egipto", "img", 0));
		g.getSelecciones().add(new Seleccion ("Uruguay", "img", 0));
		mundial.getGrupos()[0] = g;
		g = new Grupo();
		g.getSelecciones().add(new Seleccion ("Portugal", "img", 0));
		g.getSelecciones().add(new Seleccion ("España", "img", 0));
		g.getSelecciones().add(new Seleccion ("Marruecos", "img", 0));
		g.getSelecciones().add(new Seleccion ("Iran", "img", 0));
		mundial.getGrupos()[1] = g;
		g = new Grupo();
		g.getSelecciones().add(new Seleccion ("Francia", "img", 0));
		g.getSelecciones().add(new Seleccion ("Australia", "img", 0));
		g.getSelecciones().add(new Seleccion ("Perú", "img", 0));
		g.getSelecciones().add(new Seleccion ("Dinamarca", "img", 0));
		mundial.getGrupos()[2] = g;
		g = new Grupo();
		g.getSelecciones().add(new Seleccion ("Argentina", "img", 0));
		g.getSelecciones().add(new Seleccion ("Islandia", "img", 0));
		g.getSelecciones().add(new Seleccion ("Croacia", "img", 0));
		g.getSelecciones().add(new Seleccion ("Nigeria", "img", 0));
		mundial.getGrupos()[3] = g;
		g = new Grupo();
		g.getSelecciones().add(new Seleccion ("Brasil", "img", 0));
		g.getSelecciones().add(new Seleccion ("Suiza", "img", 0));
		g.getSelecciones().add(new Seleccion ("Costa Rica", "img", 0));
		g.getSelecciones().add(new Seleccion ("Serbia", "img", 0));
		mundial.getGrupos()[4] = g;
		g = new Grupo();
		g.getSelecciones().add(new Seleccion ("Alemania", "img", 0));
		g.getSelecciones().add(new Seleccion ("México", "img", 0));
		g.getSelecciones().add(new Seleccion ("Suecia", "img", 0));
		g.getSelecciones().add(new Seleccion ("Corea del Sur", "img", 0));
		mundial.getGrupos()[5] = g;
		g = new Grupo();
		g.getSelecciones().add(new Seleccion ("Bélgica", "img", 0));
		g.getSelecciones().add(new Seleccion ("Panamá", "img", 0));
		g.getSelecciones().add(new Seleccion ("Túnez", "img", 0));
		g.getSelecciones().add(new Seleccion ("Inglaterra", "img", 0));
		mundial.getGrupos()[6] = g;
		g = new Grupo();
		g.getSelecciones().add(new Seleccion ("Polonia", "img", 0));
		g.getSelecciones().add(new Seleccion ("Senegal", "img", 0));
		g.getSelecciones().add(new Seleccion ("Colombia", "img", 0));
		g.getSelecciones().add(new Seleccion ("Japón", "img", 0));
		mundial.getGrupos()[7] = g;
	}
	
	void setUpEscenario4() {
		setUpEscenario3();
		mundial.generarClasificados();
	}

	@Test
	void testGenerarGrupos1() {
		try {
			mundial.generarGrupos();
			fail("Debia fallar aqui");
		}catch (FaltanSeleccionesException e) {
		}
	}
	
	@Test
	void testGenerarGrupos2() {
		setUpEscenario1();
		try {
			mundial.generarGrupos();
			fail("Debia fallar aqui");
		}catch (FaltanSeleccionesException e) {
			
		}
	}
	
	@Test
	void testGenerarGrupos3() {
		setUpEscenario2();
		mundial.generarGrupos();
		for (int i=0; i<mundial.getGrupos().length; i++) {
			Grupo g = mundial.getGrupos()[i];
			//System.out.println("--------Grupo "+(i+1)+"------------");
			for (int c=0; c<g.getSelecciones().size(); c++) {
				//System.out.println(g.getSelecciones().get(c).getNombre());
				assertTrue(g.getSelecciones().get(c).getEscogida()==true);
			}
		}
	}
	
	@Test
	void testGenerarClasificados() {
		setUpEscenario3();
		mundial.generarClasificados();
		assertEquals("Rusia", mundial.getClasificados().get(0).getNombre());
		assertEquals("España", mundial.getClasificados().get(1).getNombre());
		assertEquals("Portugal", mundial.getClasificados().get(2).getNombre());
		assertEquals("Arabia Saudi", mundial.getClasificados().get(3).getNombre());
		assertEquals("Francia", mundial.getClasificados().get(4).getNombre());
		assertEquals("Islandia", mundial.getClasificados().get(5).getNombre());
		assertEquals("Argentina", mundial.getClasificados().get(6).getNombre());
		assertEquals("Australia", mundial.getClasificados().get(7).getNombre());
		assertEquals("Brasil", mundial.getClasificados().get(8).getNombre());
		assertEquals("México", mundial.getClasificados().get(9).getNombre());
		assertEquals("Alemania", mundial.getClasificados().get(10).getNombre());
		assertEquals("Suiza", mundial.getClasificados().get(11).getNombre());
		assertEquals("Bélgica", mundial.getClasificados().get(12).getNombre());
		assertEquals("Senegal", mundial.getClasificados().get(13).getNombre());
		assertEquals("Polonia", mundial.getClasificados().get(14).getNombre());
		assertEquals("Panamá", mundial.getClasificados().get(15).getNombre());
	}
	
	@Test
	void testGenerarCopa() {
		setUpEscenario4();
		mundial.generarCopa();
		assertTrue(mundial.getRaizClasificatoria().darAltura()==5);
		assertTrue(correspondeRaiz(mundial.getRaizClasificatoria()));
	}
	
	boolean correspondeRaiz(Clasificado c) {
		if (c.esHoja())
			return true;
		else 
			if  (c.getSeleccion().getNombre().equals(c.getDer().getSeleccion().getNombre()) || c.getSeleccion().getNombre().equals(c.getIzq().getSeleccion().getNombre())) {
				boolean d = correspondeRaiz(c.getDer());
				boolean i =correspondeRaiz(c.getIzq());
				return d || i;
			}
			return false;
	}
	
	
	
	
	
	
}
