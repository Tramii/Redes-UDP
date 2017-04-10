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

	public ServidorArchivos(int port) throws Exception{
		//lee el archivo

		File file = new File("./archivosDescarga/foto.JPG");


		DatagramSocket serverSocket = new DatagramSocket(port);
		byte[] data =new byte[1024];
		System.out.println("Enter a full file name to save data to it ?");
		FileOutputStream  FOS = new FileOutputStream(file);
		DatagramPacket serverPacket = new DatagramPacket(data,1024);
		System.out.println("listening to Port: "+port);
		int Packetcounter=0;//packet counter
		while(true)
		{
			serverSocket.receive(serverPacket);
			Packetcounter++;
			String words = new String(serverPacket.getData());
			InetAddress ip= serverPacket.getAddress();
			int portN = serverPacket.getPort();
			System.out.println("Packet # :"+Packetcounter+"Received from Host / Port: "+ip+" / "+portN);
			FOS.write(data);
			//out16.flush();
			if (Packetcounter >=10000000)
				break;

		}
		try {
			FOS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//releasing file.
		System.out.println("Data has been written to the file !");
	}

}




