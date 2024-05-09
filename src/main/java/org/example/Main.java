package org.example;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {


    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        final String URL = "jdbc:mysql://localhost:3306/javadarbas";
        final String USERNAME = "root";
        final String PASSWORD = "l3g10n4s";
        DBManager dbManager = new DatabaseManager(URL, USERNAME, PASSWORD);
        veiksmas(dbManager);
    }
    public static Klientas sukurtiKlienta() {
        System.out.println("Įveskite vardą:");
        sc.nextLine();
        String vardas = sc.nextLine();
        System.out.println("Įveskite pavardę:");
        String pavarde = sc.nextLine();
        System.out.println("Įveskite gimimo datą (yyyy-mm-dd):");
        String gimimoData = sc.nextLine();
        System.out.println("Ar klientas yra VIP? (true/false):");
        boolean vip = sc.nextBoolean();

        Klientas klientas = new Klientas(vardas, pavarde, gimimoData, vip);
        return klientas;
    }

    static void veiksmas(DBManager dbManager) throws SQLException {
        System.out.println("Pasirinkite veiksmą kurį norite atlikti.");
        System.out.println("1. Patikrinti klientų sąrašą.");
        System.out.println("2. Pridėti nauja klienta prie sąrašo.");
        System.out.println("3. Surasti klienta iš sąrašo pagal įvesta 'fraze'.");
        System.out.println("4. Sukurti nauja paslauga.");
        System.out.println("5. Pridėti nauja darbuotoją.");
        System.out.println("6. Patikrinti darbuotojų sąrašą.");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("Šiuo metu saraše esantys klientai");
                dbManager.klientuSarasas();
                List<Klientas> spausdinamSarasa = dbManager.klientuSarasas();
                for (Klientas a : spausdinamSarasa) {
                    System.out.println(a);
                }
                System.out.println();
                veiksmas(dbManager);
            case 2:
                dbManager.naujasKlientas(sukurtiKlienta());
                System.out.println();
                veiksmas(dbManager);
            case 3:
                System.out.println("Įveskite 'fraze' pagal kurią norite ieškoti kliento.");
                sc.nextLine();
                String fraze = sc.nextLine();
                for (Klientas a : dbManager.paieskaKlientuSarasas(fraze)) {
                    System.out.println(a);
                }
                System.out.println();
                veiksmas(dbManager);
            case 4:
                System.out.println("Įveskite paslaugos pavadinimą: ");
                sc.nextLine();
                String pavadinimas = sc.nextLine();
                dbManager.naujaPaslauga(pavadinimas);
                veiksmas(dbManager);
            case 5:
                System.out.println("Įveskite naujo darbuotojo vardą ir pavardę:");
                sc.nextLine();
                String vardasPavarde = sc.nextLine();
                dbManager.naujasDarbuotojas(vardasPavarde);
                veiksmas(dbManager);
            case 6:
                System.out.println("Šiuo metu esami darbuotojai: ");
                dbManager.darbuotojuSarasas();
                List<Darbuotojas> spausdinamDarbuotojus = dbManager.darbuotojuSarasas();
                for (Darbuotojas a : spausdinamDarbuotojus) {
                    System.out.println(a);
                }
                System.out.println();
                veiksmas(dbManager);

        }
    }
}