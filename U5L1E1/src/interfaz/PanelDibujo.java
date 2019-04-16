package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import modelo.Clasificado;
import modelo.Seleccion;

public class PanelDibujo extends JDialog {
	
	public static final int ANCHOICONO=30;
	public static final int ALTOICONO=18;
	public static final int MEDIDAY=400;
	public static final int MEDIDAX=1000;
	public static final String COPA = "img"+File.separator+"trofeo.png";
	private Ventana ven;
	private int criterio;
	
	public PanelDibujo(Ventana ven, int criterio) {
		super (ven, true);
		this.ven = ven;
		setSize(MEDIDAX,MEDIDAY);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		this.criterio=criterio;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, MEDIDAX, MEDIDAY);
		g.setColor(Color.BLACK);
		
		if(criterio==1 && ven.getMundial().getPrimerseleccion()!=null)
			pintarSeleccion(g, ven.getMundial().getPrimerseleccion(), MEDIDAX/2, 50, ven.getMundial().getPrimerseleccion().darAltura()-2);
		if(criterio==2 && ven.getMundial().getRaizClasificatoria()!=null) {
			ImageIcon imgi= new ImageIcon(COPA);
			g.drawImage(imgi.getImage(), MEDIDAX/2-5, 30, null );
			g.setFont(new Font("Times new roman", Font.PLAIN, 14));
			g.drawString(ven.getMundial().getRaizClasificatoria().getSeleccion().getNombre(), MEDIDAX/2+30, 70);
			pintarMundial(g, ven.getMundial().getRaizClasificatoria(), MEDIDAX/2, 60, ven.getMundial().getRaizClasificatoria().darAltura()-1);
		}
			
	
	}
	
	public void pintarSeleccion(Graphics g, Seleccion selec, int x, int y, int nivel) {
		ImageIcon ii = new ImageIcon(selec.getImagen());
		if (ii.getImage().getWidth(null) != -1) {
			Image image = ii.getImage();
			Image nueva = image.getScaledInstance(ANCHOICONO, ALTOICONO, java.awt.Image.SCALE_SMOOTH);
			ii= new ImageIcon(nueva);		
		}
		g.drawImage(ii.getImage(), x-ii.getIconHeight()/2, y,  null);
		if (selec.getIzq()!=null) {
			g.drawLine(x, y+ALTOICONO, x, y+2*ALTOICONO);
			g.drawLine(x, y+2*ALTOICONO, (int) (x+ANCHOICONO/2*Math.pow(2, nivel)), y+2*ALTOICONO);
			g.drawLine((int)(x+ANCHOICONO/2*Math.pow(2, nivel)), y+2*ALTOICONO, (int)(x+ANCHOICONO/2*Math.pow(2,nivel)), y+ALTOICONO*3);
			pintarSeleccion(g, selec.getIzq(), (int) (x+ANCHOICONO/2*Math.pow(2, nivel)), y+ALTOICONO*3, nivel-1);
		}
		if (selec.getDer()!=null) {
			g.drawLine(x, y+ALTOICONO, x, y+2*ALTOICONO);
			g.drawLine(x, y+2*ALTOICONO, (int) (x-ANCHOICONO/2*Math.pow(2, nivel)), y+2*ALTOICONO);
			g.drawLine((int) (x-ANCHOICONO/2*Math.pow(2, nivel)), y+2*ALTOICONO, (int) (x-ANCHOICONO/2*Math.pow(2, nivel)), 
					y+ALTOICONO*3);
			pintarSeleccion(g, selec.getDer(), (int)(x-ANCHOICONO/2*Math.pow(2,nivel)), y+ALTOICONO*3, nivel-1 );
		}
	}
	
	public void pintarMundial(Graphics g, Clasificado clasic, int x, int y, int nivel) {
		ImageIcon ii = new ImageIcon(clasic.getSeleccion().getImagen());
		if (ii.getImage().getWidth(null) != -1) {
			Image image = ii.getImage();
			Image nueva = image.getScaledInstance(ANCHOICONO, ALTOICONO, java.awt.Image.SCALE_SMOOTH);
			ii= new ImageIcon(nueva);		
		}
		g.drawImage(ii.getImage(), x-ii.getIconHeight()/2, y,  null);
		if (clasic.getIzq()!=null) {
			g.drawLine(x, y+ALTOICONO, x, y+2*ALTOICONO);
			g.drawLine(x, y+2*ALTOICONO, (int) (x+ANCHOICONO/2*Math.pow(2, nivel)), y+2*ALTOICONO);
			g.drawLine((int)(x+ANCHOICONO/2*Math.pow(2, nivel)), y+2*ALTOICONO, (int)(x+ANCHOICONO/2*Math.pow(2,nivel)), y+ALTOICONO*3);
			pintarMundial(g, clasic.getIzq(), (int) (x+ANCHOICONO/2*Math.pow(2, nivel)), y+ALTOICONO*3, nivel-1);
		}
		if (clasic.getDer()!=null) {
			g.drawLine(x, y+ALTOICONO, x, y+2*ALTOICONO);
			g.drawLine(x, y+2*ALTOICONO, (int) (x-ANCHOICONO/2*Math.pow(2, nivel)), y+2*ALTOICONO);
			g.drawLine((int) (x-ANCHOICONO/2*Math.pow(2, nivel)), y+2*ALTOICONO, (int) (x-ANCHOICONO/2*Math.pow(2, nivel)), 
					y+ALTOICONO*3);
			pintarMundial(g, clasic.getDer(), (int)(x-ANCHOICONO/2*Math.pow(2,nivel)), y+ALTOICONO*3, nivel-1 );
		}
	}
	
	
}
