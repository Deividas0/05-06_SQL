package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public void paslaugosNaudojimas(Klientas klientas, Darbuotojas darbuotojas, Paslauga paslauga, double suma) throws SQLException {
        String paslaugosNaudojimas = "INSERT INTO mokejimai (kliento_id,aptarnaujancio_darbuotojo_id,paslauga,mokejimo_suma) VALUES (?,?,?,?)";
        PreparedStatement statement = _connection.prepareStatement(paslaugosNaudojimas);
        statement.setInt(1, klientas.id);
        statement.setInt(2, darbuotojas.id);
        statement.setString(3, paslauga.paslaugosPavadinimas);
        statement.setDouble(4, suma);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Duomenys sėkmingai įregistruoti.");
        } else {
            System.out.println("Rregistracija nepavyko.");
        }

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
    public List<Paslauga> paslauguSarasas() throws SQLException {
        String sql = "SELECT * FROM paslaugos";
        PreparedStatement statement = _connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Paslauga> paslauguSarasas = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String paslaugosPavadinimas = resultSet.getString("pavadinimas");

            Paslauga paslauga = new Paslauga(id, paslaugosPavadinimas);
            paslauguSarasas.add(paslauga);
        }
        return paslauguSarasas;
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
    @Override
    public List<Mokejimas> mokejimuSarasas() throws SQLException {
        String sql = "SELECT * FROM mokejimai";
        PreparedStatement statement = _connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Mokejimas> mokejimuSarasas = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("mokejimo_id");
            int klientoId = resultSet.getInt("kliento_id");
            int darbuotojoId = resultSet.getInt("aptarnaujancio_darbuotojo_id");
            String paslauga = resultSet.getString("paslauga");
            double suma = resultSet.getDouble("mokejimo_suma");
            Mokejimas mokejimas = new Mokejimas(id, klientoId, darbuotojoId, paslauga, suma);
            mokejimuSarasas.add(mokejimas);
        }
        return mokejimuSarasas;
    }
    @Override
    public void naujasVizitas(Klientas klientas, Paslauga paslauga, LocalDateTime date) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatuotaDate = date.format(formatter);
        String sql = "INSERT INTO vizitai (kliento_id, paslaugos_id, rezervuotas_laikas) VALUES (?,?,?)";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setInt(1, klientas.id);
        statement.setInt(2, paslauga.id);
        statement.setString(3, formatuotaDate);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Duomenys sėkmingai įregistruoti.");
        } else {
            System.out.println("Rregistracija nepavyko.");
        }

    }
    @Override
    public List<Vizitai> artimiausiasVizitas() throws SQLException {
        String sql = "SELECT * FROM vizitai ORDER BY rezervuotas_laikas ASC LIMIT 1";
        PreparedStatement statement = _connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Vizitai> artimiausiasVizitas = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("vizito_id");
            int klientoId = resultSet.getInt("kliento_id");
            int paslaugosId = resultSet.getInt("paslaugos_id");
            LocalDateTime rezervuotaData = LocalDateTime.parse(resultSet.getString("rezervuotas_laikas"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Vizitai vizitas = new Vizitai(id, klientoId, paslaugosId, rezervuotaData);
            artimiausiasVizitas.add(vizitas);
        }
        return artimiausiasVizitas;
    }
    @Override
    public List<Vizitai> artimiausiasVizitasPagalKlientoId(int klientas) throws SQLException {
        String sqlFraze = "SELECT * FROM vizitai WHERE kliento_id = ? ORDER BY rezervuotas_laikas ASC LIMIT 1";
        PreparedStatement statement = _connection.prepareStatement(sqlFraze);
        statement.setInt(1, klientas);
        ResultSet resultSet = statement.executeQuery();
        List<Vizitai> vizituSarasas = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("vizito_id");
            int klientoId = resultSet.getInt("kliento_id");
            int paslaugosId = resultSet.getInt("paslaugos_id");
            LocalDateTime rezervuotasLaikas = LocalDateTime.parse(resultSet.getString("rezervuotas_laikas"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Vizitai vizitas = new Vizitai(id, klientoId, paslaugosId, rezervuotasLaikas);
            vizituSarasas.add(vizitas);
        }
        return vizituSarasas;
    }

}



