package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс CodeTree - бинарное дерево
 * Хранит в себе корень дерева и список из кодов каждого символа, которые находятся в листьях
 */
public class CodeTree {

    private InternalNode root;  // главный корень

    private List<List<Integer>> codes; // хранит код для каждого символа или null, если символа нет


    // symbolLimit - 0-255 все символы ASCII + End of file 256
    public CodeTree(InternalNode root, int symbolLimit) {
        if (root == null){
            throw new NullPointerException("root is null");
        }
        this.root = root;

        codes = new ArrayList<>();

        for (int i = 0; i < symbolLimit; i++){
            codes.add(null);
        }
        buildCodeList(root, new ArrayList<>());  // вызываем метод, который по данному корню дерева
                                                // пишет коды для каждого символа
    }

    public InternalNode getRoot() {

        return root;
    }

    private void buildCodeList(Node node, List<Integer> prefix) {

        if (node instanceof InternalNode) {
            InternalNode internalNode = (InternalNode)node;
            //рекурсивно идем вниз к листьям добавляя 0, если пошли на лево, 1 - направо
            //в итоге, когда дойдем до листа, в котором хранится символ для него добавляем в лист
            //символ и его код
            prefix.add(0);
            buildCodeList(internalNode.leftChild , prefix);
            prefix.remove(prefix.size() - 1);

            prefix.add(1);
            buildCodeList(internalNode.rightChild, prefix);
            prefix.remove(prefix.size() - 1);

            //Если дошли до листа устанавливаем код символа
        } else if (node instanceof Leaf) {
            Leaf leaf = (Leaf)node;
            if (leaf.symbol >= codes.size()){
                throw new IllegalArgumentException("Символ за пределами таблицы ASCII");
            }

            if (codes.get(leaf.symbol) != null){
                throw new IllegalArgumentException("Символ имеет более одного кода");
            }
            //устанавливаем код символа
            codes.set(leaf.symbol, new ArrayList<>(prefix));

        }
    }


    //возвращает код символа
    public List<Integer> getCode(int symbol) {
        if (symbol < 0){
            throw new IllegalArgumentException("Не правильный символ");
        }
        else if (codes.get(symbol) == null){
            throw new IllegalArgumentException("Для этого символа нет кода");
        }
        return codes.get(symbol);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString("", root, sb);
        return sb.toString();
    }


    private static void toString(String prefix, Node node, StringBuilder sb) {
        if (node instanceof InternalNode) {
            InternalNode internalNode = (InternalNode)node;
            toString(prefix + "0", internalNode.leftChild , sb);
            toString(prefix + "1", internalNode.rightChild, sb);
        } else if (node instanceof Leaf) {
            sb.append(String.format("Code %s:"+"\t\t\t"+" Symbol %d%n", prefix, ((Leaf)node).symbol));
        }
    }

}
