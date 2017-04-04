package interfazClient;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mundoClient.Cliente;

public class InterfazCliente extends JFrame{

	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
     * Cliente
     */
    private Cliente cliente;
	
	/**
     * Panel para desplegar estado de la conexion
     */
	//private PanelEstadoConexion panelEstado;
	
	/**
     * Panel para desplegar botones
     */
	private PanelBotones panelBotones;
	
	/**
     * Panel para desplegar archivos disponibles
     */
	private PanelConfig panelConfig;
	
	

	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea la ventana principal de la aplicación
     */
	public InterfazCliente(){

		setLayout(new BorderLayout());
		setSize(480,300);
		setResizable(false);
		setTitle( "Cliente TCP" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //Incializo panel
		//panelEstado = new PanelEstadoConexion(this);
		panelBotones= new PanelBotones(this);
		panelConfig = new PanelConfig(this);
		
		//Agrego a la interfaz
		//add( panelEstado, BorderLayout.NORTH );
		add( new JPanel(), BorderLayout.NORTH );
		add( panelConfig, BorderLayout.CENTER );
		add( panelBotones, BorderLayout.SOUTH );
		
	}

	//-----------------------------------------------------------------
	// Ejecución
	//-----------------------------------------------------------------
	public void startConn()
	{
		
		String ip = panelConfig.darIP();
		String puerto = panelConfig.darPuerto();
		String numObj = panelConfig.darNumObjetos();
		
		if (ip.equals("") || puerto.equals("") || numObj.equals(""))
			JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
		else{
			
			int puertoI = 0;
			int numObjI =0;
			try{
				puertoI = Integer.parseInt(puerto);
				numObjI = Integer.parseInt(numObj);
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(this, "Debe ingresar numeros", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			cliente = new Cliente(ip, puertoI, numObjI);
			
			cliente.iniciarConexion();
			
			boolean error = cliente.darErrores();
			
			if(error)
			{
				JOptionPane.showMessageDialog(null, "Error, host o puerto incorrectos");
			}
			else{
				JOptionPane.showMessageDialog(null, "Ya termino de mandar los objetos");
			}
			
		}		
	}
	
	public Cliente darCliente(){
		return cliente;
	}
	
	/**
	 * Inicializa la aplicación
	 * @param args argumentos de la aplicación, no se requiere ninguno
	 */

	public static void main(String[] args) {
		InterfazCliente main = new InterfazCliente();
		main.setVisible(true);
	}

}
