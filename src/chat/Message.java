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
    Connection client;
    String message;
    String receiver;

    public Message(Connection client, ArrayList<Connection> clients) {
        this.clients = clients;
        this.client = client;
    }

    public Message(Connection client) {
        this.client = client;
    }

    public void sendToAll(String message) throws IOException {

        
        message = this.removeWhiteSpace(message);
         
        if (message.substring(0, 1).equals("@")) {
            int i = this.messageStart(message);
            this.receiver = message.substring(1, i);
            this.message = message.substring(i + 1);

            //Procura usuário e se encontrar, envia mensagem privada 
            for (int j = 0; j < this.clients.size(); j++) {
                if (this.clients.get(j).getName().equals(this.receiver)) {
                    this.privateMessage(this.clients.get(j), new Cripto().cifrar("(" + this.client.getName() + ")[Privada]: " + this.message));
                }
            }

        } else {
            this.receiver = null;
            this.message = message;
           
            for (int i = 0; i < this.clients.size(); i++) {
                this.clients.get(i).getEmitter().serverSendMessage(new Cripto().cifrar("(" + this.client.getName() + "): " + this.message));
            }
        }

    }

    public void privateMessage(Connection usuario, String message) {
        usuario.getEmitter().serverSendMessage(message);
    }

    public String removeWhiteSpace(String message) {
        int i = 0;

        while (i < message.length() && Character.isWhitespace(message.charAt(i))) {
            i++;
        }

        return message.substring(i);
    }

    public int messageStart(String message) {
        int i = 0;

        while (i < message.length() && !Character.isWhitespace(message.charAt(i))) {
            i++;
        }

        return i;
    }
}
