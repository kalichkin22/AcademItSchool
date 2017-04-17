package ru.academits.kalichkin.main;

import ru.academits.kalichkin.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.add(20);
        tree.add(15);
        tree.add(25);
        tree.add(10);
        tree.add(5);
        tree.add(12);
        tree.add(16);
        tree.add(19);
        tree.add(23);
        tree.add(27);
        tree.add(24);
        tree.add(21);
        tree.add(26);
        tree.add(30);


        //System.out.println(tree.remove(null));
        //tree.bfs();

        //tree.dfsRec(tree.getRoot());
        //tree.dfs();
        System.out.println(tree.contains(null));
    }
}
