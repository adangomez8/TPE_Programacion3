package Programacion3.src.tpe;


import java.util.*;

public class ServicioDFS<T> {

	private Grafo<T> grafo;

	public ServicioDFS(Grafo<T> grafo) {
		this.grafo = grafo;
	}

	public List<Integer> dfsForest() {


		return dfs(this.grafo);

	}
	/**
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 * */

	/*private List<Integer> dfs(Grafo<T> grafo){

		Queue<Integer> colaVertices = new LinkedList<>(); //BLANCO

		List<Integer> resultado = new ArrayList<>();
		List<Integer> amarillo = new ArrayList<>(); //AMARILLO

		//TODOS BLANCOS
		for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext();) {
			colaVertices.add(it.next());
		}

		for(Integer verticeBlanco : colaVertices){
			amarillo.add(verticeBlanco);
			resultado.addAll(dfsVisit(grafo, verticeBlanco, amarillo));
		}

		List<Arco<T>> arcosRecorridos = new ArrayList<>();
		for(Integer verticeBlanco : colaVertices){
			for(Iterator<Arco<T>> it = grafo.obtenerArcos(verticeBlanco); it.hasNext();) {
				Arco<T> arcoAux = it.next();
				int destinoArcoAux = arcoAux.getVerticeDestino();
				if(!arcosRecorridos.contains((grafo.obtenerArco(verticeBlanco, destinoArcoAux)))) {
					arcosRecorridos.add(grafo.obtenerArco(verticeBlanco, destinoArcoAux));
				} else{
					System.out.println("Hay ciclo");
				}
			}
		}
		for(Arco<T> ar :arcosRecorridos){
			System.out.println("(" + ar.getVerticeOrigen() + ", " + ar.getVerticeDestino() + ")");
		}
		return resultado;
	}*/
	private List<Integer> dfs(Grafo<T> grafo){

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

	private List<Integer> dfsVisit(Grafo<T> grafo, int vertice, List<Integer> visitados) {

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