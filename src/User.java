import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;

public class User {
    private String pseudo;
    private static DatagramSocket socketEnvoi;
    private static DatagramSocket socketEcoute;
    private ArrayList<String> listUser;
    private static boolean firstMsg = false;


    public User(String pseudo){
        listUser = new ArrayList<>();
        try {
            this.socketEnvoi = new DatagramSocket();

            this.socketEcoute = new DatagramSocket(45047);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        this.pseudo = pseudo;

        System.out.println("J'ai crée un utilisateur ; son pseudo est : " + pseudo );
        Thread threadSend = new ThreadSend("thread send");
        threadSend.start();

        Thread threadReceive = new ThreadReceive("thread receive");
        threadReceive.start();

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




}