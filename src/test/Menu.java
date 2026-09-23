/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Chambre;
import entities.Hotel;
import java.util.List;
import java.util.Scanner;
import services.ChambreService;
import services.HotelService;
/**
 *
 * @author hp
 */


public class Menu {

    private final HotelService hs = new HotelService();
    private final ChambreService cs = new ChambreService();
    private final Scanner sc = new Scanner(System.in);

    public void lancer() {
        int choix;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Afficher les chambres par hôtel");
            System.out.println("2. Rechercher les chambres par état et prix");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = lireEntier();

            switch (choix) {
                case 1:
                    afficherParHotel();
                    break;
                case 2:
                    rechercherParEtatEtPrix();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);

        sc.close();
    }

    private void afficherParHotel() {
        System.out.println("--- Hôtels ---");
        for (Hotel h : hs.findAll()) {
            System.out.println(h.getId() + " - " + h.getNom() + " (" + h.getAdresse() + ")");
        }
        System.out.print("Id de l'hôtel : ");
        int id = lireEntier();
        afficher(cs.findByHotel(id));
    }

    private void rechercherParEtatEtPrix() {
        System.out.print("Saisir l'état (LIBRE / OCCUPEE) : ");
        String etat = sc.nextLine().trim().toUpperCase();

        System.out.print("Saisir le prix : ");
        double prix = lireDouble();

        afficher(cs.findByEtatAndPrix(etat, prix));
    }

    private void afficher(List<Chambre> chambres) {
        if (chambres == null || chambres.isEmpty()) {
            System.out.println("Aucune chambre trouvée.");
            return;
        }
        for (Chambre c : chambres) {
            System.out.println("Chambre " + c.getId() + " | " + c.getType()
                    + " | " + c.getPrix() + " DH | " + c.getEtat());
        }
    }

    private int lireEntier() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Nombre invalide, réessayez : ");
            }
        }
    }

    private double lireDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Prix invalide, réessayez : ");
            }
        }
    }
}
