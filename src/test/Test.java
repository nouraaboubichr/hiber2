/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Chambre;
import entities.Hotel;
import entities.Type;
import java.util.List;
import services.ChambreService;
import services.HotelService;

/**
 *
 * @author hp
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       HotelService hs = new HotelService();
        ChambreService cs = new ChambreService();

        // Données de départ (une seule fois, si la base est vide)
        List<Hotel> hotels = hs.findAll();
        if (hotels == null || hotels.isEmpty()) {
            Hotel h1 = new Hotel("Atlas", "Marrakech");
            Hotel h2 = new Hotel("Riad Palace", "Fès");
            hs.create(h1);
            hs.create(h2);
            cs.create(new Chambre(400, Type.SIMPLE, "LIBRE", h1));
            cs.create(new Chambre(700, Type.DOUBLE, "OCCUPEE", h1));
            cs.create(new Chambre(1500, Type.SUITE, "LIBRE", h1));
            cs.create(new Chambre(500, Type.F1, "LIBRE", h2));
        }

        new Menu().lancer();
    }
    
}
