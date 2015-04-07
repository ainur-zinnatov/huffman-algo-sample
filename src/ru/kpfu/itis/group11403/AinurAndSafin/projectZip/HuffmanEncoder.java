package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.io.IOException;
import java.util.List;

/**
 * Класс, который записывает символ в виде кода, который получился для символа в файл
 */
public final class HuffmanEncoder {

    private BitOutputStream output; //поток битовый, для записывания побитово в файл

    private CodeTree codeTree; //дерево, которое нужно для получения кода для символа

    public HuffmanEncoder(BitOutputStream out) {
        if (out == null){
            throw new NullPointerException("stream is null");
        }
        output = out;
    }

    public CodeTree getCodeTree() {

        return codeTree;
    }

    public void setCodeTree(CodeTree codeTree) {

        this.codeTree = codeTree;
    }

    /**
     * метод для записи символа в файл в виде кода
     */
    public void write(int symbol) throws IOException {

        if (codeTree == null){
            throw new NullPointerException("Code tree is null");
        }
        //получаем код для символа ,например кодом для символа 95 является 11001, записываем его в выходной файл
        List<Integer> bits = codeTree.getCode(symbol);
        for (int b : bits){
            output.write(b);
        }
    }

}
