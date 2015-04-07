package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.io.IOException;

/**
 * Класс, который читает из файла и декодирует последовательность битов
 */
public class HuffmanDecoder {

    private  BitInputStream input;

    private CodeTree codeTree; // дерево для раскадировки

    public HuffmanDecoder(BitInputStream in) {
        if (in == null){
            throw new NullPointerException("Stream is null");
        }
        input = in;
    }

    public void setCodeTree(CodeTree codeTree) {

        this.codeTree = codeTree;
    }

    //возвращает символ, который лежит в листе дерева
    public int read() throws IOException {
        if (codeTree == null){
            throw new NullPointerException("Code tree is null");
        }
        //Узлу присваиваем корень дерева
        InternalNode currentNode = codeTree.getRoot();

        //идем по дереву, пока не дойдем до листа(потому что в листе хранится символ)
        while (true) {

            int temp = input.readNoEof(); //считываем бит(то есть например у нас 10010)
            // значит считываем 1, потом 0, потом 0 и так далее до конца файла

            Node nextNode;

            //Если считали 0, идем к левому ребенку
            if (temp == 0) {
                nextNode = currentNode.leftChild;

                //Если 1 - к правому ребенку
            } else  {
                nextNode = currentNode.rightChild;
            }

            //если дошли до листа, то возвращаем его символ
            if (nextNode instanceof Leaf){
                return ((Leaf)nextNode).symbol;

                //Если не лист, а внутренний узел, то идем дальше вниз по дереву
            } else if (nextNode instanceof InternalNode){
                currentNode = (InternalNode)nextNode;
            }
        }
    }

}
