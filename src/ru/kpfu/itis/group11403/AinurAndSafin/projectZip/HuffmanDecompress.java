package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.io.*;

public class HuffmanDecompress {

    private CodeTree codeTree; // дерево для раскодировки


    public HuffmanDecompress(CodeTree codeTree) {

        if (codeTree == null){
            throw new NullPointerException("null code tree");
        }
        this.codeTree = codeTree;
    }


    public void decompress(File input, File output) {

        try(InputStream in = new BufferedInputStream(new FileInputStream(input));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(output)) ){

            BitInputStream b_in = new BitInputStream(in);

            decompress(codeTree,b_in,out);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private  void decompress(CodeTree code, BitInputStream in, OutputStream out) throws IOException {
        HuffmanDecoder dec = new HuffmanDecoder(in); // Создаем декодер
        dec.setCodeTree(code); //устанавливаем для него дерево, чтобы декодировать
        while (true) {
            int symbol = dec.read();
            if (symbol == 256){  // End of file symbol
                break;
            }
            out.write(symbol);
        }
    }
}
