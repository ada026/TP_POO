package SourcePackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadAcceptTCP extends Thread{
	
        private HashMap<Socket, SetThreadCommunication> listThreadToSocket;
        
        private ServerSocket client1Socket ;

        
	public ThreadAcceptTCP(String name) {
            super(name);
            listThreadToSocket = new HashMap<>();
            try {
                client1Socket = new ServerSocket(Main.user.getPort());
            } catch (IOException ex) {
                Logger.getLogger(ThreadAcceptTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
	public void run() {
		Socket client2Socket = null;
		try {
		    System.out.println("port ou jessaye d'ecouter : " + client1Socket.getLocalPort());
                    while(true){
		    if(!client1Socket.isClosed()) {
                        System.out.println("Une connexion en attente ... ");
		        client2Socket = client1Socket.accept();
                        System.out.println("connexion effectuée ");
                        Main.openFich(client2Socket.getPort());
                        System.out.println("port : " + client2Socket.getPort());
                        
                        Main.user.putListSocket(client2Socket);   // dans l'hash map des socket on va rajouter un socket du client qui se connecte
                        listThreadToSocket.put(client2Socket, new SetThreadCommunication(client2Socket));   //liste des threads
                    }
                    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}