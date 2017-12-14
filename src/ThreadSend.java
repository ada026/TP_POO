import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class ThreadSend extends Thread{
        
    private User user ;
    public ThreadSend(String name, User user){
        super(name);
        this.user = user;
    }

    public void run(){
        try {
            for(int i=0;i<10;i++) {
                sendMessageBroadcast(user);
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

    public static void sendMessageBroadcast(User user) throws IOException {

        String data = user.getPseudo() ;
        User.getSocketEnvoi().setBroadcast(true);
        InetAddress address = InetAddress.getByName("255.255.255.255"); //mettre l'adresse de broadcast directement
        DatagramPacket packet = new DatagramPacket(data.getBytes(),
                data.getBytes().length, address, 45047);
        //DatagramPacket packet2 = new DatagramPacket(data.getBytes(),
        // data.getBytes().length, address, 45048);

        System.out.println("J'ai envoyé mon paquet en broadcast");

        User.getSocketEnvoi().send(packet);
    }

}
