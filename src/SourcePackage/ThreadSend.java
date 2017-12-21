package SourcePackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSend extends Thread{
        
    public ThreadSend(String name){
        super(name);
    }

    public void run(){
        
        try {
            sendMessageBroadcast();
        } catch (IOException ex) {
            Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendMessageBroadcast() throws IOException {
        System.out.println(Main.user);
        String data = Main.user.getPseudo();
        User.getSocketEnvoi().setBroadcast(true);
        InetAddress address = InetAddress.getByName("255.255.255.255"); //mettre l'adresse de broadcast directement
        DatagramPacket packet = new DatagramPacket(data.getBytes(),
        data.getBytes().length, address, 45047);
        User.getSocketEnvoi().send(packet);
    }

}