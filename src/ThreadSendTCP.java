
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSendTCP extends Thread {
    
    private String ip;
    private int port;
    
     public ThreadSendTCP(String name, String ip1, int port1){
        super(name);
        ip = ip1;
        port = port1;
     }
     
     public void run() {
         
       Socket socket;
        try {
            socket = new Socket("192.168.2.5",45001);
            System.out.println("port ou jessaye d'envoyer "+ 45001 );
            
             if(socket.isConnected()){   //Envoie des msgs
            ThreadTesssst thread = new ThreadTesssst("ee", socket);
            thread.start();
            System.out.println("démarrage du thread d'envoie des msgs");
            }
             
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            
            while(socket.isConnected()){  // Boucle de réception des msgs
                String a = reader.readLine();
               if (a != null ){
                System.out.println("le client m'a envoyé : " + a ); 
               }
            }   
        } catch (IOException ex) {
            Logger.getLogger(ThreadSendTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     

}