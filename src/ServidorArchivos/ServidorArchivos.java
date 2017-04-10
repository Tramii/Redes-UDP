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
    	
    	file = new File("./descargas/"+tituloAPedir);
        // Get the size of the file
        
        fos = new FileOutputStream(file);
        
        
        byte [] bytes  = new byte [1024*16];
        String tamanoEnMB = inFromServerLine.readLine();
        System.out.println("\n ya va a recibir el archivo que pesa "+tamanoEnMB);
        //int bytesRead = 0;
        int current = 0;
        int i=0;
        int count;
        while ((count = inFromServer.read(bytes)) > 0) {
            fos.write(bytes, 0, count);
            i++;
            current+=count;
            System.out.println("Llego paquete. Escribiendo en el archivo el mensaje numero "+i);
            System.out.println("Descargado hasta el momento: "+current + "");
            //descomentar la linea de abajo para ver el contenido del paquete
            //System.out.println("contenido del paquete "+ " "+new String(bytes));
        }
    }

    public void run(int port) {    
    	
    }
      
}




