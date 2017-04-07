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
	
	private boolean esFinal;
	
	//--------------------------------------------------------------------------------------
	//MÉTODOS
	//--------------------------------------------------------------------------------------
	
	public Objeto(long iPos, Date iTimestamp, boolean esFin){
		pos = iPos;
		timestamp = iTimestamp;
		esFinal = esFin;
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
	
	public boolean esElFinal(){
		return esFinal;
	}

}
