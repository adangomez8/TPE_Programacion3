package tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RedSubteBacktracking {

	private Grafo grafo;
	private int cantEstaciones;
	private List<String> visitados;
	private List<Tubo> recorrido;
	private List<Tubo> solucion;
	private int totalLargoTunel;

	public RedSubteBacktracking(Grafo grafo) {
		this.grafo = grafo;
		this.cantEstaciones = grafo.cantidadVertices();
		this.visitados = new ArrayList<>();
		this.recorrido = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.totalLargoTunel = -1;
	}

	private void setTotalLargoTunel(int totalLargoTunel) {
		this.totalLargoTunel = totalLargoTunel;
	}

	public List<Tubo> construirRedSubte() {

		for (Iterator<String> itEstaciones = grafo.obtenerVertices(); itEstaciones.hasNext();) {
			String estacion = itEstaciones.next();
			visitados.add(estacion);
			backtracking(estacion);
			visitados.remove(estacion);
		}

		return solucion;
	}

	private void backtracking(String primeraEstacion) {

		if (visitados.size() == cantEstaciones) {
			if(solucion.isEmpty()) {
				solucion = new ArrayList<>(recorrido);
			} else {
				if(calcularLargoTotalDeRecorrido(recorrido)<calcularLargoTotalDeRecorrido(solucion)) {
					solucion.clear();
					solucion = new ArrayList<>(recorrido);
					this.setTotalLargoTunel(this.calcularLargoTotalDeTunel());
				}
			}
		} else {
			/**
			 * PODA
			 */
			if (recorrido.isEmpty() || calcularLargoTotalDeRecorrido(recorrido) > totalLargoTunel) {

				Iterator<String> itAdy = grafo.obtenerAdyacentes(primeraEstacion);
				while (itAdy.hasNext()) {
					String ady = itAdy.next();
					if (!visitados.contains(ady)) {
						visitados.add(ady);
						Tubo tubo = grafo.obtenerTubo(primeraEstacion, ady);
						recorrido.add(tubo);
						backtracking(ady);
						visitados.remove(ady);
						recorrido.remove(recorrido.size() - 1);
					}
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

	public int calcularLargoTotalDeTunel() {

		return calcularLargoTotalDeRecorrido(solucion);
	}

	private int calcularLargoTotalDeRecorrido(List<Tubo> lista) {

		if (lista.size() == 0)
			return -1;
		else {
			int largo = 0;
			for (int i = 0; i < lista.size(); i++) {
				String estacion1 = lista.get(i).getEstacion1();
				String estacion2 = lista.get(i).getEstacion2();
				largo += grafo.getDistanciaTubo(estacion1, estacion2);
			}
			return largo;
		}

		
	}

	public List<Tubo> getSolucion() {
		return new ArrayList<Tubo>(this.solucion);
	}

	public int getTotalLargoTunel() {
		return totalLargoTunel;
	}

}
