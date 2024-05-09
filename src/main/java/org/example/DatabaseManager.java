package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseManager implements DBManager {
    private Connection _connection;

    public DatabaseManager(String URL, String USERNAME, String PASSWORD) throws SQLException {
        _connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    @Override
    public void naujasKlientas(Klientas klientas) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String sql = "INSERT INTO klientai (vardas,pavarde,gimimo_data,registracijos_data,VIP) VALUES (?, ?, ?, DEFAULT, ?)";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setString(1, klientas.vardas);
        statement.setString(2, klientas.pavarde);
        statement.setString(3, klientas.gimimoData);
        //statement.setString(4, klientas.registracijosData);
        statement.setBoolean(4, klientas.VIP);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Klientas sėkmingai įregistruotas.");
        } else {
            System.out.println("Kliento registracija nepavyko.");
        }
    }

    @Override
    public List<Klientas> klientuSarasas() throws SQLException {
        String sql = "SELECT * FROM klientai";
        PreparedStatement statement = _connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Klientas> klientuSarasas = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String vardas = resultSet.getString("vardas");
            String pavarde = resultSet.getString("pavarde");
            String gimimoData = resultSet.getString("gimimo_data");
            String registracijosData = resultSet.getString("registracijos_data");
            boolean vip = resultSet.getBoolean("VIP");
            Klientas klientas = new Klientas(id, vardas, pavarde, gimimoData, registracijosData, vip);
            klientuSarasas.add(klientas);
        }
        return klientuSarasas;
    }
    @Override
    public List<Darbuotojas> darbuotojuSarasas() throws SQLException {
        String sql = "SELECT * FROM darbuotojai";
        PreparedStatement statement = _connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Darbuotojas> darbuotojuSarasas = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String vardasPavarde = resultSet.getString("vardas_pavarde");

            Darbuotojas darbuotojas = new Darbuotojas(id, vardasPavarde);
            darbuotojuSarasas.add(darbuotojas);
        }
        return darbuotojuSarasas;
    }

    @Override
    public void paslaugosNaudojimas(Klientas klientas, Darbuotojas darbuotojas, Paslauga paslauga, Double Suma) throws SQLException {
        String paslaugosNaudojimas = "SELECT k.id, d.id,p.pavadinimas, m.mokejimo_suma\n" +
                "FROM klientai k\n" +
                "LEFT JOIN darbuotojai d ON k.id = d.id\n" +
                "LEFT JOIN mokejimai m ON k.id = m.kliento_id\n" +
                "LEFT JOIN paslaugos p ON k.id = p.id";
        PreparedStatement statement = _connection.prepareStatement(paslaugosNaudojimas);
    }

    @Override
    public List<Klientas> paieskaKlientuSarasas(String fraze) throws SQLException {
        String sqlFraze = "SELECT * FROM klientai WHERE vardas LIKE ? OR pavarde LIKE ?";
        PreparedStatement statement = _connection.prepareStatement(sqlFraze);
        statement.setString(1, "%" + fraze + "%");
        statement.setString(2, "%" + fraze + "%");
        ResultSet resultSet = statement.executeQuery();
        List<Klientas> klientuSarasas = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String vardas = resultSet.getString("vardas");
            String pavarde = resultSet.getString("pavarde");
            String gimimoData = resultSet.getString("gimimo_data");
            String registracijosData = resultSet.getString("registracijos_data");
            boolean vip = resultSet.getBoolean("VIP");
            Klientas klientas = new Klientas(id, vardas, pavarde, gimimoData, registracijosData, vip);
            klientuSarasas.add(klientas);
        }
        return klientuSarasas;
    }

    @Override
    public void naujaPaslauga(String pavadinimas) throws SQLException {
        String sql = "INSERT INTO paslaugos (pavadinimas) VALUES (?)";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setString(1, pavadinimas);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Paslauga sėkmingai įregistruota.");
            System.out.println();
        } else {
            System.out.println("Paslaugos registracija nepavyko.");
            System.out.println();
        }

    }

    @Override
    public void naujasDarbuotojas(String vardasPavarde) throws SQLException {
        String sql = "INSERT INTO darbuotojai (vardas_pavarde) VALUES (?)";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setString(1, vardasPavarde);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Darbuotojas sėkmingai įregistruotas.");
            System.out.println();
        } else {
            System.out.println("Darbuotojo registracija nepavyko.");
            System.out.println();
        }
    }

}



