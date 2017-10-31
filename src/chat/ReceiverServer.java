/*
 * Essa classe é responsável por cuidar da parte do servidor que recebe mensagens.
 */
package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiverServer extends Thread{
    int port; 
    Connections connection;
    ArrayList<Connections> connections;
//    ArrayList<EmitterClient> clients; 
    
    
    public ReceiverServer(ArrayList<Connections> connections,  int port, Connections connection) {
    
        this.port = port;
        this.connection = connection;
        this.connections = connections;
//        this.clients = emitterClients;
        
    }
    
    public void run() { 
        
        try {
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Servidor de recebimento aberto na porta: " + this.port);
            Socket cli = server.accept();
            
            Scanner input = new Scanner(cli.getInputStream());
            while(true) {
                while(input.hasNextLine()) {
                    
                    String message = "";
                    message += this.connection.getNickname()+ ": " + input.nextLine();
               
                    
                    System.out.println(message);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ReceiverServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
