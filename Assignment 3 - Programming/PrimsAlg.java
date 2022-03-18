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
        Queue<Edge> mst = new Queue<Edge>(); // A linked list could also work.
        marked[0] = true;

        boolean[] markedM = new boolean[G.V()];
        Queue<Edge> mstM = new Queue<Edge>();
        markedM[0] = true;

        while (true) {
            // In order to find a minimum crossing edge, we loop through all the edges in
            // the graph and
            // add all of the crossing edges to a minimum priority queue.
            MinPQ<Edge> minpq = new MinPQ<Edge>();

            for (Edge e : G.edges()) {
                int u = e.either();
                int v = e.other(u);
                // B) Check the condition if they are cross edges and insert to pq
                if ((!marked[u] && marked[v]) || (marked[u] && !marked[v]))
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
            Edge eMin = minpq.delMin();

            // D) Update the (current) MST and Mark the other vertex
            int u = eMin.either();
            int v = eMin.other(u);
            if (!marked[u]) {
                marked[u] = true;
                mst.enqueue(eMin);
                for (Edge e : G.adj(u))
                    if (!marked[e.other(u)])
                        minpq.insert(e);
            }
            if (!marked[v]) {
                marked[v] = true;
                mst.enqueue(eMin);
                for (Edge e : G.adj(v))
                    if (!marked[e.other(v)])
                        minpq.insert(e);
            }

        }

        while (true) {
            // In order to find a crossing edge, we loop through all the edges in
            // the graph and
            // add all of the crossing edges to a priority queue.

            MaxPQ<Edge> maxpq = new MaxPQ<Edge>();

            for (Edge e : G.edges()) {
                int u = e.either();
                int v = e.other(u);
                // B) Check the condition if they are cross edges and insert to pq
                if ((!markedM[u] && markedM[v]) || (markedM[u] && !markedM[v]))
                    maxpq.insert(e);
            }

            // If the priority queue is empty, then this means there are no more crossing
            // edges so we are done.
            if (maxpq.isEmpty()) {
                break;
            }

            // If the priority queue is not empty, then the element in it is a
            // crossing edge.

            // C) get the edge from pq
            Edge eMax = maxpq.delMax();

            // D) Update the (current) MST and Mark the other vertex
            int u = eMax.either();
            int v = eMax.other(u);
            if (!markedM[u]) {
                markedM[u] = true;
                mstM.enqueue(eMax);
                for (Edge e : G.adj(u))
                    if (!markedM[e.other(u)])
                        maxpq.insert(e);
            }
            if (!markedM[v]) {
                markedM[v] = true;
                mstM.enqueue(eMax);
                for (Edge e : G.adj(v))
                    if (!markedM[e.other(v)])
                        maxpq.insert(e);
            }

        }

        // Once an MST has been found, print its edges and total weight.
        double eWeight = 0.0;
        for (Edge e : mst) {
            StdOut.println(e);
            eWeight += e.weight();
        }
        // E) Evaluate the weight of MST
        StdOut.printf("Total weight is=%.5f\n", eWeight);

        System.out.println();

        // Once an MST has been found, print its edges and total weight.
        eWeight = 0.0;
        for (Edge e : mstM) {
            StdOut.println(e);
            eWeight += e.weight();
        }
        // E) Evaluate the weight of MST
        StdOut.printf("Total weight is=%.5f\n", eWeight);

    }

}