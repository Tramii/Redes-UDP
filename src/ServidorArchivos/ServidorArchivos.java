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
        ServidorArchivos server = new ServidorArchivos();
        server.run(port);
    }
    
    public ServidorArchivos(){
    	//lee el archivo
    	
    	File file = new File("./archivos/foto.JPG");
        // Get the size of the file
        
    	FileOutputStream fos = new FileOutputStream(file);
        
        
    	 DatagramSocket srvskt = new DatagramSocket(SPort);
    	    byte[] data =new byte[1024];
    	    System.out.println("Enter a full file name to save data to it ?");
    	    String path = input.next();
    	    System.out.println("file : "+path+" will be created.");
    	    FileOutputStream  FOS = new FileOutputStream(path);
    	    DatagramPacket srvpkt = new DatagramPacket(data,1024);
    	    System.out.println("listening to Port: "+SPort);
    	    int Packetcounter=0;//packet counter
    	    while(true)
    	       {
    	           srvskt.receive(srvpkt);
    	           Packetcounter++;
    	           String words = new String(srvpkt.getData());
    	           InetAddress ip= srvpkt.getAddress();
    	           int port = srvpkt.getPort();
    	           System.out.println("Packet # :"+Packetcounter+"
    	            Received from Host / Port: "+ip+" / "+port);
    	           FOS.write(data);
    	           //out16.flush();
    	           if (Packetcounter >=100)
    	                 break;

    	      }
    	    FOS.close();//releasing file.
    	    System.out.println("Data has been written to the file !");
    }

    public void run(int port) {    
    	
    }
      
}




