package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Собираем кол-во символов, которые встретились
 * Создаем  CodeTree , который оптимальный для этих частот символов
 **/

public class FrequencyTable {

    private int[] frequencies; // массив с частотами


    public FrequencyTable(int[] freqs) {

        if (freqs == null){
            throw new NullPointerException("array of frequencies is null");
        }
        if (freqs.length < 2){
            throw new IllegalArgumentException("Нужно как минимум 2 символа");
        }

        frequencies = freqs.clone();
    }


    public int getSymbolLimit() {

        return frequencies.length;
    }


    // увеличивает на 1 кол-во symbol
    public void increment(int symbol) {
        if (symbol < 0 || symbol >= frequencies.length){
            throw new IllegalArgumentException("Символ за пределами массива");
        }
        frequencies[symbol]++;
    }

    // метод для просмотра таблицы код - символ
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < frequencies.length; i++)
            sb.append(String.format("%d\t%d%n", i, frequencies[i]));
        return sb.toString();
    }


    public CodeTree buildCodeTree() {
        //используем очередь с приоритетом
        Queue<NodeWithFrequency> pqueue = new PriorityQueue<>();

        // добавляем узлы для символов, которые встечались хоть раз
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0){
                pqueue.add(new NodeWithFrequency(new Leaf(i), i, frequencies[i]));
            }
        }

        //сливаем в один узел два узла с наименьшими частотами
        //причем у нового узла символом будет являться наименьший из вдух символов  - lowestSymbol
        //и частота равна сумме двух частот
        while (pqueue.size() > 1) {

            NodeWithFrequency nf1 = pqueue.remove();

            NodeWithFrequency nf2 = pqueue.remove();

            pqueue.add(new NodeWithFrequency(
                    new InternalNode(nf1.node, nf2.node),
                    Math.min(nf1.lowestSymbol, nf2.lowestSymbol),
                    nf1.frequency + nf2.frequency));
        }

        // возвращаем дерево / root
        return new CodeTree((InternalNode)pqueue.remove().node, frequencies.length);
    }


    // Узел с частатой
    private static class NodeWithFrequency implements Comparable<NodeWithFrequency> {

        public final Node node;

        public final int lowestSymbol; // используется для того, чтобы, сравнивать
        // по символам два узла

        public final long frequency; //частота символа в псевдоузле


        public NodeWithFrequency(Node node, int lowestSymbol, long freq) {
            this.node = node;
            this.lowestSymbol = lowestSymbol;
            this.frequency = freq;
        }


        public int compareTo(NodeWithFrequency other) {
            //сравниваем по частоте
            if (frequency < other.frequency){
                return -1;
            }
            else if (frequency > other.frequency){
                return 1;
            }
            //сравниваем по символам
            else if (lowestSymbol < other.lowestSymbol){
                return -1;
            }
            else if (lowestSymbol > other.lowestSymbol){
                return 1;
            }
            //иначе они равны
            return 0;
        }

    }

}
