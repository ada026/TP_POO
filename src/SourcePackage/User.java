package SourcePackage;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private String pseudo;
    private static DatagramSocket socketEnvoi;
    private static DatagramSocket socketEcoute;
    private static boolean firstMsg = false;
    private HashMap<Integer,Socket> listSocket;

    
    private HashMap<String, String> listUser ;

    public User(String pseudo){
        listUser = new HashMap<>();
        listSocket = new HashMap();

        try {
            this.socketEnvoi = new DatagramSocket();
            this.socketEcoute = new DatagramSocket(45047);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        this.pseudo = pseudo;
        
        System.out.println("J'ai crée un utilisateur ; son pseudo est : " + pseudo );
    }
    
    public void startThread(){
        Thread threadSend = new ThreadSend("thread send");
        threadSend.start();
        
        Thread threadReceive = new ThreadReceive("thread receive");
        threadReceive.start();
        
        ThreadMenu threadMenu = new ThreadMenu("thread menu");
        threadMenu.start();
        
        ThreadAcceptTCP threadReceiveTCP = new ThreadAcceptTCP("receive tcp");
        threadReceiveTCP.start();
        
    }
    
    public void startThreadTCP(String ip , int port){
       
        
        try {
            listSocket.put(port, new Socket(ip,port));
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ThreadSendTCPFinal threadSendTCP = new ThreadSendTCPFinal("name",listSocket.get(port),true);
        threadSendTCP.start();
        
    }

    public User getUser(){
        return this;
    } 
    
    public String getPseudo(){
        return this.pseudo;
    }

    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }
    @Override
    public String toString() {
        return this.pseudo;
    }
    
    public static DatagramSocket getSocketEnvoi(){
        return socketEnvoi;
    }
    
    public static DatagramSocket getSocketEcoute(){
        return socketEcoute;
    }
    
    public HashMap getListUser(){
        return this.listUser;
    }
    
    public void setListUser(String pseudo, String ipPort){
        this.listUser.put(pseudo, ipPort);
    }
    
    public boolean belongList(String pseudo){
        if (listUser.containsKey(pseudo)){
            return true;
        }
        return false;
    }
    
    public int getPort(){
        return socketEnvoi.getLocalPort();
    }
    
}