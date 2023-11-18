
public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tablero t=new Tablero();
		Jugador j1 = new Jugador("Mario");
		Jugador j2 = new Jugador("Josean");
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
