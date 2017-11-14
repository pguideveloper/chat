/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author cg3000109
 */
public class CRUD {
    
    DatabaseConnection connection;
    PreparedStatement stmt;
    
    public CRUD() {
        this.connection = new DatabaseConnection();
    }
    
    public boolean create(Messages message){
        
        try{
            this.stmt = this.connection.getConnection().prepareStatement("INSERT INTO message "
                    + "(name, ip, message, date) VALUES(?, ?, ?, ?)");

            this.stmt.setString(1, message.getName());
            this.stmt.setString(2, message.getIp());
            this.stmt.setString(3, message.getMessage());
            this.stmt.setString(4, message.getDate());
            
            stmt.executeUpdate();
            
            return true;
            
        }catch(SQLException ex) {
            System.out.println("Erro de inserção no banco de dados: " + ex);
            return false;
        }
    }
}
