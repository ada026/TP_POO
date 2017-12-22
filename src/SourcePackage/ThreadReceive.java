package SourcePackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;

public class ThreadReceive extends Thread {
    
	private User user;
	private static String nama;
	
        public ThreadReceive(String name){
            super(name);
        }
        
        public ThreadReceive(String name, User usr){
        		super(name);
        		this.user = usr;
        		this.nama = name;
        }

        public void run(){
            try {
            		if(this.getName().equals("quiti"))
            			receiveMessage2(user);
                        else {
                            receiveMessage();
                        }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public static void receiveMessage2(User user) throws IOException {   //UDP : boucle qui recoit les messages udp broadcast pour voir qui est en ligne
            
            System.out.println("THREAD COURANT ::::::     "+Thread.currentThread().toString());
            
            
            while(Main.user.getPseudo().equals("quiti")){
               // System.out.println("Jattends de recevoir un message <<< bloqué" + "temps : " + System.currentTimeMillis());

                byte[] recvBuf = new byte[1024];
                DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
                try {
                		System.out.println("wsh tu vas recevoir");
                    user.getSocketEcoute().receive(recvPacket);
                    System.out.println("t'as reçu");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // System.out.println("Jai recu le message >>> non bloqué ");

                String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());

	    	            InetAddress addr = recvPacket.getAddress();
	    	            int port = recvPacket.getPort();
	    	            System.out.println("je vais ajouter sur le port   :  :  : : : "  + port);
                            
	    	            user.setListUser(recvStr,addr.toString().substring(1)+"-"+port);
            }
        }

    public static void receiveMessage() throws IOException {   //UDP : boucle qui recoit les messages udp broadcast pour voir qui est en ligne
                    System.out.println("THREAD COURANT ::::::     "+Thread.currentThread().toString());

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
                            System.out.println("port >>>>>>>> " + port);
		            sendMessage(addr, port);
            		if(recvStr.contains("quiti")){}
                        else {
                            System.out.println("PPPPOOORRRTTT "+ port);
                    ajoutUserListe(recvStr,addr.toString().substring(1)+"-"+port);
                        }
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