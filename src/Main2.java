import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main2 {
	
	public static void main(String[] args){
		int i = 0;
	while(true){
        try {
            Socket client2Socket = null;
            ServerSocket client1Socket = new ServerSocket(45001);
            System.out.println("port ou jessaye d'ecouter : " + client1Socket.getLocalPort());
            System.out.println(" " +  client1Socket.isClosed());
            Main21 threadSendTCP = null;
            if(!client1Socket.isClosed()) {

                client2Socket = client1Socket.accept();
                threadSendTCP = new Main21(client2Socket);
                threadSendTCP.start();
            }
            
            System.out.println("Connexion effective");   
            PrintWriter writer = new PrintWriter(client2Socket.getOutputStream());
            i++;
            Scanner sc = new Scanner(System.in);
            
            while(!client2Socket.isClosed()) {
	            String msg = sc.next();
	            
	            writer.println(msg);
	            writer.flush();
	            if(msg.equals("quit")) {
	                writer.close();
            			client2Socket.close();
            			threadSendTCP.stop();
	            }
	            
            }
			client1Socket.close();
            System.out.println("closed");
            
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadReceiveTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
}
}
