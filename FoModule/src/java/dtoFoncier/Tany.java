/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtoFoncier;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Tany implements Serializable{
    int id;
    int cinproprietaire;
    int cinvendeur;
    double surface;
    double perimetre;
    float prixunitaire;
    float totalprix;
    Date date;
    String color;
    String fillcolor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCinproprietaire() {
        return cinproprietaire;
    }

    public void setCinproprietaire(int cinproprietaire) {
        this.cinproprietaire = cinproprietaire;
    }

    public int getCinvendeur() {
        return cinvendeur;
    }

    public void setCinvendeur(int cinvendeur) {
        this.cinvendeur = cinvendeur;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public float getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(float prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public float getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(float totalprix) {
        this.totalprix = totalprix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFillcolor() {
        return fillcolor;
    }

    public void setFillcolor(String fillcolor) {
        this.fillcolor = fillcolor;
    }

    public double getPerimetre() {
        return perimetre;
    }

    public void setPerimetre(double perimetre) {
        this.perimetre = perimetre;
    }
    
    public Tany() {
    }
    
}
