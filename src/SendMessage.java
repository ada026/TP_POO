import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static java.lang.Thread.sleep;

public class SendMessage {

    private static DatagramSocket socket_envoi;
    private static DatagramSocket socket_ecoute;

    public static DatagramSocket getSocketEcoute() {
        return socket_ecoute;
    }

    public static DatagramSocket getSocketEnvoi(){
        return socket_envoi;
    }

    public static void main(String[] args){
        System.out.println("Le nom du thread principal est " + Thread.currentThread().getName());

        try {
            socket_ecoute = new DatagramSocket(45047);
            socket_envoi = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadReceive t = new ThreadReceive("A");
        ThreadSend t2 = new ThreadSend("  B");
        t.start();
        t2.start();

    }


    public static void sendMessage(InetAddress address, int port ) throws IOException {

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String data = "Easy" ;
        DatagramPacket packet = new DatagramPacket(data.getBytes(),
                data.getBytes().length, address, port);
        System.out.println("J'ai bien envoyé mon paquet à " + address+ "sur le port : " + port + "\n temps : "+ System.currentTimeMillis());

        socket_envoi.send(packet);


    }

}
