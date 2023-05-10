package Programacion3.src.tpe;
import java.util.*;
public class ServicioDFS<T> {
	private Grafo<T> grafo;
	public ServicioDFS(Grafo<T> grafo) {		this.grafo = grafo;	}
	public List<Integer> dfsForest() {

		return dfs(this.grafo);
	}
	private List<Integer> dfs(Grafo<T> grafo){


		Queue<Integer> colaVertices = new LinkedList<>(); //BLANCO
		List<Integer> resultado = new ArrayList<>();
		List<Integer> amarillo = new ArrayList<>(); //AMARILLO
		List<Arco<T>> arcos = new ArrayList<>();
		//TODOS BLANCOS
		for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext();) {
			colaVertices.add(it.next());
		}

		for(Integer verticeBlanco : colaVertices){
			amarillo.add(verticeBlanco);
			resultado.addAll(dfsVisit(grafo, verticeBlanco, amarillo, arcos));
		}
		return resultado;
	}
	private List<Integer> dfsVisit(Grafo<T> grafo, int vertice, List<Integer> amarillo, List<Arco<T>> arcos) {
		List<Integer> negro = new ArrayList<>(); //NEGRO
		amarillo.add(vertice);
		for (Iterator<Integer> it = grafo.obtenerAdyacentes(vertice); it.hasNext(); ) {
			int adyacenteDelVertice = it.next();
			//SI ES BLANCO
			if (!amarillo.contains(adyacenteDelVertice)) {
				dfsVisit(grafo, adyacenteDelVertice, amarillo, arcos);
			}			if(!arcos.contains(grafo.existeArco(vertice, adyacenteDelVertice))){
				arcos.add(grafo.obtenerArco(vertice, adyacenteDelVertice));
			} else{
				Iterator<Integer> iteradorAdyacentes = grafo.obtenerAdyacentes(adyacenteDelVertice);				while (iteradorAdyacentes.hasNext()) {					System.out.println("Hay ciclo " +"(" + vertice + ", " + iteradorAdyacentes.next() + ")" + " ");				}			}		}
			negro.add(vertice);
		return negro;
	}
}