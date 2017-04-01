package interfazClient;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelConfig extends JPanel{
	
	@SuppressWarnings("unused")
	private InterfazCliente principal;
	
	private JLabel lbIP;
	
	private JLabel lbPuerto;
	
	private JLabel lbNumObj;
	
	private JTextField txtIP;
	
	private JTextField txtPuerto;
	
	private JTextField txtNumObj;
	
	public PanelConfig(InterfazCliente interfaz){
		principal = interfaz;
			
		setBorder(new TitledBorder("Archivos"));
		setLayout(new GridLayout(3,2));
		
		lbIP = new JLabel("Dirección IP: ");
		
		lbPuerto = new JLabel("Puerto: ");
		
		lbNumObj = new JLabel("Número de objetos a enviar: ");
		
		txtIP = new JTextField();
		
		txtPuerto = new JTextField();
		
		txtNumObj = new JTextField();
		
		add(lbIP);
		add(lbPuerto);
		add(lbNumObj);
		add(txtIP);
		add(txtPuerto);
		add(txtNumObj);
		
	}
	
	
	

}
