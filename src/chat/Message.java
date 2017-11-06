/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.util.ArrayList;

public class Message {

    ArrayList<Connection> clients = new ArrayList<Connection>();

    public Message(ArrayList<Connection> client) {
        this.clients = client;
    }

    public void sendToAll(String message) throws IOException {
        
        for (int i = 0; i < this.clients.size(); i++) {
            this.clients.get(i).getEmitter().sendMessage(this.clients.get(i).getName() + " : " + message);
        }
        
    }

}
