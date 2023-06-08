package Programacion3.src.tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, ArrayList<Arco<T>>> grafo;

	public GrafoDirigido(){
		this.grafo = new HashMap<>();
	}

	/**
	 * Complejidad: O(1) porque es independiente a la cantidad de vértices que tenga el grafo
	 * */
	@Override
	public void agregarVertice(int verticeId) {
		if(!grafo.containsKey(verticeId)) {
			grafo.put(verticeId, new ArrayList<Arco<T>>());
		}
	}

	/**
	 * Complejidad: O(1) porque es independiente a la cantidad de vértices que tenga el grafo
	 * */
	@Override
	public void borrarVertice(int verticeId) {

			grafo.remove(verticeId);
	}

	/**
	 * Complejidad: O(1) debido que se accede a los valores independientemente la cantidad de vértices
	 * que tenga el grafo
	 * */
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		crearArco(verticeId1, verticeId2, etiqueta);
	}

	private void crearArco(int verticeId1, int verticeId2, T etiqueta) {
		if(grafo.containsKey(verticeId1)  && grafo.containsKey(verticeId2)) {
			Arco<T> newArco = new Arco<>(verticeId1, verticeId2, etiqueta);
			grafo.get(verticeId1).add(newArco);
		}
	}


	/**
	 * Complejidad: O(n) donde n es la cantidad de adyacentes que posee el vértice
	 * */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {

		if (grafo.containsKey(verticeId1)) {

			ArrayList<Arco<T>> listAux = grafo.get(verticeId1);
			boolean arcoBorrado = false;

			while (!arcoBorrado){

			if (listAux != null && !listAux.isEmpty()) {
				for (int i = 0; i < listAux.size(); i++) {
					int destinoAux = listAux.get(i).getVerticeDestino();
					if (destinoAux == verticeId2) {
						listAux.remove(i);
						arcoBorrado = true;
					}
				}
			}
			}
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {

		return grafo.containsKey(verticeId);
	}
	/**
	 * Complejidad: O(n) donde n es la cantidad de arcos debido a que itera por cada uno de ellos para
	 * confirmar si contiene la información pasada por parámetro
	 * */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {

		for (Map.Entry<Integer, ArrayList<Arco<T>>> entry : grafo.entrySet()) {
			if(entry.getKey()==verticeId1){
				ArrayList<Arco<T>> listAux = grafo.get(entry.getKey());
				for(Arco<T> arco : listAux){
					if(arco.getVerticeDestino() == verticeId2){
						return true;
					}
				}
			}
		}

		return false;
	}


	/**
	 * Complejidad: O(n) donde n es la cantidad de arcos que tiene el vértice
	 * */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {

		ArrayList<Arco<T>> resultado = grafo.get(verticeId1);

		if(resultado != null && !resultado.isEmpty()) {
			for (Arco<T> arco : resultado) {
				if (arco.getVerticeDestino() == verticeId2) {

					return arco;
				}
			}

		}
		return null;
	}


	/**
	 * Complejidad: O(1) debido que se accede a los valores independientemente del tamaño del mapa
	 * */
	@Override
	public int cantidadVertices() {

		return grafo.size();
	}

	/**
	 * Complejidad: O(n) donde n ya que itera los adyacentes de cada vertice
	 * */
	@Override
	public int cantidadArcos() {

		int totalArcos = 0;
		for (Map.Entry<Integer, ArrayList<Arco<T>>> entry : grafo.entrySet()) {
			ArrayList<Arco<T>> listAux = entry.getValue();
			totalArcos += listAux.size();
		}
		return totalArcos;
	}

	/**
	 * Complejidad: O(n) donde n es la cantidad de vertices del grafo debido a que itera por cada uno de ellos
	 * */
	@Override
	public Iterator<Integer> obtenerVertices() {

		return grafo.keySet().iterator();
	}


	/**
	 * Complejidad: O(n) donde n es la cantidad de adyacentes debido a que itera por cada uno de ellos
	 * */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {

		ArrayList<Arco<T>> arcos = grafo.get(verticeId);
		ArrayList<Integer> adyacentes = new ArrayList<Integer>();
		if (arcos != null) {
			for (Arco<T> arco : arcos) {
				adyacentes.add(arco.getVerticeDestino());
			}
		}
		return adyacentes.iterator();
	}

	/**
	 * Complejidad: O(n) debido a que itera los adyacentes de cada vértice
	 * */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {

		ArrayList<Arco<T>> arcos = new ArrayList<>();
		for(Map.Entry<Integer, ArrayList<Arco<T>>> entry : grafo.entrySet()){
			arcos.addAll(entry.getValue());
		}
		return arcos.iterator();
	}

	/**
	 * Complejidad: O(n) debido a que itera los adyacentes de cada vértice
	 * */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {

		ArrayList<Arco<T>> arcos = new ArrayList<>();
		for(Map.Entry<Integer, ArrayList<Arco<T>>> entry : grafo.entrySet()){
			if(entry.getKey()==verticeId) {
				arcos.addAll(entry.getValue());
			}
		}
		return arcos.iterator();
	}

}
