package eu.ensup.gestionetablissement.dao;

import eu.ensup.gestionetablissement.business.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoursePersonDao
{
    String className = getClass().getName();

    /*public Map<Person, Course> getAll() throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Connection cn = Connect.openConnection();
        List<Mark> allMark = new ArrayList<Mark>();

        Statement st = null;
        ResultSet res = null;
        try
        {
            st = cn.createStatement();
            res = st.executeQuery("SELECT * FROM Mark");
            if(!res.next()){
                throw new ExceptionDao("Aucun mark disponible dans la base de donnée.");
            }
            while( res.next() )
            {
                Mark cours = new Mark(res.getInt("id"), res.getInt("idStudent"), res.getInt("idCourse"), res.getFloat("mark"), res.getString("assessment"));

                allMark.add(cours);
            }

            // TODO:  Add logger failed and successfull
            if(allMark.isEmpty())
            {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant tous les Mark.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant tous les mark a réussie.");
            st.close();
            cn.close();
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }

        return allMark;
    }

    public Map<Person, Course> getAllByIdPerson(int idPerson) throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Connection cn = Connect.openConnection();
        List<Mark> allMark = new ArrayList<Mark>();

        Statement st = null;
        ResultSet res = null;
        try
        {
            st = cn.createStatement();
            res = st.executeQuery("SELECT * FROM Mark");
            if(!res.next()){
                throw new ExceptionDao("Aucun mark disponible dans la base de donnée.");
            }
            while( res.next() )
            {
                Mark cours = new Mark(res.getInt("id"), res.getInt("idStudent"), res.getInt("idCourse"), res.getFloat("mark"), res.getString("assessment"));

                allMark.add(cours);
            }

            // TODO:  Add logger failed and successfull
            if(allMark.isEmpty())
            {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant tous les Mark.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant tous les mark a réussie.");
            st.close();
            cn.close();
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }

        return allMark;
    }

    public Map<Person, Course> getAllByIdCourse(int idCourse) throws ExceptionDao
    {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        Connection cn = Connect.openConnection();
        List<Mark> allMark = new ArrayList<Mark>();

        Statement st = null;
        ResultSet res = null;
        try
        {
            st = cn.createStatement();
            res = st.executeQuery("SELECT * FROM Mark");
            if(!res.next()){
                throw new ExceptionDao("Aucun mark disponible dans la base de donnée.");
            }
            while( res.next() )
            {
                Mark cours = new Mark(res.getInt("id"), res.getInt("idStudent"), res.getInt("idCourse"), res.getFloat("mark"), res.getString("assessment"));

                allMark.add(cours);
            }

            // TODO:  Add logger failed and successfull
            if(allMark.isEmpty())
            {
                DaoLogger.logDaoError(className, methodName,"Echec de récupération d'information concernant tous les Mark.");
            }

            DaoLogger.logDaoInfo(className, methodName,"La récupération des informations concernant tous les mark a réussie.");
            st.close();
            cn.close();
        }
        catch (SQLException e) {

            // TODO:  Add logger failed and successfull
            DaoLogger.logDaoError(className, methodName,"La transaction SELECT dans la méthode getAll a échouée.",e);
            throw new ExceptionDao("Un problème est survenu au niveau de la base de donnée.");
        }

        return allMark;
    }

    public int linkToCourse(int entity, int course) throws ExceptionDao {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

        try {
            cn = Connect.openConnection();

            String sql_request = "INSERT INTO Course_Person(idPerson, idCourse) VALUES (?, ?)";
            st = cn.prepareStatement(sql_request);
            st.setInt(1, entity);
            st.setInt(2, course);

            res = st.executeUpdate();
            if(res == 0)
            {
                DaoLogger.logDaoError(className, methodName, "Echec lors de la liasion de l'utilisateur au cours demandé.");
                throw new ExceptionDao("Échec lors de la tentative de création de lien entre cette personne et le cours demandé. Le cours ou l'étudiant n'existe pas.");
            }
            else {
                DaoLogger.logDaoInfo(className, methodName, "Le lien entre l'utilisateur et le cours a bien été créé.");
            }
            cn.close();

        } catch (SQLException e) {
            DaoLogger.logDaoError(className, methodName,"La transaction INSERT dans la méthode LinkToCourse a échouée.",e);
            throw new ExceptionDao("Impossible de lier l'utilisateur à ce cours. Veuillez contacter votre administrateur.");
        }
        return 0;
    }*/
}
