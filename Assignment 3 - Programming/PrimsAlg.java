import edu.princeton.cs.algs4.*;

import java.io.*;

// We implement Prim's algorithm using data structures implemented in edu.princeton.cs.algs4.
// Download link: https://algs4.cs.princeton.edu/code/algs4.jar
// Documentation: https://algs4.cs.princeton.edu/code/
//
// Specifically, we will use EdgeWeightedGraph, Edge, MinPQ, and Queue classes.

public class PrimsAlg {
    public static void main(String[] args) {
        // A) copy and modify the main function in test.java but change the data
        // structure to a weightedgraph
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        boolean[] marked = new boolean[G.V()]; // In the early Monday tutorial, Boolean is a class whereas boolean is a
                                               // primitive.
        Queue<Edge> mst = new Queue<Edge>(); // A linked list could also work.
        marked[0] = true;

        while (true) {
            // In order to find a minimum crossing edge, we loop through all the edges in
            // the graph and
            // add all of the crossing edges to a minimum priority queue.
            MinPQ<Edge> minpq = new MinPQ<Edge>();

            for (Edge e : G.edges()) {
                int u = e.either();
                int v = e.other(u);
                // B) Check the condition if they are cross edges and insert to pq
                assert marked[v] || marked[v];
                if (marked[u] && marked[v])
                    continue;
                minpq.insert(e);
            }

            // If the priority queue is empty, then this means there are no more crossing
            // edges so we are done.
            if (minpq.isEmpty()) {
                break;
            }

            // If the priority queue is not empty, then the minimum element in it is a
            // minimum crossing edge.

            // C) get the edge minimum from pq
            Edge eMin = minpq.min();

            // D) Update the (current) MST and Mark the other vertex

            int v = eMin.either(), u = eMin.other(v);

            if (!marked[u]) {
                assert !marked[u];
                marked[u] = true;
                for (Edge e : G.adj(u))
                    if (!marked[e.other(u)])
                        mst.enqueue(e);
            }
            if (!marked[v]) {
                assert !marked[v];
                marked[v] = true;
                for (Edge e : G.adj(v))
                    if (!marked[e.other(v)])
                        mst.enqueue(e);
            }
        }

        // Once an MST has been found, print its edges and total weight.
        LazyPrimMST mstL = new LazyPrimMST(G);
        double edgeWeight = 0.0;
        for (Edge e : mstL.edges()) {
            StdOut.println(e);
            edgeWeight += e.weight();
        }
        // E) Evaluate the weight of MST
        StdOut.printf("Total weight is=%.5f\n", edgeWeight);
    }

}
