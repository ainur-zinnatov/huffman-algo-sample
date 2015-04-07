package ru.kpfu.itis.group11403.AinurAndSafin.projectZip;

/**
 * класс Leaf - лист
 * содержит поле symbol -  символ(char)
 */
public final class Leaf extends Node {

    private final int symbol;
    
    public int getSymbol() {

        return symbol;
    }

    public Leaf(int symbol) {
        if (symbol < 0){
            throw new IllegalArgumentException("Illegal symbol value");
        }
        this.symbol = symbol;
    }

}
