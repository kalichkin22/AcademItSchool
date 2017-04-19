package ru.academits.kalichkin.graph;

import java.util.*;

public class Graph<T> {
    private int[][] matrix;
    private Set<Integer> set;

    public Graph(int n) {
        matrix = new int[n][n];
    }

    public void addEdge(int begin, int end) {
        matrix[begin][end] = 1;
        matrix[end][begin] = 1;
    }

    private int getNotVisitedVertex(int vertex) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[vertex][i] == 1 && !set.contains(i)) {
                return i;
            }
        }
        return -1;
    }


    public void bfs(int begin) {
        Queue<Integer> queue = new LinkedList<>();
        set = new HashSet<>();
        int numberVertex;

        set.add(begin);
        queue.add(begin);
        System.out.println(begin);

        while (!queue.isEmpty()) {
            int currentVertex = queue.remove();
            while ((numberVertex = getNotVisitedVertex(currentVertex)) != -1) {
                queue.add(numberVertex);
                set.add(numberVertex);
                System.out.println(numberVertex);
            }
        }
    }


    public void dfs(int begin) {
        ArrayList<Integer> stack = new ArrayList<>();
        set = new HashSet<>();
        stack.add(begin);
        set.add(begin);

        System.out.println(begin);

        while (!stack.isEmpty()) {
            int currentVertex = stack.get(stack.size() - 1);
            int vertex = getNotVisitedVertex(currentVertex);
            if (vertex == -1) {
                stack.remove(stack.size() - 1);
            } else {
                set.add(vertex);
                System.out.println(vertex);
                stack.add(vertex);
            }
        }
    }
}
