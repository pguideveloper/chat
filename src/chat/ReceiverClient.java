/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ReceiverClient extends Thread {
    
    int port;
    
    public ReceiverClient(int port) {
        this.port = port;
    }
    
    public void run() {
     
        try {
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Cliente de recebimento aceitou conexão na porta: " + this.port);
            Socket client = server.accept();
            System.out.println("Cliente de recebimento recebeu nova conexão de " + client.getInetAddress().getHostAddress());
            
        } catch (IOException ex) {
            Logger.getLogger(ReceiverClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
