package model;

public class Directeur extends Personne {

    private String matricule;


    public Directeur(String nom, String prenom, String adresse,String matricule) {
        super(nom, prenom, adresse);
        this.matricule = generatematricule();
    }

    public Directeur(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    private String generatematricule() {
        return this.getPrenom().substring(0, 1) +
                "000" +
                this.getNom().length();
    }


}
