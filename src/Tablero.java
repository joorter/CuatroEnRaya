//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
public class Tablero {
	private int tablero[][];

	public Tablero() {
		this.tablero = new int[6][7];
		int i = 0;
		int j = 0;
		while (i < 6) {
			while (j < 7) {
				this.tablero[i][j] = 0;
				j++;
			}
			i++;
			j = 0;
		}
	}
	public void ponerFichaRoja(int columna) {
		if(tablero[5][columna]==0) {
			tablero[5][columna]=1;
		}else {
			int cont=5;
			while((tablero[cont][columna]!=0)&&(cont>0)) {
				cont--;
			}
			tablero[cont][columna]=1;
		}
	}
	
	public void ponerFichaAmarilla(int columna) {
		if(tablero[5][columna]==0) {
			tablero[5][columna]=2;
		}else {
			int cont=5;
			while((tablero[cont][columna]!=0) &&(cont>0)) {
				cont--;
			}
			tablero[cont][columna]=2;
		}
	}

	public void mostrarTablero() {
		for (int x = 0; x < tablero.length; x++) {
			for (int y = 0; y < tablero[x].length; y++) {
				System.out.print("|");
				if(tablero[x][y]==0) {
					System.out.print(" ");
				}
				if(tablero[x][y]==1) {
					System.out.print("X");
				}
				if(tablero[x][y]==2) {
					System.out.print("O");
				}
				
				
			}
			System.out.println("|");
		}
	}
}
