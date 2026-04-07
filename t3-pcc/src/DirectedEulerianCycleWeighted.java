import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;
import java.util.Iterator;

// Versão modificada da classe DirectedEulerianCycle (Algs4)
// Agora trabalha diretamente com arestas ponderadas

public class DirectedEulerianCycleWeighted {

    private Stack<DirectedEdge> cycle = null;

    public DirectedEulerianCycleWeighted(EdgeWeightedDigraph G) {

        if (G.E() == 0) return;

        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) != G.indegree(v))
                return;

        Iterator<DirectedEdge>[] adj =
                (Iterator<DirectedEdge>[]) new Iterator[G.V()];

        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        Stack<Integer> stack = new Stack<>();

        int s = nonIsolatedVertex(G);
        stack.push(s);

        cycle = new Stack<>();

        while (!stack.isEmpty()) {

            int v = stack.peek();

            if (adj[v].hasNext()) {

                DirectedEdge e = adj[v].next();
                stack.push(e.to());

            } else {

                stack.pop();

                if (!stack.isEmpty()) {

                    int u = stack.peek();
                    DirectedEdge edge = findEdge(G, u, v);
                    cycle.push(edge);
                }
            }
        }

        if (cycle.size() != G.E())
            cycle = null;
    }

    private DirectedEdge findEdge(EdgeWeightedDigraph G, int from, int to) {

        for (DirectedEdge e : G.adj(from))
            if (e.to() == to)
                return e;

        return null;
    }

    private static int nonIsolatedVertex(EdgeWeightedDigraph G) {

        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) > 0)
                return v;

        return -1;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    public boolean hasEulerianCycle() {
        return cycle != null;
    }
}