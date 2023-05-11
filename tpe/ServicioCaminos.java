package Programacion3.src.tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

		 ArrayList res = new ArrayList<>();
		 Iterator<Integer> itAdy = grafo.obtenerAdyacentes(origen);
/**
 *
 *
 *
 * RECURSIVO*/

		 while (itAdy.hasNext()) {
			 int cont = 0;
			 ArrayList<Integer> caminos = new ArrayList<>();
			 ArrayList<Arco> camins = new ArrayList<>();

			 caminos.add(origen);

			 while (cont < lim) {
				 caminos.add(itAdy.next());
				 itAdy = grafo.obtenerAdyacentes(itAdy.next());
				 cont++;

			 }
			 if (caminos.size() == lim) {
				 res.add(caminos);
			 }
		 }

		return res;
	}

}
