package tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RedSubte {

	private Grafo grafo;
	private int cantEstaciones;
	private List<String> visitados;
	private List<Tubo> recorrido;
	private List<Tubo> solucion;
	private int totalLargoTunel;

	public RedSubte(Grafo grafo) {
		this.grafo = grafo;
		this.cantEstaciones = grafo.cantidadVertices();
		this.visitados = new ArrayList<>();
		this.recorrido = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.totalLargoTunel = -1;
	}

	public List<Tubo> construirRedSubte() {

		/*Iterator<String> itEstaciones = grafo.obtenerVertices();
		String estacion = itEstaciones.next();*/

		/*Iterator<String> itAdy = grafo.obtenerAdyacentes(estacion);
		String adyacente = itAdy.next();*/

		for (Iterator<String> itEstaciones = grafo.obtenerVertices(); itEstaciones.hasNext();) {
			String estacion = itEstaciones.next();
			Iterator<String> itAdy = grafo.obtenerAdyacentes(estacion);
			String adyacente = itAdy.next();
			visitados.add(estacion);
			backtracking(estacion, adyacente);
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

	private void backtracking(String primeraEstacion, String siguienteEstacion) {

		if (visitados.size() == cantEstaciones && !primeraEstacion.equals(siguienteEstacion)) {
			int largoTunel = calcularLargoTotalDeTunel();
			if (totalLargoTunel == -1 || largoTunel < totalLargoTunel) {
				totalLargoTunel = largoTunel;
				solucion = new ArrayList<>(recorrido);
			}
		} else {

			Iterator<String> itAdy = grafo.obtenerAdyacentes(siguienteEstacion);
			while (itAdy.hasNext()) {
				String ady = itAdy.next();
				if (!visitados.contains(ady)) {
					visitados.add(ady);
					Tubo tubo = grafo.obtenerTubo(primeraEstacion, ady);
					recorrido.add(tubo);
					backtracking(primeraEstacion, ady);
					visitados.remove(ady);
					recorrido.remove(recorrido.size() - 1);
				}
			}

		}
	}

	private void borrarArcosInnecesarios() {

		Iterator<String> vertices = grafo.obtenerVertices();

		while (vertices.hasNext()) {
			Iterator<String> ady = grafo.obtenerAdyacentes(vertices.next());
			while (ady.hasNext()) {
				if (!solucion.contains(grafo.obtenerTubo(vertices.next(), ady.next()))) {
					grafo.borrarTubo(vertices.next(), ady.next());
				}
			}
		}
	}

	private int calcularLargoTotalDeTunel() {
		int largo = 0;

		for (int i = 0; i < cantEstaciones - 1; i++) {
			String estacion1 = recorrido.get(i).getEstacion1();
			String estacion2 = recorrido.get(i).getEstacion2();
			largo += grafo.getDistanciaTubo(estacion1, estacion2);
		}

		return largo;
	}

	public List<Tubo> getSolucion() {
		return new ArrayList<Tubo>(this.solucion);
	}

	public int getTotalLargoTunel() {
		return totalLargoTunel;
	}

}
