package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Mundial;
import modelo.NoExisteException;

public class Ventana extends JFrame {

	private JDialog ventanadialogo;
	private JDialog dialogdibujo;
	private PanelJugadores paneljugadores;
	private PanelSelecciones panelselecciones;
	private PanelInfo paneldatos;
	
	private Mundial mundial;
	
	public Ventana(){
		
		setTitle("Copa Mundial de la FIFA");
		setSize (600,600);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.getContentPane().setBackground(Color.WHITE);
		
		mundial = new Mundial ();
		mundial.setElements();
		
		paneldatos = new PanelInfo(this);
		panelselecciones = new PanelSelecciones (this);
		paneljugadores = new PanelJugadores (this);
		
		add(panelselecciones, BorderLayout.WEST);
		add(paneljugadores, BorderLayout.CENTER);
		add(paneldatos, BorderLayout.EAST);
		
		pack();
		
	}

	public static void main(String[] args) {
		Ventana ven = new Ventana();
		ven.setVisible(true);
	}
	
	public Mundial getMundial() {
		return mundial;
	}
	
	public PanelSelecciones getPanelselecciones() {
		return panelselecciones;
	}
	
	public void abrirdialogo(boolean opa) {
		String id = panelselecciones.getindex();
		ventanadialogo = new DialogSeleccion(this, id, opa);
		ventanadialogo.setVisible(true);
	}
	
	public void anadirseleccion (String nombre, String imagen, int puntos) {
		mundial.crearSeleccion(nombre, imagen, puntos);
		JOptionPane.showMessageDialog(null, "Se ha añadido una nueva selección");
		panelselecciones.actualizar();
	}
	
	public void modificarseleccion (String [] array) {
		try {
		mundial.modificarseleccion(array);
		JOptionPane.showMessageDialog(null, "Actualización existosa");
		}catch (NoExisteException e) {
			JOptionPane.showMessageDialog(null, "No existe esa selección","Warning", JOptionPane.WARNING_MESSAGE);
		}
		panelselecciones.actualizar();
	}
	
	public void eliminarseleccion(String nombre) {
		mundial.eliminarseleccion(nombre);
		panelselecciones.actualizar();
	}
	
	public void buscarseleccion(String nombre) {
		String id = mundial.buscarseleccion(nombre).getNombre();
		ventanadialogo = new DialogSeleccion(this, id, false);
		ventanadialogo.setVisible(true);
	}
	
	public String [] darElementsJugador () {
		return mundial.darinfojugador(panelselecciones.getindex(), paneljugadores.getindex());
	}
	
	public void limpiardatos() {
		paneldatos.limpiardatos();
	}
	
	public void guardarjugador() {
		String nombre, fechanac, nomimagen, posicion;
		int puntajefifa;
		double altura;
		
		String [] info = paneldatos.darinfo();
		
		nombre = info[0];
		posicion = info[1];
		puntajefifa = Integer.parseInt(info[2]);
		fechanac = info[3];
		altura = Double.parseDouble(info[4]);
		nomimagen = info[5];
		
		mundial.buscarseleccion(panelselecciones.getindex()).addjugador(nombre, posicion, puntajefifa, fechanac, altura, nomimagen);
		
		JOptionPane.showMessageDialog(null, "Se ha añadido un jugador nuevo");
		paneljugadores.actualizar();
	}
	
	public void mostrarjugador() {
		paneldatos.setElements();
	}
	
	public void buscarjugador (String nombre) {
		String nombre1 = mundial.buscarseleccion(panelselecciones.getindex()).buscarjugador(nombre).getNombre();
		paneljugadores.setId(nombre1);
		paneldatos.setElements();
		paneljugadores.actualizar();
	}
	
	public void daranterior (String nombre) {
		String nombre1=mundial.buscarseleccion(panelselecciones.getindex()).localizaranterior(nombre).getNombre();
		paneljugadores.setId(nombre1);
		paneldatos.setElements();
	}
	
	public void darprimero () {
		String nombre1=mundial.buscarseleccion(panelselecciones.getindex()).getPrimerjugador().getNombre();
		paneljugadores.setId(nombre1);
		paneldatos.setElements();
	}
	
	public void darsiguiente(String nombre) {
		String nombre1=mundial.buscarseleccion(panelselecciones.getindex()).buscarjugador(nombre).getSiguiente().getNombre();
		paneljugadores.setId(nombre1);
		paneldatos.setElements();
	}
	
	public void mostrarlistajugador() {
		try {
			paneljugadores.actualizar();
		}catch (NullPointerException e) {
			System.out.println("Primera vez que se crea");;
		}
	}
	
	public void mostrarDialog() {
		int respuesta = JOptionPane.showOptionDialog(this, "¿Cuál arbol desea visualizar?", "Mostrar",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Selecciones",
						"Clasificatoria" },
				null);
		if (respuesta == JOptionPane.YES_OPTION) {
			dialogdibujo = new PanelDibujo(this, 1);
		} else if (respuesta == JOptionPane.NO_OPTION) {
			mundial.generarMundial();
			dialogdibujo = new PanelDibujo(this, 2);
		}
		dialogdibujo.setVisible(true);
	}
}
