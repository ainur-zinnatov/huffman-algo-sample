package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.io.*;

/*
    Есть идея сериализовывать дерево в какой-нибудь файл и потом, когда хотим расжать, дисериализовать его.
*/

public class Test {

    public static void main(String[] args) {

        Zipper zipper = new Zipper();

        File input = new File("/Users/Ramil/Desktop/input.txt"); // файл для сжатия
        File result = new File("/Users/Ramil/Desktop/result.txt"); // файл сжатый
        File output = new File("/Users/Ramil/Desktop/output.txt"); // файл расжатый

        zipper.compress(input, result); //сжимаем

        zipper.decompress(result, output); //расжимаем

        CodeTree codeTree = zipper.getCodeTree();

//        можно посмотреть какие символы и коды присутсвуют
        System.out.println(codeTree.toString());

    }


}
