
public class Jugador {
	private String nombre;
	private static int numJugadores=0;
	private String color;
	public Jugador() {
		numJugadores++;
		this.nombre="Jugador "+numJugadores;
		if(numJugadores%2==0) {
			this.color="rojo";
		}else {
			this.color="amarillo";
		}	
	}
	public void setNombre(String Nombre) {
		this.nombre=Nombre;
	}
	public String getNombre(String Nombre) {
		return this.nombre;
	}
	public void setColor(String Color) {
		this.color=Color;
	}
	public String getColor(String Color) {
		return this.color;
	}
	
}
