public class BinaryTree<E extends Comparable<E>> {
    private Node<E> root;

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;

        public Node(E element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }

    public BinaryTree() {
        this.root = null;
    }

    public void insert(E element) {
        root = insert(root, element);
    }

    private Node<E> insert(Node<E> node, E element) {
        if (node == null) {
            node = new Node<>(element);
        } else if (element.compareTo(node.element) < 0) {
            node.left = insert(node.left, element);
        } else if (element.compareTo(node.element) > 0) {
            node.right = insert(node.right, element);
        }
        return node;
    }

    public boolean contains(E element) {
        return contains(root, element);
    }

    private boolean contains(Node<E> node, E element) {
        if (node == null) {
            return false;
        }
        if (node.element.compareTo(element) == 0) {
            return true;
        } else if (node.element.compareTo(element) < 0) {
            return contains(node.right, element);
        } else {
            return contains(node.left, element);
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.println(node.element);
        inOrderTraversal(node.right);
    }
}
