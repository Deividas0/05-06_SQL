package org.example;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface DBManager {

    void naujasKlientas(Klientas klientas) throws SQLException;
    List<Klientas> klientuSarasas() throws SQLException;

    List<Klientas> paieskaKlientuSarasas(String fraze) throws SQLException;

    void naujaPaslauga(String pavadinimas) throws SQLException;
    List<Paslauga> paslauguSarasas() throws SQLException;

    void naujasDarbuotojas(String vardasPavarde) throws SQLException;

    List<Darbuotojas> darbuotojuSarasas() throws SQLException;

     void paslaugosNaudojimas(Klientas klientas, Darbuotojas darbuotojas, Paslauga paslauga, double Suma) throws SQLException;

    List<Mokejimas> mokejimuSarasas() throws SQLException;

    void naujasVizitas(Klientas klientas, Paslauga paslauga, LocalDateTime date) throws SQLException;

    List<Vizitai> artimiausiasVizitas() throws SQLException;

    List<Vizitai> artimiausiasVizitasPagalKlientoId(int klientas) throws SQLException;
}
