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
			
		setBorder(new TitledBorder("Ingrese las especificaciones de envío"));
		setLayout(new GridLayout(3,2));
		
		lbIP = new JLabel("Dirección IP: ");
		lbPuerto = new JLabel("Puerto: ");
		lbNumObj = new JLabel("Número de objetos a enviar: ");
		txtIP = new JTextField();
		txtIP.setText("192.168.56.1");
		txtPuerto = new JTextField();
		txtPuerto.setText("57");
		txtNumObj = new JTextField();
		txtNumObj.setText("100");
		
		add(lbIP);
		add(txtIP);
		add(lbPuerto);
		add(txtPuerto);
		add(lbNumObj);
		add(txtNumObj);
		
	}
	
	public String darIP(){
		String ip = txtIP.getText();
		return ip;
	}
	
	public String darPuerto(){
		String port = txtPuerto.getText();
		return port;
	}
	
	public String darNumObjetos(){
		String numObj = txtNumObj.getText();
		return numObj;
	}

}
