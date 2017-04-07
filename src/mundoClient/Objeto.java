package mundoClient;

import java.io.Serializable;
import java.util.Date;

public class Objeto implements Serializable{
	
	//--------------------------------------------------------------------------------------
	//VARIABLES
	//--------------------------------------------------------------------------------------	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Valor numérico entero que identifica la posición del objeto 
	 */
	private long pos;
	
	/**
	 * Marca de tiempo asociada al momento en el que es enviado el objeto
	 */
	private Date timestamp;
	
	private long deTotal;
	
	//--------------------------------------------------------------------------------------
	//MÉTODOS
	//--------------------------------------------------------------------------------------
	
	public Objeto(long iPos, long deTota, Date iTimestamp){
		pos = iPos;
		timestamp = iTimestamp;
		deTotal = deTota;
	}
	
	public void setPos(int iPos){
		pos = iPos;
	}
	
	public long getPos(){
		return pos;
	}
	
	public void setTimestamp(Date iTime){
		timestamp = iTime;
	}
	
	public Date getTimestamp(){
		return timestamp;
	}
	
	public long darTotal(){
		return deTotal;
	}

}
