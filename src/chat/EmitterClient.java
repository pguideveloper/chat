/*
 * Classe responsável por gerenciar os clientes emissores, que são os clients que enviam 
 * mensagens ao servidor, o qual decide o que fazer com as mensagens. 
 */
package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class EmitterClient {

    int port;
    String ip;
    Socket client;
    PrintStream output;
    String nickname;

    public EmitterClient() {
    }

    public EmitterClient(String ip, int port) throws IOException {

        this.ip = ip;
        this.port = port;
        System.out.println("Cliente está pronto para se conectar na porta : " + this.port);

        this.client = new Socket(this.ip, this.port);
        System.out.println("O " + this.ip + " se conectou como emissor na porta: " + this.port);
        this.output = new PrintStream(this.client.getOutputStream());
    }

    public void connection(String ip, int port, String nick) throws IOException {
        this.ip = ip;
        this.port = port;
        this.nickname = nick;

        System.out.println("Cliente está pronto para se conectar na porta : " + this.port);

        this.client = new Socket(this.ip, this.port);
        System.out.println("O " + this.ip + " se conectou como emissor na porta: " + this.port);
        this.output = new PrintStream(this.client.getOutputStream());
    }

    public final void sendMessage(String message) throws IOException {
        String m;
        m = message;
        this.output.println(m);

    }
    
    public final void serverSendMessage(String message) {
        this.output.println(message);
    }

}
