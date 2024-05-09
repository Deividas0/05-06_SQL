package org.example;

public class Paslauga extends DataBaseTableObject{
    public String paslaugosPavadinimas;

    public Paslauga() {
        super(0);
    }


    @Override
    public String toString(){
        return "Paslaugos ID: " + id + ". Paslaugos pavadinimas: " + paslaugosPavadinimas + ".";
    }


}
