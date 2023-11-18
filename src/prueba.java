import java.util.List;
import java.util.ArrayList;

public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Logros logro1 = new Logros("Primera victoria", "Gana una partida",1);
		Logros logro2 = new Logros("Segunda victoria", "Gana dos partidas",2);
		
		List<Logros> logrosJugador = new ArrayList<>();
		logrosJugador.add(logro1);
		logrosJugador.add(logro2);
		
		Tablero t=new Tablero();
		Jugador j1 = new Jugador("Mario",logrosJugador);
		Jugador j2 = new Jugador("Josean",logrosJugador);
		
		System.out.println("Comprobamos logros:");
		j1.consultarLogros();
		
		
		j2.setEquiporojo(false);
		
		
		
		
		t.mostrarTablero();
		t.ponerFicha(j2, 2);
		t.ponerFicha(j1, 2);
		t.ponerFicha(j1, 1);
		t.ponerFicha(j2, 3);
		t.ponerFicha(j2, 3);
		t.ponerFicha(j1, 3);
		t.ponerFicha(j2, 4);
		t.ponerFicha(j2, 4);
		t.ponerFicha(j2, 4);
		t.ponerFicha(j1, 4);
		t.ponerFicha(j1, 1);
		t.ponerFicha(j1, 0);
		t.ponerFicha(j1, 4);
		t.ponerFicha(j1, 4);t.ponerFicha(j1, 4);
		
		
		System.out.println(t.jugadorGana(5, 1,1));

		t.mostrarTablero();

		System.out.println(t.columnaLlena(4));
		System.out.println(t.columnaLlena(5));

		}

}
