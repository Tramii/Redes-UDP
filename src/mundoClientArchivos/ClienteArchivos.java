package mundoClientArchivos;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

import ServidorArchivos.ServidorArchivos;
import interfazClient.InterfazCliente;
import mundoClient.Objeto;

public class ClienteArchivos {
	public static void main(String[] args) {
		try {
			ClienteArchivos cli = new ClienteArchivos("192.168.0.11",57);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private DatagramSocket clientSocket;

	private String ip;
	private int puerto;
	private boolean error;

	@SuppressWarnings("unused")
	private InterfazCliente interfaz;
	public ClienteArchivos(String dirIP, int iPuerto)
	{
		error=false;

		ip = dirIP;
		puerto = (iPuerto);
		iniciarConexion();
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


			File initialFile = new File("./archivosParaDescarga/foto.JPG");
			initialFile.createNewFile();
			FileInputStream targetStream = new FileInputStream(initialFile);
			int filesize=targetStream.available();
			//int neededpackets =(int)Math.ceil((double)(size/1024));
			System.out.println("tamaño archivo: "+ filesize/1024 + " kb");
			for(int h=0;h<(filesize/1024) +1;h++){
				byte [] data= new byte[1024];
				// counting bytes
				for (int i=0;i<1024;i++)
				{
					data[i]=(byte)targetStream.read();
				}
				//create a packet
				DatagramPacket clpkt=new DatagramPacket(data,data.length,IPAddress,puerto);
				
				//String hash = MD5.md5(clpkt);
				System.out.println("mandando paquete "+h);
				clientSocket.send(clpkt);
			}
			byte [] data= "termino".getBytes();
			//manda paquete de hash con el termino
			DatagramPacket clpkt=new DatagramPacket(data,data.length,IPAddress,puerto);
			clientSocket.send(clpkt);
			System.out.println("termino");

			clientSocket.close();



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
