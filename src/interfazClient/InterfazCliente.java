package interfazClient;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	private PanelEstadoConexion panelEstado;
	
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
     * Crea la ventana principal de la aplicaci�n
     */
	public InterfazCliente(){

		setLayout(new BorderLayout());
		setSize(480,300);
		setResizable(false);
		setTitle( "Cliente TCP" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //Incializo panel
		panelEstado = new PanelEstadoConexion(this);
		panelBotones= new PanelBotones(this);
		panelConfig = new PanelConfig(this);
		
		//Agrego a la interfaz
		add( panelEstado, BorderLayout.NORTH );
		add( panelConfig, BorderLayout.CENTER );
		add( panelBotones, BorderLayout.SOUTH );
		
		cliente = new Cliente(this);
	}

	//-----------------------------------------------------------------
	// Ejecuci�n
	//-----------------------------------------------------------------
	public void startConn()
	{
		JOptionPane.showMessageDialog(null, "Iniciando conexi�n");
		cliente = new Cliente(this);
		cliente.iniciarConexion();
		JOptionPane.showMessageDialog(null, "Se ha establecido conexi�n");
	}
	
	public void cerrarConexion(){
		cliente.cerrarConexion();
		JOptionPane.showMessageDialog(null, "Ha cerrado la conexi�n");
	}
	
	public Cliente darCliente(){
		return cliente;
	}
	
	/**
	 * Inicializa la aplicaci�n
	 * @param args argumentos de la aplicaci�n, no se requiere ninguno
	 */

	public static void main(String[] args) {
		InterfazCliente main = new InterfazCliente();
		main.setVisible(true);
	}

}
