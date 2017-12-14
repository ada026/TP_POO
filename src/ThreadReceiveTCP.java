
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadReceiveTCP extends Thread {
    
    ServerSocket client1Socket = null;
    
      public ThreadReceiveTCP(String name){
        super(name);
        
      }
      
      public void run(){
           Scanner sc = new Scanner(System.in);
        
          try {
              System.out.println("Lancement de l'ecoute tcp");
              client1Socket = new ServerSocket(Main.user.getPort());
          } catch (IOException ex) {
              Logger.getLogger(ThreadReceiveTCP.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          while(true){
            try {
                System.out.println("port ou jessaye d'ecouter : " + client1Socket.getLocalPort());
                Socket client2Socket = client1Socket.accept();
                System.out.println("Connexion effective");
                PrintWriter writer = new PrintWriter(client2Socket.getOutputStream());
                while (client1Socket.isBound()){
                    //System.out.println("message a envoyer : ");
                    //writer.println(sc.next());
                 //    writer.println("coucou");
                }
               writer.println("coucou");
                writer.close(); 
            } catch (IOException ex) {
                Logger.getLogger(ThreadReceiveTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
              
          }
      }  
}
