import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static java.lang.Thread.sleep;

public class SendMessage {

    public static void sendMessage(InetAddress address, int port, User user) throws IOException {
        
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //String data = user.getPseudo();
        String data = user.getPseudo();
        DatagramPacket packet = new DatagramPacket(data.getBytes(),
                data.getBytes().length, address, 45047);
        System.out.println("J'ai renvoyé mon paquet apres l'ecoute sur le port : " + 45047);
        
        
        User.getSocketEcoute().send(packet);


    }

}
