/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public class Connection {
  
    String ip; 
    int port; 
    Socket client; 
    
    
    public Connection(String ip, int port, Socket client) {
        this.ip = ip;
        this.port = port;
        this.client = client;  
    }
    
    public String getIp() {
        return this.ip; 
    }
    
    public int getPort() {
        return this.port; 
    }
    
    public Socket getClient() {
        return this.client;
    }
    
    public void sendMessage(String message) throws IOException {
        PrintStream output = new PrintStream(client.getOutputStream());
        output.println(message);
    }
}
