import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class EjecutarJugador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.next();
		List<Logros> logros = new ArrayList<>();
		Jugador j = new Jugador(nombre, logros);
		int menu = 0;
		int modojuego=0;
		while (menu != 5) {

			System.out.println("¿Que quieres hacer " + j.getNombre() + "? Introduce el numero");
			System.out.println("1.Cambiar nombre");
			System.out.println("2.Jugar");
			System.out.println("3.Numero victorias con el nombre " + j.getNombre());
			System.out.println("4. Ranking");
			System.out.println("5.Salir");

			menu = sc.nextInt();
			if (menu == 1) {
				System.out.println("Introduce el nombre");
				nombre = sc.next();
				j.setNombre(nombre);
			}
			if (menu == 2) {
				try (Socket s = new Socket("localhost", 255);
						DataInputStream dis = new DataInputStream(s.getInputStream());
						DataOutputStream dos = new DataOutputStream(s.getOutputStream());
						ObjectOutputStream oosj = new ObjectOutputStream(s.getOutputStream());
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						ObjectInputStream ois = new ObjectInputStream(s.getInputStream());) {
					dos.writeBytes(j.getNombre() + "\n");
					boolean rojo;
					rojo = dis.readBoolean();
					modojuego=0;
					
					if (rojo) {
						j.setEquiporojo(true);
						System.out.println("Por ser el primero en conectarte puedes elegir el modo de juego.");
						System.out.println("Selecciona el numero.");
						System.out.println("1.Normal");
						System.out.println("2.Marcar a la vez.En caso de elegir la misma columna el primero será aleatorio");
						
						while((modojuego!=1) && (modojuego!=2)) {
							modojuego=sc.nextInt();
						}
						System.out.println("hola");
						dos.writeInt(modojuego);
						
					}else {
						j.setEquiporojo(false);
						
						modojuego=dis.readInt();
						System.out.println(modojuego);
						
					}
					if (j.getEquiporojo()) {
						System.out.println("Eres del equipo rojo, marcaras con X");
					} else {
						System.out.println("Eres del equipo amarillo, marcaras con O");
					}
					int empezar = dis.readInt();
					
					
					
					
					
					boolean turno;
					if ((empezar == 1) && (j.getEquiporojo()) || ((empezar == 0) && (!j.getEquiporojo()))) {
						System.out.println("Es tu turno");
						turno = true;
					} else {
						System.out.println("Espera tu turno");
						turno = false;
					}
					dos.flush();

					Tablero t = (Tablero) ois.readObject();
					if(modojuego==1) {
						while (!t.getFinalizado()) {
							t.mostrarTablero();
							
							if (turno) {
								System.out.println("Indica el numero de columna del 1 al 7 para añadir tu ficha ");
								int columna = sc.nextInt();
								t.ponerFicha(j, columna);
								turno = false;
								oos.writeObject(t);
								dos.flush();

							} else {
								System.out.println("Espera tu turno");
								turno = true;
								t = (Tablero) ois.readObject();
							}
						}
					}
					if(modojuego==2) {
						int columna=0;
						while (!t.getFinalizado()) {
							
							System.out.println("Indica el numero de columna del 1 al 7 para añadir tu ficha ");
							 columna = sc.nextInt();
							dos.writeInt(columna);
							int rival=dis.readInt();
							boolean suerteprimero=dis.readBoolean();
							Jugador contrario=new Jugador();
							System.out.print(rival);
							if(j.getEquiporojo()) {
								contrario.setEquiporojo(false);
							}
							if(suerteprimero) {
								t.ponerFicha(j, columna);
								t.ponerFicha(contrario, rival);
							}else {
								t.ponerFicha(contrario, rival);
								t.ponerFicha(j, columna);
							}
							t.mostrarTablero();
							if(j.getEquiporojo()) {
								dos.writeBoolean(t.getFinalizado());
								dos.flush();
							}
							
						}
						boolean ganador=false;
						if(j.getEquiporojo()) {
							for(int i=0;i<6;i++) {
								if(t.jugadorGana(i, columna-1, 1)) {
									ganador=true;
								}
							}
						}else {
							for(int i=0;i<6;i++) {
								if(t.jugadorGana(i, columna-1, 2)) {
									ganador=true;
								}
							}
						}
						dos.writeBoolean(ganador);
						
					}
					
					if (turno) {
						
					} else {
						dos.writeChars(j.getNombre() + "\n");
						dos.flush();
					}
					t.mostrarTablero();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (menu == 3) {

				try {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db;
					db = dbf.newDocumentBuilder();
					Document doc = db.parse(new File("src/Jugadores.xml"));
					int cont = 0;
					boolean existe = false;
					int posicionJugadorGanador = 0;
					Element raiz = doc.getDocumentElement();
					NodeList nombres = raiz.getElementsByTagName("nombre");
					NodeList victoriastodosganadores = raiz.getElementsByTagName("victorias");
					while (cont < nombres.getLength()) {
						if (nombres.item(cont).getTextContent().equals(j.getNombre())) {
							existe = true;
							posicionJugadorGanador = cont;
						}
						cont++;
					}
					if (existe) {
						System.out.println("Tu numero de victorias es "
								+ victoriastodosganadores.item(posicionJugadorGanador).getTextContent());

					} else {
						System.out.println("Lo siento no has ganado nunca con este nombre");

					}
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (menu == 4) {

				try {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db;
					db = dbf.newDocumentBuilder();
					Document doc = db.parse(new File("src/Jugadores.xml"));
					int cont = 0;
					boolean existe = false;
					int posicionJugadorGanador = 0;
					Element raiz = doc.getDocumentElement();
					NodeList nombres = raiz.getElementsByTagName("nombre");
					NodeList victoriastodosganadores = raiz.getElementsByTagName("victorias");
					HashMap<String, Integer> Ranking = new HashMap<String, Integer>();
					while (cont < nombres.getLength()) {
						if (Ranking.containsKey(nombres.item(cont).getTextContent())) {
							Ranking.replace(nombres.item(cont).getTextContent(),
									Integer.parseInt(victoriastodosganadores.item(cont).getTextContent()));
						} else {
							Ranking.put(nombres.item(cont).getTextContent(),
									Integer.parseInt(victoriastodosganadores.item(cont).getTextContent()));
						}
						cont++;
					}
					 List<Map.Entry<String, Integer>> listaEntradas = new ArrayList<>(Ranking.entrySet());				
					 Collections.sort(listaEntradas, Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
					 	int x=0; 
				        for (Map.Entry<String, Integer> entry : listaEntradas) {
				            x++;
				        	System.out.println(x+"."+entry.getKey() + ":" + entry.getValue()+" victorias");
				            if(x==10) {
				            	break;
				            }
				        }				

				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
