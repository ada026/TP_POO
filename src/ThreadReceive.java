import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class ThreadReceive extends Thread {

        public ThreadReceive(String name){
            super(name);
        }

        public void run(){
            try {
                receiveMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void receiveMessage() throws IOException {

        while(true){

            System.out.println("Jattends de recevoir un message <<< bloqué" + "temps : " + System.currentTimeMillis());

            byte[] recvBuf = new byte[1024];
            DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);

            try {
                SendMessage.getSocketEcoute().receive(recvPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Jai recu le message >>> non bloqué ");

            String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
            InetAddress addr = recvPacket.getAddress();
            int port = recvPacket.getPort();
            System.out.println("nouveau client : " + recvStr + "\n voici son adresse IP : " + addr + " et voici son port : " + port);

        }

    }


}
