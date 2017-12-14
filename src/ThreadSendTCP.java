
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
            socket = new Socket(ip,port);
            System.out.println("port ou jessaye d'envoyer "+ port );
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            String message = reader.readLine();
            System.out.println("le client m'a rep : " + message );
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadSendTCP.class.getName()).log(Level.SEVERE, null, ex);
        } 
     } 
     
     

}
