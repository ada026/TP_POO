import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadISendTCP {
    static public User user;
    
    public static void main(String[] args){
        
         
        Socket socket;
        try {
            socket = new Socket("192.168.2.5",45001);
            System.out.println("port ou jessaye d'envoyer "+ 45001 );
            
             if(socket.isConnected()){   //Envoie des msgs
            Main21 thread = new Main21(socket);
            thread.start();
            System.out.println("démarrage du thread d'envoie des msgs");
            }
             
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
             
            //System.out.println("je vais lui rep ");
           // System.out.println("je lui ai rep ");
           // testMsg(socket);
            
           
            
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
