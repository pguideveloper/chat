/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.util.ArrayList;
import java.util.List;

public class Connections {
   
    List<Connection> connections = new ArrayList<Connection>();
    
   public void addConnection(Connection connection) {
       this.connections.add(connection);
   } 
}
