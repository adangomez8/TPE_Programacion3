package Programacion3.src.tpe;

import java.util.*;

public class ServicioBFS {

	private Grafo<?> grafo;

	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}

	public List<Integer> bfsForest() {
		// Resolver BFS
		return bfsForest(this.grafo);
	}

	/**
	 * Complejidad: O(|V|+|A|) debido a que pasa una vez por cada vértice, y como mucho una vez cada arco
	 * */

	private List<Integer> bfsForest(Grafo<?> grafo){

		//HASHSET
		HashSet<Integer> noVisitados = new HashSet<>();
		List<Integer> resultado = new ArrayList<>();

		for(Iterator<Integer> iteradorDeVertices = grafo.obtenerVertices(); iteradorDeVertices.hasNext();){
			Integer verticeAux = iteradorDeVertices.next();
			noVisitados.add(verticeAux);
		}

		while (!noVisitados.isEmpty()){
			Integer v = noVisitados.iterator().next();
			resultado.addAll(bfs(grafo, v, noVisitados));
		}

		return resultado;
	}

	private List<Integer> bfs(Grafo<?> grafo, Integer v, HashSet<Integer> noVisitados) {

		List<Integer> visitados = new ArrayList<>();

		Queue<Integer> colaVertices = new LinkedList<>();
		colaVertices.offer(v);
		noVisitados.remove(v);

		while (!colaVertices.isEmpty()) {
			//Poll devuelve y elimina el primer elemento
			Integer aux = colaVertices.poll();
			visitados.add(aux);

			for (Iterator<Integer> itAdy = grafo.obtenerAdyacentes(aux); itAdy.hasNext(); ) {
				Integer adyAux = itAdy.next();
				if (noVisitados.contains(adyAux)) {
					colaVertices.offer(adyAux);
					noVisitados.remove(adyAux);
				}
			}
		}

		return visitados;
	}
}
