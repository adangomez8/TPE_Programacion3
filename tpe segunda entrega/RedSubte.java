package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RedSubte {

	private Grafo grafo;
	private int cantEstaciones;
	private List<String> visitados;
	private List<Tubo> recorrido;
	private List<Tubo> solucion;
	private int totalLargoTunel;

	public RedSubte(HashMap<Integer, String> nombreEstaciones) {
		this.cantEstaciones = 0;
		for (Iterator<String> itAdy = grafo.obtenerVertices(); itAdy.hasNext();) {
			this.cantEstaciones++;
		}
		this.visitados = new ArrayList<>();
		this.recorrido = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.totalLargoTunel = -1;
	}

	public List<Tubo> construirRedSubte() {

		return construirRedSubte(this.grafo);
	}

	private List<Tubo> construirRedSubte(Grafo grafo) {

		Iterator<String> itEstaciones = grafo.obtenerVertices();
		String estacion = itEstaciones.next();

		Iterator<String> itAdy = grafo.obtenerAdyacentes(estacion);
		String adyacente = itAdy.next();

		while (itEstaciones.hasNext()) {
			visitados.add(estacion);
			backtracking(grafo, estacion, adyacente);
			visitados.remove(estacion);
		}
		
		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		borrarArcosInnecesarios();
		
		

		return solucion;
	}

	private void backtracking(Grafo grafo, String PrimeraEstation, String siguienteEstacion) {

		if (recorrido.size() == cantEstaciones-1) {
			int largoTunel = calcularLargoTotalDeTunel(grafo);
			if (totalLargoTunel == -1 || largoTunel < totalLargoTunel) {
				totalLargoTunel = largoTunel;
				solucion = new ArrayList<>(recorrido);
			}
		} else {

			visitados.add(siguienteEstacion);
			Tubo tubo = grafo.obtenerTubo(PrimeraEstation, siguienteEstacion);
			recorrido.add(tubo);
			Iterator<String> itAdy = grafo.obtenerAdyacentes(siguienteEstacion);

			while (itAdy.hasNext()) {
				if (!recorrido.contains(tubo) && !visitados.contains(itAdy.next())) {
					backtracking(grafo, PrimeraEstation, itAdy.next());
					visitados.remove(siguienteEstacion);
					recorrido.remove(recorrido.size() - 1);
				}
			}

		}
	}
	
	private void borrarArcosInnecesarios() {
		
		Iterator<String> vertices = grafo.obtenerVertices();
		
		
		while(vertices.hasNext()) {
			Iterator<String> ady = grafo.obtenerAdyacentes(vertices.next());
			while(ady.hasNext()) {
				if(!solucion.contains(grafo.obtenerTubo(vertices.next(), ady.next()))) {
					grafo.borrarTubo(vertices.next(), ady.next());
				}
			}
		}
	}

	public int calcularLargoTotalDeTunel(Grafo grafo) {
		int largo = 0;

		for (int i = 0; i < cantEstaciones - 1; i++) {
			String estacion1 = recorrido.get(i).getEstacion1();
			String estacion2 = recorrido.get(i).getEstacion2();
			largo += grafo.getDistanciaTubo(estacion1, estacion2);
		}

		return largo;
	}

}
