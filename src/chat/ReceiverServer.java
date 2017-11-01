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
    Connections connections = new Connections();
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
                
                String ip = cli.getInetAddress().getHostAddress();
                this.connections.addConnection(new Connection(ip, this.port, cli));
                
                
                Scanner input = new Scanner(cli.getInputStream());
                while(input.hasNextLine()) {
                    this.connections.sendToAll(input.nextLine());
                    System.out.println(input.nextLine());
                }
                
            }
            }catch (IOException ex) {
            Logger.getLogger(ReceiverServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    }
