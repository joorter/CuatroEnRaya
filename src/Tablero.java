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
		int cont;
		if (tablero[5][columna] == 0) {
			tablero[5][columna] = 1;
			cont=5;
		} else {
			 cont = 5;
			while ((tablero[cont][columna] != 0) && (cont > 0)) {
				cont--;
			}
			tablero[cont][columna] = 1;
		}
		if(ganarojo(cont, columna)) {
			System.out.println("Enhorabuena has ganado");
		}
	}

	public void ponerFichaAmarilla(int columna) {
		int cont;
		if (tablero[5][columna] == 0) {
			tablero[5][columna] = 2;
			 cont = 5;
		} else {
			 cont = 5;
			while ((tablero[cont][columna] != 0) && (cont > 0)) {
				cont--;
			}
			tablero[cont][columna] = 2;
		}
		if(ganaamarillo(cont, columna)) {
			System.out.println("Enhorabuena has ganado");
		}
	}
	public boolean ganarojo(int i,int j) {
		int cont=0;
		int suma=0;
		while(cont<6) {
			if(tablero[cont][j]==1) {
				suma++;
				if(suma==4) {
					return true;
				}
			}else {
				suma=0;
			}
			cont++;
		}
		 cont=0;
		 suma=0;
		while(cont<7) {
			if(tablero[i][cont]==1) {
				suma++;
				if(suma==4) {
					return true;
				}
			}else {
				suma=0;
			}
			cont++;
		}
		 cont=0;
		 suma=0;
		int iaux=i;
		int jaux=j;
		while(iaux>0 && jaux>0) {
			iaux--;
			jaux--;
		}
		while(iaux<6 && jaux<7) {
			if(tablero[iaux][jaux]==1) {
				suma++;
				if(suma==4) {
					return true;
				}
			}else {
				suma=0;
			}
			iaux++;
			jaux++;
		}
		 iaux=i;
		 jaux=j;
		while(iaux>0 && jaux<7) {
			iaux--;
			jaux++;
		}
		while(iaux<6 && jaux>0) {
			if(tablero[iaux][jaux]==1) {
				suma++;
				if(suma==4) {
					return true;
				}
			}else {
				suma=0;
			}
			iaux++;
			jaux--;
		}
		return false;
				
	}
	public boolean ganaamarillo(int i,int j) {
		int cont=0;
		int suma=0;
		while(cont<6) {
			if(tablero[cont][j]==2) {
				suma++;
				if(suma==4) {
					return true;
				}
			}else {
				suma=0;
			}
			cont++;
		}
		 cont=0;
		 suma=0;
		while(cont<7) {
			if(tablero[i][cont]==2) {
				suma++;
				if(suma==4) {
					return true;
				}
			}else {
				suma=0;
			}
			cont++;
		}
		 cont=0;
		 suma=0;
		int iaux=i;
		int jaux=j;
		while(iaux>0 && jaux>0) {
			iaux--;
			jaux--;
		}
		while(iaux<6 && jaux<7) {
			if(tablero[iaux][jaux]==2) {
				suma++;
				if(suma==4) {
					return true;
				}
			}else {
				suma=0;
			}
			iaux++;
			jaux++;
		}
		 iaux=i;
		 jaux=j;
		while(iaux>0 && jaux<7) {
			iaux--;
			jaux++;
		}
		while(iaux>0 && jaux<7) {
			if(tablero[iaux][jaux]==2) {
				suma++;
				if(suma==4) {
					return true;
				}
			}else {
				suma=0;
			}
			iaux++;
			jaux--;
		}
		return false;
				
	}
	
	public void mostrarTablero() {
		for (int x = 0; x < tablero.length; x++) {
			for (int y = 0; y < tablero[x].length; y++) {
				System.out.print("|");
				if (tablero[x][y] == 0) {
					System.out.print(" ");
				}
				if (tablero[x][y] == 1) {
					System.out.print("X");
				}
				if (tablero[x][y] == 2) {
					System.out.print("O");
				}

			}
			System.out.println("|");
		}
	}
}
