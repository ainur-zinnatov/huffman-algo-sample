package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

/**
 * Класс InternalNode - внутренний узел (псевдоузел)
 * У него есть поля leftChild, rightChild - правый и левый потомки
 * Символ в себе не содержит
 */

public final class InternalNode extends Node {

    private final Node leftChild;  // левое дитя

    private final Node rightChild;  // правое дитя


    public InternalNode(Node leftChild, Node rightChild) {
        if (leftChild == null || rightChild == null){
            throw new NullPointerException("Argument is null");
        }
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }


    public Node getLeftChild() {

        return leftChild;
    }

    public Node getRightChild() {

        return rightChild;
    }

}