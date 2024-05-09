package org.example;

public class Klientas extends DataBaseTableObject{
    public String vardas;
    public String pavarde;
    public String gimimoData;
    public String registracijosData;
    public boolean VIP;

    public Klientas() {
        super(0);
    }

    public Klientas(String vardas, String pavarde, String gimimoData, boolean VIP) {
        super(0);
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.gimimoData = gimimoData;
        this.VIP = VIP;
    }
    public Klientas(int id,String vardas, String pavarde, String gimimoData, String registracijosData, boolean VIP) {
        super(id);
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.gimimoData = gimimoData;
        this.registracijosData = registracijosData;
        this.VIP = VIP;

    }
    @Override
    public String toString(){
        return "Kliento ID: " +id+". Kliento vardas ir pavardė - " + vardas+" "+pavarde+". Gimimo data: " +
                gimimoData + ". Registracijos data: " + registracijosData + ". Ar asmuo yra VIP?: " + VIP;
    }

}
