import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadReceiveTCPFinal extends Thread {

	private Socket socket;
	
	public ThreadReceiveTCPFinal(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
        InputStreamReader stream;
		try {
			stream = new InputStreamReader(socket.getInputStream());
	        BufferedReader reader = new BufferedReader(stream);
	        System.out.println("Je vais lire1");
	        while(socket.isConnected()) {
                    	        System.out.println("Je lis ");

	        		String a = reader.readLine();
	        		if(a != null)
	        			System.out.println(a);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
}
