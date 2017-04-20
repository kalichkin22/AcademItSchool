package ru.academits.kalichkin.main;


import ru.academits.kalichkin.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(10);

        graph.addEdge(0, 5);
        graph.addEdge(0, 2);
        graph.addEdge(2, 5);
        graph.addEdge(1, 4);
        graph.addEdge(0, 1);
        graph.addEdge(7, 8);
        graph.addEdge(3, 8);
        graph.addEdge(8, 6);
        graph.addEdge(9, 9);

        //graph.bfs(0);
        graph.dfs(0);


    }
}
