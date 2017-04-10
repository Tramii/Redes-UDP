package mundoClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

import interfazClient.InterfazCliente;

public class ClienteArchivos {

	private DatagramSocket clientSocket;
	
	private String ip;
	private int puerto;
	private int numObj;
	private Objeto[] objetos;
	private boolean error;
	
	@SuppressWarnings("unused")
	private InterfazCliente interfaz;
	public ClienteArchivos(String dirIP, int iPuerto, int iNumObj)
	{
		error=false;
		
		ip = dirIP;
		puerto = (iPuerto);
		numObj = (iNumObj);
		
		objetos= new Objeto[numObj];
	}
	
	/**
	 * 
	 * crea el client socket que es un Datagram socket UDP
	 */
	public void iniciarConexion(){
        

        try {
        	
        	System.out.println("Creando socket en "+ip  );
        	
        	clientSocket = new DatagramSocket();
        	InetAddress IPAddress = InetAddress.getByName(ip);
			
             //manda objetos de inicio y fin
        	 for(int i=0; i< objetos.length ;i++)
        	 {
        		 Objeto aMandar =null;
        		 objetos[i] = new Objeto(i+1,  new Long(objetos.length),new Date()); 
        		 aMandar = objetos[i];
        	
        			
        		//convierte el objeto a object
        		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        		ObjectOutput out =new ObjectOutputStream(bos);   
        		out.writeObject(aMandar);
        		
        		byte [] sendData = bos.toByteArray();
        		
        		
        		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puerto);
        		clientSocket.send(sendPacket);
        		System.out.println("manda datagrama "+i);
        	 }
        	 
        	 
             /**   SOLO EN CASO DE QUE SE QUIERA QUE EL SERVIDOR RESPONDA
             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
             clientSocket.receive(receivePacket);
             String modifiedSentence = new String(receivePacket.getData());*/
             System.out.println("\n Ya mando todos los datagramas");
             
             cerrarConexion();
			
		} catch (Exception e) {
			error =true;
			e.printStackTrace();
        	try {
        		clientSocket.close();
			} catch (Exception f) {
				f.printStackTrace();
			}
		} 
	}
	

	
    
	/**
	 * Cierra conexión udp
	 * @throws Exception
	 */

    public void cerrarConexion(){
    	/**
    	 * siempre que termina de descargar algo, cierra conexion
    	 */
        try {
        	if(clientSocket != null)
        	{
        		clientSocket.close();
        		clientSocket = null;
        	}
			
		} catch (Exception e) {
			System.out.println(" se cierra clientSocket");
		}
    	
    }
    
    public boolean darErrores(){
    	return error;
    }
    

}
