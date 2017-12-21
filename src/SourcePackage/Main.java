package SourcePackage;

import java.util.Scanner;

public class Main {
    static public User user;
    
    public static void main(String[] args){
        System.out.println("Bonjour : Entrez votre pseudo : ");
        Scanner sc = new Scanner(System.in);
        String pseudo = sc.nextLine();
        user = new User(pseudo);
        user.startThread();
    }
    
    public User getUser(){
        return user;
    }
}