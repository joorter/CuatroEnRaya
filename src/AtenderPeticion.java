import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AtenderPeticion implements Runnable {
private Socket j1;
private Socket j2;
private CountDownLatch contador=new CountDownLatch(2);
private Tablero t;
private int empieza;

public AtenderPeticion(Socket s, Socket s1) {
	this.j1=s;
	this.j2=s1;
	Random r=new Random();
	empieza=r.nextInt(2);
	
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			
			try {
				t=new Tablero();
				System.out.println("Empieza la partida");
				
				
				try(DataInputStream dis=new DataInputStream(j1.getInputStream());
						DataOutputStream dos=new DataOutputStream(j1.getOutputStream());
						ObjectInputStream ois=new ObjectInputStream(j1.getInputStream());
						ObjectOutputStream oos=new ObjectOutputStream(j1.getOutputStream());
						DataInputStream dis2=new DataInputStream(j2.getInputStream());
						DataOutputStream dos2=new DataOutputStream(j2.getOutputStream());
						ObjectInputStream ois2=new ObjectInputStream(j2.getInputStream());
						ObjectOutputStream oos2=new ObjectOutputStream(j2.getOutputStream());
						) {
						String jugador1=dis.readLine();
						String jugador2=dis2.readLine();
						dos.writeBoolean(true);
						dos2.writeBoolean(false);
				        dos.writeInt(empieza);	
				        dos2.writeInt(empieza);
				        	oos.writeObject(t);
				        	oos2.writeObject(t);
							boolean turno=false;
							contador =new CountDownLatch(2);
							while(!t.getFinalizado()) {
								
								
								if(turno) {
									
									t=(Tablero)ois.readObject();
									t.mostrarTablero();
									oos2.writeObject(t);
									oos2.flush();
									oos2.reset();
									turno=false;

								}else {
									
									t=(Tablero)ois2.readObject();
									t.mostrarTablero();
									oos.writeObject(t);
									oos.flush();
									oos.reset();
									turno=true;
								}
									
								
								System.out.println("fin de turno");
							}
								
							String ganador=null;
							if(!turno) {
								System.out.println("Ha ganado "+jugador1);
								ganador=jugador1;
							}else {
								System.out.println("Ha ganado "+jugador2);
								ganador=jugador2;
							}
								
								
								int numvictorias;
								int posicionJugadorGanador = 0;
								
								DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
								DocumentBuilder db = dbf.newDocumentBuilder();
								Document doc = db.parse(new File("src/BD.xml"));
								//Document doc = db.parse(new File("src/Jugadores.xml"));
								
								int cont=0;
								boolean existe=false;
								
								Element raiz= doc.getDocumentElement();
								NodeList nombres=raiz.getElementsByTagName("nombre");
								NodeList victoriastodosganadores=raiz.getElementsByTagName("victorias");
								while(cont<nombres.getLength()){
									System.out.println(nombres.item(cont).getTextContent());
									System.out.println(ganador);
									if(nombres.item(cont).getTextContent().equals(ganador)) {
										existe=true;
										posicionJugadorGanador=cont;
									}
									cont++;
								}
								if(existe) {
									numvictorias=Integer.parseInt(victoriastodosganadores.item(posicionJugadorGanador).getTextContent());
									numvictorias++;
								}else {
									numvictorias=1;
									
								}

								Element persona = doc.createElement("persona");
			                    raiz.appendChild(persona);
			                    Element nombreElement = doc.createElement("nombre");
			                    persona.appendChild(nombreElement);
			                    nombreElement.setTextContent(ganador);
			                    Element victoriasElement = doc.createElement("victorias");
			                    persona.appendChild(victoriasElement);
			                    victoriasElement.setTextContent(numvictorias + "");
			                    
			                    Element logrosElement = doc.createElement("logros");
			                    persona.appendChild(logrosElement);
			                    for(int i=1;i<=5;i++) {
			                    	Element logroElement = doc.createElement("logro");
			                    	
			                    	Element idElement = doc.createElement("id");
			                    	idElement.setTextContent(Integer.toString(i));
			                    	logroElement.appendChild(idElement);
			                    	
			                    	Element nombreLogroElement = doc.createElement("nombre");
			                        nombreLogroElement.setTextContent("Logro"+i);
			                        logroElement.appendChild(nombreLogroElement);
			                        
			                        Element descripcionElement = doc.createElement("descripcion");
			                        descripcionElement.setTextContent("Descripcion"+i);
			                        logroElement.appendChild(descripcionElement);
			                        
			                        Element completadoElement = doc.createElement("completado");
			                        completadoElement.setTextContent("Completado");
			                        logroElement.appendChild(completadoElement);
			                    }

			                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
			                    Transformer transformer = transformerFactory.newTransformer();
			                    DOMSource source = new DOMSource(raiz);
			                    StreamResult result = new StreamResult(new File("src/BD.xml"));
			                    transformer.transform(source, result);
							}				
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
