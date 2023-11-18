import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class EjecutarJugador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre");
		Scanner sc = new Scanner(System.in);
		String nombre=sc.next();
		Jugador j=new Jugador(nombre);
		int menu=0;
		while(menu!=3) {
			
			System.out.println("¿Que quieres hacer? Introduce el numero");
			System.out.println("1.Cambiar nombre");
			System.out.println("2.Jugar");
			System.out.println("3.Salir");
			
			menu=sc.nextInt();
			if(menu==1) {
				System.out.println("Introduce el nombre");
				nombre=sc.next();
				j.setNombre(nombre);
			}
			if(menu==2) {
				try(Socket s=new Socket("localhost",255);
				DataInputStream dis=new DataInputStream(s.getInputStream());
				DataOutputStream dos=new DataOutputStream(s.getOutputStream());

				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
						ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
						){
					boolean rojo;
					rojo=dis.readBoolean();
					if(!rojo) {
						j.setEquiporojo(false);
					}
					if(j.getEquiporojo()) {
						System.out.println("Eres del equipo rojo, marcaras con X");
					}else {
						System.out.println("Eres del equipo amarillo, marcaras con O");
					}
		            int empezar=dis.readInt();
		            boolean turno;
					if((empezar==1 )&&(j.getEquiporojo()) ||((empezar==0 )&&(!j.getEquiporojo()))) {
						System.out.println("Es tu turno");
						 turno=true;
					}else {
						System.out.println("Espera tu turno");
						 turno=false;
					}
					dos.flush();
					
					Tablero t =(Tablero) ois.readObject();
					while(!t.getFinalizado()) {
						t.mostrarTablero();
						dos.writeBoolean(turno);
						if(turno) {
							System.out.println("Indica el numero de columna del 1 al 7 para añadir tu ficha ");
							int columna=sc.nextInt();
							columna=columna-1;
							t.ponerFicha(j,columna);
							turno=false;
							oos.writeObject(t);
							dos.flush();
														

						}else {
							System.out.println("Espera tu turno");
							turno=true;
							t=(Tablero)ois.readObject();
							
							
						}
						
						
					}
					if(turno) {
						System.out.println("Lo siento has perdido");
					}
					t.mostrarTablero();
					oos.close();
					ois.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
