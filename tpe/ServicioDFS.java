package Programacion3.src.tpe;
import java.util.*;
public class ServicioDFS {
	private Grafo<?> grafo;
	public ServicioDFS(Grafo grafo) {
		this.grafo = grafo;
	}
	public List<Integer> dfsForest() {

		return dfs(this.grafo);
	}

	/**
	 * Complejidad: O(|V|+|A|) debido a que pasa una vez por cada vértice, y como mucho una vez cada arco
	 * */
	public List<Integer> dfs(Grafo<?> grafo) {
		Set<Integer> visitados = new HashSet<>();//AMARILLO
		List<Integer> resultado = new ArrayList<>();//NEGRO

		for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext(); ) {
			Integer vertice = it.next();
			if (!visitados.contains(vertice)) {
				dfsVisit(grafo, vertice, visitados, resultado);
			}
		}
		Collections.reverse(resultado); // Invertir el orden para obtener el orden topológico
		return resultado;
	}

	private void dfsVisit(Grafo<?> grafo, Integer vertice, Set<Integer> visitados, List<Integer> resultado) {

		visitados.add(vertice);

		for (Iterator<Integer> it = grafo.obtenerAdyacentes(vertice); it.hasNext(); ) {
			Integer adyacenteDelVertice = it.next();
			if (!visitados.contains(adyacenteDelVertice)) {
				dfsVisit(grafo, adyacenteDelVertice, visitados, resultado);
			}
		}
		resultado.add(vertice);
	}
}