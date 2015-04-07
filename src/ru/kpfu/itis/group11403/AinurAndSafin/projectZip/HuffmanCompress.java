package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.io.*;


public class HuffmanCompress{

    private CodeTree codeTree;

    public CodeTree getCodeTree()
    {
        return codeTree;
    }

    //input - файл, который сжимаем
    //output - файл, в который записываем результат
    public void compress(File input, File output){

        try(OutputStream out = new BufferedOutputStream(new FileOutputStream(output));
            InputStream in = new BufferedInputStream(new FileInputStream(input))    ) {

            BitOutputStream b_out = new BitOutputStream(out);

            FrequencyTable frequencyTable = getFrequencies(input);
            frequencyTable.increment(256); // end of file symbol
            codeTree = frequencyTable.buildCodeTree();

            compress(codeTree,in,b_out);

            b_out.flush(); //дописываем

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //читаем файл и собираем информацию о кол-ве символов
    private static FrequencyTable getFrequencies(File file) throws IOException {

        try(InputStream input = new BufferedInputStream(new FileInputStream(file))){

            FrequencyTable freq = new FrequencyTable(new int[257]);

            while (true){
                int b = input.read();
                if (b == -1){
                    break;
                }
                freq.increment(b);//увеличиваем кол-во на 1
            }
            return freq;
        }
    }



    private static void compress(CodeTree code, InputStream in, BitOutputStream out) throws IOException {

        HuffmanEncoder enc = new HuffmanEncoder(out);
        enc.setCodeTree(code);
        while (true) {
            int b = in.read();
            if (b == -1){
                break;
            }
            enc.write(b);
        }
        enc.write(256);  // End of file symbol
    }

}
