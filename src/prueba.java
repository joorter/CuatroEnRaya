
public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tablero t=new Tablero();
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
		
		System.out.println(t.ganarojo(4, 2));
		t.mostrarTablero();
	}

}
