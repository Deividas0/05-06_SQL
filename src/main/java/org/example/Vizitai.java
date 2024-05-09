package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Vizitai extends  DataBaseTableObject{
    int klientoId;
    int paslaugosId;
    LocalDateTime rezervuotasLaikas;

    public Vizitai(int id, int klientoId, int paslaugosId, LocalDateTime rezervuotasLaikas) {
        super(id);
        this.klientoId = klientoId;
        this.paslaugosId = paslaugosId;
        this.rezervuotasLaikas = rezervuotasLaikas;
    }

    public Vizitai() {
        super(0);
    }
    @Override
    public String toString(){
        String formattedDateTime = rezervuotasLaikas.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));;
        return "Vizito ID: " + id + ". Kliento ID: " + klientoId + ". Paslaugos ID: " +
                paslaugosId + ". Vizitui registruota data: " + formattedDateTime + ".";
    }
}
