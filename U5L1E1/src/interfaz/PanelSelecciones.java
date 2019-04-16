package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelSelecciones extends JPanel {

	private Ventana ven;
	private PanelButSelecciones pbs;
	private PanelListSelecciones pls;
	
	private String id;
	
	public PanelSelecciones(Ventana ven) {
		this.ven=ven;
		setSize(200,1000);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		pbs = new PanelButSelecciones(this);
		pls = new PanelListSelecciones(this);
		
		TitledBorder border = BorderFactory.createTitledBorder("Selecciones");
		border.setTitleColor(Color.DARK_GRAY);
		border.setTitleFont(new Font ("Bookman Old Style", Font.BOLD, 12));
		setBorder(border);
		
		add(pls, BorderLayout.NORTH);
		add(pbs, BorderLayout.SOUTH);
		
	}
	
	public Ventana getVentana() {
		return ven;
	}
	
	public PanelListSelecciones getpls(){
		return pls;
	}
	
	public String getindex() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public void actualizar() {
		pls.actualizarlista();
//				 ven.getMundial().toArrayList());
	}
	
	

}
