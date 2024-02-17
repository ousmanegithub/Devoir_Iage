package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementPersonne implements IPersonne<Personne>{

    public Connection connection;
    public ImplementPersonne(){this.connection = new BD().getConnection();}
    @Override
    public void addPersonne(Personne personne) throws SQLException {
        boolean  result = personne instanceof Directeur;
        String choix = (result) ? "matricule":"zone";
        String sql = "Insert Into personnes (nom,prenom,adresse,"+choix+") value(?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1, personne.getNom());
        statement.setString(2, personne.getPrenom());
        statement.setString(3, personne.getAdresse());
        if ((result)) {
            statement.setString(4, ((Directeur) personne).getMatricule());
        } else {
            statement.setString(4, ((Employer) personne).getZone());
            statement.setInt(5,((Employer) personne).getSalaire());
        }
        statement.executeUpdate();

    }

    @Override
    public void UpdatePersonne(int id, Personne personne) throws SQLException {
        boolean  result = personne instanceof Directeur;
        String choix = (result) ? "matricule":"zone";
        String sql = "Update personnes Set nom = ?, prenom = ?, adresse= ?,"+choix+", where id = ?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1, personne.getNom());
        statement.setString(2, personne.getPrenom());
        statement.setString(3, personne.getAdresse());
        if ((result)) {
            statement.setString(4, ((Directeur) personne).getMatricule());
        } else {
            statement.setString(4, ((Employer) personne).getZone());
            statement.setInt(5,((Employer) personne).getSalaire());
        }
        statement.setInt(6,id);
        statement.executeUpdate();

    }

    @Override
    public void DeleteByMatricule(String matricule, Personne personne) throws SQLException {
        String sql = "Delete * from personnes where matricule = ?";
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,matricule);
        statement.executeUpdate();
        throw new SQLException("le matricule n'existe pas");

    }

    @Override
    public List<Personne> getAllDirecteur() throws SQLException {
        List<Personne> personnes = new ArrayList<>();
        String sql = "SELECT * FROM personnes where matricule is not null";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result= statement.executeQuery();
        while(result.next()){
            personnes.add(generatePersonne(result));
        }
        return personnes;
    }

    @Override
    public List<Personne> getAllEmployer() throws SQLException {
        List<Personne> personnes = new ArrayList<>();
        String sql = "SELECT * FROM personnes where salaire is not null";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result= statement.executeQuery();
        while(result.next()){
            personnes.add(generatePersonne(result));
        }
        return personnes;
    }


    private Personne generatePersonne(ResultSet result) throws SQLException {
        Personne personne;
        if(result.getInt("salaire") != 0){
            personne  = new Employer(
                    result.getString("nom"),
                    result.getString("prenom"),
                    result.getString("adresse"),
                    result.getInt("salaire"),
                    result.getString("zone")
            );
        }else{
            personne  = new Directeur(
                    result.getString("nom"),
                    result.getString("prenom"),
                    result.getString("adresse"),
                    result.getString("matricule")


            );
        }
        return  personne;
    }

    }

