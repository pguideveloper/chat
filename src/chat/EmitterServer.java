/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class EmitterServer extends Thread{
    
    int port;
    String ip;
    Socket client;

    public EmitterServer(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
    }
    
    @Override
    public void run() {

        try {
            System.out.println("CHEGOU AQUI EM?");
            this.client = new Socket(this.ip, this.port);
            System.out.println("Servidor de envio se conectou ao cliente de recebimento através da porta: " + this.port);

            Scanner keyBoard = new Scanner(System.in);
            
            PrintStream output = new PrintStream(this.client.getOutputStream());
            
            while(keyBoard.hasNextLine()) {
                output.println(keyBoard.nextLine());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(EmitterClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
