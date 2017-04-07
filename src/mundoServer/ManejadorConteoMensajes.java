package mundoServer;

import java.util.ArrayList;

import mundoClient.Objeto;

public class ManejadorConteoMensajes extends Thread {
	
	private ArrayList <Long> cuantosMensajesPorCliente;
	private ArrayList clientes;
	private ArrayList mensajes;
	
	public ManejadorConteoMensajes(){
		cuantosMensajesPorCliente = new ArrayList<Long>();
		clientes = new ArrayList();
		mensajes = new ArrayList();
	}
	
	public void run(){
		while (true){
			
			if(mensajes.size() >0){
				Mensaje actual;
				synchronized (mensajes){
				actual=(Mensaje) mensajes.remove(0);
				}
				procesarMensaje(actual);
			}
			else{
				yield();
			}
		}
		
	}
	public synchronized void procesarMensaje(Mensaje msg){
		String cliente = msg.clie;
		Objeto objeto = msg.obje;
		
		int i =clientes.lastIndexOf(cliente);
		if(i==-1){
			clientes.add(cliente);
			
			i= clientes.size()-1;//la pos de este cliente
			System.out.println("crea cliente");
			
			//los mensajes acumulados hasta ahora son: 0
			cuantosMensajesPorCliente.add(i,new Long(0));
		}
		
		//por cada mensaje estoy añandiendo en la lista la suma de las pos.
		//al llegar n mensajes, la lista del cliente va a contener el numero n(n+1)/2
		//pero cuando llegue el ultimo objeto de terminación, su pos será -((n*n+1)/2)
		//osea que cuantos mensajes por cliente debe ser 0 cuando todos los mensajes sean enviados
		//mientras no sea 0, o se perdieron paquetes del cliente o no ha terminado de mandar
		cuantosMensajesPorCliente.add(i, (cuantosMensajesPorCliente.get(i) + objeto.getPos())  );
		
		if(cuantosMensajesPorCliente.get(i) != 0){
			System.out.println("Se han perdido paquetes o no ha terminado. La cuenta es: "+cuantosMensajesPorCliente.get(i));
		}
		else{
			System.out.println("\n El cliente "+ cliente +" NO PERDIO ningun paquete");
		}
	}
	
	public void procesarMensaje(String cliente, Objeto objetoEntrante){
		System.out.println(cliente);
        System.out.println("RECEIVED: " +objetoEntrante.getPos()+"-"+ objetoEntrante.getTimestamp());
        
        synchronized (mensajes){
        	mensajes.add(new Mensaje(cliente,objetoEntrante));        	
        }
	}
	
	public class Mensaje{
		private String clie;
		private Objeto obje;
		public Mensaje(String cli, Objeto obj){
			obje= obj;
			clie=cli;
		}
	}
}
