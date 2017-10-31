/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

public class Connections {
    
    String ip; 
    String nickname; 
    int sendPort;
    
    public Connections(String ip, String nickname, int sendPort) {
       this.ip = ip;
       this.nickname = nickname;
       this.sendPort = sendPort;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSendPort() {
        return sendPort;
    }

    public void setSendPort(int sendPort) {
        this.sendPort = sendPort;
    }
    
    
    
}
