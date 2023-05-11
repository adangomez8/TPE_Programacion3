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

	public List<List<Integer>> caminos(Grafo<?> grafo, int origen, int destino, int lim) {
		List<List<Integer>> resultados = new ArrayList<>();
		List<Integer> caminoActual = new ArrayList<>();
		Set<Integer> nodosVisitados = new HashSet<>();
		List<Arco> arcosVisitados = new ArrayList<>();

		caminoActual.add(origen);
		nodosVisitados.add(origen);

		caminosRecursivo(grafo, origen, destino, caminoActual, nodosVisitados, arcosVisitados, resultados, lim);

		return resultados;
	}

	private void caminosRecursivo(Grafo<?> grafo, int origen, int destino, List<Integer> caminoActual, Set<Integer> nodosVisitados, List<Arco> arcosVisitados, List<List<Integer>> resultados, int lim) {
		if (origen == destino) {
			resultados.add(new ArrayList<>(caminoActual));
		} else {
			if (arcosVisitados.size() < lim) {
				for (Iterator<Integer> it = grafo.obtenerAdyacentes(origen); it.hasNext(); ) {
					Integer adyacente = it.next();
					Arco arco = grafo.obtenerArco(origen, adyacente);
					if (arco != null && !arcosVisitados.contains(arco)) {
						arcosVisitados.add(arco);
						if (!nodosVisitados.contains(adyacente)) {
							caminoActual.add(adyacente);
							nodosVisitados.add(adyacente);
							caminosRecursivo(grafo, adyacente, destino, caminoActual, nodosVisitados, arcosVisitados, resultados, lim);
							nodosVisitados.removeAll(nodosVisitados);
							caminoActual.remove(caminoActual.size() - 1);
						}
						arcosVisitados.remove(arco);
					}
				}
			}
		}
	}
}
