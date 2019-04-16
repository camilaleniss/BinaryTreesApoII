package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelBandera extends JPanel {

	private String nombre;
	
	public PanelBandera(String nombre) {
		setSize(300,200);
		this.nombre=nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 400, 300);
		try {
		ImageIcon ii = new ImageIcon(nombre);
		if (ii.getImage().getWidth(null) != -1) {
			Image image = ii.getImage();
			Image nueva = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
			ii= new ImageIcon(nueva);		
		}
		g.drawImage(ii.getImage(), 20, 0,  null);
		}catch(Exception e) {
			
		}
	}

	
}
