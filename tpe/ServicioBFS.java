package Programacion3.src.tpe;

import java.util.*;

/**
 *
 *
 * Esta mal si agrego el "<T>" al nombre de la clase??
 *
 * */
public class ServicioBFS<T> {

	private Grafo<T> grafo;
	
	public ServicioBFS(Grafo<T> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> bfsForest() {
		// Resolver BFS
		return bfsForest(this.grafo);
	}

	private List<Integer> bfsForest(Grafo<T> grafo){

		ArrayList<Integer> noVisitados = new ArrayList<>();
		List<Integer> resultado = new ArrayList<>();

		for(Iterator<Integer> iteradorDeVertices = grafo.obtenerVertices(); iteradorDeVertices.hasNext();){
			int verticeAux = iteradorDeVertices.next();
			noVisitados.add(verticeAux);
		}

		while (!noVisitados.isEmpty()){
			int v = noVisitados.get(0);
			resultado.addAll(bfs(grafo, v, noVisitados));
		}

		return resultado;
	}

	private List<Integer> bfs(Grafo<T> grafo, Integer v, ArrayList<Integer> noVisitados) {

		List<Integer> visitados = new ArrayList<>();

		Queue<Integer> colaVertices = new LinkedList<>();
		colaVertices.offer(v);
		noVisitados.remove(v);

		while (!colaVertices.isEmpty()) {
			//Poll devuelve y elimina el primer elemento
			int aux = colaVertices.poll();
			visitados.add(aux);

			for (Iterator<Integer> itAdy = grafo.obtenerAdyacentes(aux); itAdy.hasNext(); ) {
				int adyAux = itAdy.next();
				if (noVisitados.contains(adyAux)) {
					colaVertices.offer(adyAux);
					int aBorrar = noVisitados.indexOf(adyAux);
					noVisitados.remove(aBorrar);
				}
			}
		}

		return visitados;
	}



}
