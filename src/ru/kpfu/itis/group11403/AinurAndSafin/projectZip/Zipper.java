package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.io.File;

/*
    Класс Zipper. Сжимает и расжимает файл
*/

public class Zipper {

    private CodeTree codeTree;

    public CodeTree getCodeTree() {
        return codeTree;
    }
    
    // in - файл для сжатия, и out - файл, в который сжимаем
    public void compress(File in, File out){

        HuffmanCompress huffmanCompress = new HuffmanCompress();
        huffmanCompress.compress(in, out);
        codeTree = huffmanCompress.getCodeTree(); // сохраняем дерево, которое получили при сжатии файла
                                                    // для дальнейшего расжатия
    }
    
    //in - файл, который расжимаем, out - файл в  который записываем расжатый файл
    public void decompress(File in, File out){

        HuffmanDecompress huffmanDecompress = new HuffmanDecompress(codeTree);

        huffmanDecompress.decompress(in, out);
    }


}
