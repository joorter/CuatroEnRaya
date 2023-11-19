import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class AtenderPeticion implements Runnable {
private Socket s;
private static int numeroconexiones;
private boolean rojo;
private static CountDownLatch contador=new CountDownLatch(2);
private static Tablero t;
private static int empieza;

public AtenderPeticion(Socket s) {
	this.s=s;
	
	numeroconexiones++;
	
	if(numeroconexiones%2==1) {
		rojo=true;
		t=new Tablero();
		Random random = new Random();
         this.empieza = random.nextInt(2);
	}else {
		rojo=false;
	}
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			
			try {
				t=new Tablero();
				System.out.println("Participante encontrado");
				
				contador.countDown();
				contador.await();
				try(DataInputStream dis=new DataInputStream(s.getInputStream());
						DataOutputStream dos=new DataOutputStream(s.getOutputStream());
						ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
						ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
						) {
						dos.writeBoolean(rojo);
				        dos.writeInt(AtenderPeticion.empieza);		
				        	oos.writeObject(t);
							boolean turno;
							contador =new CountDownLatch(2);
							while(!t.getFinalizado()) {
								
								turno=dis.readBoolean();
								if(turno) {
									t=(Tablero)ois.readObject();
									t.mostrarTablero();
								}
								contador.countDown();
								contador.await();
								if(!turno) {
									oos.writeObject(t);
									dos.flush();
									
								}else {
									contador = new CountDownLatch(2);
								}
								System.out.println("fin de turno");
								
								
							}
				        	
						
				        
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
