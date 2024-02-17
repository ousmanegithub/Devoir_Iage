package metier;

import model.Directeur;
import model.Employer;
import model.ImplementPersonne;
import model.Personne;

import java.sql.SQLException;
import java.util.Scanner;

public class Fontionnalite {
    Scanner clavier = new Scanner(System.in);

    private ImplementPersonne implementPersonne;
    public Fontionnalite(){
        implementPersonne = new ImplementPersonne();
    }
    public int menu(){
        int choix;

        System.out.println("1-Ajouter personne");
        System.out.println("2-Afficher Directeur");
        System.out.println("3-Afficher employer");
        System.out.println("4-Supprimer personne");
        System.out.println("5-Mettre à jour");
        System.out.println("6-Quitter");
        do{
            choix = clavier.nextInt();
            if(choix<1 || choix >6){
                System.out.println("Le choix est incorrect .");
            }
        }while(choix<1 || choix >6);
        return  choix;
    }

    public int menuTypePersonne(){
        int choix;

        System.out.println("1-Directeur");
        System.out.println("2-Employer");
        do{
            choix = clavier.nextInt();
            if(choix<1 || choix >2){
                System.out.println("Le choix est incorrect .");
            }
        }while(choix<1 || choix >2);
        return  choix;
    }
    public void ajoutPersonne() throws SQLException {
        int type = menuTypePersonne();
        Personne personne = (type == 1) ?  new Directeur() : new Employer();

        clavier.nextLine();
        System.out.println("Saisir le nom");
        personne.setNom(clavier.nextLine());
        System.out.println("Saisir le solde du compte");
        personne.setPrenom(clavier.nextLine());
        clavier.nextLine();
        System.out.println("Saisir le devise du compte");
        personne.setAdresse(clavier.nextLine());
        clavier.nextLine();
        if( type == 1 ){
            System.out.println("Le Matricule");
            ((Directeur)personne).setMatricule(clavier.nextLine());
        }else {
            System.out.println("Saisir le taux");
            ((Employer)personne).setZone(clavier.nextLine());
        }
        implementPersonne.addPersonne(personne);
    }
}
