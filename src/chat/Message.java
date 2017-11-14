/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import database.CRUD;
import database.Messages;
import java.io.IOException;
import java.util.ArrayList;

public class Message {

    ArrayList<Connection> clients = new ArrayList<Connection>();
    Connection client;
    String message;
    String receiver;
    CRUD crud;

    public Message(Connection client, ArrayList<Connection> clients) {
        this.clients = clients;
        this.client = client;
        this.crud = new CRUD();
    }

    public Message(Connection client) {
        this.client = client;
    }

    public void sendToAll(String message) throws IOException {
        Messages messages = new Messages();

        message = this.removeWhiteSpace(message);

        if (message.substring(0, 1).equals("@")) {
            int i = this.messageStart(message);
            this.receiver = message.substring(1, i);
            this.message = message.substring(i + 1);

            //Procura usuário e se encontrar, envia mensagem privada 
            for (int j = 0; j < this.clients.size(); j++) {
                if (this.clients.get(j).getName().equals(this.receiver)) {
                    this.privateMessage(this.clients.get(j), new Cripto().cifrar("(" + this.client.getName() + ")[Privada]: " + this.message));
                    messages.setName(this.clients.get(j).getName());
                    messages.setIp(this.clients.get(j).getIp());
                    messages.setMessage(message);
                    messages.setDate("2017-10-22");
                    this.crud.create(messages);
                }
            }

        } else {
            this.receiver = null;
            this.message = message;

            for (int i = 0; i < this.clients.size(); i++) {
                this.clients.get(i).getEmitter().serverSendMessage(new Cripto().cifrar("(" + this.client.getName() + "): " + this.message));
                messages.setName(this.clients.get(i).getName());
                messages.setIp(this.clients.get(i).getIp());
                messages.setMessage(message);
                messages.setDate("2017-10-22");
                this.crud.create(messages);
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
