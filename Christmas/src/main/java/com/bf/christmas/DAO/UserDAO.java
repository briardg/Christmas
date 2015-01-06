package com.bf.christmas.DAO;

import com.bf.christmas.DAO.dbConnection.DBConnection;
import com.bf.christmas.model.Present;
import com.bf.christmas.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Gaut
 */
public class UserDAO {
    
    public static User read(int id){
        return null;
    }
    
    public static List<User> readAll() throws SQLException, NamingException{
        Connection  c=null; 
        try {
            List<User> users = new LinkedList<User>();
            //read info presents without this user;
            c  =DBConnection.getConnection();
            String sql="Select *  from user";
            ResultSet res = c.createStatement().executeQuery(sql);
            while(res.next()){
                users.add(new User(res.getInt("id"),res.getString("firstname"),res.getString("lastname"), res.getString("login")));
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    public static List<User> readAllDifferentFrom(int idUser) throws SQLException, NamingException{
        Connection  c=null; 
        try {
            List<User> users = new LinkedList<User>();
            //read info presents without this user;
            c  =DBConnection.getConnection();
            String sql="SELECT distinct u.* \n" +
                        "FROM user u\n" +
                        "LEFT JOIN Procuration p ON u.id = p.user_id1\n" +
                        "WHERE u.id <> ? \n" +
                        "AND  \n" +
                        "( p.user_id1 <> ? OR p.user_id1 IS NULL )\n" +
                        "AND \n" +
                        "( p.user_id2 <> ? OR p.user_id2 IS NULL )\n" +
                        "AND \n" +
                        "( p.user_id1 IS NULL OR p.user_id1 NOT IN \n" +
                        " (\n" +
                        "     SELECT user_id2 \n" +
                        "     FROM Procuration \n" +
                        "     WHERE user_id1 = ? \n" +
                        " )\n" +
                        ")\n" +
                        "";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, idUser);
            p.setInt(2, idUser);
            p.setInt(3, idUser);
            p.setInt(4, idUser);
            ResultSet res = p.executeQuery();
            while(res.next()){
                users.add(new User(res.getInt("id"),res.getString("firstname"),res.getString("lastname"), res.getString("login")));
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    
    public static User getByLogin(String login) throws SQLException, NamingException{
        Connection  c=null; 
        try {
            User user = null;
            //read info presents without this user;
            c  =DBConnection.getConnection();
            
            String sql="Select *  from user where login=?;";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1,login);
            ResultSet res = p.executeQuery();
            while(res.next()){
                user= new User(res.getInt("id"),res.getString("firstname"),res.getString("lastname"), res.getString("login"));
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    
    public static void save(User p){
        //save p present
    }
    
    public static void update(User p){
        //update this p present
    }
    
    public static void delete(int idUser){
        //delete this present
    }
}
