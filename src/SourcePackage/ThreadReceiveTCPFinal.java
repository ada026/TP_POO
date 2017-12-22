package SourcePackage;

import GraphiquePackage.FichCom;
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
                           System.out.println("debut thread threadreceivetcpfinal");

        InputStreamReader stream;
		try {
                                System.out.println("Dans mon thread de receive je suis :  " + socket.isConnected());

                                           System.out.println(" jattends de lire");

			stream = new InputStreamReader(socket.getInputStream());
	        BufferedReader reader = new BufferedReader(stream);
	        System.out.println("Je vais lire1");
	        while(socket.isConnected()) {
	        		String a = reader.readLine();
	        		if(a != null){
                                    if(a.equals("quita")){
                                        socket.close();
                                    }
                                    Main.user.getFichCom(0).setListMsg(a);
                                }
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
}
