public class Logros {
	private String nombre;
	private String descripcion;
	private int progresoActual;
	private int progresoCompleto;

	public Logros(String nombre, String descripcion, int progresoCompleto) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.progresoActual = 0;
		this.progresoCompleto = progresoCompleto;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getProgresoActual() {
		return this.progresoActual;
	}
	
	public void setProgresoActual(int progresoActual) {
		this.progresoActual = progresoActual;
	}
	
	public int getProgresoComplet() {
		return this.progresoCompleto;
	}
	
	public void setProgresoCompleto(int progresoCompleto) {
		this.progresoCompleto = progresoCompleto;
	}
	
	public void actualizarProgreso() {
		if(this.progresoCompleto < this.progresoActual) {
			this.progresoActual++;
		}
	}
	
	public boolean logroCompleto() {
		return this.progresoActual == this.progresoCompleto;
	}
	
	
}
