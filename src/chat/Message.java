/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import database.CRUD;
import database.Messages;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Message {

    ArrayList<Connection> clients = new ArrayList<Connection>();
    Connection client;
    String message;
    String receiver;
    String emitter;
    CRUD crud;

    public Message(Connection client, ArrayList<Connection> clients, String emitter) {
        this.clients = clients;
        this.client = client;
        this.crud = new CRUD();
        this.emitter = emitter;
    }

    public Message(Connection client) {
        this.client = client;
    }

    public void sendToAll(String message) throws IOException {
        Messages messages = new Messages();

        message = this.removeWhiteSpace(message);

        /**
         * Verifica se a mensagem é privada, caso seja, separa a string da mensagem para saber qual é o destinatário. 
         */
        if (message.substring(0, 1).equals("@")) {
            int i = this.messageStart(message);
            this.receiver = message.substring(1, i);
            this.message = message.substring(i + 1);

            //Procura usuário e se encontrar, envia mensagem privada 
            for (int j = 0; j < this.clients.size(); j++) {
                if (this.clients.get(j).getName().equals(this.receiver) || this.clients.get(j).getName().equals(this.emitter)) {

                    if (this.clients.get(j).getName().equals(this.emitter)) {
                        this.privateMessage(this.clients.get(j), new Cripto().cifrar("Eu (Privada): " + this.message));
                    } else {
                        this.privateMessage(this.clients.get(j), new Cripto().cifrar(this.client.getName() + "(Privada): " + this.message));
                    }
                }
            }

            //Salva no banco de dados
            messages.setEmitter(this.client.getName());
            messages.setReceiver(this.receiver);
            messages.setIp(this.client.getIp());
            messages.setMessage(new Cripto().cifrar(this.message));
            messages.setDate(this.getCurrentDate());
            this.crud.create(messages);

        } else {
            this.message = message;

            for (int i = 0; i < this.clients.size(); i++) {
                if (this.clients.get(i).getName().equals(this.emitter)) {
                    this.clients.get(i).getEmitter().serverSendMessage(new Cripto().cifrar("Eu: " + this.message));
                } else {
                    this.clients.get(i).getEmitter().serverSendMessage(new Cripto().cifrar(this.client.getName() + ": " + this.message));
                }
            }

            //Salva no banco de dados
            messages.setEmitter(this.client.getName());
            messages.setReceiver("Todos");
            messages.setIp(this.client.getIp());
            messages.setMessage(new Cripto().cifrar(message));
            messages.setDate(this.getCurrentDate());
            this.crud.create(messages);
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

    public String getCurrentDate() {

        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis());

        //Seta valores da data
        String date = currentDate.substring(0, 10);

        //Seta valores do horário
        String hour = currentDate.substring(11, 19);
        
        return date + " " + hour;

    }
}
