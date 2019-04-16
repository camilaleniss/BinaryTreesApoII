package interfaz;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.NoExisteException;

public class PanelButSelecciones extends JPanel implements ActionListener{

	public static final String ANADIR ="Añadir";
	public static final String MODIFICAR ="Modificar";
	public static final String ELIMINAR ="Eliminar";
	public static final String BUSCAR ="Buscar";
	public static final String MOSTRAR="Mostrar";
	
	private PanelSelecciones ven;
	private JButton butanadir;
	private JButton butmodificar;
	private JButton buteliminar;
	private JButton butbuscar;
	private JButton butmostrar;
	
	public PanelButSelecciones(PanelSelecciones ven) {
		this.ven=ven;
		
		setLayout(new GridLayout (2,3));
		setBackground(Color.WHITE);
		
		butanadir = new JButton ("Añadir");
		butanadir.setActionCommand(ANADIR);
		butanadir.addActionListener(this);
		butanadir.setBackground(Color.LIGHT_GRAY);
		
		butmodificar = new JButton ("Modificar");
		butmodificar.setActionCommand(MODIFICAR);
		butmodificar.addActionListener(this);
		butmodificar.setBackground(Color.LIGHT_GRAY);
		
		buteliminar = new JButton ("Eliminar");
		buteliminar.setActionCommand(ELIMINAR);
		buteliminar.addActionListener(this);
		buteliminar.setBackground(Color.LIGHT_GRAY);
		
		butbuscar = new JButton ("Buscar");
		butbuscar.setActionCommand(BUSCAR);
		butbuscar.addActionListener(this);
		butbuscar.setBackground(Color.LIGHT_GRAY);
		
		butmostrar = new JButton ("Mostrar");
		butmostrar.setActionCommand(MOSTRAR);
		butmostrar.addActionListener(this);
		butmostrar.setBackground(Color.LIGHT_GRAY);
		
		Font font = new Font ("Bookman Old Style", Font.BOLD, 12);
		
		butanadir.setFont(font);
		butmodificar.setFont(font);
		buteliminar.setFont(font);
		butbuscar.setFont(font);
		butmostrar.setFont(font);
		
		add(butanadir);
		add(butmodificar);
		add(buteliminar);
		add(butbuscar);
		add(butmostrar);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String comando = event.getActionCommand();
		
		if (comando.equals(ANADIR)) {
			ven.getVentana().abrirdialogo(true);
		}
		if (comando.equals(MODIFICAR)) {
			if (ven.getpls().getListaselecciones().getSelectedValue()!=null) {
				ven.getVentana().abrirdialogo(false);
			}else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una selección","Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (comando.equals(ELIMINAR)) 
			eliminarseleccion();
		if (comando.equals(BUSCAR)) 
			buscarseleccion();
		if (comando.equals(MOSTRAR))
			mostrardialog();
	}
	
	public void eliminarseleccion() {
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la selección a eliminar");
		try {
			ven.getVentana().eliminarseleccion(nombre);
		}catch (NoExisteException e) {
			JOptionPane.showMessageDialog(null, "No existe esa selección","Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void buscarseleccion() {
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la selección a buscar");
		try {
			ven.getVentana().buscarseleccion(nombre);
		}catch (NoExisteException e) {
			JOptionPane.showMessageDialog(null, "No existe esa selección","Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void mostrardialog() {
		ven.getVentana().mostrarDialog();
	}


}
