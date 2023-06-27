package tp;


import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		String path = "Dataset/dataset2.txt";
		CSVReader reader = new CSVReader(path);

		Grafo grafo = new Grafo();
		
		reader.read(grafo);

		System.out.println("Backtracking");
		RedSubte redSubte = new RedSubte(grafo);
		redSubte.construirRedSubte();
		
		for(Tubo tubo : redSubte.getSolucion()) {
			System.out.println(tubo);
		}
		System.out.println(redSubte.getTotalLargoTunel());

		System.out.println();

		System.out.println("Greedy");
		RedSubteGreedy redSubteGreedy = new RedSubteGreedy(grafo);
		redSubteGreedy.construirRedSubte();

		for(Tubo tubo : redSubteGreedy.getSolucion()) {
			System.out.println(tubo);
		}
		System.out.println(redSubteGreedy.getTotalLargoTunel());
		
		
	}

}
