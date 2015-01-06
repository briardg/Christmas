
package com.bf.christmas.DAO;

import com.bf.christmas.DAO.dbConnection.DBConnection;
import com.bf.christmas.model.Present;
import com.bf.christmas.model.User;
import java.sql.Connection;
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
public class PresentDAO {
    
    
    public static Present read(int id) throws SQLException, NamingException{
        //read info present;
        Connection  c=null; 
        try {
            Present p=null;
            c  = DBConnection.getConnection();
            String sql="select p.* " +
                       "from Present p "+
                       "where p.id = " + id +
                       " ;";
            ResultSet res = c.createStatement().executeQuery(sql);
            while(res.next()){
               p = new Present(res.getInt("id"), res.getString("name"),res.getString("description") ,res.getDouble("price"));
            }
            
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    
    public static List<Present> readAll(int idOwner) throws SQLException, NamingException{
        Connection  c=null; 
        try {
            List<Present> presents = new LinkedList<Present>();
            //read info presents without this user;
            c  =DBConnection.getConnection();
            String sql="select p.*, SUM(b.percentage) as percentage " +
                       "from Present p left join buyby b on p.id=b.present_id "+
                       "where p.owner="+idOwner+" " +
                       "group by p.id, p.name,p.description,p.owner,p.price " +
                       ";";
            ResultSet res = c.createStatement().executeQuery(sql);
            while(res.next()){
                presents.add(new Present(res.getInt("id"), res.getString("name"),res.getString("description") ,res.getDouble("price"),res.getDouble("percentage")));
            }
            
            return presents;
        } catch (SQLException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    
    public static void save(Present p) throws SQLException, NamingException{
        //save p present
        Connection  c=null; 
        try {
            c  =DBConnection.getConnection();
            String sql="INSERT INTO Present (name, description, owner, price) " +
                       "VALUES ('"+p.getName()+"', '"+p.getDescription()+"','"+p.getOwner().getId()+"', '"+p.getPrice()+"');" +
                       " ;";
            c.createStatement().executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
            
        } catch (NamingException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    
    public static void update(Present p) throws SQLException, NamingException{
        
        //update this p present
        Connection  c=null; 
        try {
            c  =DBConnection.getConnection();
            String sql="UPDATE Present " +
                       "SET price = " + p.getPrice() +
                       ", name= '" + p.getName() +
                       "', description='"+ p.getDescription() +
                       "' WHERE id = " + p.getId()+
                       " ;";
            c.createStatement().executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    
    public static void buy(int idUser,int idPresent, int percentage) throws SQLException, NamingException{
        Connection  c=null; 
        try {
            c  =DBConnection.getConnection();
            //insert percentage with
            
            String sql="INSERT INTO buyby (present_id, user_id, comment, percentage) VALUES "+
                       "('"+idPresent+"', '"+idUser+"', NULL, '"+percentage+"') " +
                       " ;";
            c.createStatement().executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    
    public static void delete(int idPresent) throws SQLException, NamingException {
        Connection  c=null; 
        try {
            c  =DBConnection.getConnection();
            String sql="DELETE FROM Present WHERE id = " +
                       idPresent +
                       " ;";
            c.createStatement().executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
}
