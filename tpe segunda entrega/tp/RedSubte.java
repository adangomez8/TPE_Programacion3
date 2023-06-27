package tp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RedSubte {

	private Grafo grafo;
	private int cantEstaciones;
	private List<String> visitados;
	private List<Tubo> recorridoCompleto;
	private List<Tubo> solucion;
	private int totalLargoTunel;

	public RedSubte(Grafo grafo) {
		this.grafo = grafo;
		this.cantEstaciones = grafo.cantidadVertices();
		this.visitados = new ArrayList<>();
		this.recorridoCompleto = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.totalLargoTunel = -1;
	}

	public List<Tubo> construirRedSubte() {

		for (Iterator<String> itEstaciones = grafo.obtenerVertices(); itEstaciones.hasNext();) {
			String estacion = itEstaciones.next();
			visitados.add(estacion);
			backtracking(estacion);
			visitados.remove(estacion);
		}

		return solucion;
	}

	private void backtracking(String primeraEstacion) {

		if (recorridoCompleto.size() == cantEstaciones) {

			if(seRecorrieronTodLosVertices()){
				//borrarTubosRepetidos();
				if(solucion.isEmpty()) {
					solucion=new ArrayList<>(generarRecorrido());
					this.setTotalLargoTunel(this.calcularLargoTotalDeTunel());
				}
				else {
					if(calcularLargoTotalDeRecorrido(generarRecorrido())<calcularLargoTotalDeRecorrido(solucion)) {
						solucion.clear();
						solucion=new ArrayList<>(generarRecorrido());
						this.setTotalLargoTunel(this.calcularLargoTotalDeTunel());

					}
				}
			}

	} else {
			/**
			 * PODA
			 */
			if (visitados.size()!=cantEstaciones+1 || calcularLargoTotalDeRecorrido(recorridoCompleto) > totalLargoTunel) {

				Iterator<String> itAdy = grafo.obtenerAdyacentes(primeraEstacion);
				while (itAdy.hasNext()) {
					String ady = itAdy.next();
					Tubo tubo = grafo.obtenerTubo(primeraEstacion, ady);
					visitados.add(ady);
					recorridoCompleto.add(tubo);
					backtracking(ady);
					visitados.remove(ady);
					recorridoCompleto.remove(recorridoCompleto.size() - 1);

				}
			}
		}

	}

	private ArrayList<Tubo> generarRecorrido() {
		ArrayList<Tubo> tubos = new ArrayList<>();
		for (Tubo tubo : recorridoCompleto) {
			String e1 = tubo.getEstacion1();
			String e2 = tubo.getEstacion2();
			if (!tubos.contains(grafo.obtenerTubo(e1, e2)) && !tubos.contains(grafo.obtenerTubo(e2, e1))) {
				tubos.add(tubo);
			}
		}
		return tubos;
	}

	private boolean seRecorrieronTodLosVertices() {

		ArrayList<String> verices = new ArrayList<>();
		for(Tubo tubo : recorridoCompleto){
			if(!verices.contains(tubo.getEstacion1())){
				verices.add(tubo.getEstacion1());
			}
			if(!verices.contains(tubo.getEstacion2())){
				verices.add(tubo.getEstacion2());
			}
		}

		if(verices.size()==cantEstaciones){
			return true;
		}

		return false;
	}

	private int calcularLargoTotalDeTunel() {

		return calcularLargoTotalDeRecorrido(solucion);
	}

	private int calcularLargoTotalDeRecorrido(List<Tubo> lista) {
		int largo = 0;

		for (int i = 0; i < lista.size(); i++) {
			String estacion1 = lista.get(i).getEstacion1();
			String estacion2 = lista.get(i).getEstacion2();
			largo += grafo.getDistanciaTubo(estacion1, estacion2);
		}

		return largo;
	}

	public List<Tubo> getSolucion() {
		return new ArrayList<Tubo>(this.solucion);
	}

	public int getTotalLargoTunel() {
		return totalLargoTunel;
	}

	public void setTotalLargoTunel(int totalLargoTunel) {
		this.totalLargoTunel = totalLargoTunel;
	}

	private ArrayList<String>obtenerVertices(){
		ArrayList<String>vertice=new ArrayList<>();
		Iterator<String>vertices=grafo.obtenerVertices();
		while(vertices.hasNext()) {
			vertice.add(vertices.next());
		}
		return vertice;
	}
}