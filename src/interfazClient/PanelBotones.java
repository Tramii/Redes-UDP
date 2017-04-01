package interfazClient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelBotones extends JPanel implements ActionListener{
	
	private final static String START = "Iniciar envío";
	private final static String OTHER = "Otro botón";

	private InterfazCliente principal;
		
	private JButton btSTART;
	
	private JButton btOTHER;
	
	public PanelBotones(InterfazCliente interfaz){
		
		principal = interfaz;
		
		setLayout(new GridLayout(2,1));		
		setBorder(new TitledBorder("Botoncitos"));
				
		btSTART = new JButton();
		btSTART.setText(START);
		btSTART.setActionCommand(START);
		btSTART.addActionListener(this);
		
		btOTHER = new JButton();
		btOTHER.setText(OTHER);
		btOTHER.setActionCommand(OTHER);
		btOTHER.addActionListener(this);
		
		add(btSTART);
		add(btOTHER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
		
		if(comando.equals(START)){
			principal.startConn();
		}
		
		
//		else if (comando.equals(OTHER)){
//			
//		}
		
	}

}
