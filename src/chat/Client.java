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


public class Client extends Thread{    
    int port = 2000; 
    String ip; 
    String nickname;
    ChatInterface chatinterface;
    EmitterClient client;
    
    
    public Client(ChatInterface chatinterface, EmitterClient client, String ip, String nickname) {
        this.ip = ip;
        this.nickname = nickname;
        this.chatinterface = chatinterface;
        this.client = client;
    }
    
    @Override
    public void run() {
        try {
            Socket client = new Socket(this.ip, this.port);
            System.out.println("Cliente: " + this.nickname + " conectou-se ao servidor principal");
            
     
            
            PrintStream output = new PrintStream(client.getOutputStream());
            Scanner input = new Scanner(client.getInputStream());
            
            //Envia para o servidor o nickname do cliente que acabou de se conectar
            output.println(this.nickname);
            
            //Recebe a porta liberada pelo servidor 
            int newPort = (Integer.parseInt(input.nextLine()));
            System.out.println("A porta delegada para o cliente foi: " + newPort);
            
            //Fecha conexão com servidor principal para deixá-lo livre 
            client.close();
            
            //Cria um servidor para receber do lado do cliente
            new ReceiverClient(this.chatinterface, newPort + 1).start();
            
            //Cliente responsável por enviar mensagens ao servidor 
            this.client.connection(ip, newPort);
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
