/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Server extends Thread {

    int port = 2000;
    static int newPort = 2000;
    ArrayList<Connection> clients = new ArrayList<Connection>();

    public Server(int port) {
        this.port = port;
        this.newPort++;
    }

    public static void main(String... args) {
        Server server = new Server(2000);
        server.start();
    }

    public void run() {
        
        try {
            ServerSocket server = new ServerSocket(port);
            
            //Servidor abre a porta 2000
            System.out.println("Servidor principal iniciado na porta: " + this.port);

            while (true) {
                
                //Espera conexão do cliente
                Socket client = server.accept();
                String ip = client.getInetAddress().getHostAddress();
                System.out.println("Cliente " + ip + " se conectou ao servidor principal.");
                
                //Objeto responsável pelo envio de mensagens para a saída da conexão. 
                PrintStream output = new PrintStream(client.getOutputStream());
                
                //Objeto responsável por receber mensagens do cliente 
                Scanner input = new Scanner(client.getInputStream());
                String nickname = input.nextLine();
                    
                System.out.println("Porta: " + Server.newPort + " delegada ao usuario: " + nickname);
                
                //Armazena dados do cliente que se conectou 
                Connection user = new Connection(ip, newPort, nickname);
                
                
                //Abre a porta liberada para que o cliente possa se conectar.
                //Responsável por receber as mensagens do cliente.
                ReceiverServer receiverServer = new ReceiverServer(this.clients, Server.newPort, user, nickname);
                receiverServer.start();
                
                //Enviar porta liberada ao cliente após já ter aberto ela no servidor. 
                output.println(Server.newPort);
                
                //Fecha a conexão com o cliente e com as entradas e saídas.
                client.close();
             
                //Incrementa 2 para que o próximo também se conecte a uma porta impar
                Server.newPort += 2;
                
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
