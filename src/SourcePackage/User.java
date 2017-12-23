package SourcePackage;

import GraphiquePackage.FichCom;
import static SourcePackage.Main.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private String pseudo;
    private static DatagramSocket socketEnvoi;
    private static DatagramSocket socketEcoute;
    private static boolean firstMsg = false;
    private HashMap<Integer,Socket> listSocket;
    private ArrayList<FichCom> listFichCom;
    private PrintWriter writer = null;
    private HashMap<String, String> listUser ;  //jai recu sur mon port d'udp mais je veux fermer celui de tcp  ? comment recup ce port ??????

    public User(String pseudo){
        listFichCom = new ArrayList<>();
        listUser = new HashMap<>();
        listSocket = new HashMap();

        try {
            this.socketEnvoi = new DatagramSocket();
            this.socketEcoute = new DatagramSocket(45047);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        this.pseudo = pseudo;
        
        System.out.println("J'ai crée un utilisateur ; son pseudo est : " + pseudo );
    }
    
    public void startThread(){
        sendMessageUDP(null,0);
        
        Thread threadReceive = new ThreadReceive("thread receive");
        threadReceive.start();
        
        ThreadAcceptTCP threadReceiveTCP = new ThreadAcceptTCP("receive tcp");
        threadReceiveTCP.start();
        
    }
    
    public void sendMessageUDP(String message,int port){
        String data = "" ;
        
        if(message != null){
            
        
        
        if(message.contains("quita") ){
            data = message+"-"+pseudo;
            
        }
        else if(message.contains("quito")){
            data = message+"-"+port;
            listSocket.remove(port);
        }
        }
        
        else {
            data = Main.user.getPseudo();
        }
        
        System.out.println(Main.user);
        try {
        User.getSocketEnvoi().setBroadcast(true);
        InetAddress address = InetAddress.getByName("255.255.255.255"); //mettre l'adresse de broadcast directement
        DatagramPacket packet = new DatagramPacket(data.getBytes(),
        data.getBytes().length, address, 45047);
        
            User.getSocketEnvoi().send(packet);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void sendMessageTCP(String msg,String ip, int port ,boolean lancementOuPas ){

        try {
        /* listSocket.put(port, new Socket(ip,port));
        Socket socket = listSocket.get(port); */
            System.out.println("port : " + port );
        Socket socket = listSocket.get(port);
        
              if(lancementOuPas){
                  socket = new Socket(ip, port);
                  listSocket.put(port, socket);
                       System.out.println(" je vais envoyer pr me co>>>>>>>>>");
            		socketReceiveIniationThread(socket);
            }

        writer = new PrintWriter(socket.getOutputStream());

            /*while(socket.isConnected()){
                
                if (msg.equals("quit")){
                    socket.close();
                    writer.close();
                    System.out.println("il est parti");
                    return ;
                }
            }*/
            
            
                writer.print(msg  + "\n");
                writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     private void socketReceiveIniationThread(Socket socket) throws UnknownHostException, IOException {
            System.out.println("Avant de debuter mon thread de receive mon socket est : " + socket.isConnected());
		ThreadReceiveTCPFinal threadReceive = new ThreadReceiveTCPFinal(socket);
		threadReceive.start();
    }
    

    public User getUser(){
        return this;
    } 
    
    public String getPseudo(){
        return this.pseudo;
    }

    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }
    @Override
    public String toString() {
        return this.pseudo;
    }
    
    public static DatagramSocket getSocketEnvoi(){
        return socketEnvoi;
    }
    
    public static DatagramSocket getSocketEcoute(){
        return socketEcoute;
    }
    
    public HashMap getListUser(){
        return this.listUser;
    }
    
    public void setListUser(String pseudo, String ipPort){
        System.out.println("JE PUT AVEC PSEUDO : " + pseudo + " ET IP-PORT  : " + ipPort);
        this.listUser.put(pseudo, ipPort);
    }
    
    public void removeUserList(String pseudo1){
        System.out.println("J'ai remove !!! ");
        this.listUser.remove(pseudo1);
    }
    
    public void removeSocketList(int port){
        System.out.println("J'ai fermé la co !!! ");
        try {
            System.out.println("PORT QUE JE VAIS FERMER " + port);
            listSocket.get(port).close();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listSocket.remove(port);
    }
    
    public boolean belongList(String pseudo){
        if (listUser.containsKey(pseudo)){
            return true;
        }
        return false;
    }
    
    public int getPort(){
        return socketEnvoi.getLocalPort();
    }
    
    public void addFichCom(FichCom fichCom){
        this.listFichCom.add(fichCom);
    }
    
    public FichCom getFichCom(int i){
        return listFichCom.get(i);
    }
    
    public void putListSocket(Socket socket){
        listSocket.put(socket.getPort(), socket);
    }
    
    
}