/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hp
 */
import javax.persistence.*;

@Entity
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double prix;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String etat; // "LIBRE" / "OCCUPEE"

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Chambre() {}

    public Chambre(double prix, Type type, String etat, Hotel hotel) {
        this.prix = prix;
        this.type = type;
        this.etat = etat;
        this.hotel = hotel;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    @Override
    public String toString() {
        return "Chambre [id=" + id + ", prix=" + prix + ", type=" + type + ", etat=" + etat + "]";
    }
}