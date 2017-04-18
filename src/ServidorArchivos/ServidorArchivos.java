package ServidorArchivos;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

import mundoClient.Objeto;

public class ServidorArchivos {

	public static void main(String[] args) {
		int port = args.length == 0 ? 57 : Integer.parseInt(args[0]);
		try {
			ServidorArchivos server = new ServidorArchivos(port);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DatagramSocket serverSocket;
	public FileOutputStream  FOS;
	public DatagramPacket serverPacket;
	public byte[] data;
	
	public ServidorArchivos(int port) throws Exception{
		int i=0;
		serverSocket = new DatagramSocket(port);
		data =new byte[1024];
		System.out.println("Listo para recibir la foto por udp");
		
		serverPacket = new DatagramPacket(data,1024);
		System.out.println("listening to Port: "+port);
		while(true){
			File file = new File("./archivosDescargados/archivo"+i+".JPG");
			FOS = new FileOutputStream(file);
			procesarLlegadaArchivo();
			i++;
		}
	}
	
	public void procesarLlegadaArchivo() throws Exception{
		//lee el archivo
		int Packetcounter=0;//packet counter
		while(true)
		{
			System.out.println("listo para recibir paquete");
			serverSocket.receive(serverPacket);
			Packetcounter++;
			String words = new String(serverPacket.getData());
			//System.out.println(words);
			InetAddress ip= serverPacket.getAddress();
			int portN = serverPacket.getPort();
			System.out.println("Packet # :"+Packetcounter+"Received from Host / Port: "+ip+" / "+portN);
			if(!words.startsWith("termino")){
				FOS.write(data);
				FOS.flush();
			}
			else{
				break;
			}

		}
		try {
			FOS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//releasing file.
		System.out.println("\n Data has been written to the file !");
	}

}




