package task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {
    @Test
    void testAddElement(){
        Tree tree = new Tree();
        tree.insertNode(6);
        tree.insertNode(12);

        assertTrue(tree.containsNodeByValue(6));
        assertTrue(tree.containsNodeByValue(12));
    }

    @Test
    void testRemoveElement(){
        Tree tree = new Tree();
        tree.insertNode(12);
        tree.insertNode(6);

        assertTrue(tree.containsNodeByValue(6));
        tree.deleteNode(6);
        assertFalse(tree.containsNodeByValue(6));
    }

    @Test
    public void testSearchElement() {
        Tree tree = new Tree();
        tree.insertNode(5);
        tree.insertNode(8);

        assertEquals(5, tree.findNodeByValue(5).getValue());
        assertEquals(8, tree.findNodeByValue(8).getValue());
        assertFalse(tree.containsNodeByValue(20));
    }

    @Test
    public void testSizeOfTree() {
        Tree tree = new Tree();
        tree.insertNode(1);
        tree.insertNode(2);
        tree.insertNode(3);
        tree.insertNode(4);
        assertEquals(4, tree.size());
    }

    @Test
    public void testLeftChild() {
        Node nodeRoot;
        Node node;
        Tree tree = new Tree();
        tree.insertNode(4);
        tree.insertNode(3);
        tree.insertNode(5);
        nodeRoot = tree.findNodeByValue(4);
        node = tree.findNodeByValue(3);

        assertEquals(node.getValue(), nodeRoot.getLeftChild().getValue());
        assertEquals(null, node.getLeftChild());
        assertEquals(null, node.getRightChild());
    }

    @Test
    public void testRightChild() {
        Node nodeRoot;
        Node node;
        Tree tree = new Tree();
        tree.insertNode(4);
        tree.insertNode(3);
        tree.insertNode(5);
        nodeRoot = tree.findNodeByValue(4);
        node = tree.findNodeByValue(5);

        assertEquals(node.getValue(), nodeRoot.getRightChild().getValue());
        assertEquals(null, node.getLeftChild());
        assertEquals(null, node.getRightChild());
    }

    @Test
    public void testRoot() {
        Node nodeRoot;
        Node nodeLeft;
        Node nodeRight;
        Tree tree = new Tree();
        tree.insertNode(4);
        tree.insertNode(3);
        tree.insertNode(5);
        nodeRoot = tree.findNodeByValue(4);
        nodeLeft = nodeRoot.getLeftChild();
        nodeRight = nodeRoot.getRightChild();

        assertEquals(4, nodeRoot.getValue());
        assertEquals(nodeLeft, nodeRoot.getLeftChild());
        assertEquals(nodeRight, nodeRoot.getRightChild());
    }

}
