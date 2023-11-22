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

						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						ObjectInputStream ois = new ObjectInputStream(s.getInputStream());) {
					dos.writeBytes(j.getNombre() + "\n");
					boolean rojo;
					rojo = dis.readBoolean();
					if (!rojo) {
						j.setEquiporojo(false);
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
					while (!t.getFinalizado()) {
						t.mostrarTablero();
						dos.writeBoolean(turno);
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
					if (turno) {
						System.out.println("Lo siento has perdido");
					} else {
						dos.writeChars(j.getNombre() + "\n");
						dos.flush();
					}
					t.mostrarTablero();
					oos.close();
					ois.close();

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
					Document doc = db.parse(new File("src/BD.xml"));
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
					Document doc = db.parse(new File("src/BD.xml"));
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

					Map.Entry<String, Integer>[] ordenaranking = Ranking.entrySet().toArray(new Map.Entry[0]);
					int x = 0;
					int y = 0;
					while (x < ordenaranking.length) {
						while (y+1 < ordenaranking.length) {

							if (ordenaranking[y].getValue() > ordenaranking[y + 1].getValue()) {
								Map.Entry<String, Integer> temp = ordenaranking[y];
								ordenaranking[y] = ordenaranking[y + 1];
								ordenaranking[y + 1] = temp;
							}
							y++;
						}
						y=0;
						x++;
					}
					Ranking.clear();
					for (Map.Entry<String, Integer> entrada : ordenaranking) {
						Ranking.put(entrada.getKey(), entrada.getValue());
					}
					x=0;
					
					
						for (String nombreRanking : Ranking.keySet()) {
							System.out.println(x+". "+nombreRanking+" con "+Ranking.get(nombreRanking)+" victorias");
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
