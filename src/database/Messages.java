/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author cg3000109
 */
public class Messages {
    
    private String message; 
    private String date; 
    private String ip; 
    private String emitter;
    private String receiver;

    public String getEmitter() {
        return emitter;
    }

    public void setEmitter(String emissor) {
        this.emitter = emissor;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receptor) {
        this.receiver = receptor;
    }
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
