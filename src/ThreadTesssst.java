
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadTesssst extends Thread {
    
    private Socket socket;
    
    public ThreadTesssst(String name, Socket socket1){
        super();
        socket = socket1;
    }
    
    @Override
    public void run(){
        
        try {
            PrintWriter writer = null;
            Scanner sc = new Scanner(System.in);
            System.out.println("Possibilité d'envoyer des msgs ");
            while(socket.isConnected()){
                try {
                    writer = new PrintWriter(socket.getOutputStream());
                } catch (IOException ex) {
                    Logger.getLogger(ThreadTesssst.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String msg = sc.next();
                
                if (msg.equals("quit")){
                    this.stop();
                    socket.close();
                    writer.close();
                    System.out.println("il est parti");
                    return ;
                }
                
                
                writer.println(msg);
                writer.flush();
                
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadTesssst.class.getName()).log(Level.SEVERE, null, ex);
        }

     }
}
