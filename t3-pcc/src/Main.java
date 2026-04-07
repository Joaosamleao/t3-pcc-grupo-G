import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

import java.util.ArrayList;

public class Main {

    static char[] nomes = {'a','b','c','d','e','f','g','h','i','j'};

    public static void main(String[] args) {

        In in = new In("dados/entrada_eulerizada.txt");

        int V = in.readInt();
        int E = in.readInt();

        System.out.println("Vertices: " + V);
        System.out.println("Arestas: " + E + "\n");

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);

        for (int i = 0; i < E; i++) {

            int v = in.readInt();
            int w = in.readInt();
            double peso = in.readDouble();

            DirectedEdge e = new DirectedEdge(v, w, peso);
            G.addEdge(e);
        }

        System.out.println("Grafo carregado.\n");

        System.out.println("Graus dos vertices:");
        System.out.println("--------------------");

        for (int v = 0; v < G.V(); v++) {

            int inDegree = G.indegree(v);
            int outDegree = G.outdegree(v);

            System.out.println(
                    nomes[v] +
                            " | entrada = " + inDegree +
                            " | saida = " + outDegree
            );
        }

        if (!grafoBalanceado(G)) {

            System.out.println("\nO grafo NÃO está balanceado.");
            System.out.println("Não existe circuito Euleriano.");

            return;
        }

        System.out.println("\nO grafo está balanceado.");
        System.out.println("Executando algoritmo de Hierholzer...\n");

        DirectedEulerianCycleWeighted euler =
                new DirectedEulerianCycleWeighted(G);

        if (!euler.hasEulerianCycle()) {

            System.out.println("Nenhum circuito Euleriano encontrado.");
            return;
        }

        ArrayList<Integer> vertices = new ArrayList<>();

        for (DirectedEdge e : euler.cycle()) {

            if (vertices.isEmpty())
                vertices.add(e.from());

            vertices.add(e.to());
        }

        if (!vertices.isEmpty() &&
                vertices.get(0) != vertices.get(vertices.size()-1)) {

            vertices.add(vertices.get(0));
        }

        System.out.print("Circuito: ");

        for (int v : vertices)
            System.out.print(nomes[v] + " ");

        System.out.println();

        double custoTotal = 0;

        for (DirectedEdge e : euler.cycle())
            custoTotal += e.weight();

        System.out.println("\nCusto total do circuito: " + custoTotal);
    }

    public static boolean grafoBalanceado(EdgeWeightedDigraph G) {

        for (int v = 0; v < G.V(); v++) {

            if (G.indegree(v) != G.outdegree(v))
                return false;
        }

        return true;
    }
}