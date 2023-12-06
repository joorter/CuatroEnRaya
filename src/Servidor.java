import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
			ExecutorService pool = Executors.newCachedThreadPool();
			
			try(ServerSocket socket = new ServerSocket(255)){
				
			while (true) {
				try {
					Socket s=socket.accept();
					Socket s1=socket.accept();
					pool.execute(new AtenderPeticion(s,s1));
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
