package ru.academits.kalichkin.main;


import ru.academits.kalichkin.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);

        graph.addEdge(0, 5);
        graph.addEdge(0, 4);
        graph.addEdge(0, 5);
        graph.addEdge(1, 4);


        //graph.bfs(0);
        //graph.dfs(0);

    }
}
