package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelJugadores extends JPanel {

	private String id;
	
	private Ventana ven;
	
	private PanelListJugadores plj;
	private PanelButJugadores pbj;
	
	public PanelJugadores(Ventana ven) {
		this.ven= ven;
		setSize(200,1000);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		plj = new PanelListJugadores (this);
		pbj = new PanelButJugadores (this);
		
		TitledBorder border = BorderFactory.createTitledBorder("Jugadores");
		border.setTitleColor(Color.DARK_GRAY);
		border.setTitleFont(new Font ("Bookman Old Style", Font.BOLD, 12));
		setBorder(border);
		
		add(plj, BorderLayout.NORTH);
		add(pbj, BorderLayout.SOUTH);
		
		actualizar();
		
	}
	
	public Ventana getVentana() {
		return ven;
	}
	
	public String getindex() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public void actualizar() {
		plj.actualizarlista();
				//ven.getMundial().buscarseleccion(ven.getPanelselecciones().getindex()).toArrayList());
	}
	
	
	

}
