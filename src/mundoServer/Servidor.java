package mundoServer;


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
import java.util.Date;

import mundoClient.Objeto;

public class Servidor {

    public static void main(String[] args) {
        int port = args.length == 0 ? 57 : Integer.parseInt(args[0]);
        new Servidor().run(port);
    }

    public void run(int port) {    
      try {
        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[512];

        System.out.printf("Listening on udp:%s:%d%n",
                InetAddress.getLocalHost().getHostAddress(), port);     
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                           receiveData.length);

        while(true)
        {
              serverSocket.receive(receivePacket);
              String sentence = new String( receivePacket.getData(), 0,
                                 receivePacket.getLength() );
              Objeto objectoEntrante= (Objeto)deserialize(receivePacket.getData());
              //llega esto -> 192.168.56.1:63365
              String complete = receivePacket.getSocketAddress() +"";
              String cliente = complete.split(":")[0];
              System.out.println(cliente);
              System.out.println("RECEIVED: " +objectoEntrante.getPos()+"-"+ objectoEntrante.getTimestamp());
              
              File log = new File("./data/"+cliente+".txt");
              PrintWriter out = new PrintWriter(new FileWriter(log, true));
              
              out.write(objectoEntrante.getPos()+": "+(System.currentTimeMillis() -objectoEntrante.getTimestamp().getTime())+ "\n  ");
              out.flush();
              out.close();
        
        }
      } catch (Exception e) {
              System.out.println(e);
      }
      // should close serverSocket in finally block
    }
    
    private Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return (Objeto)o.readObject();
    }
}