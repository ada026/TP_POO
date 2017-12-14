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

    public static void sendMessage(InetAddress address, int port, User user) throws IOException {

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String data = user.getPseudo();
        DatagramPacket packet = new DatagramPacket(data.getBytes(),
                data.getBytes().length, address, port);
        System.out.println("J'ai bien envoyé mon paquet à " + address+ "sur le port : " + port + "\n temps : "+ System.currentTimeMillis());

        socket_envoi.send(packet);


    }

}
