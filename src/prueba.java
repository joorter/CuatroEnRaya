
public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tablero t=new Tablero();
		Jugador j1 = new Jugador("Mario");
		Jugador j2 = new Jugador("Josean");
		j2.setEquiporojo(false);
		
		t.ponerFicha(j1, 1);
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
		
		
		/*
		t.ponerFichaRoja(1);
		t.ponerFichaAmarilla(2);
		t.ponerFichaRoja(2);
		t.ponerFichaRoja(1);
		t.ponerFichaAmarilla(3);
		t.ponerFichaAmarilla(3);
		t.ponerFichaRoja(3);
		t.ponerFichaAmarilla(4);
		t.ponerFichaAmarilla(4);
		t.ponerFichaAmarilla(4);
		t.ponerFichaRoja(4);
		t.ponerFichaRoja(1);
		t.ponerFichaRoja(0);
		*/
		System.out.println(t.ganarojo(4, 2));		//System.out.println(t.jugadorGana(4, 2,1));
		//System.out.println(t.jugadorGana(4, 2,2));
		//System.out.println(t.ganarojo(4, 2));
		t.mostrarTablero();
	}

}
