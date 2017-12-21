package SourcePackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadAcceptTCP extends Thread{
	
	private ThreadReceiveTCPFinal threadReceiveTCP = null;
	private ThreadSendTCPFinal threadSendTCP = null;
        private HashMap<Integer, Socket> listSocket;
        private HashMap<Socket, SetThreadCommunication> listThreadToSocket;
        
        private ServerSocket client1Socket ;

        
	public ThreadAcceptTCP(String name) {
            super(name);
            listSocket = new HashMap<>();          
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
		        client2Socket = client1Socket.accept();
                        listSocket.put(client2Socket.getPort(), client2Socket);   // dans l'hash map des socket on va rajouter un socket du client qui se connecte
                        listThreadToSocket.put(client2Socket, new SetThreadCommunication(client2Socket));   //liste des threads
                    }
                    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        
	
}