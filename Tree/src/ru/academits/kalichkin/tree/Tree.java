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


    public boolean remove(T data) {
        if (root == null) {
            return false;
        }
        TreeNode<T> node = root;
        TreeNode<T> parent = null;

        while (!data.equals(node.getData())) {
            if (comparator.compare(data, node.getData()) < 0) {
                parent = node;
                node = node.getLeft();
            } else {
                parent = node;
                node = node.getRight();
            }

            if (node == null) {
                return false;
            }
        }

        if (node.getRight() == null) {
            if (node == root) {
                root = node.getLeft();
            } else {
                if (comparator.compare(node.getData(), parent.getData()) < 0) {
                    parent.setLeft(node.getLeft());
                } else {
                    parent.setRight(node.getLeft());
                }
            }
        } else if (node.getRight().getLeft() == null) {
            node.getRight().setLeft(node.getLeft());

            if (node == root) {
                root = node.getRight();
            } else {
                if (comparator.compare(node.getData(), parent.getData()) < 0) {
                    parent.setLeft(node.getRight());
                } else {
                    parent.setRight(node.getRight());
                }
            }
        } else {
            TreeNode<T> min = node.getRight().getLeft();
            TreeNode<T> prev = node.getRight();

            while (min.getLeft() != null) {
                prev = min;
                min = min.getLeft();
            }
            prev.setLeft(min.getRight());
            min.setLeft(node.getLeft());
            min.setRight(node.getRight());

            if (node == root) {
                root = min;
            } else {
                if (comparator.compare(node.getData(), parent.getData()) < 0) {
                    parent.setLeft(min);
                } else {
                    parent.setRight(min);
                }
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


