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

public class PanelButJugadores extends JPanel implements ActionListener{

	public static final String ANADIR ="Añadir";
	public static final String GUARDAR ="Guardar";
	public static final String ELIMINAR ="Eliminar";
	public static final String BUSCAR ="Buscar";
	
	private PanelJugadores ven;
	private JButton butanadir;
	private JButton butguardar;
	private JButton buteliminar;
	private JButton butbuscar;
	
	public PanelButJugadores(PanelJugadores ven) {
		this.ven=ven;
		
		setLayout(new GridLayout (1,4));
		setBackground(Color.WHITE);
		
		butanadir = new JButton ("Añadir");
		butanadir.setActionCommand(ANADIR);
		butanadir.addActionListener(this);
		butanadir.setBackground(Color.LIGHT_GRAY);
		
		butguardar = new JButton ("Guardar");
		butguardar.setActionCommand(GUARDAR);
		butguardar.addActionListener(this);
		butguardar.setBackground(Color.LIGHT_GRAY);
		
		buteliminar = new JButton ("Eliminar");
		buteliminar.setActionCommand(ELIMINAR);
		buteliminar.addActionListener(this);
		buteliminar.setBackground(Color.LIGHT_GRAY);
		
		butbuscar = new JButton ("Buscar");
		butbuscar.setActionCommand(BUSCAR);
		butbuscar.addActionListener(this);
		butbuscar.setBackground(Color.LIGHT_GRAY);
		
		Font font = new Font ("Bookman Old Style", Font.BOLD, 12);
		
		butanadir.setFont(font);
		butguardar.setFont(font);
		buteliminar.setFont(font);
		butbuscar.setFont(font);
		
		add(butanadir);
		add(butguardar);
		add(buteliminar);
		add(butbuscar);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String comando = event.getActionCommand();
		
		if (comando.equals(ANADIR)) {
			ven.getVentana().limpiardatos();
		}
		if (comando.equals(GUARDAR)) {
			ven.getVentana().guardarjugador();
		}
		if (comando.equals(ELIMINAR)) {
			eliminarjugador();
		}
		if (comando.equals(BUSCAR)) {
			buscarjugador();
		}
	}
	
	public void eliminarjugador() {
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador a eliminar");
		try {
			ven.getVentana().getMundial().buscarseleccion(ven.getVentana().getPanelselecciones().getindex()).eliminarjugador(nombre);
			ven.actualizar();
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No existe ese jugador en esta selección","Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void buscarjugador() {
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador a buscar");
		try {
			ven.getVentana().buscarjugador(nombre);
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No existe ese jugador en esta selección","Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
