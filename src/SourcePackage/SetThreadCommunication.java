package SourcePackage;

import java.net.Socket;

public class SetThreadCommunication {
    private ThreadReceiveTCPFinal threadReceiveTCP;
    
    public SetThreadCommunication(Socket client2Socket) {
       threadReceiveTCP = new ThreadReceiveTCPFinal(client2Socket);
       threadReceiveTCP.start();
       
    }
}
