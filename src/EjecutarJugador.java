import java.util.Scanner;

public class EjecutarJugador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jugador j=new Jugador();
		int menu=0;
		while(menu!=3) {
			System.out.println("¿Que quieres hacer? Introduce el numero");
			System.out.println("1.Cambiar nombre");
			System.out.println("2.Jugar");
			System.out.println("3.Salir");
			Scanner sc = new Scanner(System.in);
			menu=sc.nextInt();
			if(menu==1) {
				System.out.println("Introduce el nombre");
				String nombre=sc.next();
				j.setNombre(nombre);
			}
			if(menu==2) {
				
			}
		}
	}

}
