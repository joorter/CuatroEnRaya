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
private Socket s;
private static int numeroconexiones;
private boolean rojo;
private static CountDownLatch contador=new CountDownLatch(2);
private static Tablero t;
private static int empieza;

public AtenderPeticion(Socket s) {
	this.s=s;
	
	numeroconexiones++;
	
	if(numeroconexiones%2==1) {
		rojo=true;
		t=new Tablero();
		Random random = new Random();
         this.empieza = random.nextInt(2);
	}else {
		rojo=false;
	}
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			
			try {
				t=new Tablero();
				System.out.println("Participante encontrado");
				
				contador.countDown();
				contador.await();
				try(DataInputStream dis=new DataInputStream(s.getInputStream());
						DataOutputStream dos=new DataOutputStream(s.getOutputStream());
						ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
						ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
						) {
						String jugador=dis.readLine();
						dos.writeBoolean(rojo);
				        dos.writeInt(AtenderPeticion.empieza);		
				        	oos.writeObject(t);
							boolean turno=false;
							contador =new CountDownLatch(2);
							while(!t.getFinalizado()) {
								
								turno=dis.readBoolean();
								if(turno) {
									t=(Tablero)ois.readObject();
									t.mostrarTablero();
								}
								contador.countDown();
								contador.await();
								if(!turno) {
									oos.writeObject(t);
									dos.flush();
									
								}else {
									contador = new CountDownLatch(2);
								}
								System.out.println("fin de turno");
								
								
							}
							if(turno) {
								
								System.out.println("Ha ganado "+jugador);
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
									System.out.println(jugador);
									if(nombres.item(cont).getTextContent().equals(jugador)) {
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
			                    nombreElement.setTextContent(jugador);
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
				}

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
