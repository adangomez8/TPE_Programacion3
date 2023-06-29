package Programacion3.src.tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;


public class ServicioDFS {

	private Grafo<?> grafo;
	private ArrayList<Integer>marcado;
	private HashMap<Integer,String>color;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		marcado= new ArrayList<>();
		color= new HashMap<>();
	}

	public List<Integer> dfsForest() {
		Iterator<Integer>iterVert= grafo.obtenerVertices();

		while(iterVert.hasNext()) {
			int v=iterVert.next();
			color.put(v, "BLANCO");
		}

		for (Integer v : color.keySet()) {
			if(color.get(v).equals("BLANCO")) {
				dfsVisit(v);
			}
		}

		return marcado;
	}

	private ArrayList<Integer> dfsVisit(int v) {
		color.put(v, "AMARILLO");
		marcado.add(v);

		Iterator<Integer>iterAdya= grafo.obtenerAdyacentes(v);

		while(iterAdya.hasNext()) {
			int ver=iterAdya.next();
			if(!marcado.contains(ver)) {
				if(color.get(ver).equals("BLANCO")) {
					dfsVisit(ver);
				}
				else {
					if(color.get(ver).equals("AMARILLO")) {
						System.out.println("There is a cicle");
					}
				}
			}
		}
		color.put(v, "NEGRO");
		return marcado;
	}

}