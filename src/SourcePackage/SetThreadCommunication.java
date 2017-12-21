package SourcePackage;

import java.net.Socket;

public class SetThreadCommunication {
    private ThreadReceiveTCPFinal threadReceiveTCP;
    private ThreadSendTCPFinal threadSendTCP;
    
    public SetThreadCommunication(Socket client2Socket) {
       threadReceiveTCP = new ThreadReceiveTCPFinal(client2Socket);
       threadSendTCP = new ThreadSendTCPFinal("Send",client2Socket.getInetAddress().toString(),client2Socket.getPort(),client2Socket,false);
       
    }

}
