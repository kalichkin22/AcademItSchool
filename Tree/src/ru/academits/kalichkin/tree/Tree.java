package ru.academits.kalichkin.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree<T> {
    private TreeNode<T> root;
    private Comparator<T> comparator;

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }


    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            return;
        }
        TreeNode<T> node = root;
        TreeNode<T> parent;

        while (node != null) {
            parent = node;
            if (comparator.compare(data, node.getData()) < 0) {
                node = node.getLeft();
                if (node == null) {
                    parent.setLeft(new TreeNode<>(data));
                }
            } else {
                node = node.getRight();
                if (node == null) {
                    parent.setRight(new TreeNode<>(data));
                }
            }
        }
    }

/*
    public boolean add(T data) {
        return addNode(root, data);
    }

    private boolean addNode(TreeNode<T> node, T data) {
        if (root == null) {
            root = new TreeNode<T>(data);
            return true;
        }
        if (comparator.compare(data, node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new TreeNode<T>(data));
                return true;
            }
            return addNode(node.getLeft(), data);
        }
        if (comparator.compare(data, node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new TreeNode<T>(data));
                return true;
            }
            return addNode(node.getRight(), data);
        }
        return false;
    }
*/

    public boolean contains(T data) {
        TreeNode<T> node = root;

        while (!data.equals(node.getData())) {
            if (comparator.compare(data, node.getData()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }

            if (node == null) {
                return false;
            }
        }
        return true;
    }


    public void bfs() {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> element = queue.remove();
            System.out.println(element.getData());

            if (element.getLeft() != null) {
                queue.add(element.getLeft());
            }
            if (element.getRight() != null) {
                queue.add(element.getRight());
            }
        }
    }


    public void dfsRec(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getData());

        dfsRec(root.getLeft());
        dfsRec(root.getRight());
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void dfs() {
        if (root == null) {
            return;
        }

        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode<T> node = stack.pop();
            System.out.println(node.getData());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }
}


