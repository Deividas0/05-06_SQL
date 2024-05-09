package org.example;

public class Mokejimas extends DataBaseTableObject{
    int klientoId;
    int darbuotojoId;
    String paslauga;
    double mokejimoSuma;

    public Mokejimas(int id, int klientoId, int darbuotojoId, String paslauga, double mokejimoSuma) {
        super(id);
        this.klientoId = klientoId;
        this.darbuotojoId = darbuotojoId;
        this.paslauga = paslauga;
        this.mokejimoSuma = mokejimoSuma;
    }

    public Mokejimas() {
        super(0);
    }

    @Override
    public String toString(){
        return "Mokėjimo ID: " + id + ". Kliento ID: " + klientoId + ". Darbuotojo ID: " + darbuotojoId + ". Paslauga: " +
                paslauga + ". Mokėjimo suma: " + mokejimoSuma + ". ";
    }
}
