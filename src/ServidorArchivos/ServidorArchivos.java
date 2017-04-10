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

    }

    public void run(int port) {    
    	
    }
      
}




