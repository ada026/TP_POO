package GraphiquePackage;

import SourcePackage.Main;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadPrintListUser extends Thread {

    public ThreadPrintListUser(String name){
        super(name);

    }
    
    @Override
    public void run(){
    
        HashMap<String, String> listUser2 = Main.getUserList();

       // System.out.println(listUser.toString());
        
        while(true) {
            try {
                sleep(1000);
                        FichMenu.setListUser(Main.user.getListUser().toString());
                        System.out.println(Main.user.getListUser().toString());
    
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadPrintListUser.class.getName()).log(Level.SEVERE, null, ex);
            }

      }
    }
    
}
