import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadAcceptTCP extends Thread{
	
	private ThreadReceiveTCPFinal threadReceiveTCP = null;
	private ThreadSendTCPFinal threadSendTCP = null;
	
	
	public ThreadAcceptTCP(String name) {
		super(name);
	}
	
	public void run() {
		Socket client2Socket = null;
	    ServerSocket client1Socket;
		try {
			client1Socket = new ServerSocket(Main.user.getPort());
		    System.out.println("port ou jessaye d'ecouter : " + client1Socket.getLocalPort());
		    System.out.println(" " +  client1Socket.isClosed());
		    if(!client1Socket.isClosed()) {
		
		        client2Socket = client1Socket.accept();
		        threadReceiveTCP = new ThreadReceiveTCPFinal(client2Socket);
		        threadReceiveTCP.start();
		        threadSendTCP = new ThreadSendTCPFinal("Send",client2Socket.getInetAddress().toString(),client2Socket.getPort(),client2Socket,false);
		        threadSendTCP.start();
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}