package ru.academits.kalichkin.main;

import ru.academits.kalichkin.tree.Tree;
import ru.academits.kalichkin.tree.TreeNode;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(Integer::compareTo);

        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);

        //tree.bfs();
        //tree.dfs();
        tree.dfsRec();
       // System.out.println(tree.contains(20));
    }
}
