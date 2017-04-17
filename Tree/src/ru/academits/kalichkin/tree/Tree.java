package ru.academits.kalichkin.tree;

import java.util.*;

public class Tree<T> {
    private TreeNode<T> root;
    private Comparator<T> comparator;

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public Tree() {

    }

    private int resultCompare(T data, T data2) {
        int result = 0;
        if (data != null && data2 != null) {
            if (comparator != null) {
                result = comparator.compare(data, data2);
            } else {
                Comparable<T> comparable = (Comparable<T>) data;
                result = comparable.compareTo(data2);
            }
            return result;
        } else {
            if (data == null && data2 == null) {
                return 0;
            } else {
                return -1;
            }
        }
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

            if (resultCompare(data, node.getData()) < 0) {
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
        while (true) {
            int result = resultCompare(data, root.getData());
            if (result < 0) {
                root = root.getLeft();
            } else if (result > 0) {
                root = root.getRight();
            } else {
                break;
            }

            if (root == null) {
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
        while (true) {
            int result = resultCompare(data, node.getData());
            if (result < 0) {
                parent = node;
                node = node.getLeft();
            } else if (result > 0) {
                parent = node;
                node = node.getRight();
            } else {
                break;
            }

            if (node == null) {
                return false;
            }
        }

        // если узел не имеет детей
        if (node.getRight() == null) {
            if (node == root) {
                root = node.getLeft();
            } else {
                assert parent != null;
                if (resultCompare(node.getData(), parent.getData()) < 0) {
                    parent.setLeft(node.getLeft());
                } else {
                    parent.setRight(node.getLeft());
                }
            }

            // если узел имеет одного ребенка
        } else if (node.getRight().getLeft() == null) {
            node.getRight().setLeft(node.getLeft());

            if (node == root) {
                root = node.getRight();
            } else {
                assert parent != null;
                if (resultCompare(node.getData(), parent.getData()) < 0) {
                    parent.setLeft(node.getRight());
                } else {
                    parent.setRight(node.getRight());
                }
            }

            // если узел является корнем или у него два ребенка, то берем наименьшего ребенка из правой ветви
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
                assert parent != null;
                if (resultCompare(node.getData(), parent.getData()) < 0) {
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
            System.out.print(element.getData() + " ");

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
        System.out.print(root.getData() + " ");

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

        ArrayList<TreeNode<T>> stack = new ArrayList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.remove(stack.size() - 1);
            System.out.print(node.getData() + " ");


            if (node.getRight() != null) {
                stack.add(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.add(node.getLeft());
            }
        }
    }
}


