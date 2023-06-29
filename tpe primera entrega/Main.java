package Programacion3.src.tpe;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        GrafoDirigido gd = new GrafoDirigido<>();

        gd.agregarVertice(3);
        gd.agregarVertice(5);
        gd.agregarVertice(7);
        gd.agregarVertice(4);
        gd.agregarVertice(15);
        gd.agregarVertice(18);
        gd.agregarVertice(10);

        gd.agregarArco(3, 5, null);
        gd.agregarArco(5, 15, null);
        gd.agregarArco(5, 10, null);
        gd.agregarArco(15, 10, null);
        gd.agregarArco(3, 7, null);
        gd.agregarArco(7, 18, null);
        gd.agregarArco(18, 10, null);
        gd.agregarArco(3, 4, null);
        gd.agregarArco(4, 7, null);
        gd.agregarArco(3, 10, null);
        gd.agregarArco(7, 3, null);

        System.out.print("Arcos del vértice 3: ");
        Iterator<Arco<String>> iteradorArcosDelVertice3 = gd.obtenerArcos(3);
        while (iteradorArcosDelVertice3.hasNext()) {
            System.out.print(iteradorArcosDelVertice3.next() + " ");
        }
        System.out.println();

        gd.borrarVertice(5);

        System.out.print("Arcos del vértice 3: ");
        Iterator<Arco<String>> iteradorArcosDelVertice3DespuesDeSerBorrado = gd.obtenerArcos(3);
        while (iteradorArcosDelVertice3DespuesDeSerBorrado.hasNext()) {
            System.out.print(iteradorArcosDelVertice3DespuesDeSerBorrado.next() + " ");
        }

        /*gd.agregarVertice(16);
        gd.agregarVertice(34);
        gd.agregarVertice(52);
        gd.agregarVertice(7);

        gd.agregarArco(16, 7, "pe");
        gd.agregarArco(34, 7, "se");
        gd.agregarArco(34, 16, "te");
        gd.agregarArco(52, 16, "ce");*/

       /* System.out.println("Contiene vértice 34: " + gd.contieneVertice( 34));
        System.out.println("Contiene vértice 61: " + gd.contieneVertice(61));
        System.out.println("Existe arco (16, 7) :" + gd.existeArco(16, 7));
        System.out.println("Existe arco (7, 16) :" + gd.existeArco(7, 16));
        System.out.println("Arco de origen 52 y destino 16: " + gd.obtenerArco(52,16));
        System.out.println("Arco de origen 52 y destino 7: " + gd.obtenerArco(16,52));
        System.out.println("Cantidad de vértices: " + gd.cantidadVertices());
        System.out.println("Cantidad de arcos: " + gd.cantidadArcos());

        System.out.print("Vértices del grafo: ");
        Iterator<Integer> iteradorTodosLosVertices = gd.obtenerVertices();
        while (iteradorTodosLosVertices.hasNext()) {
            System.out.print(iteradorTodosLosVertices.next() + " ");
        }
        System.out.println();

        System.out.print("Adyacentes del vértice 34: ");
        Iterator<Integer> iteradorAdyacentes34 = gd.obtenerAdyacentes(34);
        while (iteradorAdyacentes34.hasNext()) {
            System.out.print(iteradorAdyacentes34.next() + " ");
        }
        System.out.println();

        System.out.println("Adyacentes del vértice 7: ");
        Iterator<Integer> iteradorAdyacentes7 = gd.obtenerAdyacentes(7);
        while (iteradorAdyacentes7.hasNext()) {
            System.out.print(iteradorAdyacentes7.next() + " ");
        }

        System.out.println("Cantidad de arcos del grafo: " + gd.cantidadArcos());

        System.out.print("Arcos del vértice 34: ");
        Iterator<Arco<String>> iteradorArcosDelVertice34 = gd.obtenerArcos(34);
        while (iteradorArcosDelVertice34.hasNext()) {
            System.out.print(iteradorArcosDelVertice34.next() + " ");
        }
        System.out.println();

        System.out.println("Arcos del vértice 7: ");
        Iterator<Arco<String>> iteradorArcosDelVertice7 = gd.obtenerArcos(7);
        while (iteradorArcosDelVertice7.hasNext()) {
            System.out.print(iteradorArcosDelVertice7.next() + " ");
        }
        System.out.println();

        /**
         *
         * SE ELIMINA UN VERTICE
         *
         * */

        /*gd.borrarVertice(34);
        System.out.println("Contiene vértice 16: " + gd.contieneVertice( 16));
        System.out.println("Contiene vértice 34: " + gd.contieneVertice(34));
        System.out.println("Existe arco (16, 7) :" + gd.existeArco(16, 7));
        System.out.println("Existe arco (7, 16) :" + gd.existeArco(7, 16));
        System.out.println("Arco de origen 52 y destino 16: " + gd.obtenerArco(52,16));
        System.out.println("Arco de origen 52 y destino 7: " + gd.obtenerArco(16,52));
        System.out.println("Cantidad de vértices: " + gd.cantidadVertices());
        System.out.println("Cantidad de arcos: " + gd.cantidadArcos());

        System.out.print("Vértices del grafo: ");
        Iterator<Integer> iteradorTodosLosVertices_a = gd.obtenerVertices();
        while (iteradorTodosLosVertices_a.hasNext()) {
            System.out.print(iteradorTodosLosVertices_a.next() + " ");
        }
        System.out.println();

        System.out.print("Adyacentes del vértice 34: ");
        Iterator<Integer> iteradorAdyacentes34_a = gd.obtenerAdyacentes(34);
        while (iteradorAdyacentes34_a.hasNext()) {
            System.out.print(iteradorAdyacentes34_a.next() + " ");
        }
        System.out.println();

        System.out.println("Adyacentes del vértice 7: ");
        Iterator<Integer> iteradorAdyacentes7_a = gd.obtenerAdyacentes(7);
        while (iteradorAdyacentes7_a.hasNext()) {
            System.out.print(iteradorAdyacentes7_a.next() + " ");
        }

        System.out.println("Cantidad de arcos del grafo: " + gd.cantidadArcos());

        System.out.print("Arcos del vértice 34: ");
        Iterator<Arco<String>> iteradorArcosDelVertice34_a = gd.obtenerArcos(34);
        while (iteradorArcosDelVertice34_a.hasNext()) {
            System.out.print(iteradorArcosDelVertice34_a.next() + " ");
        }
        System.out.println();

        System.out.println("Arcos del vértice 7: ");
        Iterator<Arco<String>> iteradorArcosDelVertice7_a = gd.obtenerArcos(7);
        while (iteradorArcosDelVertice7_a.hasNext()) {
            System.out.print(iteradorArcosDelVertice7_a.next() + " ");
        }
        System.out.println();*/


        /*ServicioDFS dfs = new ServicioDFS(gd);
        System.out.println("Servicio Depth First Search: " + dfs.dfsForest());

        ServicioBFS bfs = new ServicioBFS(gd);
        System.out.println("Servicio Breadth First Search: " + bfs.bfsForest());*/



        /*ServicioCaminos sc = new ServicioCaminos(gd, 3, 5, 3);

        System.out.println(sc.caminos());*/
    }
}
