import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {
	private String nombre;
	private boolean Equiporojo;
	private List<Logros> logrosJugador;

	public Jugador() {

		this.nombre = "Jugador";
		Equiporojo = true;
		logrosJugador = new ArrayList<>();

	}

	public Jugador(String nombre, List<Logros> logros) {
		this.nombre = nombre;
		Equiporojo = true;
		logrosJugador = logros;
	}

	public void setNombre(String Nombre) {
		this.nombre = Nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setEquiporojo(boolean Color) {
		this.Equiporojo = Color;
	}

	public boolean getEquiporojo() {
		return this.Equiporojo;
	}

	public void completarLogro(String nombreLogro) {
		for (Logros logro : logrosJugador) {
			if (logro.getNombre().equals(nombreLogro)) {
				logro.setCompletado(true);
			}
		}
	}

	public List<Logros> consultarLogros() {
		for (Logros logro : logrosJugador) {
			System.out.println("ID logro: " + logro.getId() + " " + logro.getNombre() + ": " + logro.getDescripcion()
					+ "(" + logro.getCompletado() + ")");
		}
		return logrosJugador;
	}

	public boolean haCompletadoLogro(String nombreLogro) {
		for (Logros logro : logrosJugador) {
			if (logro.getNombre().equals(nombreLogro) && logro.getCompletado()) {
				return true;
			}
		}
		return false;
	}
}
