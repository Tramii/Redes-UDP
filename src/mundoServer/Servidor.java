package mundoServer;


import java.io.IOException;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        int port = args.length == 0 ? 57 : Integer.parseInt(args[0]);
        new Servidor().run(port);
    }

    public void run(int port) {    
      try {
        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[1024];

        System.out.printf("Listening on udp:%s:%d%n",
                InetAddress.getLocalHost().getHostAddress(), port);     
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                           receiveData.length);

        while(true)
        {
              serverSocket.receive(receivePacket);
              String sentence = new String( receivePacket.getData(), 0,
                                 receivePacket.getLength() );
              System.out.println("RECEIVED: " + sentence);
              
        }
      } catch (IOException e) {
              System.out.println(e);
      }
      // should close serverSocket in finally block
    }
}