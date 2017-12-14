import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String pseudo;
    private static DatagramSocket socketEnvoi;
    private static DatagramSocket socketEcoute;
    private static boolean firstMsg = false;
    
    private HashMap<String, String> listUser ;


    public User(String pseudo){
        listUser = new HashMap<>();
        
        try {
            this.socketEnvoi = new DatagramSocket();

            this.socketEcoute = new DatagramSocket(45047);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        this.pseudo = pseudo;
        
        System.out.println("J'ai crée un utilisateur ; son pseudo est : " + pseudo );
        Thread threadSend = new ThreadSend("thread send", this);
        threadSend.start();
        
        Thread threadReceive = new ThreadReceive("thread receive", this);
        threadReceive.start();
        
        ThreadMenu threadMenu = new ThreadMenu("thread menu", this);
        threadMenu.start();
        
        
        
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
    
    public void setListUser(String pseudo, String ip){
        this.listUser.put(pseudo, ip);
    }
    
    public boolean belongList(String pseudo){
        if (listUser.containsKey(pseudo)){
            return true;
        }
        return false;
    }
}