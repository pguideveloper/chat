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

    public EmitterClient(String ip, int port) throws IOException {

        this.ip = ip;
        this.port = port;
        System.out.println("Cliente está pronto para se conectar na porta : " + this.port);
        this.client = new Socket(this.ip, this.port);
        System.out.println("O " + this.ip + " se conectou como emissor na porta: " + this.port);
        
//        PrintStream output = new PrintStream(this.client.getOutputStream());
//        Scanner input = new Scanner(System.in);
//        
//        while(input.hasNextLine()) {
//            output.println(input.nextLine());
//        }
        
    }
    
    public final void sendMessage(String message) throws IOException {
        PrintStream output = new PrintStream(this.client.getOutputStream());
        output.print(message);
    }
}
