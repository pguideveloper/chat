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
    ArrayList<Connection> clients;
    Connection client;
    ServerSocket server;
    String nickname; 
    
    public ReceiverServer(ArrayList<Connection> clients, int port, Connection client, String nickname) throws IOException {

        this.server = new ServerSocket(port);
        this.port = port;
        this.clients = clients;
        this.client = client;
        this.nickname = nickname;
    }

    public void run() {

        try {

            System.out.println("Servidor de recebimento aberto na porta: " + this.port + " e aguardando conexões");
            Socket cli = this.server.accept();

            System.out.println("Cliente " + cli.getInetAddress().getHostAddress() + " conectado ao servidor de recebimento pela porta: " + this.port);

            String ip = cli.getInetAddress().getHostAddress();

            //Cria um cliente emissor por parte do servidor.
            EmitterClient emitterServer = new EmitterClient(ip, this.port + 1);

            //Define um cliente emissor
            this.client.setEmitter(emitterServer);

            //Insere no array de clientes. 
            this.clients.add(this.client);

            Scanner input = new Scanner(cli.getInputStream());

            while (input.hasNextLine()) {
                new Message(this.client, this.clients, this.nickname).sendToAll(input.nextLine());
            }

            input.close();

        } catch (IOException ex) {
            Logger.getLogger(ReceiverServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
