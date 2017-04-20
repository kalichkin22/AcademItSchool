package ru.academits.kalichkin.graph;

import java.util.*;

public class Graph<T> {
    private int[][] matrix;

    public Graph(int n) {
        matrix = new int[n][n];
    }

    public void addEdge(int begin, int end) {
        matrix[begin][end] = 1;
        matrix[end][begin] = 1;
    }

    private int getNotVisitedVertex(int vertex, Set<Integer> visitedVertex) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[vertex][i] == 1 && !visitedVertex.contains(i)) {
                return i;
            }
        }
        return -1;
    }


    public void bfs(int begin) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedVertex = new HashSet<>();
        int numberVertex;

        visitedVertex.add(begin);
        queue.add(begin);
        System.out.println(begin);

        while (!queue.isEmpty()) {
            int currentVertex = queue.remove();
            while ((numberVertex = getNotVisitedVertex(currentVertex, visitedVertex)) != -1) {
                queue.add(numberVertex);
                visitedVertex.add(numberVertex);
                System.out.println(numberVertex);
            }

            if (queue.isEmpty()) {
                for (int i = 0; i < matrix.length; i++) {
                    if (!visitedVertex.contains(i)) {
                        queue.add(i);
                        break;
                    }
                }
                if (matrix.length != visitedVertex.size()) {
                    for (int i = 0; i < matrix.length; i++) {
                        if (!visitedVertex.contains(i)) {
                            System.out.println(i);
                        }
                    }
                    break;
                }
            }
        }
    }


    public void dfs(int begin) {
        ArrayList<Integer> stack = new ArrayList<>();
        Set<Integer> visitedVertex = new HashSet<>();
        stack.add(begin);
        visitedVertex.add(begin);

        System.out.println(begin);

        while (!stack.isEmpty()) {
            int currentVertex = stack.get(stack.size() - 1);
            int vertex = getNotVisitedVertex(currentVertex, visitedVertex);

            if (vertex == -1) {
                stack.remove(stack.size() - 1);
            } else {
                visitedVertex.add(vertex);
                System.out.println(vertex);
                stack.add(vertex);
            }

            if (stack.isEmpty() && matrix.length != visitedVertex.size()) {
                for (int i = 0; i < matrix.length; i++) {
                    if (!visitedVertex.contains(i)) {
                        stack.add(i);
                        break;
                    }
                }
                if (matrix.length != visitedVertex.size()) {
                    for (int i = 0; i < matrix.length; i++) {
                        if (!visitedVertex.contains(i)) {
                            System.out.println(i);
                        }
                    }
                    break;
                }
            }
        }
    }
}

