import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
			ExecutorService pool = Executors.newFixedThreadPool(2);
			
			try(ServerSocket socket = new ServerSocket(233)){
				
			while (true) {
				Socket s=socket.accept();
				pool.execute(new AtenderPeticion(s));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
