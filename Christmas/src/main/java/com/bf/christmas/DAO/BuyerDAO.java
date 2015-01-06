/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bf.christmas.DAO;

import com.bf.christmas.DAO.dbConnection.DBConnection;
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
public class BuyerDAO {
    
    
    public static List<User> readAll(int idPresent) throws  SQLException, NamingException{
        Connection  c=null;
        try {
            List<User> users = new LinkedList<User>();
            //read info presents without this user;
            c  =DBConnection.getConnection();
            String sql="Select u.* from buyby b inner join user u on b.user_id=u.id where b.present_id="+idPresent+";";
            ResultSet res = c.createStatement().executeQuery(sql);
            while(res.next()){
                users.add(new User(res.getInt("id"),res.getString("firstname"),res.getString("lastname"), res.getString("login")));
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(PresentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (NamingException ex) {
            Logger.getLogger(BuyerDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        finally{
            if(c!=null)
                c.close();
        }
    }
    
}
