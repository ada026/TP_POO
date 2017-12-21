package SourcePackage;

import GraphiquePackage.FichAccueil;
import GraphiquePackage.FichMenu;
import java.util.Scanner;

public class Main {
    static public User user;
    private static FichAccueil fichAccueil;
    private static FichMenu fichMenu;
    
    public static void main(String[] args){
    fichAccueil = new FichAccueil();
    fichAccueil.setVisible(true);

    }
    
    public static void launchUser(String pseudo){
        user = new User(pseudo);
        user.startThread();
    }
    
    
    
    public static void setFichMenu(FichMenu fichMenu1){
        fichMenu = fichMenu1;
    }
    
    public static void openFich(){
        fichMenu.openWindow();
    }  
    
    public User getUser(){
        return user;
    }
}