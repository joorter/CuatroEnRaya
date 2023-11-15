import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class AtenderPeticion implements Runnable {
private Socket s;
private static int numeroconexiones=0;
private boolean rojo;
CountDownLatch contador = new CountDownLatch(2);

public AtenderPeticion(Socket s) {
	this.s=s;
	numeroconexiones++;
	if(numeroconexiones==1) {
		rojo=true;
	}else {
		rojo=false;
	}
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			System.out.println("Participante encontrado");
			contador.countDown();
			try(DataInputStream dis=new DataInputStream(s.getInputStream());
				DataOutputStream dos=new DataOutputStream(s.getOutputStream())) {
				dos.writeBoolean(rojo);
				Random random = new Random();
		        int empieza = random.nextInt(2);
		        dos.writeInt(empieza);
		        
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
