package org.example;

public class Darbuotojas extends DataBaseTableObject{
    public String vardasPavarde;

    public Darbuotojas(){
        super(0);
    }

    public Darbuotojas(int id, String vardasPavarde) {
        super(id);
        this.vardasPavarde = vardasPavarde;
    }

    @Override
    public String toString(){
        return "Darbuotojo ID: " + id + ". Darbuotojo vardas ir pavardė: " + vardasPavarde + ". ";
    }
}
