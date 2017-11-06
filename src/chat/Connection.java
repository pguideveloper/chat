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
    int sender; 
    int receiver; 
    String name; 
    EmitterClient emitter;
    
  
    public Connection(String ip, int port, String name) {
        this.ip = ip;
        this.sender = port;
        this.receiver = port + 1; 
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public String getName() {
        return name;
    }
    
    public void setEmitter(EmitterClient emitter) {
        this.emitter = emitter;
    }
    
    public EmitterClient getEmitter() {
        return this.emitter;
    }
}
