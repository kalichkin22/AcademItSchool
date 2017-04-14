package ru.academits.kalichkin.main;

import ru.academits.kalichkin.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.add(10);
        tree.add(8);
        tree.add(9);
        tree.add(7);
        tree.add(12);
        tree.add(11);
        tree.add(null);

        //System.out.println(tree.remove(11));
        tree.bfs();

        //tree.dfsRec(tree.getRoot());
        //tree.dfs();
        //System.out.println(tree.contains(null));
    }
}
