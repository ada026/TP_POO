
import java.util.Scanner;

public class ThreadMenu extends Thread {

    
        private static User user;
        
        public ThreadMenu(String name, User user1){
            super(name);
         user = user1;
        }

        public void run(){
              int choix = 0;
        System.out.println("MENU : \n 1 : Voir liste des utilisateurs présents \n 2 : Initialiser communication (TCP) \n 9 : Quitter");
            
        
        while(choix != 9) {
           
                Scanner sc = new Scanner(System.in);
                choix = sc.nextInt();
                
               switch(choix){
                   case 1: user.getListUser().toString();
                           System.out.println(">>>>>>");
                       break;
                 //  case 2: ..
                       //break;
                   case 9 : return ;
               }
        }
        }
}