import java.io.Serializable;
import java.util.Scanner;

//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
public class Tablero implements Serializable {
	private int tablero[][];
	private boolean finalizado;
	public Tablero()
	//Constructor del Tablero
	//Genera un tablero con 6 filas y 7 columnas.
	{
		finalizado=false;
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
	public boolean getFinalizado() {
		return this.finalizado;
	}
	public boolean columnaLlena(int col) {
		if(tablero[0][col] == 1 || tablero[0][col] == 2) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void ponerFicha(Jugador j, int columna) {
		int cont;
		int ficha;
		
		while(columnaLlena(columna)) {
			System.out.println("Has seleccionado una columna llena, selecciona otra por favor:");
			Scanner sc = new Scanner(System.in);
			columna = sc.nextInt();
			while(columna < 0 || columna >=7) {
				System.out.println("No has elegido una columna válida.");
				columna = sc.nextInt();
			}
		}
	
		if(j.getEquiporojo() == true) {
			ficha = 1;
		}else {
			ficha = 2;
		}
		
		if (tablero[5][columna] == 0) {
			tablero[5][columna] = ficha;
			cont=5;
		}
		else {
			cont = 5;
			while ((tablero[cont][columna] != 0) && (cont > 0)) {
				cont--;
			}
			tablero[cont][columna] = ficha;
		}
		if(jugadorGana(cont, columna, ficha)) {
			System.out.println("Enhorabuena, " + j.getNombre() + " has ganado.");
			finalizado=true;
		}
		if(tablerocompleto()) {
			System.out.println("No se pueden colocar mas fichas partida en empate");
			finalizado=true;
		}
	
	}
	
	public boolean jugadorGana(int i, int j, int jugador) {
		int cont=0;
		int suma=0;
		//Comprobar victoria por columna
		while(cont<6) {
			if(tablero[cont][j] == jugador) {
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
		 //Comprobar victoria por fila
		
		 while(cont<7) {
			if(tablero[i][cont] == jugador) {
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
			if(tablero[iaux][jaux] == jugador) {
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
		 suma=0;
		
		while(iaux>0 && jaux<6) {
			iaux--;
			jaux++;
		}
		while(iaux<6 && jaux>=0) {
			if(tablero[iaux][jaux] == jugador) {
				
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
	
	
	public boolean tablerocompleto(){
		int i = 0;
		int j = 0;
		while (i < 6) {
			while (j < 7) {
				if(tablero[i][j]==0) {
					return false;
				}
				j++;
			}
			i++;
			j = 0;
		}
		return true;
	}
	public void mostrarTablero()
	//Muestra el estado actual del tablero.
	//Las fichas X son las del jugador del equipo rojo.
	//Las fichas O son las del otro jugador.
	{
		
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

	/*public void ponerFichaRoja(int columna)
	//
	{
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
			System.out.println("Enhorabuena, has ganado.");
		}
	}
	*/
	
	

	/*public void ponerFichaAmarilla(int columna) {
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
			System.out.println("Enhorabuena, has ganado.");
		}
	}
	*/
	
/*	
	
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
		while(iaux>0 || jaux>0) {
			iaux--;
			jaux--;
		}
		while(iaux<6 || jaux<7) {
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
	
	
	
	/*
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
	*/
}
