
package SourcePackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSendTCPFinal extends Thread {
    
    private Socket socket;
    private boolean lancementOuPas;
    private String ip;
    private int port;
    private Scanner sc = new Scanner(System.in);
    PrintWriter writer = null;
    ThreadReceiveTCPFinal threadReceive;
    
    public ThreadSendTCPFinal(String name,String ip, int port, Socket socket1,boolean lancementOuPas){
        super(name);
        socket = socket1;
        this.lancementOuPas = lancementOuPas;
        this.ip = ip;
        this.port = port;
    }
    
    @Override
    public void run(){
        try {
            if(lancementOuPas) 
            		socketReceiveIniationThread();
            
            System.out.println("Possibilité d'envoyer des msgs ");

            writer = new PrintWriter(socket.getOutputStream());

            while(socket.isConnected()){
            	
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
            Logger.getLogger(ThreadSendTCPFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

     }
    
    private void socketReceiveIniationThread() throws UnknownHostException, IOException {
		socket = new Socket(ip,port);
		threadReceive = new ThreadReceiveTCPFinal(socket);
		threadReceive.start();
    }
    
}
