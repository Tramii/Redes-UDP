package mundoClient;

import java.util.Date;

public class Objeto {
	
	//--------------------------------------------------------------------------------------
	//VARIABLES
	//--------------------------------------------------------------------------------------	
	
	/**
	 * Valor num�rico entero que identifica la posici�n del objeto 
	 */
	private int pos;
	
	/**
	 * Marca de tiempo asociada al momento en el que es enviado el objeto
	 */
	private Date timestamp;
	
	//--------------------------------------------------------------------------------------
	//M�TODOS
	//--------------------------------------------------------------------------------------
	
	public Objeto(int iPos, Date iTimestamp){
		pos = iPos;
		timestamp = iTimestamp;
	}
	
	public void setPos(int iPos){
		pos = iPos;
	}
	
	public int getPos(){
		return pos;
	}
	
	public void setTimestamp(Date iTime){
		timestamp = iTime;
	}
	
	public Date getTimestamp(){
		return timestamp;
	}
	

}