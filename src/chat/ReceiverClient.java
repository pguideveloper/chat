/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ReceiverClient extends Thread {

    int port;
    ServerSocket server;
    Connections connections = new Connections();
    ChatInterface chatinterface;
    
    public ReceiverClient(ChatInterface chatinterface, int port) throws IOException {
        this.port = port;
        this.server = new ServerSocket(this.port);
        this.chatinterface = chatinterface;
        System.out.println("Cliente receptor aberto na porta: " + this.port);
    }

    public void run() {

        try {
            Socket client = this.server.accept();
            System.out.println("Cliente de recebimento recebeu nova conexão de " + client.getInetAddress().getHostAddress());
            

            Scanner input = new Scanner(client.getInputStream());
            
            //Espera uma mensagem e envia para o clientReceptor 
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
                
                this.chatinterface.sendMessage(input.nextLine());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ReceiverClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
