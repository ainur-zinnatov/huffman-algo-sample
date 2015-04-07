package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class BitInputStream {

    private InputStream input;

    private int nextBits;

    private int numBitsRemaining; //осталось битов в текущем байте

    private boolean isEndOfStream; //флажок для определения кончился ли поток



    public BitInputStream(InputStream in) {
        if (in == null){
            throw new NullPointerException("stream is null");
        }
        input = in;
        numBitsRemaining = 0;
        isEndOfStream = false;
    }



    public int read() throws IOException {
        if (isEndOfStream){
            return -1;
        }
        if (numBitsRemaining == 0) {

            nextBits = input.read(); // считываем байт

            if (nextBits == -1) {

                isEndOfStream = true;
                return -1;
            }
            numBitsRemaining = 8;
        }
        numBitsRemaining--;

        return (nextBits >>> numBitsRemaining) & 1; // возвращаем бит
    }


    public int readNoEof() throws IOException {

        int result = read();

        if (result != -1){
            return result;
        }else {
            throw new EOFException("End of file reached!");
        }
    }

}
