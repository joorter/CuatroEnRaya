import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Logros {
	private int id;
	private String nombre;
	private String descripcion;
	private boolean completado;
	
	public Logros(int id, String nombre, String descripcion, boolean completado) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.completado = completado;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
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
	
	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	
	public boolean getCompletado() {
		return this.completado;
	}
	
	public static List<Logros> obtenerLogrosDesdeXML(String xmlPath){
		List<Logros> listaLogros = new ArrayList<>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlPath);
			
			NodeList logros = doc.getElementsByTagName("logro");
			
			for(int i=0;i<logros.getLength();i++) {
				Node logro = logros.item(i);
				
				if(logro.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) logro;
					int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
					String nombre = element.getElementsByTagName("nombrelogro").item(0).getTextContent();
					String descripcion = element.getElementsByTagName("descripcion").item(0).getTextContent();
					boolean completado = Boolean.parseBoolean(element.getElementsByTagName("completado").item(0).getTextContent());
				
					
					Logros logro2 = new Logros(id,nombre,descripcion,completado);
					listaLogros.add(logro2);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return listaLogros;
		
	}
	
	
	
	
}
