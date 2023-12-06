import java.io.DataInputStream;
import java.io.IOException;

public class RecibirColumna extends Thread{
	private DataInputStream dis;
	private int columna;
	
	public RecibirColumna(DataInputStream dis) {
		this.dis=dis;
	}
	
	public void run() {
		try {
			columna=dis.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getColumna() {
		return columna;
	}
}
