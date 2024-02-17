package model;

import java.sql.SQLException;
import java.util.List;

public interface IPersonne<T> {
    void addPersonne(T t) throws SQLException;
    void UpdatePersonne(int id,T t) throws SQLException;
    void DeleteByMatricule(String matricule,T t) throws SQLException;
    List<T> getAllDirecteur() throws SQLException;
    List<T> getAllEmployer() throws SQLException;
}
