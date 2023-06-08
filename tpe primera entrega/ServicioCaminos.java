package Programacion3.src.tpe;

import java.util.*;

public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	public List<List<Integer>> caminos() {

		return caminos(grafo, origen, destino, lim);
	}

	/**
	 * Complejidad: O(|V|+|A|) debido a que pasa una vez por cada vértice, y como mucho una vez cada arco
	 * */

	private List<List<Integer>> caminos(Grafo<?> grafo, int origen, int destino, int lim) {
		List<List<Integer>> resultados = new ArrayList<>();
		List<Integer> caminoActual = new ArrayList<>();
		Set<String> arcosVisitados = new HashSet<>();

		caminoActual.add(origen);

		caminosRecursivo(grafo, origen, destino, caminoActual, arcosVisitados, resultados, lim);

		return resultados;
	}

	private void caminosRecursivo(Grafo<?> grafo, int origen, int destino, List<Integer> caminoActual,  Set<String> arcosVisitados, List<List<Integer>> resultados, int lim) {
		if (origen == destino) {
			resultados.add(new ArrayList<>(caminoActual));
		} else {
			if (arcosVisitados.size() < lim) {
				for (Iterator<Integer> it = grafo.obtenerAdyacentes(origen); it.hasNext(); ) {
					Integer adyacente = it.next();
					Arco arco = grafo.obtenerArco(origen, adyacente);
					if (arco != null && !arcosVisitados.contains(arco.getVerticeOrigen()+"-"+arco.getVerticeDestino())) {
						arcosVisitados.add(arco.getVerticeOrigen()+ "-" +arco.getVerticeDestino());
						caminoActual.add(adyacente);
						caminosRecursivo(grafo, adyacente, destino, caminoActual, arcosVisitados, resultados, lim);
						caminoActual.remove(caminoActual.size() - 1);
						arcosVisitados.remove(arco.getVerticeOrigen()+ "-" +arco.getVerticeDestino());
					}
				}
			}
		}
	}
}
