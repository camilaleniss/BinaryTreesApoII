package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class DialogSeleccion extends JDialog implements ActionListener{
	
	public static final String GUARDAR = "Guardar";
	public static final String SALIR = "Salir";
	
	private PanelBandera panelimagen;
	private JPanel panelinfo;
	
	private JLabel labpais;
	private JLabel labpuntos;
	private JLabel labpromaltura;
	private JLabel labpromedad;
	private JLabel labpromfifa;
	private JLabel labnomimagen;
	
	private JTextField txtpais;
	private JTextField txtpuntos;
	private JTextField txtpromaltura;
	private JTextField txtpromedad;
	private JTextField txtpromfifa;
	private JTextField txtnomimagen;
	
	private JButton butguardar;
	private JButton butsalir;
	
	private Ventana ven;
	private String id;
	private boolean anadir; 
	
	public DialogSeleccion(Ventana ven, String id, boolean anadir) {
		super (ven, true);
		
		this.id=id;
		this.anadir=anadir;
		
		this.ven=ven;
		
		setSize(300,100);
		setLayout(new GridLayout(2,1));
		
		
		panelimagen = new PanelBandera(null);
		panelimagen.setSize(100,100);
		panelimagen.setBackground(Color.WHITE);
		
		TitledBorder border = BorderFactory.createTitledBorder("Nombre");
		border.setTitleColor(Color.DARK_GRAY);
		border.setTitleFont(new Font ("Bookman Old Style", Font.BOLD, 14));
		panelimagen.setBorder(border);
		panelinfo = new JPanel();
		panelinfo.setSize(200,100);
		panelinfo.setLayout(new GridLayout(7,2));
		panelinfo.setBackground(Color.WHITE);
		
		labpais = new JLabel ("Pais");
		labpuntos = new JLabel ("Puntos");
		labpromaltura = new JLabel ("Promedio altura");
		labpromedad = new JLabel ("Promedio edad");
		labpromfifa = new JLabel ("Promedio FIFA");
		labnomimagen = new JLabel ("Imagen ");
		
		txtpais = new JTextField();
		txtpuntos = new JTextField();
		txtpromaltura = new JTextField();
		txtpromedad = new JTextField();
		txtpromfifa = new JTextField();
		txtnomimagen = new JTextField();
		
		butguardar = new JButton ("Guardar");
		butguardar.setActionCommand(GUARDAR);
		butguardar.addActionListener(this);
		butguardar.setBackground(Color.LIGHT_GRAY);
		
		butsalir = new JButton ("Salir");
		butsalir.setActionCommand(SALIR);
		butsalir.addActionListener(this);
		butsalir.setBackground(Color.LIGHT_GRAY);
		
		Font font = new Font ("Bookman Old Style", Font.BOLD, 12);
		
		labpais.setFont(font);
		labpuntos.setFont(font);
		labpromaltura.setFont(font);
		labpromedad.setFont(font);
		labpromfifa.setFont(font);
		labnomimagen.setFont(font);
		
		txtpais.setFont(font);
		txtpuntos.setFont(font);
		txtpromaltura.setFont(font);
		txtpromedad.setFont(font);
		txtpromfifa.setFont(font);
		txtnomimagen.setFont(font);
		
		butguardar.setFont(font);
		butsalir.setFont(font);
		
		txtpromaltura.setEditable(false);
		txtpromedad.setEditable(false);
		txtpromfifa.setEditable(false);
		
		panelinfo.add(labpais);
		panelinfo.add(txtpais);
		panelinfo.add(labpuntos);
		panelinfo.add(txtpuntos);
		panelinfo.add(labpromaltura);
		panelinfo.add(txtpromaltura);
		panelinfo.add(labpromedad);
		panelinfo.add(txtpromedad);
		panelinfo.add(labpromfifa);
		panelinfo.add(txtpromfifa);
		panelinfo.add(labnomimagen);
		panelinfo.add(txtnomimagen);
		panelinfo.add(butguardar);
		panelinfo.add(butsalir);
		
		add(panelimagen);
		add(panelinfo);
		
		if (!anadir)
			setElements();
		
		pack();
	}
	
	public JTextField getTxtpais() {
		return txtpais;
	}

	public JTextField getTxtpuntos() {
		return txtpuntos;
	}

	public JTextField getTxtnomimagen() {
		return txtnomimagen;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setElements() {
		String nombre, nomimagen;
		int puntos;
		double promaltura, promedad, promfifa;
		
		nombre = id;
		nomimagen=ven.getMundial().buscarseleccion(nombre).getImagen();
		puntos = ven.getMundial().buscarseleccion(nombre).getPuntos();
		promaltura=ven.getMundial().buscarseleccion(nombre).getPromaltura();
		promedad=ven.getMundial().buscarseleccion(nombre).getPromedad();
		promfifa=ven.getMundial().buscarseleccion(nombre).getPromfifa();
		
		
		txtpais.setText(nombre);
		txtpuntos.setText(""+puntos);
		txtpromaltura.setText(""+promaltura);
		txtpromedad.setText(""+promedad);
		txtpromfifa.setText(""+promfifa);
		txtnomimagen.setText(""+nomimagen);
		
		TitledBorder border = BorderFactory.createTitledBorder(nombre);
		border.setTitleColor(Color.DARK_GRAY);
		border.setTitleFont(new Font ("Bookman Old Style", Font.BOLD, 14));
		panelimagen.setBorder(border);
		panelimagen.setNombre(txtnomimagen.getText());
		panelimagen.repaint();
		//labimagen.setIcon(new ImageIcon(nomimagen));
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		if (comando.equals(SALIR)) {
			dispose();
		}
		if (comando.equals(GUARDAR)) {
			if (!anadir) {
				modificarseleccion();
				dispose();
			}else {
				anadirseleccion();
				dispose();
			}
		}
		
	}
	
	public void anadirseleccion() {
		ven.anadirseleccion(txtpais.getText(), txtnomimagen.getText(), 
				Integer.parseInt(txtpuntos.getText()));
	}
	
	public void modificarseleccion() {
		String [] array = new String[4];
		array[0] = id;
		array[1]=txtpais.getText();
		array[2]=txtnomimagen.getText();
		array[3]=txtpuntos.getText();
		
		ven.modificarseleccion(array);
	}


}
