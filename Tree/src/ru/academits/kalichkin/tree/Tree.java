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
        if (data == null && data2 == null) {
            return 0;
        } else if (data != null && data2 == null) {
            return 1;
        } else if (data == null) {
            return -1;
        } else {
            int result;
            if (comparator != null) {
                result = comparator.compare(data, data2);
            } else {
                Comparable<T> comparable = (Comparable<T>) data;
                result = comparable.compareTo(data2);
            }
            return result;
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
        TreeNode<T> node = root;
        while (true) {
            int result = resultCompare(data, node.getData());
            if (result < 0) {
                node = node.getLeft();
            } else if (result > 0) {
                node = node.getRight();
            } else {
                break;
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

        // Находим удаляемый узел.
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

        // Случай 1: Если нет детей справа, левый ребенок встает на место удаляемого.
        if (node.getRight() == null) {
            if (node == root) {
                root = node.getLeft();
            } else {
                assert parent != null;
                if (resultCompare(parent.getData(), node.getData()) > 0) {
                    // Если значение родителя больше текущего, левый ребенок текущего узла становится левым ребенком родителя.
                    parent.setLeft(node.getLeft());
                } else {
                    // Если значение родителя меньше или равно текущему, левый ребенок текущего узла становится правым ребенком родителя.
                    parent.setRight(node.getLeft());
                }
            }

            // Случай 2: Если у правого ребенка нет детей слева, то он занимает место удаляемого узла.
        } else if (node.getRight().getLeft() == null) {
            node.getRight().setLeft(node.getLeft());

            if (node == root) {
                root = node.getRight();
            } else {
                assert parent != null;
                if (resultCompare(parent.getData(), node.getData()) > 0) {
                    // Если значение родителя больше текущего, правый ребенок текущего узла становится левым ребенком родителя.
                    parent.setLeft(node.getRight());
                } else {
                    // Если значение родителя меньше или равно текущему, правый ребенок текущего узла становится правым ребенком родителя.
                    parent.setRight(node.getRight());
                }
            }


            // Случай 3: Если у правого ребенка есть дети слева, крайний левый ребенок из правого поддерева заменяет удаляемый узел.
        } else {
            TreeNode<T> min = node.getRight().getLeft();
            TreeNode<T> prev = node.getRight();

            // Найдем крайний левый узел.
            while (min.getLeft() != null) {
                prev = min;
                min = min.getLeft();
            }

            // Левое поддерево родителя становится правым поддеревом крайнего левого узла.
            prev.setLeft(min.getRight());

            // Левый и правый ребенок текущего узла становится левым и правым ребенком крайнего левого.
            min.setLeft(node.getLeft());
            min.setRight(node.getRight());

            if (node == root) {
                root = min;
            } else {
                assert parent != null;
                if (resultCompare(parent.getData(), node.getData()) > 0) {

                    // Если значение родителя больше текущего, крайний левый узел становится левым ребенком родителя.
                    parent.setLeft(min);
                } else {
                    // Если значение родителя меньше или равно текущему, крайний левый узел становится правым ребенком родителя.
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


