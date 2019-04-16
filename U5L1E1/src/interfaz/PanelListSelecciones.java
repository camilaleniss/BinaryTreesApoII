package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelListSelecciones extends JPanel implements ListSelectionListener{

	private JList listaselecciones;
	private JScrollPane scroll;
	
	private PanelSelecciones ven;
	
	public PanelListSelecciones(PanelSelecciones ven) {
		setSize(200,400);
		this.ven=ven;
		setBackground(Color.WHITE);
		listaselecciones = new JList();
		scroll = new JScrollPane (listaselecciones);
		
		listaselecciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(200,300));
		
		listaselecciones.addListSelectionListener(this);
		
		add (scroll, BorderLayout.CENTER);
		
		if (ven.getVentana().getMundial().getPrimerseleccion()!=null)
		ven.setId(ven.getVentana().getMundial().getPrimerseleccion().getNombre());
		actualizarlista();
		
	}
	
	public void actualizarlista() {
		listaselecciones.setListData(ven.getVentana().getMundial().toArrayList().toArray());
		listaselecciones.setSelectedIndex(0);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (listaselecciones.getSelectedValue()!=null) {
			String nombre = listaselecciones.getSelectedValue().toString();
			ven.setId(nombre);
			ven.getVentana().mostrarlistajugador();
		}
	}
	
	public JList getListaselecciones () {
		return listaselecciones;
	}
	
	
}
