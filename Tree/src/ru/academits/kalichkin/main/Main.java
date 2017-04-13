package ru.academits.kalichkin.main;

import ru.academits.kalichkin.tree.Tree;
import ru.academits.kalichkin.tree.TreeNode;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(Integer::compareTo);

        tree.add(10);
        tree.add(8);
        tree.add(9);
        tree.add(7);
        tree.add(12);
        tree.add(11);
        tree.add(14);

        //tree.bfs();

        tree.dfsRec();
       // System.out.println(tree.contains(20));
    }
}
