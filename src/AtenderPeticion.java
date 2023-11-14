import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class AtenderPeticion implements Runnable {
private Socket s;
public AtenderPeticion(Socket s) {
	this.s=s;
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try(DataInputStream dis=new DataInputStream(s.getInputStream())) {
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
