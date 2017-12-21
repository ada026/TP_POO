package SourcePackage;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

public class ThreadMenu extends Thread {
        
        public ThreadMenu(String name){
            super(name);
        }

        public void run(){
            
        System.out.println("MENU : \n 1 : Voir liste des utilisateurs présents \n 2 : Initialiser communication (TCP) \n 9 : Quitter");
        int choix = 0;
        while(choix != 9) {
                
                Scanner sc = new Scanner(System.in);
                choix = sc.nextInt();
                
               switch(choix){
                   case 1: System.out.println(Main.user.getListUser().toString());
                                              choix = 9 ;

                       break;
                   case 2: System.out.println("A qui veux tu parler ? entre son pseudo ");
                           String pseudo = sc.next();
                           System.out.println("pseudo entré: " + pseudo);
                           String[] info = Main.user.getListUser().get(pseudo).toString().split("-");
                           
                           Main.user.startThreadTCP(info[0], parseInt(info[1],10));
                           choix = 9 ;
                       break;
                   
                
                   
               }
        }
        }
}