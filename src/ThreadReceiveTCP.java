
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadReceiveTCP extends Thread {
    
      public ThreadReceiveTCP(String name){
        super(name);
        ServerSocket client1Socket = null;
        
          try {
              System.out.println("Lancement de l'ecoute tcp");
              client1Socket = new ServerSocket(Main.user.getPort());
          } catch (IOException ex) {
              Logger.getLogger(ThreadReceiveTCP.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          while(true){
            try {
                Socket client2Socket = client1Socket.accept();
                System.out.println("Connexion effective");   
                PrintWriter writer = new PrintWriter(client2Socket.getOutputStream());
                writer.println("Coucou ");
                writer.close(); 
            } catch (IOException ex) {
                Logger.getLogger(ThreadReceiveTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
              
          }
          
    }
    
   
    
     
      
      
}
