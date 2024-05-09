package org.example;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        System.out.println("7. paslaugosNaudojimas.");
        System.out.println("8. Patikrinti mokėjimus.");
        System.out.println("9. Registruoti vizitą.");
        System.out.println("10. Patikrinti artimiausia vizitą.");
        System.out.println("11. Patikrinti artimiausia vizitą pagal kliento ID.");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("Šiuo metu saraše esantys klientai");
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
            case 7:
                System.out.println("Pasirinkite kliento ID: ");
                spausdinamSarasa = dbManager.klientuSarasas();
                for (Klientas a : spausdinamSarasa) {
                    System.out.println(a);
                }
                int klientas = sc.nextInt();            // turime kliento ID
                Klientas pasirinkitasKlientas = new Klientas();
                for (Klientas a : spausdinamSarasa) {
                    if (a.id == klientas) {
                        pasirinkitasKlientas = a;
                        break;
                    }
                }

                System.out.println("Pasirinkite darbuotojo ID: ");
                spausdinamDarbuotojus = dbManager.darbuotojuSarasas();
                for (Darbuotojas a : spausdinamDarbuotojus) {
                    System.out.println(a);
                }
                int darbuotojas = sc.nextInt();                   // turime darbuotojo ID
                Darbuotojas pasirinktasDarbuotojas = new Darbuotojas();
                for (Darbuotojas a : spausdinamDarbuotojus) {
                    if (a.id == darbuotojas) {
                        pasirinktasDarbuotojas = a;
                    }
                }

                System.out.println("Pasirinkite paslaugos ID: ");
                List<Paslauga> paslauguSarasas = dbManager.paslauguSarasas();
                for (Paslauga a : paslauguSarasas) {
                    System.out.println(a);
                }
                int paslauga = sc.nextInt();                        // turime paslaugos ID
                Paslauga pasirinktaPaslauga = new Paslauga();
                for (Paslauga a : paslauguSarasas) {
                    if (a.id == paslauga) {
                        pasirinktaPaslauga = a;
                    }
                }

                System.out.println("Įveskite sumą: ");
                double suma = sc.nextDouble();
                dbManager.paslaugosNaudojimas(pasirinkitasKlientas, pasirinktasDarbuotojas, pasirinktaPaslauga, suma);
                System.out.println();
                veiksmas(dbManager);
            case 8:
                System.out.println("Visi mokėjimai");
                List<Mokejimas> mokejimuSarasas = dbManager.mokejimuSarasas();
                for (Mokejimas a : mokejimuSarasas) {
                    System.out.println(a);
                }
                System.out.println();
                veiksmas(dbManager);
            case 9:
                System.out.println("Pasirinkite kliento ID: ");
                spausdinamSarasa = dbManager.klientuSarasas();
                for (Klientas a : spausdinamSarasa) {
                    System.out.println(a);
                }
                klientas = sc.nextInt();                        // turime kliento ID
                pasirinkitasKlientas = new Klientas();
                for (Klientas a : spausdinamSarasa) {
                    if (a.id == klientas) {
                        pasirinkitasKlientas = a;
                        break;
                    }

                }
                System.out.println("Pasirinkite paslaugos ID: ");
                paslauguSarasas = dbManager.paslauguSarasas();
                for (Paslauga a : paslauguSarasas) {
                    System.out.println(a);
                }
                paslauga = sc.nextInt();                        // turime paslaugos ID
                pasirinktaPaslauga = new Paslauga();
                for (Paslauga a : paslauguSarasas) {
                    if (a.id == paslauga) {
                        pasirinktaPaslauga = a;
                    }
                }
                System.out.println("Įveskite norima rezervuoti laiką yyyy-MM-dd HH:mm:ss");
                sc.nextLine();
                LocalDateTime data = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                dbManager.naujasVizitas(pasirinkitasKlientas, pasirinktaPaslauga, data);

                System.out.println();
                veiksmas(dbManager);
            case 10:
                System.out.println("Artimiausias vizitas: ");
                List<Vizitai> artimiausiasVizitas = dbManager.artimiausiasVizitas();
                for (Vizitai a : artimiausiasVizitas) {
                    System.out.println(a);
                }
                System.out.println();
                veiksmas(dbManager);
            case 11:
                System.out.println("Įveskite kliento ID pagal kurį norite patikrinti artimiausia vizito datą.");
                sc.nextLine();
                int klientoId = sc.nextInt();
                for (Vizitai a : dbManager.artimiausiasVizitasPagalKlientoId(klientoId)) {
                    System.out.println(a);
                }
                System.out.println();
                veiksmas(dbManager);


        }
    }
}