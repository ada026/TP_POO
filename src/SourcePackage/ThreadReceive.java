package SourcePackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;

public class ThreadReceive extends Thread {
    
        public ThreadReceive(String name){
            super(name);
        }

        public void run(){
            try {
                receiveMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void receiveMessage() throws IOException {   //UDP : boucle qui recoit les messages udp broadcast pour voir qui est en ligne
        while(true){
           // System.out.println("Jattends de recevoir un message <<< bloqué" + "temps : " + System.currentTimeMillis());

            byte[] recvBuf = new byte[1024];
            DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);

            try {
                User.getSocketEcoute().receive(recvPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // System.out.println("Jai recu le message >>> non bloqué ");

            String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
            
            //System.out.println("nouveau client : " + recvStr + "\n voici son adresse IP : " + addr + " et voici son port : " + port);
          //  System.out.println("pseudo recu : "  + recvStr);

            if(recvStr.contains("quita")){
                String[] info = recvStr.split("-");
                  Main.user.removeUserList(info[1]);//mettre a jour sa liste : l'enlever
            }
            
            else if(recvStr.contains("quito")){
                System.out.print("etat du socket : "+ User.getSocketEcoute());
                Main.user.removeSocketList(recvPacket.getPort());
            }
            
            else {
            if( !Main.user.belongList(recvStr)){
                InetAddress addr = recvPacket.getAddress();
            int port = recvPacket.getPort();
            sendMessage(addr, port);
            ajoutUserListe(recvStr,addr.toString().substring(1)+"-"+port);
            }
            }
          
        }
    }
    
    public static void sendMessage (InetAddress address, int port) throws IOException {    // Pour faire la réponse
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //String data = user.getPseudo();
        String data = Main.user.getPseudo();
        DatagramPacket packet = new DatagramPacket(data.getBytes(),
                data.getBytes().length, address, 45047);
        //System.out.println("J'ai renvoyé mon paquet apres l'ecoute sur le port : " + 45047);
        User.getSocketEcoute().send(packet);
    }
    
    public synchronized static void ajoutUserListe(String pseudo, String ip){
            Main.user.setListUser(pseudo,ip);
    }


}