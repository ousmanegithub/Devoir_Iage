package model;

public class Employer extends Personne {
    private int salaire;
    private String zone;


    public Employer(String nom, String prenom, String adresse, int salaire, String zone) {
        super(nom, prenom, adresse);
        this.salaire = salaire;
        this.zone = zone;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
