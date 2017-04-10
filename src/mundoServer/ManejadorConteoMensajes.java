package mundoServer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import mundoClient.Objeto;

public class ManejadorConteoMensajes extends Thread {
	
	private ArrayList <Long> cuantosMensajesPorCliente;
	private ArrayList <Long> sumaTiemposPorCliente;
	private ArrayList clientes;
	private ArrayList mensajes;
	
	public ManejadorConteoMensajes(){
		cuantosMensajesPorCliente = new ArrayList<Long>();
		sumaTiemposPorCliente = new ArrayList<Long>();
		clientes = new ArrayList();
		mensajes = new ArrayList(100000);
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
		
		long tiempoTravesia = msg.fechaArriboAlServidor.getTime() - objeto.getTimestamp().getTime();
		
		int i =clientes.lastIndexOf(cliente);
		if(i==-1){
			//nuevo cliente
			
			clientes.add(cliente);
			
			i= clientes.size()-1;//la pos de este cliente
			//system.out.println("crea cliente");
			
			//faltan por mandar objeto.getPos()
			cuantosMensajesPorCliente.add(i,-objeto.darTotal());
			//sumaTiemposPorCliente.add(i, tiempoTravesia); se hace más adelante
		}
		
		//por cada mensaje estoy añandiendo en la lista de mensajes +1.
		//al llegar n mensajes, la lista del cliente va a contener el numero n
		//pero la lista se inicializo en -TODOS los que iban a llegar que es -n
		//entonces la lista comienza  en -100, y por cada uno que llega actualiza a -99.. -98.. hasta 0
		cuantosMensajesPorCliente.add(i, (cuantosMensajesPorCliente.get(i) + 1)  );
		
		System.out.println("\n El cliente "+ cliente +" paquetes que faltan: "+(-cuantosMensajesPorCliente.get(i)));
		//los que llegaron son el total - los que faltan
		long cuantosLlegaron = objeto.darTotal()+cuantosMensajesPorCliente.get(i);
		System.out.println(" paquetes que llegaron: "+cuantosLlegaron);
		//tiempo promedio = tiempo de los que han llegado / el total
		
		sumaTiemposPorCliente.add(i, sumaTiemposPorCliente.get(i)+tiempoTravesia);
		
		long tiempoPromedio = sumaTiemposPorCliente.get(i)/cuantosLlegaron;
		System.out.println("tiempo promedio: "+ tiempoPromedio+"   tiempo paquete actual: "+tiempoTravesia);
		
		if(cuantosMensajesPorCliente.get(i) == 0){
			System.out.println("\n El cliente "+ cliente +" NO PERDIO ningun paquete");
		}
		
		try {
			//perdidas son los que faltaron
			escribirArchivo(msg, -cuantosMensajesPorCliente.get(i)+"" , ""+tiempoPromedio, ""+tiempoTravesia);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void procesarMensaje(String cliente, Objeto objetoEntrante, Date fechaLlegada){
		//System.out.println(cliente);
        //System.out.println("RECEIVED: " +objetoEntrante.getPos()+"-"+ objetoEntrante.getTimestamp());
        
        synchronized (mensajes){
        	mensajes.add(new Mensaje(cliente,objetoEntrante,fechaLlegada));        	
        }
	}
	
	public void escribirArchivo(Mensaje msg, String perdidas, String tiempoPromedio, String tiempoTravesia) throws Exception{
		String cliente = msg.clie;
		Objeto objetoEntrante = msg.obje;
		
		//escribe en el file el numero del mensaje y el tiempo
        File log = new File("data/"+cliente+".txt");
        PrintWriter out = new PrintWriter(new FileWriter(log, true));
        
        out.write(objetoEntrante.getPos()+": "+(tiempoTravesia )+ " ms 			"+ " Perdidas/faltas totales: "+ perdidas+ " Objetos TOTALES: "+msg.obje.darTotal() + " Tiempo promedio: "+ tiempoPromedio+ " ms \n");
        
        out.flush();
        out.close();
	}
	
	public class Mensaje{
		private String clie;
		private Objeto obje;
		private Date fechaArriboAlServidor;
		public Mensaje(String cli, Objeto obj, Date fechaLlegada){
			obje= obj;
			clie=cli;
			fechaArriboAlServidor = fechaLlegada;
			//tiempoTravesia = fechaLlegada.getTime() - obj.getTimestamp().getTime();
		}
	}
}
