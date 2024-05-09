package org.example;

import java.sql.SQLException;
import java.util.List;

public interface DBManager {

    void naujasKlientas(Klientas klientas) throws SQLException;
    List<Klientas> klientuSarasas() throws SQLException;

    List<Klientas> paieskaKlientuSarasas(String fraze) throws SQLException;

    void naujaPaslauga(String pavadinimas) throws SQLException;

    void naujasDarbuotojas(String vardasPavarde) throws SQLException;

    List<Darbuotojas> darbuotojuSarasas() throws SQLException;

     void paslaugosNaudojimas(Klientas klientas, Darbuotojas darbuotojas, Paslauga paslauga, Double Suma) throws SQLException;

}
