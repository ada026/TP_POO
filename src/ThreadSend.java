import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class ThreadSend extends Thread{

    public ThreadSend(String name){
        super(name);
    }

    public void run(){
        try {
            for(int i=0;i<10;i++) {
                sendMessageBroadcast();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageBroadcast() throws IOException {

        String data = "Easy" ;
        SendMessage.getSocketEnvoi().setBroadcast(true);
        InetAddress address = InetAddress.getByName("255.255.255.255"); //mettre l'adresse de broadcast directement
        DatagramPacket packet = new DatagramPacket(data.getBytes(),
                data.getBytes().length, address, 45047);
        //DatagramPacket packet2 = new DatagramPacket(data.getBytes(),
        // data.getBytes().length, address, 45048);

        System.out.println("J'ai envoyé mon paquet en broadcast");

        SendMessage.getSocketEnvoi().send(packet);
        //socket_envoi.send(packet2);
    }

}
