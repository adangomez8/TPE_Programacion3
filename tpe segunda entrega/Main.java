package tpe;

public class Main {

	public static void main(String[] args) {

		Grafo grafo = new Grafo();
		
		grafo.agregarVertice("Rauch");
		grafo.agregarVertice("Tandil");
		grafo.agregarVertice("Balcarce");		
		
		grafo.agregarVertice("Ayacucho");
		grafo.agregarVertice("Mar del Plata");
		
		grafo.addTubo("Rauch", "Tandil", 76);
		grafo.addTubo("Rauch", "Balcarce", 170);
		grafo.addTubo("Tandil", "Balcarce", 105);
		
		grafo.addTubo("Rauch", "Ayacucho", 71);
		grafo.addTubo("Rauch", "Mar del Plata", 220);
		grafo.addTubo("Tandil", "Ayacucho", 77);
		grafo.addTubo("Tandil", "Mar del Plata", 160);
		grafo.addTubo("Ayacucho", "Balcarce", 96);
		grafo.addTubo("Ayacucho", "Mar del Plata", 150);
		grafo.addTubo("Balcarce", "Mar del Plata", 74);
		
		RedSubteBacktracking redSubteBacktracking = new RedSubteBacktracking(grafo);
		redSubteBacktracking.construirRedSubte();
		/*
		System.out.println("Backtracking");
		for(Tubo tubo : redSubteBacktracking.getSolucion()) {
			System.out.println(tubo);
		}
		System.out.println("Distancia total: " + redSubteBacktracking.getTotalLargoTunel());
		*/
		System.out.println();
		RedSubteGreedy redSubteGreedy = new RedSubteGreedy(grafo);
		redSubteGreedy.construirRedSubte();

		System.out.println("Greedy");
		for(Tubo tubo : redSubteGreedy.getSolucion()) {
			System.out.println(tubo);
		}
		System.out.println("Distancia total: " + redSubteGreedy.getTotalLargoTunel());

	}

}
