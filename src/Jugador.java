
public class Jugador {
	private String nombre;
	private boolean Equiporojo;
	public Jugador() {
		
		this.nombre="Jugador";
		Equiporojo=true;
		
	}
	public Jugador(String nombre) {
		this.nombre=nombre;
		Equiporojo=true;
	}
	public void setNombre(String Nombre) {
		this.nombre=Nombre;
	}
	public String getNombre(String Nombre) {
		return this.nombre;
	}
	public void setEquiporojo(boolean Color) {
		this.Equiporojo=Color;
	}
	public boolean getEquiporojo() {
		return this.Equiporojo;
	}
	
}
