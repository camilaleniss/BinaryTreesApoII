package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelInfo extends JPanel implements ActionListener{
	
	public static final String ANTERIOR = "Anterior";
	public static final String PRIMERO = "Primero";
	public static final String SIGUIENTE = "Siguiente";
	
	private JPanel panelimagen;
	private JPanel panelinfo;
	private JPanel panelbotones;
	
	private JLabel labimagen;
	private JLabel labnombre;
	private JLabel labposicion;
	private JLabel labfifa;
	private JLabel labfechanac;
	private JLabel labaltura;
	private JLabel labnomimagen;

	private JTextField txtnombre;
	private JTextField txtposicion;
	private JTextField txtfifa;
	private JTextField txtfecha;
	private JTextField txtaltura;
	private JTextField txtnomimagen;
	
	private JButton butanterior;
	private JButton butprimero;
	private JButton butsiguiente;
	
	private Ventana ven;

	public PanelInfo(Ventana ven) {
		this.ven = ven;
		setSize(200,1000);
		setLayout ( new BorderLayout());
		
		panelimagen = new JPanel();
		panelimagen.setSize(300,100);
		panelimagen.setBackground(Color.WHITE);
		
		TitledBorder border = BorderFactory.createTitledBorder("Nombre");
		border.setTitleColor(Color.DARK_GRAY);
		border.setTitleFont(new Font ("Bookman Old Style", Font.BOLD, 14));
		panelimagen.setBorder(border);
		
		labimagen = new JLabel();
		labimagen.setOpaque(false);
		panelimagen.add(labimagen);
		
		panelinfo = new JPanel();
		panelinfo.setSize(200,100);
		panelinfo.setLayout(new GridLayout(6,2));
		panelinfo.setBackground(Color.WHITE);
		
		labnombre = new JLabel ("Nombre");
		labposicion = new JLabel ("Posición");
		labfifa = new JLabel ("Puntaje FIFA");
		labfechanac = new JLabel ("Fecha Nacimiento");
		labaltura = new JLabel ("Altura");
		labnomimagen = new JLabel ("Imagen");
		
		txtnombre = new JTextField();
		txtposicion = new JTextField();
		txtfifa = new JTextField();
		txtfecha = new JTextField();
		txtfecha.setText("dd-mm-aa");
		txtaltura = new JTextField();
		txtaltura.setText("(m)");
		txtnomimagen = new JTextField();
		txtnomimagen.setText("solo nombre imagen");
		
		Font font = new Font ("Bookman Old Style", Font.BOLD, 12);
		
		labnombre.setFont(font);
		labposicion.setFont(font);
		labfifa.setFont(font);
		labfechanac.setFont(font);
		labaltura.setFont(font);
		labnomimagen.setFont(font);
		
		txtnombre.setFont(font);
		txtposicion.setFont(font);
		txtfifa.setFont(font);
		txtfecha.setFont(font);
		txtaltura.setFont(font);
		txtnomimagen.setFont(font);
		
		panelinfo.add(labnombre);
		panelinfo.add(txtnombre);
		panelinfo.add(labposicion);
		panelinfo.add(txtposicion);
		panelinfo.add(labfifa);
		panelinfo.add(txtfifa);
		panelinfo.add(labfechanac);
		panelinfo.add(txtfecha);
		panelinfo.add(labaltura);
		panelinfo.add(txtaltura);
		panelinfo.add(labnomimagen);
		panelinfo.add(txtnomimagen);
		
		panelbotones = new JPanel();
		//panelbotones.setSize(200,100);
		panelbotones.setLayout(new GridLayout(1,3));
		panelbotones.setBackground(Color.WHITE);
		
		butanterior = new JButton ("Anterior");
		butanterior.setActionCommand(ANTERIOR);
		butanterior.addActionListener(this);
		butanterior.setBackground(Color.LIGHT_GRAY);
		
		butprimero = new JButton ("Primero");
		butprimero.setActionCommand(PRIMERO);
		butprimero.addActionListener(this);
		butprimero.setBackground(Color.LIGHT_GRAY);
		
		butsiguiente = new JButton ("Siguiente");
		butsiguiente.setActionCommand(SIGUIENTE);
		butsiguiente.addActionListener(this);
		butsiguiente.setBackground(Color.LIGHT_GRAY);
		
		Font font2 = new Font ("Bookman Old Style", Font.BOLD, 14);
		
		butprimero.setFont(font2);
		butanterior.setFont(font2);
		butsiguiente.setFont(font2);
		
		panelbotones.add(butanterior);
		panelbotones.add(butprimero);
		panelbotones.add(butsiguiente);
		
		add(panelimagen, BorderLayout.NORTH);
		add(panelinfo, BorderLayout.CENTER);
		add(panelbotones, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String e = arg0.getActionCommand();
		
		if (e.equals(ANTERIOR)) {
			ven.daranterior(txtnombre.getText());
		}
		if (e.equals(PRIMERO)) {
			ven.darprimero();
		}
		if(e.equals(SIGUIENTE)) {
			ven.darsiguiente(txtnombre.getText());
		}
	}
	
	public void setElements () {
		try {
		String[] elementos = ven.darElementsJugador();
		txtnombre.setText(elementos[0]);
		txtposicion.setText(elementos[1]);
		txtfifa.setText(elementos[2]);
		txtfecha.setText(elementos[3]);
		txtaltura.setText(elementos[4]);
		txtnomimagen.setText(elementos[5]);
		labimagen.setIcon(new ImageIcon(elementos[5]));
		labimagen.setOpaque(false);
		labimagen.setVisible(true);
		
		TitledBorder border = BorderFactory.createTitledBorder(txtnombre.getText());
		border.setTitleColor(Color.DARK_GRAY);
		border.setTitleFont(new Font ("Bookman Old Style", Font.BOLD, 14));
		panelimagen.setBorder(border);
		
		}catch (NullPointerException e) {
			System.out.println("Primera vez que se crea");
		}
	}
	
	public void limpiardatos() {
		txtnombre.setText("");
		txtposicion.setText("");
		txtfifa.setText("");
		txtfecha.setText("");
		txtaltura.setText("");
		txtnomimagen.setText("");
		labimagen.setVisible(false);
	}
	
	public String [] darinfo() {
		String [] info = new String [6];
		try {
		info[0] = txtnombre.getText();
		info[1] = txtposicion.getText();	
		info[2] = txtfifa.getText();
		info[3] = txtfecha.getText();
		String [] fecha = info[3].split("-");
		if ( fecha.length!=3){
			throw new Exception ("fecha no valida");
		}
		info[4] = txtaltura.getText();
		info[5] = "img/"+txtnomimagen.getText()+".jpg";
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Formato no  valido", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		return info;
	}

	
}
