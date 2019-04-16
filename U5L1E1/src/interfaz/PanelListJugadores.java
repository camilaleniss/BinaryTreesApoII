package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelListJugadores extends JPanel implements ListSelectionListener{

	private JList listjugadores;
	private JScrollPane scroll;
	
	private PanelJugadores ven;
	
	public PanelListJugadores(PanelJugadores ven) {
		this.ven = ven;
		setBackground(Color.WHITE);
		setSize(200,400);
		listjugadores = new JList();
		scroll = new JScrollPane(listjugadores);
		
		listjugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(200,300));
		
		listjugadores.addListSelectionListener(this);
		
		add (scroll, BorderLayout.CENTER);
		
		actualizarlista();
	}
	
	public void actualizarlista() {
		String index = ven.getVentana().getPanelselecciones().getindex();
		if (index!=null) {
		listjugadores.setListData(ven.getVentana().getMundial().buscarseleccion(index).toArrayList().toArray());
		listjugadores.setSelectedIndex(0);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (listjugadores.getSelectedValue()!=null) {
			String id= listjugadores.getSelectedValue().toString();
			ven.setId(id);
			ven.getVentana().mostrarjugador();
		}
		
	}
	

}
