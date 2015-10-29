package com.kilomobi.bbfoot.Model;

import java.io.Serializable;

/**
 * Created by Nawako on 22/10/2015.
 */
public class Player implements Serializable {

    private int _id;
    private String nom;
    private String prenom;
    private String image;
    private boolean isSelected;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
