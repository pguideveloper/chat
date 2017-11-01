/*
 * Essa classe é responsável por cuidar da parte do servidor que recebe mensagens.
 */
package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiverServer extends Thread {

    int port;
    Connections connection;
    ArrayList<EmitterClient> client;
    ArrayList<Socket> clients;
    ServerSocket server;
//    ArrayList<EmitterClient> clients; 

    public ReceiverServer(ArrayList<EmitterClient> client, int port, Connections connection) throws IOException {

        this.port = port;
        this.connection = connection;
        this.client = client;
        this.server = new ServerSocket(this.port);
    }

    public void run() {

        try {
            
            
            while (true) {
                System.out.println("Servidor de recebimento aberto na porta: " + this.port);
                Socket cli = this.server.accept();
                
                System.out.println("Cliente: " + cli.getInetAddress().getHostAddress() + " conectado ao servidor de recebimento pela porta: " + this.port);
//              this.clients.add(cli);

//            Scanner input = new Scanner(cli.getInputStream());;
//            while(true) {
//                while(input.hasNextLine()) {
//                    
//                    String message = "";
//                    message += this.connection.getNickname()+ ": " + input.nextLine();
//                    
//                    System.out.println(message);
//                }
//            }
            }
            }catch (IOException ex) {
            Logger.getLogger(ReceiverServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    }
