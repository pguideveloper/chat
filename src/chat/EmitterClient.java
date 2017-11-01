/*
 * Classe responsável por gerenciar os clientes emissores, que são os clients que enviam 
 * mensagens ao servidor, o qual decide o que fazer com as mensagens. 
 */
package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

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
        this.client = new Socket(this.ip, 2000);
        System.out.println("Cliente de envio se conectou ao servidor de recebimento através da porta: " + this.port);
        
//        this.sendMessage(System.in.toString());
//        
//        Scanner input = new Scanner(this.client.getInputStream());
//        
//        while(input.hasNextLine()) {
//            System.out.println(input.nextLine());
//        }
  
    }
    
    public void sendMessage(String message) throws IOException {
        PrintStream output = new PrintStream(this.client.getOutputStream());
        output.println(message);
    }
}
