package Programacion3.src.tpe;
import java.util.*;
public class ServicioDFS {
	private Grafo<?> grafo;
	public ServicioDFS(Grafo<?> grafo) {		this.grafo = grafo;	}

	public List<Integer> dfsForest() {

		return dfs(this.grafo);
	}
	private List<Integer> dfs(Grafo<?> grafo){

		List<Integer> resultado = new ArrayList<>();

		Queue<Integer> verticesPendientes = new LinkedList<>(); //BLANCO
		List<Integer> visitados = new ArrayList<>(); //AMARILLO

		//TODOS BLANCOS
		for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext();) {
			verticesPendientes.add(it.next());
		}

		for(Integer verticeBlanco : verticesPendientes){
			visitados.add(verticeBlanco);
			resultado.addAll(dfsVisit(grafo, verticeBlanco, visitados));

		}

		return resultado;
	}

	private List<Integer> dfsVisit(Grafo<?> grafo, int vertice, List<Integer> visitados) {

		List<Integer> recorridos = new ArrayList<>(); //NEGRO

		visitados.add(vertice);

		for (Iterator<Integer> it = grafo.obtenerAdyacentes(vertice); it.hasNext(); ) {

			int adyacenteDelVertice = it.next();

			//SI ES BLANCO
			if (!visitados.contains(adyacenteDelVertice)) {
				List<Integer> subarbol = dfsVisit(grafo, adyacenteDelVertice, visitados);
				recorridos.addAll(subarbol);
			}
		}

		recorridos.add(vertice);
		return recorridos;
	}
}